package com.example.routedetector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button bt1;
    EditText etName;
    Button loginButton;
    TextView signUpTextLoginScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(intent);
        bt1 = findViewById(R.id.maps);
        signUpTextLoginScreen = findViewById(R.id.signUpTextLoginScreen);



//        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//                    startActivity(intent);
//                    v.setBackgroundResource( R.drawable.focused_border);
//                }
//                else{
//                    v.setBackgroundResource( R.drawable.unfocused_border);
//                }
//            }
//        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}