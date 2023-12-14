package com.example.myapplication5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText etnombre, etusuario, etpassword, etedad;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etnombre = findViewById(R.id.nombreApellidos);
        etusuario = findViewById(R.id.dni);
        etpassword = findViewById(R.id.correo);
        etedad = findViewById(R.id.contra);

        btn_registrar = findViewById(R.id.registrar);

        btn_registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String name = etnombre.getText().toString();
        final String username = etusuario.getText().toString();
        final String password = etpassword.getText().toString();
        final int age = Integer.parseInt(etedad.getText().toString());

        Response.Listener<String> repoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Corregir aquí: Crear JSONObject a partir de la respuesta de cadena
                    JSONObject jsonResponse = new JSONObject(response);

                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Error en el registro")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        // Ajuste: Agregar un Response.ErrorListener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar errores de la solicitud aquí
                // Puedes mostrar un mensaje de error o realizar otras acciones según tus necesidades
                error.printStackTrace();
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, repoListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        queue.add(registerRequest);
    }
}