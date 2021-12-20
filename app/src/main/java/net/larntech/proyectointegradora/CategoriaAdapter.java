package net.larntech.proyectointegradora;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.proyectointegradora.Responses.CategoriaResponse;
import net.larntech.proyectointegradora.Responses.ProductoResponse;
import net.larntech.retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {
    List<CategoriaResponse> categoriaResponseList;
    Context context;
    public CategoriaAdapter(Context context, List<CategoriaResponse> categorias){

        this.context= context;
        categoriaResponseList=categorias;
    }
    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_categorias,parent,false);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        CategoriaResponse categoriaResponse = categoriaResponseList.get(position);
        int id = categoriaResponse.getId(position);
        holder.nombre.setText((categoriaResponse.getNombre()));

        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<CategoriaResponse> categoriaBorrar = ApiClient.getUserService().borrarCategorias(id);
                categoriaBorrar.enqueue(new Callback<CategoriaResponse>() {
                    @Override
                    public void onResponse(Call<CategoriaResponse> call, Response<CategoriaResponse> response) {
                        Toast.makeText(context,"ELIMINADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CategoriaResponse> call, Throwable t) {
                        Log.e("failure",t.getLocalizedMessage());
                    }
                });
            }
        });




    }

    @Override
    public int getItemCount() {
        return categoriaResponseList.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView id,nombre;
        ImageView borrar;
        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            borrar=itemView.findViewById(R.id.eliminarCategoria);

            nombre=itemView.findViewById(R.id.nombreCategoriaRecycler);
        }
    }
}
