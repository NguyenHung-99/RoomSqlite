package com.example.nguyenthehung;

import android.icu.text.Replaceable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PersonDAO {
    //Thêm person
    @Insert(onConflict = REPLACE)
    void insertPerson(Person person);
    //Xóa
    @Delete
    void delete(Person person);

    //Delete all query
    @Delete
    void reset(List<Person> personList);

    //Updata query
    @Query("UPDATE persons SET `person-name` = :name , `person-tuoi` = :tuoi WHERE id = :id")
    void update(int id, String name, int tuoi);

    //Get all data query
    @Query("SELECT * FROM persons")
    List<Person> getAll();

}
