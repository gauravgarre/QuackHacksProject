package com.example.quackhacksproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classRecyclerView = findViewById(R.id.classRecyclerView);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Profiles");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<TeacherClasses>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    TeacherClasses p = dataSnapshot1.getValue(TeacherClasses.class);
                    list.add(p);
                }

                FirebaseRecyclerOptions<TeacherClasses> options =
                        new FirebaseRecyclerOptions.Builder<TeacherClasses>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Classes"), TeacherClasses.class)
                                .build();
                adapter = new MyAdapter(options);
                classRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }

        });
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