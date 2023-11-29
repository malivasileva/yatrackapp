package com.example.yatrackapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MainActivity extends Activity {
    private final Point TARGET_LOCATION = new Point(52.262492, 104.260917);
    private MapView mapView;
    private DrawerLayout drawerLayout;
//    private SlidingPaneLayout slidingPaneLayout;
    private ImageButton btnSettings;
    private ImageButton btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Now MapView can be created.
        setContentView(R.layout.actitvity_main);
        super.onCreate(savedInstanceState);

        mapView = (MapView)findViewById(R.id.mapview);
        /*slidingPaneLayout = findViewById(R.id.slide_layout);*/
        drawerLayout = findViewById(R.id.drawer_layout);
        btnSettings = findViewById(R.id.btnSettings);
        btnInfo = findViewById(R.id.btnInfo);

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

        // And to show what can be done with it, we move the camera to the center of Saint Petersburg.
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 17.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
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
}
