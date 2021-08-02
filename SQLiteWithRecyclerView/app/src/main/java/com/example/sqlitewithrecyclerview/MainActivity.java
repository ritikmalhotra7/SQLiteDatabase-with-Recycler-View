package com.example.sqlitewithrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Model> modelList;
    RecyclerViewAdapter adapter;
    ArrayList<Model> models;
    DatabaseHandler db;
    //Array list will give the data required by recycler view
    //modellist will obtain the data of database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(MainActivity.this);

        recyclerView = findViewById(R.id.Recyclerid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //List and array list difference is that ist cannot be instantiated and array list must instantiated

        modelList = db.getAllModel();
        //here we are giving List data to arrayList data
        for(Model model: modelList){
            models.add(model);
        }

        adapter = new RecyclerViewAdapter(MainActivity.this,models);
        recyclerView.setAdapter(adapter);



    }
}