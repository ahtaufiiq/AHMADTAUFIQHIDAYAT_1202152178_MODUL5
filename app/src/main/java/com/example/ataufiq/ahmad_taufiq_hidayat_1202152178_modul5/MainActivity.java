package com.example.ataufiq.ahmad_taufiq_hidayat_1202152178_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    RecyclerView rv;
    ListAdapter adapter;
    ArrayList<ListItem> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewByID RecylerView
        rv = findViewById(R.id.recyclerview);

        //create array list
        datalist = new ArrayList<>();
        //inisiasi database
        database = new Database(this);
        //call method readData
        database.readData(datalist);

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //Create Adapter
        adapter = new ListAdapter(this, datalist, color);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //Set Adapter
        rv.setAdapter(adapter);

        //menjalankan method swipe for Delete
        swipeDelete();
    }

    //Swipe for Delete Data
    public void swipeDelete() {
        //touch helper for recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ListItem current = adapter.getData(position);
                //if swipe to left
                if (direction == ItemTouchHelper.LEFT) {
                    //remove item
                    if (database.deleteData(current.getTodo())) {
                        //delete data
                        adapter.deleteData(position);
                        //snack bar
                        Snackbar.make(findViewById(R.id.coor), "Deleted Data", 1000).show();
                    }
                }
            }
        };
        //Define itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }

    //ketika menu dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get item id
        int id = item.getItemId();
        // if action setting
        if (id == R.id.action_settings) {
            //Intent to Setting Activity
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    //Method for Add Data
    public void addToDo(View view) {
        //intent To AddTodo
        Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
        startActivity(intent);
    }
}
