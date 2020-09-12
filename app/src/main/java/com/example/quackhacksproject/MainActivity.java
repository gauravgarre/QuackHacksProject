package com.example.quackhacksproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView classRecyclerView;
    String s1[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classRecyclerView = findViewById(R.id.classRecyclerView);


        MyAdapter myAdapter = new MyAdapter(this, s1);

    }
    public void rayTest() {

    }

    public void tester() {

    }
}