package com.example.admin.recyclerviewproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.recyclerviewproject.Models.ModelPerson;
import com.example.admin.recyclerviewproject.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by admin on 12/12/2017.
 */

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<ModelPerson> personArrayList;


    public void updateData(ArrayList<ModelPerson> newPersonArrayList){
        this.personArrayList = newPersonArrayList;
        notifyDataSetChanged();
    }

    public PersonsAdapter(Context mContext, ArrayList<ModelPerson> personArrayList){
        this.mContext = mContext;
        this.personArrayList = personArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_person, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.idTextView.setText(personArrayList.get(position).getId());
        holder.nameTextView.setText(personArrayList.get(position).getName());
        holder.ageTextView.setText(personArrayList.get(position).getAge() + "");
        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelPerson currentPerson = personArrayList.get(position);
                currentPerson.setName("Hello");
                EventBus.getDefault().post(currentPerson);
            }
        });

    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView idTextView, nameTextView, ageTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            idTextView = (TextView)itemView.findViewById(R.id.personId);
            nameTextView = (TextView)itemView.findViewById(R.id.personName);
            ageTextView = (TextView)itemView.findViewById(R.id.personAge);
        }
    }
}
