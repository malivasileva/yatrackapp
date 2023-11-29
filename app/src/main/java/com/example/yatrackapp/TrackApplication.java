package com.example.yatrackapp;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class TrackApplication extends Application {
    private String MAPKIT_API_KEY = "1e150f61-775c-493a-b731-c6662e0e67a6";

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
