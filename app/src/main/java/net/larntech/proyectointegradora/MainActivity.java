package net.larntech.proyectointegradora;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import net.larntech.proyectointegradora.Responses.ProductoResponse;
import net.larntech.retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ProductosAdapter.ClickedItem{


    private Button btnPublicar;
    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPublicar = findViewById(R.id.btnPublicar);
        recyclerView = findViewById(R.id.recyclerProductos);


        swipeRefreshLayout = findViewById(R.id.swipeRefreshProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        productosAdapter = new ProductosAdapter(this::ClickedUser);


        getAllProductos();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllProductos();

                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void getAllProductos(){

        Call<List<ProductoResponse>> userlist = ApiClient.getUserService().getAllProductos();

        userlist.enqueue(new Callback<List<ProductoResponse>>() {
            @Override
            public void onResponse(Call<List<ProductoResponse>> call, Response<List<ProductoResponse>> response) {

                if(response.isSuccessful()){
                 List<ProductoResponse> productoRespons = response.body();
                 productosAdapter.setData(productoRespons);
                 recyclerView.setAdapter(productosAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<ProductoResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void ClickedUser(ProductoResponse productoResponse) {

        startActivity(new Intent(this, ProductoDetallesActivity.class).putExtra("data", productoResponse));

    }


    public void publicar(View view) {
        Intent i = new Intent(this,TableroUsuarioActivity.class);
        startActivity(i);
    }



}
