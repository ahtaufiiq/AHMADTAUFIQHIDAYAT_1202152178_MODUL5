package com.example.ataufiq.ahmad_taufiq_hidayat_1202152178_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDoActivity extends AppCompatActivity {

    EditText ToDo, Description, Priority;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        //Find View by ID
        ToDo = (EditText) findViewById(R.id.et_todo);
        Description = (EditText) findViewById(R.id.et_description);
        Priority = (EditText) findViewById(R.id.et_priority);
        //Inisiasi Object Database
        database = new Database(this);
    }

    @Override
    public void onBackPressed() {
        //intent from AddToDO to Main Activity
        Intent intent = new Intent(AddToDoActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void add(View view) {
        //If Form is not Empty
        if (ToDo.getText().toString().isEmpty() ||
                Description.getText().toString().isEmpty() ||
                Priority.getText().toString().isEmpty()){
            //If Empty
            Toast.makeText(this, "Please Fill The Form", Toast.LENGTH_SHORT).show();
        }else {
            database.createData(new ListItem(ToDo.getText().toString(),
                    Description.getText().toString(),
                    Priority.getText().toString()));

            Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
            //after add data intent to Main Activity
            startActivity(new Intent(AddToDoActivity.this, MainActivity.class));
            this.finish();
        }
    }

}
