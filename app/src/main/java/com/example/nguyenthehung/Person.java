package com.example.nguyenthehung;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "persons")
public class Person implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "person-name")
    private String name;
    @ColumnInfo(name = "person-tuoi")
    private int tuoi;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTuoi() {
        return tuoi;
    }
}
