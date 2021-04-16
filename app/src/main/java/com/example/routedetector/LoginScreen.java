package com.example.routedetector;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                etLoginEmail = findViewById(R.id.etLoginEmail);
                String email = etLoginEmail.getText().toString();
                etLoginPassword = findViewById(R.id.etLoginPassword);
                String password = etLoginPassword.getText().toString();
                Database dbClass = new Database(LoginScreen.this);
                Cursor cursor = dbClass.getData();
                boolean emailCheck = false;
                if (!email.isEmpty() && !password.isEmpty()) {
                    if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        if (cursor.moveToPosition(0)) {
                            do {
                                String emailDB =
                                        cursor.getString(cursor.getColumnIndex("email"));
                                if (email.equals(emailDB)) {
                                    emailCheck = true;
                                    String passwordDB =
                                            cursor.getString(cursor.getColumnIndex("password"));
                                    if (password.equals(passwordDB))
                                        Toast.makeText(LoginScreen.this, emailDB + " | " + password + "\n Login Successful", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(LoginScreen.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                                }
                            } while (cursor.moveToNext());
                        }
                        if (!emailCheck) {
                            Toast.makeText(LoginScreen.this, "Email not exist", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(LoginScreen.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginScreen.this, "Fields required", Toast.LENGTH_LONG).show();
                }
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