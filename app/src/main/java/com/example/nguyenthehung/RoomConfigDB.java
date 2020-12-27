package com.example.nguyenthehung;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1, exportSchema = true)
public abstract class RoomConfigDB extends RoomDatabase {
    private static RoomConfigDB database;
    private static String DATABASE_NAME = "NguyenTheHung";

    public synchronized static RoomConfigDB getInstance(Context context){
        //Check condition
        if(database ==null){
            //when database is null
            //Initialize database
            database = Room.databaseBuilder( context.getApplicationContext()
                    , RoomConfigDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //Return database
        return database;
    }
    //Create Dao
    public abstract PersonDAO personDAO();
}
