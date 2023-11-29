package com.example.yatrackapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrackerDao {

    @Insert
    void insert(Tracker tracker);

    @Query("SELECT * FROM Tracker")
    List<Tracker> getAllTrackers();

    @Query("SELECT T.id, T.mac, E.name, E.job, T.lastLati, T.lastLongi " +
            "FROM Tracker T, Employee E " +
            "WHERE T.employee_id = E.id")
    List<TrackerEmployee> getAllTrackersWithEmployee();
}
