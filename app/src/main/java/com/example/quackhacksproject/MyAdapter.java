package com.example.quackhacksproject;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.ContextMenu;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<TeacherClasses> teacherData;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public Button button;
        private Context context;

        public MyViewHolder(View v,Context mContext) {
            super(v);
            context = mContext;
            textView = v.findViewById(R.id.classname);
            button = v.findViewById(R.id.classBtn);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent i = new Intent(context.getApplicationContext(),ClassActivity.class);
                    i.putExtra("className",textView.getText().toString());
                    context.startActivity(new Intent(context.getApplicationContext(), ClassActivity.class));
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<TeacherClasses> teacherData,Context mContext) {
        this.teacherData = teacherData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_item, parent, false),parent.getContext());
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(teacherData.get(position).getClassName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {


        return teacherData.size();
    }

    
}