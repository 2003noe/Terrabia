package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.example.myapplication.R;


public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setupListeners();
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(v -> handleLogin());

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        tvForgotPassword.setOnClickListener(v -> {
            // TODO: Implémenter la récupération de mot de passe
            Toast.makeText(this, "Fonction à implémenter", Toast.LENGTH_SHORT).show();
        });
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implémenter la logique de connexion avec votre backend
        // Pour le moment, simulation d'une connexion réussie
        Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show();

        // Rediriger vers le dashboard acheteur par défaut
        Intent intent = new Intent(LoginActivity.this, AcheteurDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}