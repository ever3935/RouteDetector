package com.example.routedetector;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class SignUpScreen extends AppCompatActivity {
    EditText etSignUpEmail;
    EditText etSignUpPassword;
    EditText etSignUpConfirmPassword;
    EditText etSignUpName;
    Button createAccountButton;
    TextView loginTextSignUpScreen;
    CheckBox checkBoxEmail;
    CheckBox checkBoxPassword;
    CheckBox checkBoxConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        checkBoxEmail = findViewById(R.id.checkboxEmail);
        checkBoxPassword = findViewById(R.id.checkboxPassword);
        checkBoxConfirmPassword = findViewById(R.id.checkboxConfirmPassword);

        checkBoxEmail.setChecked(true);
        checkBoxPassword.setChecked(true);
        checkBoxConfirmPassword.setChecked(true);

        checkBoxEmail.setVisibility(View.INVISIBLE);
        checkBoxPassword.setVisibility(View.INVISIBLE);
        checkBoxConfirmPassword.setVisibility(View.INVISIBLE);

        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        etSignUpEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etSignUpEmail.getText().toString().equals("bari")) {
                    checkBoxEmail.setVisibility(View.VISIBLE);
                }
                else {
                    checkBoxEmail.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
        etSignUpConfirmPassword = findViewById(R.id.etSignUpConfirmPassword);
        etSignUpConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
                startActivity(intent);
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
                String confirmPassword = etSignUpConfirmPassword.getText().toString();
                boolean emailExistenceCheck = false;
                Cursor cursor = dbClass.getData();
                if (cursor.moveToPosition(0)) {
                    do {
                        String emailDB =
                                cursor.getString(cursor.getColumnIndex("email"));
                        if (email.equals(emailDB)) {
                            emailExistenceCheck = true;
                            Toast.makeText(SignUpScreen.this, "Email already exists", Toast.LENGTH_SHORT).show();
                        }
                    } while (cursor.moveToNext());
                }
                if (!emailExistenceCheck) {
                    if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(SignUpScreen.this, "Insert Data First", Toast.LENGTH_LONG).show();
                    } else {
                        if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                            if (password.length() >= 8) {
                                if (password.equals(confirmPassword)) {
                                    dbClass.insert(name, email, password);
                                    Toast.makeText(SignUpScreen.this, "Data Saved", Toast.LENGTH_LONG).show();
                                    etSignUpName.setText("");
                                    etSignUpEmail.setText("");
                                    etSignUpPassword.setText("");
                                } else {
                                    Toast.makeText(SignUpScreen.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignUpScreen.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpScreen.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}