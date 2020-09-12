package com.example.quackhacksproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class classCreator extends AppCompatActivity {
    EditText className;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_creator);

    }

    public void storeInFirebase(View view) {
        TextView className = findViewById(R.id.newClassName);
        TeacherClasses classSet = new TeacherClasses(className.getText().toString());
        FirebaseDatabase.getInstance().getReference("Classes").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(classSet).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(classCreator.this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(classCreator.this, "RFailure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}