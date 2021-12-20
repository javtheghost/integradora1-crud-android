package net.larntech.proyectointegradora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.larntech.retrofit.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo, etPassword;
    private Button btn_login, btn_registrar;
    private String email, password;
    LocalStorage localStorage;
    public String Url_Login= "http://192.168.1.8:8080/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        localStorage = new LocalStorage(LoginActivity.this);
        etCorreo=findViewById(R.id.etCorreo);
        etPassword=findViewById(R.id.etContraseña);

        btn_login=findViewById(R.id.btnIniciarSesion);
        btn_registrar=findViewById(R.id.btnRegistrarse);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checarLogin();
            }
        });
    }
    private void checarLogin() {

        email=etCorreo.getText().toString();
        password=etPassword.getText().toString();
        if(email.isEmpty() || password.isEmpty()) {

            alertFail("Correo y Contraseña Requerida");

        }
        else{

            enviarLogin();

        }
    }

    private void enviarLogin() {

        JSONObject params = new JSONObject();
        try {
            params.put("email",email);
            params.put("password",password);

        } catch (JSONException e){
            e.printStackTrace();
        }

        String data= params.toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(LoginActivity.this,Url_Login);
                http.setMethod("post");
                http.setData(data);
                http.enviar();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code= http.getStatusCode();
                        if(code==200){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String token = response.getString("token");
                                localStorage.setToken(token);
                                Intent i= new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }catch (JSONException e){}

                        }
                        else if(code == 422){

                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg= response.getString("message");
                                alertFail(msg);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                        }
                        else if(code==401){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg= response.getString("message");
                                alertFail(msg);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Error"+code, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }).start();

    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("FALLO")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    public void registrarse(View view) {
        Intent i = new Intent(LoginActivity.this,RegistroActivity.class);
        startActivity(i);
    }
}