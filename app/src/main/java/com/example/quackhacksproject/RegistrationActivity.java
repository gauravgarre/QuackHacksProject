package com.example.quackhacksproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quackhacksproject.R;
import com.example.quackhacksproject.user.Student;
import com.example.quackhacksproject.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.quackhacksproject.user.Teacher;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.local.PersistedInstallation;

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
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        firstNameText = findViewById(R.id.firstName);
        lastNameText = findViewById(R.id.lastName);
        btnRegister = findViewById(R.id.registerBtn);

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        final Switch teacherStudentSwitch = findViewById(R.id.teacherStudentSwitch);
        final TextView teacherStudentText = findViewById(R.id.teacherStudentText);
        teacherStudentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    teacherStudentText.setText("teacher");

                }
                else{
                    teacherStudentText.setText("student");

                }
            }

        });
        final String position = teacherStudentText.getText().toString();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                firstName = firstNameText.getText().toString();
                lastName = lastNameText.getText().toString();
                if (email.isEmpty()) {
                    passwordText.setError("Please enter password");
                    passwordText.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    emailText.setError("Please enter email ID");
                    emailText.requestFocus();
                    return;
                }
                if (firstName.isEmpty()) {
                    lastNameText.setError("Please enter last name");
                    lastNameText.requestFocus();
                    return;
                }
                if (lastName.isEmpty()){
                    firstNameText.setError("Please enter first name");
                    firstNameText.requestFocus();
                    return;
                }
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user  = new User(email, lastName, firstName, position);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(RegistrationActivity.this, "Registration Failure", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                Toast.makeText(RegistrationActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            } else {
                                Toast.makeText(RegistrationActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                //Registering the User in FireBase
            }
        });
    }


}