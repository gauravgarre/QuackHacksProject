package com.example.quackhacksproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class ClassActivity extends AppCompatActivity {
    int num_students = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        final Switch goingSwitch = findViewById(R.id.switch1);
        final TextView goingView = findViewById(R.id.goingView);
        goingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    num_students++;
                    goingView.setText("Number of attendees: " + num_students);
                    //System.out.println(teacherStudentText.getText().toString());
                }
                else {
                    num_students--;
                    goingView.setText("Number of attendees: " + num_students);
                }
    }
});
    }

    public void goToClass(View view) {
        startActivity(new Intent(getApplicationContext(), ClassActivity.class));
    }
}