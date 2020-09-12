package com.example.quackhacksproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quackhacksproject.user.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.example.quackhacksproject.user.Teacher;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends AppCompatActivity {

    EditText emailText, passwordText, firstNameText, lastNameText;
    String email, password, firstName, lastName;
    Button btnRegister;
    Student student;
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        firstNameText = findViewById(R.id.firstName);
        lastNameText = findViewById(R.id.lastName);
        btnRegister = findViewById(R.id.registerBtn);

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
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                firstName = firstNameText.getText().toString();
                lastName = lastNameText.getText().toString();
                if (email.isEmpty()){
                    emailText.setError("Please enter email ID");
                    emailText.requestFocus();
                }
                else if (password.isEmpty()){
                    passwordText.setError("Please enter password");
                    passwordText.requestFocus();
                }
                else if (firstName.isEmpty()){
                    firstNameText.setError("Please enter first name");
                    firstNameText.requestFocus();
                }
                else if (lastName.isEmpty()){
                    lastNameText.setError("Please enter last name");
                    lastNameText.requestFocus();
                }
                else if (email.isEmpty() && password.isEmpty() && firstName.isEmpty() && lastName.isEmpty()){
                    Toast.makeText(RegistrationActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && password.isEmpty() && firstName.isEmpty() && lastName.isEmpty())) {
                    //Register
                }
            }
        });
    }


}