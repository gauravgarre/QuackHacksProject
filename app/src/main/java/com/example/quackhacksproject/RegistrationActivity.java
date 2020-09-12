package com.example.quackhacksproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quackhacksproject.user.Student;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


public class RegistrationActivity extends AppCompatActivity {

    EditText emailText, passwordText;
    String email, password;
    Button btnRegister;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegister = findViewById(R.id.registerBtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText = findViewById(R.id.emailText);
                passwordText = findViewById(R.id.passwordText);
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                student = new Student(email, password);
            }
        });

        final Switch teacherStudentSwitch = findViewById(R.id.teacherStudentSwitch);
        final TextView teacherStudentText = findViewById(R.id.teacherStudentText);
        teacherStudentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    teacherStudentText.setText("Registering as teacher");
                }
                else{
                    teacherStudentText.setText("Registering as student");
                }
            }

        });
    }


}