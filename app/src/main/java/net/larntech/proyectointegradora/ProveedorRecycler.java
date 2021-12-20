package net.larntech.proyectointegradora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import net.larntech.proyectointegradora.Responses.ProveedorResponse;
import net.larntech.retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProveedorRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    ProveedorAdapter proveedorAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_recycler);

        recyclerView = findViewById(R.id.recyclerProveedores);


        swipeRefreshLayout = findViewById(R.id.swipeRefreshProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        getAllProveedores();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllProveedores();

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void getAllProveedores(){
        Call<List<ProveedorResponse>> proveedorlist = ApiClient.getUserService().getAllProveedores();

        proveedorlist.enqueue(new Callback<List<ProveedorResponse>>() {
            @Override
            public void onResponse(Call<List<ProveedorResponse>> call, Response<List<ProveedorResponse>> response) {
                if (response.isSuccessful()){
                    List<ProveedorResponse> proveedorGetResponses = response.body();
                    ProveedorAdapter proveedorAdapter = new ProveedorAdapter(ProveedorRecycler.this, proveedorGetResponses);
                    recyclerView.setAdapter(proveedorAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ProveedorResponse>> call, Throwable t) {
                Toast.makeText(ProveedorRecycler.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postProveedores(View view) {
        Intent i = new Intent(this, PublicarProveedor.class);
        startActivity(i);
    }
}