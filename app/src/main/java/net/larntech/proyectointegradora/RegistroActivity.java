package net.larntech.proyectointegradora;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.larntech.retrofit.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {
  EditText enombre, eCorreo, eContra, eConfirmacion;
  Button btn_Registrar;
  String nombre, correo, contrasena, confirmacion;
  public String Url_registro = "http://192.168.1.8:8080/api/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        enombre=findViewById(R.id.etNombre);
        eCorreo=findViewById(R.id.etCorreo);
        eContra=findViewById(R.id.etContraseña);
        eConfirmacion=findViewById(R.id.etConfirmarContraseña);

        btn_Registrar=findViewById(R.id.btnRegistrarse);


        btn_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegistro();
            }
        });
    }

    private void checkRegistro() {
        nombre=enombre.getText().toString();
        correo=eCorreo.getText().toString();
        contrasena=eContra.getText().toString();
        confirmacion=eConfirmacion.getText().toString();

        if(nombre.isEmpty()|| correo.isEmpty()||contrasena.isEmpty()){


            AlertaFail("llenar campos");

        }
        else if(!contrasena.equals((confirmacion))){
            AlertaFail("Contrasena no existe");

        }
        else {

            EnviarRegistro();

        }

    }

    private void EnviarRegistro() {

        JSONObject params = new JSONObject();
        try {
            params.put("name", nombre);
            params.put("email",correo);
            params.put("password",contrasena);
            params.put("password_confirmation",confirmacion);

        } catch (JSONException e){
            e.printStackTrace();
        }

        String data =params.toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http= new Http(RegistroActivity.this,Url_registro);
                http.setMethod("post");
                http.setData(data);
                http.enviar();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 201|| code==200){

                            alertasucedio("Registro Completado");

                        } else if (code == 422){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg= response.getString("message") ;
                                AlertaFail("msg");
                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                        }
                        else{


                        }
                    }
                });

            }
        }).start();
    }

    private void alertasucedio(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Completado")
                .setMessage(s)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                })
                .show();
    }

    private void AlertaFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Fallo")
                .setMessage(s)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}