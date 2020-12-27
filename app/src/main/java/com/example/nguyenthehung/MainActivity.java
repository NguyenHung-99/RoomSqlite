package com.example.nguyenthehung;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edt_name, edt_tuoi;
    Button btAdd, btReset;
    RecyclerView recyclerView;
    List<Person> personList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    MainAdapter adapter;
    RoomConfigDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name = findViewById(R.id.edt_name);
        edt_tuoi = findViewById(R.id.edt_tuoi);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.btn_reset);
        recyclerView = findViewById(R.id.recycler_view);

        database = RoomConfigDB.getInstance(this);
        //get all person in db
        personList = database.personDAO().getAll();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MainAdapter(personList,this);

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get string from edit text
                String sName = edt_name.getText().toString().trim();
               // int sTuoi = Integer.parseInt(edt_tuoi.getText().toString().trim());
                if(sName.equals("") || edt_tuoi.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Name or Tuổi incorrect", Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(edt_tuoi.getText().toString().trim()) <= 0){
                    Toast.makeText(MainActivity.this, "Tuổi Phải lớn hơn 0", Toast.LENGTH_LONG).show();
                }else if(!sName.equals("")){
                    int sTuoi = Integer.parseInt(edt_tuoi.getText().toString().trim());
                    Person data = new Person();
                    data.setName(sName);
                    data.setTuoi(sTuoi);
                    database.personDAO().insertPerson(data);
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                    edt_tuoi.setText("");
                    edt_name.setText("");
                    //Notify
                    personList.clear();
                    personList.addAll(database.personDAO().getAll());
                    adapter.notifyDataSetChanged();
                }

            }
        });
        //delete all person
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.personDAO().reset(personList);
                Toast.makeText(MainActivity.this, "Reset List User Thành công", Toast.LENGTH_LONG).show();
                //Notify
                personList.clear();
                personList.addAll(database.personDAO().getAll());
                adapter.notifyDataSetChanged();

            }
        });

    }
}