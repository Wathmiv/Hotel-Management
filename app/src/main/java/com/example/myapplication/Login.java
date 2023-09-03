package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class Login extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.editTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.button);

        loginBtn.setOnClickListener(v -> loginAccount());
    }

    void loginAccount() {
        String email = this.emailEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();

        boolean valid = validate(email);
        if(!valid) return;

        authenticate(email, password);
}

void authenticate(String email, String password) {
    // Authenticate the user
    // If the user is authenticated, then open the main activity
    // Else, show an error message
    FirebaseAuth auth = FirebaseAuth.getInstance();
    auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if(task.isSuccessful()) {
                    // Open the main activity
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Show an error message
                    emailEditText.setError("Invalid Email or Password");
                }
            });
}
boolean validate(String email) {
        // Check for a valid email address and password.
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        emailEditText.setError("Invalid Email");
        return false;
    }
    return true;}

}