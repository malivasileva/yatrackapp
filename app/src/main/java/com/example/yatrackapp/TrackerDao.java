package com.example.yatrackapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrackerDao {

    @Insert
    void insert(Tracker tracker);

    @Query("SELECT * FROM Tracker")
    List<Tracker> getAllTrackers();

    @Delete
    void delete(Tracker tracker);
    @Query("SELECT T.id, T.mac, E.id AS employee_id, E.name, E.job, T.lastLati, T.lastLongi " +
            "FROM Tracker T, Employee E " +
            "WHERE T.employee_id = E.id")
    List<TrackerEmployee> getAllTrackersWithEmployee();

    @Update
    void update(Tracker tracker);
}
