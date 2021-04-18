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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;
import android.widget.ImageButton;
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
    boolean emailExistenceCheck;
    Cursor cursor;
    Database dbClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        dbClass = new Database(SignUpScreen.this);
        cursor = dbClass.getData();
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
                if (checkEmailExistence(cursor, etSignUpEmail.getText().toString())) {
                    checkBoxEmail.setVisibility(View.VISIBLE);
                } else {
                    checkBoxEmail.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        etSignUpPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkPassword(etSignUpPassword.getText().toString())) {
                    checkBoxPassword.setVisibility(View.VISIBLE);
                } else {
                    checkBoxPassword.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etSignUpConfirmPassword = findViewById(R.id.etSignUpConfirmPassword);
        etSignUpConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkConfirmPassword(etSignUpConfirmPassword.getText().toString())) {
                    checkBoxConfirmPassword.setVisibility(View.VISIBLE);
                } else {
                    checkBoxConfirmPassword.setVisibility(View.INVISIBLE);
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
                String name = etSignUpName.getText().toString();
                String email = etSignUpEmail.getText().toString();
                String password = etSignUpPassword.getText().toString();
                String confirmPassword = etSignUpConfirmPassword.getText().toString();
                emailExistenceCheck = false;
                cursor = dbClass.getData();
                String output = validateUserInfo(cursor, email, emailExistenceCheck, name, password, confirmPassword, dbClass);
                Toast.makeText(SignUpScreen.this, output, Toast.LENGTH_SHORT).show();
                if(output == "Signed Up Successfully"){
                    Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void onClickBackButtonSignUpScreen(View v){
        finish();
    }

    String validateUserInfo(Cursor cursor, String email, boolean emailExistenceCheck, String name, String password, String confirmPassword, Database dbClass) {
        if (cursor.moveToPosition(0)) {
            do {
                String emailDB =
                        cursor.getString(cursor.getColumnIndex("email"));
                if (email.equals(emailDB)) {
                    emailExistenceCheck = true;
                    return "Email already exists";
                }
            } while (cursor.moveToNext());
        }
        if (!emailExistenceCheck) {
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                return "Insert Data First";
            } else {
                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    if (password.length() >= 8) {
                        if (password.equals(confirmPassword)) {
                            dbClass.insert(name, email, password);

                            etSignUpName.setText("");
                            etSignUpEmail.setText("");
                            etSignUpPassword.setText("");
                            etSignUpConfirmPassword.setText("");
                            return "Signed Up Successfully";
                        } else {
                            return "Password doesn't match";
                        }
                    } else {
                        return "Password must be at least 8 characters long";

                    }
                } else {
                    return "Invalid Email";
                }
            }
        }
        return "";
    }

    boolean checkEmailExistence(Cursor cursor, String email) {
        boolean temp = false;
        if (cursor.moveToPosition(0)) {
            do {
                String emailDB =
                        cursor.getString(cursor.getColumnIndex("email"));
                if (email.equals(emailDB)) {
                    temp = true;
                }
            } while (cursor.moveToNext());
        }
        if (!temp) {
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean checkPassword(String password) {
        if (password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkConfirmPassword(String confirmPassword) {
        if (confirmPassword.equals(etSignUpPassword.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}