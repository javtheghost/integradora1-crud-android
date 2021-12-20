package net.larntech.proyectointegradora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import net.larntech.proyectointegradora.Responses.CategoriaResponse;
import net.larntech.retrofit.R;
import net.larntech.proyectointegradora.Responses.ProductoResponse;

public class ProductoDetallesActivity extends AppCompatActivity {

    TextView username, email;

    ProductoResponse productoResponse, categoriaResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        username  = findViewById(R.id.username);
        email =  findViewById(R.id.email);



        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            productoResponse = (ProductoResponse) intent.getSerializableExtra("data");
            String usernamedata = productoResponse.getNombre();
            String useremail = productoResponse.getDescripcion();
            email.setText(useremail);
            username.setText(usernamedata);

        }


    }
}
