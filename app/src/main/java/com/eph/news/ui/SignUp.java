package com.eph.news.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.eph.news.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity {
    private static final String TAG = "Sign up";
    private FirebaseAuth mAuth;
    private ActivitySignUpBinding binding;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //initializing firebase auth
        mAuth = FirebaseAuth.getInstance();
        Log.d( TAG, String.valueOf(mAuth));

        //Starting login up activity
        binding.LoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar3.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Login...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                binding.progressBar3.setVisibility(View.INVISIBLE);
                startActivity(intent);
            }
        });

        binding.regiserUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar3.setVisibility(View.VISIBLE);
                createUser();
            }
        });
    }

    private void createUser() {
        final String email = binding.emailText.getText().toString().trim();
        final String password = binding.passwordText.getText().toString().trim();
        final String confirmPassword = binding.confirmPasswordText.getText().toString().trim();

        if(email.isEmpty()){
            binding.progressBar3.setVisibility(View.INVISIBLE);
            binding.emailText.setError("Email is required");
            binding.emailText.requestFocus();
            return;
        }

        if(password.isEmpty()){
            binding.progressBar3.setVisibility(View.INVISIBLE);
            binding.passwordText.setError("Password is required");
            binding.passwordText.requestFocus();
            return;
        }

        if(confirmPassword.isEmpty()){
            binding.progressBar3.setVisibility(View.INVISIBLE);
            binding.confirmPasswordText.setError("Enter your password again");
            binding.confirmPasswordText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.progressBar3.setVisibility(View.INVISIBLE);
            binding.emailText.setError("Enter a valid email");
            binding.emailText.requestFocus();
            return;
        }

        if(password.length() < 6){
            binding.progressBar3.setVisibility(View.INVISIBLE);
            binding.passwordText.setError("Your password must have 6 or more characters");
            binding.passwordText.requestFocus();
            return;
        }

        if(!password.equals(confirmPassword)){
            binding.progressBar3.setVisibility(View.INVISIBLE);
            binding.confirmPasswordText.setError("Passwords do not match");
            binding.confirmPasswordText.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,task -> {
                    binding.progressBar3.setVisibility(View.VISIBLE);
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "Account created", Toast.LENGTH_SHORT).show();
                        Log.d( TAG, "Authentication successful");

                        //Saving the uid to shared preferences
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                        mEditor = sharedPreferences.edit();
                        addToSharedPreferences(uid);

                        //Opening login intent
                        Intent intent = new Intent(SignUp.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        binding.progressBar3.setVisibility(View.INVISIBLE);
                        startActivity(intent);

                    } else{
                        binding.progressBar3.setVisibility(View.INVISIBLE);
                        Log.d( "mail", email);
                        Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void addToSharedPreferences(String uid) {
        mEditor.putString("User", uid).apply();

    }
}















