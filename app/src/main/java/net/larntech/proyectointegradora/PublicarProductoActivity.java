package net.larntech.proyectointegradora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonIOException;

import net.larntech.proyectointegradora.Requests.ProductoRequest;
import net.larntech.proyectointegradora.Responses.CategoriaResponse;
import net.larntech.retrofit.R;
import net.larntech.proyectointegradora.Responses.ProductoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PublicarProductoActivity extends AppCompatActivity {

    ArrayList<String> getstateName=new ArrayList<>();
    Spinner spinnerCategoria;
    Button btnPost;
    EditText categoria, proveedor, nombre, descripcion, precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_producto);

        btnPost = findViewById(R.id.btnCategoriaPost);
        nombre  = findViewById(R.id.etNombreCategoria);
        categoria  = findViewById(R.id.etCategoria);
        descripcion  = findViewById(R.id.etDescripcion);
        proveedor  = findViewById(R.id.etProveedor);
        precio  = findViewById(R.id.etPrecio);


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarProducto(createRequest());
            }
        });
    }






    public ProductoRequest createRequest(){
        ProductoRequest productoRequest=new ProductoRequest();
        productoRequest.setNombre(nombre.getText().toString());
        productoRequest.setDescripcion(descripcion.getText().toString());
        productoRequest.setPrecio(precio.getText().toString());
        productoRequest.setCategoria_id(Integer.parseInt(categoria.getText().toString()));
        productoRequest.setProveedor_id(Integer.parseInt(proveedor.getText().toString()));

        return productoRequest;

    }


    public void guardarProducto(ProductoRequest productoRequest){

        Call<ProductoResponse> productoResponseCall=ApiClient.getUserService().saveUsers(productoRequest);
        productoResponseCall.enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {


                if(response.isSuccessful()){

                    Toast.makeText(PublicarProductoActivity.this,"Guardado",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(PublicarProductoActivity.this,"Peticion Fallada",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {

                Toast.makeText(PublicarProductoActivity.this,"Peticion Fallada"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();


            }
        });

    }

}