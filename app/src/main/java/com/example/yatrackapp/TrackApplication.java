package com.example.yatrackapp;

import android.app.Application;

import androidx.room.Room;

import com.yandex.mapkit.MapKitFactory;

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
        initDB();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public static TrackApplication getInstance() {
        return instance;
    }

    private void initDB() {


// Создание нового сотрудника
        Employee newEmployee = new Employee();
        newEmployee.setName("Иванов Иван");
        newEmployee.setJob("Инженер");

// Вставка сотрудника в базу данных
        database.employeeDao().insert(newEmployee);

// Создание нового трекера
        Tracker newTracker = new Tracker();
        newTracker.setMac("00:11:22:33:44:55");
        newTracker.setEmployeeId(newEmployee.getId());
        newTracker.setLastLati(52.262129);
        newTracker.setLastLongi(104.264121);

// Вставка трекера в базу данных
        database.trackerDao().insert(newTracker);

    }
}
