package com.example.yatrackapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void insert(Employee employee);

    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployees();

    @Delete
    void delete(Employee employee);

    @Query("DELETE FROM Employee " +
            "WHERE Employee.id = :id")
    void deleteById(int id);

    @Update
    void update(Employee employee);
}

