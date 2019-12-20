package com.example.diagrama;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    EditText et;
    diagrama diag;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        diag =findViewById(R.id.diag);
        tv = findViewById(R.id.t1);
    }
    public void setText(String str){
        tv.setText(str);
    }
    public void onClick(View view){
        String temp = et.getText().toString();
        diag.siemens(temp);
    }


}

