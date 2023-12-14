package com.example.myapplication5;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://192.168.0.105/proyec/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("age", String.valueOf(age));
        params.put("password", password);
        setShouldCache(false); // Desactiva el almacenamiento en caché para evitar problemas con POST

        // Agregar parámetros a la solicitud
        setParams(params);
    }

    private void setParams(Map<String, String> params) {
    }

    // Agregar este método para enviar los parámetros en la solicitud POST
    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}