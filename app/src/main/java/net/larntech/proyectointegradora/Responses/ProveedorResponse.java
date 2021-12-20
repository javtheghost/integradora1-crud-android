package net.larntech.proyectointegradora.Responses;

import java.io.Serializable;

public class ProveedorResponse implements Serializable {
    private int id;
    private String nombre;

    public int getId(int position) { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ProveedorResponse{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
