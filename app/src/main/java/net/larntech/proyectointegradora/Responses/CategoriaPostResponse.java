package net.larntech.proyectointegradora.Responses;

public class CategoriaPostResponse {
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CategoriaPostResponse{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
