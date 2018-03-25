package com.example.ataufiq.ahmad_taufiq_hidayat_1202152178_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    TextView shapeclr;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        alert = new AlertDialog.Builder(this);

        //inisiasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        spe = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);
        //findview
        shapeclr = findViewById(R.id.shape_color);
        //set shape color
        shapeclr.setText(getShapeColor(colorid));
    }

    //BackButton
    @Override
    public void onBackPressed() {
        //intent to MainActivity
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //On Options Selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Call Method onBack
            this.onBackPressed();
        }
        return true;
    }

    //get String color
    public String getShapeColor(int i) {
        if (i == R.color.red) {
            return "Red";
        } else if (i == R.color.green) {
            return "Green";
        } else if (i == R.color.blue) {
            return "Blue";
        } else {
            return "Default";
        }
    }

    //get Id Color
    public int getColorid(int i) {
        if (i == R.color.red) {
            return R.id.red;
        } else if (i == R.color.green) {
            return R.id.green;
        } else if (i == R.color.blue) {
            return R.id.blue;
        } else {
            return R.id.white;
        }
    }

    public void chooseColor(View view) {
        //set title alert
        alert.setTitle("Shape Color");
        //create new view
        View view1 = getLayoutInflater().inflate(R.layout.color, null);
        alert.setView(view1);

        //Find RadioGrup
        final RadioGroup radG = view1.findViewById(R.id.radio_color);
        radG.check(getColorid(colorid));

        //Pressed Ok
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //get id radio button
                int a = radG.getCheckedRadioButtonId();
                switch (a) {
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //set shape color
                shapeclr.setText(getShapeColor(colorid));
                spe.putInt("Colourground", colorid);
                spe.commit();
            }
        });

        //Pressed Cancel
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //Create Alert
        alert.create().show();
    }
}
