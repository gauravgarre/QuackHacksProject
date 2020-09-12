package com.example.quackhacksproject;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quackhacksproject.MyAdapter;
import com.example.quackhacksproject.R;
import com.example.quackhacksproject.TeacherClasses;
import com.example.quackhacksproject.classCreator;
import com.example.quackhacksproject.user.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;


public class MainActivity extends AppCompatActivity {

    RecyclerView classRecyclerView;
    MyAdapter adapter;
    String uid;
    User user;
    Button classButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classRecyclerView = findViewById(R.id.classRecyclerView);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String firstName = String.valueOf(dataSnapshot.child("firstName").getValue());
                String email = String.valueOf(dataSnapshot.child("email").getValue());
                String lastName = String.valueOf(dataSnapshot.child("lastName").getValue());
                String position = String.valueOf(dataSnapshot.child("position").getValue());

                user = new User(email, firstName, lastName, position);
                classButton = findViewById(R.id.classButton);
                String text = user.position.equals("student") ? "Add Class" : "Create Class";
                classButton.setText(text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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