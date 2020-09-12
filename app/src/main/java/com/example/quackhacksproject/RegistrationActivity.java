package com.example.quackhacksproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final Switch teacherStudentSwitch = findViewById(R.id.teacherStudentSwitch);
        final TextView teacherStudentText = findViewById(R.id.teacherStudentText);
        teacherStudentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void  onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    teacherStudentText.setText("Logging in as student");
                }
                else{
                    teacherStudentText.setText("Logging in as teacher");
                }
            }

        });
    }
}