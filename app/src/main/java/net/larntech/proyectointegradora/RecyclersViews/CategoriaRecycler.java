package net.larntech.proyectointegradora.RecyclersViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import net.larntech.proyectointegradora.ApiClient;
import net.larntech.proyectointegradora.CategoriaAdapter;
import net.larntech.proyectointegradora.ProveedorRecycler;
import net.larntech.proyectointegradora.PublicarCategoria;
import net.larntech.proyectointegradora.Responses.CategoriaResponse;
import net.larntech.retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoriaAdapter categoriaAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshCategorias);
        recyclerView = findViewById(R.id.recyclerCategorias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getAllCategorias();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllCategorias();

                swipeRefreshLayout.setRefreshing(false);
            }
        });
   }
    public void getAllCategorias(){
        Call<List<CategoriaResponse>> categorialist = ApiClient.getUserService().getAllCategorias();

        categorialist.enqueue(new Callback<List<CategoriaResponse>>() {
            @Override
            public void onResponse(Call<List<CategoriaResponse>> call, Response<List<CategoriaResponse>> response) {
                if (response.isSuccessful()){
                    List<CategoriaResponse> categoriaResponses = response.body();
                    CategoriaAdapter categoriaAdapter = new CategoriaAdapter(CategoriaRecycler.this,categoriaResponses);
                    recyclerView.setAdapter(categoriaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaResponse>> call, Throwable t) {
                Toast.makeText(CategoriaRecycler.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void publicarCategoria(View view) {
        Intent i = new Intent(this, PublicarCategoria.class);
        startActivity(i);
    }
}