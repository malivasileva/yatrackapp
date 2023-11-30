package com.example.yatrackapp;

import android.app.Application;

import androidx.room.Room;

import com.yandex.mapkit.MapKitFactory;

import java.util.List;
public class TrackApplication extends Application {
    private String MAPKIT_API_KEY = "1e150f61-775c-493a-b731-c6662e0e67a6";
    AppDatabase database;
    public static TrackApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();

        /*List<Tracker> trackerList = database.trackerDao().getAllTrackers();
        List<Employee> employeeList = database.employeeDao().getAllEmployees();
        System.out.println("pumpkin");
        employeeList.stream()
                .map(Employee::getId)
                .forEach(System.out::println);*/

    }

    public AppDatabase getDatabase() {
        return database;
    }

    public static TrackApplication getInstance() {
        return instance;
    }

    private void initDB() {

        Employee newEmployee = new Employee();
        newEmployee.setName("Семёнов Александр");
        newEmployee.setJob("Инженер");
        database.employeeDao().insert(newEmployee);

// Создание нового трекера
/*        Tracker newTracker = new Tracker();
        newTracker.setMac("00:11:22:33:44:77");
        newTracker.setEmployeeId(4);
        newTracker.setLastLati(52.263120);
        newTracker.setLastLongi(104.263362);

// Вставка трекера в базу данных
        database.trackerDao().insert(newTracker);*/

    }
}
