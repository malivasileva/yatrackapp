package com.example.yatrackapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void insert(Employee employee);

    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployees();
}

