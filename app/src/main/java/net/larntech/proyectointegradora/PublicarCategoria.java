package net.larntech.proyectointegradora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.larntech.proyectointegradora.Requests.CategoriaRequest;
import net.larntech.proyectointegradora.Responses.CategoriaPostResponse;
import net.larntech.retrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicarCategoria extends AppCompatActivity {

    private Button btnCategoriaPost;
    private EditText nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_categoria);

        nombre = findViewById(R.id.etNombreCategoria);
        btnCategoriaPost = findViewById(R.id.btnCategoriaPost);

        btnCategoriaPost.setOnClickListener(v -> guardarCategoria(createRequest()));
    }

    public CategoriaRequest createRequest(){
        CategoriaRequest categoriaRequest = new CategoriaRequest();
        categoriaRequest.setNombre(nombre.getText().toString());

        return categoriaRequest;
    }
    public void guardarCategoria(CategoriaRequest categoriaRequest){

        Call<CategoriaPostResponse> categoriaResponseCall=ApiClient.getUserService().saveCategorias(categoriaRequest);
        categoriaResponseCall.enqueue(new Callback<CategoriaPostResponse>() {
            @Override
            public void onResponse(Call<CategoriaPostResponse> call, Response<CategoriaPostResponse> response) {
                Toast.makeText(net.larntech.proyectointegradora.PublicarCategoria.this,"!Publicación exitosa!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CategoriaPostResponse> call, Throwable t) {
                Toast.makeText(net.larntech.proyectointegradora.PublicarCategoria.this,"Peticion Fallada", Toast.LENGTH_SHORT).show();
            }
        });
    }
}