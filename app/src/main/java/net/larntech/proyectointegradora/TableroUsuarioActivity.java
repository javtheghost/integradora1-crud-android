package net.larntech.proyectointegradora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import net.larntech.proyectointegradora.RecyclersViews.CategoriaRecycler;
import net.larntech.retrofit.R;

public class TableroUsuarioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero_usuario);


    }



    public void recyclerCategorias(View view) {
        Intent intent = new Intent(this, CategoriaRecycler.class);
        startActivity(intent);
    }

    public void recyclerProveedor(View view) {
        Intent i = new Intent(this, ProveedorRecycler.class);
        startActivity(i);
    }


    public void postProducto(View view) {
        Intent i = new Intent(this, PublicarProductoActivity.class);
        startActivity(i);
    }
}