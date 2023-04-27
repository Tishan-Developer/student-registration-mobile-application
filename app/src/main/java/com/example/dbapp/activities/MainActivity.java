package com.example.dbapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbapp.R;
import com.example.dbapp.classes.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText layout_reg_no, layout_fName, layout_bDay, layout_email;
    Button l_btn_insert, l_btn_update, l_btn_delete, l_btn_view;
    String reg_no, fName, bDay, email;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_reg_no = findViewById(R.id.et_st_reg_no);
        layout_fName = findViewById(R.id.et_full_name);
        layout_bDay = findViewById(R.id.et_birthday);
        layout_email = findViewById(R.id.et_email);

        l_btn_insert = findViewById(R.id.btn_insert);
        l_btn_update = findViewById(R.id.btn_update);
        l_btn_delete = findViewById(R.id.btn_delete);
        l_btn_view = findViewById(R.id.btn_view);

        db = new DBHelper(MainActivity.this);

        l_btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation(1)) {
                    if (db.insertData(reg_no,fName,bDay,email)) {
                        resetFields();
                        Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        l_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation(2)) {
                    if (db.updateDate(reg_no,fName,bDay,email)) {
                        resetFields();
                        Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        l_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation(0)) {
                    if (db.deleteData(reg_no)){
                        resetFields();
                        Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        l_btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
            }
        });

    }

    public boolean validation(int type){
        if (layout_reg_no.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Registration Number to Continue", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (type == 1){
            if (layout_fName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Full Name to continue", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (layout_bDay.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Birthday to continue", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (layout_email.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Email to continue", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (type == 2) {
            boolean x = false;
            if (!layout_fName.getText().toString().isEmpty()) {
                x = true;
            }
            if (!layout_bDay.getText().toString().isEmpty()) {
                x = true;
            }
            if (!layout_email.getText().toString().isEmpty()) {
                x = true;
            }
            if (!x) {
                Toast.makeText(this, "To continue fill at least one field", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        reg_no = layout_reg_no.getText().toString().isEmpty() ? null : layout_reg_no.getText().toString();
        fName = layout_fName.getText().toString().isEmpty() ? null : layout_fName.getText().toString();
        bDay = layout_bDay.getText().toString().isEmpty() ? null : layout_bDay.getText().toString();
        email = layout_email.getText().toString().isEmpty() ? null : layout_email.getText().toString();
        return true;
    }

    public void resetFields(){
        layout_reg_no.setText("");
        layout_fName.setText("");
        layout_bDay.setText("");
        layout_email.setText("");
    }

}