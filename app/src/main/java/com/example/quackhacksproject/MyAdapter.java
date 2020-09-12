package com.example.quackhacksproject;

import android.content.Context;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class MyAdapter extends FirebaseRecyclerAdapter<TeacherClasses,MyAdapter.MyViewHolder> {


    public MyAdapter(@NonNull FirebaseRecyclerOptions<TeacherClasses> options){
        super(options);
    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull TeacherClasses model) {
        holder.className.setText(model.getClassName());

    }

    @Override
    public int getItemCount() {

       return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       public TextView className;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            className = itemView.findViewById(R.id.classname);

        }
    }

}
