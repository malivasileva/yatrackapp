package com.example.yatrackapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tracker", foreignKeys = @ForeignKey(entity = Employee.class,
        parentColumns = "id",
        childColumns = "employee_id",
        onDelete = ForeignKey.SET_NULL))
public class Tracker {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mac;

    @ColumnInfo(name = "employee_id")
    private int employeeId;  // Внешний ключ

    private double lastLongi;
    private double lastLati;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMac() {
        return mac;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getLastLati() {
        return lastLati;
    }

    public double getLastLongi() {
        return lastLongi;
    }

    public void setLastLati(double lastLati) {
        this.lastLati = lastLati;
    }

    public void setLastLongi(double lastLongi) {
        this.lastLongi = lastLongi;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
