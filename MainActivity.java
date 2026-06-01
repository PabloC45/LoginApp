package com.example.loginapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTextCorreo;
    EditText editTextPassword;
    Button buttonIngresar;
    Spinner spinnerTipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );
            return insets;
        });

        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonIngresar = findViewById(R.id.button);
        spinnerTipoUsuario = findViewById(R.id.spinnerTipoUsuario);

        String[] tiposUsuario = {
                "Seleccione un tipo de usuario",
                "Administrador",
                "Empleado",
                "Cliente"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                tiposUsuario
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerTipoUsuario.setAdapter(adapter);

        buttonIngresar.setOnClickListener(v -> {

            String correo =
                    editTextCorreo.getText().toString().trim();

            String password =
                    editTextPassword.getText().toString().trim();

            String tipoUsuario =
                    spinnerTipoUsuario.getSelectedItem().toString();

            if (correo.isEmpty()) {
                editTextCorreo.setError(
                        "Ingrese un correo electrónico"
                );
                editTextCorreo.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError(
                        "Ingrese una contraseña"
                );
                editTextPassword.requestFocus();
                return;
            }

            if (!correo.contains("@")) {
                editTextCorreo.setError(
                        "Correo inválido"
                );
                editTextCorreo.requestFocus();
                return;
            }

            if (spinnerTipoUsuario.getSelectedItemPosition() == 0) {

                Toast.makeText(
                        MainActivity.this,
                        "Seleccione un tipo de usuario",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Toast.makeText(
                    MainActivity.this,
                    "¡Bienvenido " + tipoUsuario +
                            "! Iniciando sesión...",
                    Toast.LENGTH_LONG
            ).show();

        });
    }
}