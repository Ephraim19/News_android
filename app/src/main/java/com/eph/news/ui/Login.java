package com.eph.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eph.news.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //logging in a user
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBarLogin.setVisibility(View.VISIBLE);
                loginUser();
            }
        });

        //Starting sign up activity
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"register",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loginUser() {
        final String email = binding.emailText.getText().toString().trim();
        final String password = binding.passwordText.getText().toString().trim();

        if(email.isEmpty()){
            binding.progressBarLogin.setVisibility(View.INVISIBLE);
            binding.emailText.setError("Email is required");
            binding.emailText.requestFocus();
            return;
        }

        if(password.isEmpty()){
            binding.progressBarLogin.setVisibility(View.INVISIBLE);
            binding.passwordText.setError("Password is required");
            binding.passwordText.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(Login.this, "Logging in...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        //Saving the uid to shared preferences
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                        mEditor = sharedPreferences.edit();
                        addToSharedPreferences(uid);

                        binding.progressBarLogin.setVisibility(View.INVISIBLE);
                        startActivity(intent);
                    }else {
                        binding.progressBarLogin.setVisibility(View.INVISIBLE);
                        Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void addToSharedPreferences(String uid) {
        mEditor.putString("User", uid).apply();
    }

    // Check if user is signed in
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

//    //Logging in user automatically if they did not log out
//    private void updateUI(FirebaseUser currentUser) {
//        if (currentUser != null){
//            Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Login.this, MainActivity2.class);
//            startActivity(intent);
//        }
//    }
}
























