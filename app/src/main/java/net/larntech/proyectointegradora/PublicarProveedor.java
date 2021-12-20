package net.larntech.proyectointegradora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.larntech.proyectointegradora.Requests.ProveedorRequest;
import net.larntech.proyectointegradora.Responses.ProveedorPostResponse;
import net.larntech.retrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicarProveedor extends AppCompatActivity {

    private EditText nombreProveedor;
    private Button btnProveedorPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_proveedor);
        nombreProveedor = findViewById(R.id.etNombreProveedor);
        btnProveedorPost = findViewById(R.id.btnproveedorPost);
        btnProveedorPost.setOnClickListener(v -> guardarProveedor(createRequest()));




    }

    public ProveedorRequest createRequest(){
        ProveedorRequest proveedorRequest = new ProveedorRequest();
        proveedorRequest.setNombre(nombreProveedor.getText().toString());

        return proveedorRequest;
    }
    public void guardarProveedor(ProveedorRequest proveedorRequest){

        Call<ProveedorPostResponse> proveedorResponseCall=ApiClient.getUserService().saveProveedores(proveedorRequest);
        proveedorResponseCall.enqueue(new Callback<ProveedorPostResponse>() {
            @Override
            public void onResponse(Call<ProveedorPostResponse> call, Response<ProveedorPostResponse> response) {
                Toast.makeText(PublicarProveedor.this,"!Publicación exitosa!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ProveedorPostResponse> call, Throwable t) {
                Toast.makeText(PublicarProveedor.this,"Peticion Fallada",Toast.LENGTH_SHORT).show();
            }
        });


    }
}