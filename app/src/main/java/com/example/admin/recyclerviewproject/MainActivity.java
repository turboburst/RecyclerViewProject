package com.example.admin.recyclerviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.recyclerviewproject.Adapters.PersonsAdapter;
import com.example.admin.recyclerviewproject.Models.ModelPerson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ModelPerson> personData1;
    private ArrayList<ModelPerson> personData2;
    private RecyclerView recyclerView1, recyclerView2;
    private RecyclerView.LayoutManager layoutManager1, layoutManager2;
    private PersonsAdapter adapter1, adapter2;
    private Button updateList1Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        updateList1Button = findViewById(R.id.button1id);
        personData1 = new ArrayList<ModelPerson>();
        personData1.add(new ModelPerson());
        for(int i = 0; i < 10; i++){
            personData1.add(new ModelPerson(String.valueOf(i), "person" + i, i + 10));
            personData1.add(new ModelPerson(String.valueOf(i%2), "person" + (i%2), i + 10));
        }
        personData2 = new ArrayList<ModelPerson>();
        personData2.add(new ModelPerson());
        for(int i = 7; i < 15; i++){
            personData2.add(new ModelPerson(String.valueOf(i), "person" + i, i + 10));
            personData2.add(new ModelPerson(String.valueOf(i%2), "person" + (i%2), i + 10));
        }

        layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView1 = findViewById(R.id.recyclerview1Id);
        recyclerView2 = findViewById(R.id.recyclerview2Id);

        adapter1 = new PersonsAdapter(this, personData1);
        adapter2 = new PersonsAdapter(this, personData2);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(adapter1);


        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(adapter2);

        updateList1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter1.updateData(personData1);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainThreadEvent(ModelPerson person){
        for(ModelPerson eachPerson: personData1){
            if(eachPerson.getId().equals(person.getId()) && eachPerson != person){
                eachPerson.setName("Hello");
            }
        }
        for(ModelPerson eachPerson: personData2){
            if(eachPerson.getId().equals(person.getId()) && eachPerson != person){
                eachPerson.setName("Hello");
            }
        }
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
