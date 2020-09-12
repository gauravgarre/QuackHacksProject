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

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<TeacherClasses> classes;
   public MyAdapter(Context ct, List<TeacherClasses> classes){
       this.classes = classes;
       context = ct;

   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

       return classes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       public TextView className;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            className = itemView.findViewById(R.id.classname);

        }
    }

}
