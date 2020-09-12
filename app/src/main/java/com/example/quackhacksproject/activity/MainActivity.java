package com.example.quackhacksproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quackhacksproject.MyAdapter;
import com.example.quackhacksproject.R;
import com.example.quackhacksproject.TeacherClasses;
import com.example.quackhacksproject.activity.classCreator;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    RecyclerView classRecyclerView;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classRecyclerView = findViewById(R.id.classRecyclerView);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<TeacherClasses> options =
                new FirebaseRecyclerOptions.Builder<TeacherClasses>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Classes"), TeacherClasses.class)
                        .build();
        adapter = new MyAdapter(options);
        classRecyclerView.setAdapter(adapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void createClass(View view) {
        startActivity(new Intent(getApplicationContext(), classCreator.class));
    }
}