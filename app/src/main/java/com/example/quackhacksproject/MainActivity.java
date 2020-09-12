package com.example.quackhacksproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Button;

import com.example.quackhacksproject.MyAdapter;
import com.example.quackhacksproject.R;
import com.example.quackhacksproject.TeacherClasses;
import com.example.quackhacksproject.classCreator;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView classRecyclerView;
    MyAdapter adapter;
    ArrayList<TeacherClasses> list;
    DatabaseReference reference;
    Button class1, class2, class3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        classRecyclerView = findViewById(R.id.classRecyclerView);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference("Classes");
        adapter = new MyAdapter(list);
        classRecyclerView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    TeacherClasses p = dataSnapshot1.getValue(TeacherClasses.class);
                    list.add(p);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Oops.... Something is wrong", Toast.LENGTH_SHORT).show();
            }

        });
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
    public void createClass(View view) {
        startActivity(new Intent(getApplicationContext(), classCreator.class));
    }
    public void goToClass(View view) {
        startActivity(new Intent(getApplicationContext(), ClassActivity.class));
    }
}