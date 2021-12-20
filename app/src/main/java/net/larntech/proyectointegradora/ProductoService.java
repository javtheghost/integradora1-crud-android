package net.larntech.proyectointegradora;

import net.larntech.proyectointegradora.Requests.CategoriaRequest;
import net.larntech.proyectointegradora.Requests.ProductoRequest;
import net.larntech.proyectointegradora.Requests.ProveedorRequest;
import net.larntech.proyectointegradora.Responses.CategoriaPostResponse;
import net.larntech.proyectointegradora.Responses.CategoriaResponse;
import net.larntech.proyectointegradora.Responses.CategoriaSpinnerResponse;
import net.larntech.proyectointegradora.Responses.ProductoResponse;
import net.larntech.proyectointegradora.Responses.ProveedorPostResponse;
import net.larntech.proyectointegradora.Responses.ProveedorResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductoService {

    @GET("productos")
    Call<List<ProductoResponse>> getAllProductos();
    @GET("categorias")
    Call<List<CategoriaResponse>>getAllCategorias();
    @GET("proveedores")
    Call<List<ProveedorResponse>>getAllProveedores();



    @POST("productos")
    Call<ProductoResponse> saveUsers(@Body ProductoRequest productoResponse);

    @POST("categorias")
    Call<CategoriaPostResponse> saveCategorias (@Body CategoriaRequest categoriaResponse);

    @POST("categorias")
    Call<CategoriaSpinnerResponse> spinnerCategoria(@Body CategoriaRequest categoriaResponse);

    @POST("proveedores")
    Call<ProveedorPostResponse> saveProveedores (@Body ProveedorRequest proveedorRequest);


    @DELETE("productos/{id}")
    Call<ProductoResponse> borrarProductos(@Path("id") int id);

    @DELETE("categorias/{id}")
    Call<CategoriaResponse> borrarCategorias(@Path("id") int id);

    @DELETE("proveedores/{id}")
    Call<ProveedorResponse> borrarProveedores(@Path("id") int id);


}
