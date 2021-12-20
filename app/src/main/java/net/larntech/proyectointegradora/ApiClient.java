package net.larntech.proyectointegradora;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


    public static ProductoService getUserService(){
        ProductoService productoService = getRetrofit().create(ProductoService.class);
        return productoService;

    }



}
