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

import net.larntech.proyectointegradora.Responses.ProveedorResponse;
import net.larntech.retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder> {
    List<ProveedorResponse> proveedorResponseListo;
    Context context;
    public ProveedorAdapter(Context context, List<ProveedorResponse> proveedores){

        this.context= context;
        proveedorResponseListo =proveedores;
    }


    @NonNull
    @Override
    public ProveedorAdapter.ProveedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_proveedores, parent, false);

        return new ProveedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProveedorAdapter.ProveedorViewHolder holder, int position) {
        ProveedorResponse proveedorResponse = proveedorResponseListo.get(position);
        int id = proveedorResponse.getId(position);
        holder.nombre.setText((proveedorResponse.getNombre()));

        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ProveedorResponse> borrarProveedores = ApiClient.getUserService().borrarProveedores(id);

                borrarProveedores.enqueue(new Callback<ProveedorResponse>() {
                    @Override
                    public void onResponse(Call<ProveedorResponse> call, Response<ProveedorResponse> response) {
                        Toast.makeText(context,"ELIMINADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ProveedorResponse> call, Throwable t) {
                        Log.e("failure",t.getLocalizedMessage());
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() { return proveedorResponseListo.size(); }

    public class ProveedorViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView borrar;
        public ProveedorViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombreProveedorRecycler);
            borrar=itemView.findViewById(R.id.borrarProveedor);

        }
    }
}
