package com.example.routedetector;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {

    Button loginButton;
    TextView signUpTextLoginScreen;
    EditText etLoginEmail;
    EditText etLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginButton = findViewById(R.id.loginButton);
        signUpTextLoginScreen = findViewById(R.id.signUpTextLoginScreen);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(LoginScreen.this, MapsActivity.class);
////                startActivity(intent);
//
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database dbClass = new Database(LoginScreen.this);
                AlertDialog.Builder alertDialog = new
                        AlertDialog.Builder(LoginScreen.this);
                alertDialog.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                alertDialog.setTitle("Data");
                String dbRecord = "";
                Cursor cursor = dbClass.getData();
                if (cursor.moveToFirst()) {
                    do {
                        String name =
                                cursor.getString(cursor.getColumnIndex("name"));
                        String email =
                                cursor.getString(cursor.getColumnIndex("email"));
                        String password =
                                cursor.getString(cursor.getColumnIndex("password"));
                        dbRecord += "Name: " + name + " Email: " +
                                email + " Password: " + password + "\n";
                    } while (cursor.moveToNext());
                }
                cursor.close();
                alertDialog.setMessage(dbRecord);
                AlertDialog alert = alertDialog.create();
                alert.show();
            }
        });


        signUpTextLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundResource(R.drawable.focused_border);
                } else {
                    v.setBackgroundResource(R.drawable.unfocused_border);
                }
            }
        });

        etLoginPassword = findViewById(R.id.etLoginPassword);
        etLoginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundResource(R.drawable.focused_border);
                } else {
                    v.setBackgroundResource(R.drawable.unfocused_border);
                }
            }
        });
    }
}