package com.example.dbapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dbapp.R;
import com.example.dbapp.adapters.RecyclerAdapter;
import com.example.dbapp.classes.DBHelper;
import com.example.dbapp.models.StudentModels;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<StudentModels> studentList;
    RecyclerAdapter recyclerAdapter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.viewList);

        db = new DBHelper(ViewActivity.this);

        Cursor c = db.getData();
        if (c.getCount() == 0) {
            Toast.makeText(this, "Database is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        studentList = new ArrayList<>();
        while (c.moveToNext()){
            studentList.add(new StudentModels(c.getString(0), c.getString(1), c.getString(2), c.getString(3)));
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerAdapter = new RecyclerAdapter(studentList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

}