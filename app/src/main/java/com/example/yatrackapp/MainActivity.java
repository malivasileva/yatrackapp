package com.example.yatrackapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.TextStyle;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    private final Point TARGET_LOCATION = new Point(52.262492, 104.260917);
    AppDatabase db;
    private MapView mapView;
    private MapObjectCollection mapObjects;
    private DrawerLayout drawerLayout;
    TrackerEmployeeAdapter adapter;
    ListView listView;
//    List<TrackerEmployee> dataList;
//    private SlidingPaneLayout slidingPaneLayout;
    private ImageButton btnSettings;
    private ImageButton btnInfo;
    private Button btnCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Now MapView can be created.
        setContentView(R.layout.actitvity_main);
        super.onCreate(savedInstanceState);

        db = TrackApplication.getInstance().getDatabase();

        mapView = (MapView)findViewById(R.id.mapview);
        mapObjects = mapView.getMap().getMapObjects().addCollection();
        /*slidingPaneLayout = findViewById(R.id.slide_layout);*/
        drawerLayout = findViewById(R.id.drawer_layout);
        btnSettings = findViewById(R.id.btnSettings);
        btnInfo = findViewById(R.id.btnInfo);
        btnCon = findViewById(R.id.btn_connected);
        listView = findViewById(R.id.listView);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLeftSlideMenu();
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleRightSlideMenu();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TrackerEmployee trackerEmployee = (TrackerEmployee) parent.getItemAtPosition(position);
                Point empPos = new Point (trackerEmployee.getLastLati(), trackerEmployee.getLastLongi());
                mapView.getMap().move(
                        new CameraPosition(empPos,17.0f, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 1),
                        null);
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        // And to show what can be done with it, we move the camera to the center of Saint Petersburg.
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 17.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);

        new DatabaseTask(this, db).execute();

    }

    private void toggleLeftSlideMenu() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    private void toggleRightSlideMenu() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    @Override
    protected void onStop() {
        // Activity onStop call must be passed to both MapView and MapKit instance.
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        // Activity onStart call must be passed to both MapView and MapKit instance.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    public void handleDatabaseResult(List<TrackerEmployee> trackerEmployeeList) {
        adapter = new TrackerEmployeeAdapter(this, trackerEmployeeList);
        listView.setAdapter(adapter);
        for (TrackerEmployee tracker : trackerEmployeeList) {
            Point point = new Point (tracker.getLastLati(), tracker.getLastLongi());
            PlacemarkMapObject mark = mapObjects.addPlacemark(point);
            mark.setIcon(ImageProvider.fromResource(this, R.drawable.ic_marker));
            mark.setText(tracker.getName() + "\n" + tracker.getJob());
            mark.setTextStyle(new TextStyle().setPlacement(TextStyle.Placement.TOP));
        }
        String connected = Integer.toString(trackerEmployeeList.size());
        btnCon.setText(connected);
    }

    public class DatabaseTask extends AsyncTask<Void, Void, List<TrackerEmployee>> {

        private WeakReference<MainActivity> activityReference;
        private AppDatabase database;

        DatabaseTask(MainActivity activity, AppDatabase database) {
            activityReference = new WeakReference<>(activity);
            this.database = database;
        }

        @Override
        protected List<TrackerEmployee> doInBackground(Void... voids) {
            if (database != null) {
                return database.trackerDao().getAllTrackersWithEmployee();
            } else {
                return Collections.emptyList();
            }
        }

        @Override
        protected void onPostExecute(List<TrackerEmployee> trackerEmployeeList) {
            MainActivity activity = activityReference.get();
            if (activity != null) {
                activity.handleDatabaseResult(trackerEmployeeList);
            }
        }
    }

}
