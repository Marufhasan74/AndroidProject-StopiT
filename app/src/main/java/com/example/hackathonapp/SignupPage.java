package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;


import android.util.Log;
import android.util.Patterns;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;

public class SignupPage extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName, editTextPassword, editConfirmPassword, editPhone, editEmail, editAdress;
    private RadioGroup editTextGender;
    private TextView as_tvBack;
    private CheckBox checker;
    private Button SignUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        editTextName = findViewById(R.id.Signup_etFirstName);
        editTextGender = findViewById(R.id.RadioGroupGender);
        editEmail = findViewById(R.id.Signup_etEmail);
        editAdress = findViewById(R.id.Signup_etAddress);
        editPhone = findViewById(R.id.Signup_etPhone);
        editTextPassword = findViewById(R.id.Signup_etPassword);
        editConfirmPassword = findViewById(R.id.Signup_etConfirmPassword);
        checker = findViewById(R.id.Signup_cbPrivacyPolicy);
        as_tvBack = findViewById(R.id.as_tvBack);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginPage_btnLogin).setOnClickListener(this);

        SignUp = findViewById(R.id.loginPage_btnLogin);

        as_tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignupPage.this, SignInPage.class));
                finish();

            }
        });
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password1 = editConfirmPassword.getText().toString().trim();
        final String phone = editPhone.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(editTextGender.getCheckedRadioButtonId()))
                .getText().toString();
        final String adress = editAdress.getText().toString().trim();
        final String check = checker.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Name field cannot be empty ");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError("Email field cannot be empty ");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Invalid email format ");
            editEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password field cannot be empty ");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters ");
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editPhone.setError("Phone field cannot be empty ");
            editPhone.requestFocus();
            return;
        }

        if (phone.length() != 11) {
            editPhone.setError("Phone number must be 11 digits");
            editPhone.requestFocus();
            return;
        }

        if (!password.equals(password1)) {
            editConfirmPassword.setError("Passwords do not match");
            editConfirmPassword.requestFocus();
            return;
        }


//

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {


                            User user = new User(
                                    name,
                                    email,
                                    phone,
                                    adress,
                                    gender
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignupPage.this, "Registration Succesfull!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SignupPage.this, Fun_page.class));


                                    } else {
                                        Toast.makeText(SignupPage.this, "Registration Unsuccesfull!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(SignupPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(SignupPage.this, "You are already logged in!", Toast.LENGTH_SHORT).show();
        }
    }


    //  @Override
    //  public void onClick(View v) {
    //      switch (v.getId()) {
    //        case R.id.loginPage_btnLogin:
    //             registerUser();
    //            break;
    //     }
    //  }


    @Override
    public void onBackPressed() {


        Intent new_intent = new Intent(SignupPage.this, SignInPage.class);
        this.startActivity(new_intent);
        finish();


    }

    @Override
    public void onClick(View view) {
        if (view == SignUp)
            registerUser();
    }
}
