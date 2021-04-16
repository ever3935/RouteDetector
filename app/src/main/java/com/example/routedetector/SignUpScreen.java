package com.example.routedetector;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpScreen extends AppCompatActivity {
    EditText etSignUpEmail;
    EditText etSignUpPassword;
    EditText etSignUpName;
    Button createAccountButton;
    TextView loginTextSignUpScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        etSignUpEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundResource(R.drawable.focused_border);
                } else {
                    v.setBackgroundResource(R.drawable.unfocused_border);
                }
            }
        });
        etSignUpName = findViewById(R.id.etSignUpName);
        etSignUpName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundResource(R.drawable.focused_border);
                } else {
                    v.setBackgroundResource(R.drawable.unfocused_border);
                }
            }
        });
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        etSignUpPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundResource(R.drawable.focused_border);
                } else {
                    v.setBackgroundResource(R.drawable.unfocused_border);
                }
            }
        });
        loginTextSignUpScreen = findViewById(R.id.loginTextSignUpScreen);
        loginTextSignUpScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
//                startActivity(intent);

            }
        });
        createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database dbClass = new Database(SignUpScreen.this);
                String name = etSignUpName.getText().toString();
                String email = etSignUpEmail.getText().toString();
                String password = etSignUpPassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpScreen.this, "Insert Data First", Toast.LENGTH_LONG).show();
                } else {
                    dbClass.insert(name, email, password);
                    Toast.makeText(SignUpScreen.this, "Data Saved", Toast.LENGTH_LONG).show();
                    etSignUpName.setText("");
                    etSignUpEmail.setText("");
                    etSignUpPassword.setText("");
                }
            }
        });
    }
}