package net.larntech.proyectointegradora;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.retrofit.R;
import net.larntech.proyectointegradora.Responses.ProductoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.UserAdapterVH> {

    private List<ProductoResponse> productoResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public ProductosAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<ProductoResponse> productoResponseList) {
        this.productoResponseList = productoResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ProductosAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {

        ProductoResponse productoResponse = productoResponseList.get(position);
        int id= productoResponse.getId(position);

        String precio = productoResponse.getPrecio();
        String nombre = productoResponse.getNombre();

        holder.nombre.setText(nombre);
        holder.precio.setText(precio);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(productoResponse);
            }
        });

        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ProductoResponse> productoBorrar = ApiClient.getUserService().borrarProductos(id);
                productoBorrar.enqueue(new Callback<ProductoResponse>() {
                    @Override
                    public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
                        Toast.makeText(context,"ELIMINADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ProductoResponse> call, Throwable t) {
                        Log.e("failure",t.getLocalizedMessage());
                    }
                });

            }
        });

    }

    public interface ClickedItem{
        public void ClickedUser(ProductoResponse productoResponse);
    }

    @Override
    public int getItemCount() {
        return productoResponseList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, precio;
        ImageView imageMore, borrar;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreProducto);
            borrar = itemView.findViewById(R.id.imgBorrarProducto);
            imageMore = itemView.findViewById(R.id.detallesProducto);
            precio = itemView.findViewById(R.id.precio);


        }
    }
}
