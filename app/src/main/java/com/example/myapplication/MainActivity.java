package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.models.NewApiResponse;
import com.example.myapplication.models.NewsHeadlines;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnNewsClicked, View.OnClickListener{
RecyclerView recyclerView;
CustomAdapter adapter;
ProgressDialog dialog;
Button b1,b2,b3,b4,b5,b6,b7;
SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Searching ...");
                dialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNewsHeadline(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        b3=findViewById(R.id.btn3);
        b4=findViewById(R.id.btn4);
        b5=findViewById(R.id.btn5);
        b6=findViewById(R.id.btn6);
        b7=findViewById(R.id.btn7);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadline(listener,"general",null);

    }

  private final OnFetchDataListener listener=new OnFetchDataListener() {
      @Override
      public void onFetchData(ArrayList<NewsHeadlines> mList, String message) {
          showData(mList);
          dialog.dismiss();
      }

      @Override
      public void onError(String message) {
          Toast.makeText(MainActivity.this, "Not find", Toast.LENGTH_SHORT).show();
      }
  };

    private void showData(ArrayList<NewsHeadlines> list) {
        adapter=new CustomAdapter(this,list,this);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void onClick(NewsHeadlines news) {
      startActivity(new Intent(this,NewsDetails.class).putExtra("news",news));
    }

    @Override
    public void onClick(View view) {
        Button button= (Button) view;
        String category=button.getText().toString();
        dialog.setTitle("Fetching Data for "+category);
        dialog.show();
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadline(listener,category,null);

    }


}