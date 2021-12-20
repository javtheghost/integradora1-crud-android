package net.larntech.proyectointegradora.Requests;

public class ProductoRequest {
    private int categoria_id;
    private int proveedor_id;
    private String nombre;
    private  String descripcion;
    private  String precio;

        public String getNombre() {
        return nombre;
    }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getPrecio() {
            return precio;
        }

        public void setPrecio(String precio) {
            this.precio = precio;
        }

        public int getCategoria_id() {
            return categoria_id;
        }

        public void setCategoria_id(int categoria_id) {
            this.categoria_id = categoria_id;
        }

        public int getProveedor_id() {
            return proveedor_id;
        }

        public void setProveedor_id(int proveedor_id) {
            this.proveedor_id = proveedor_id;
        }
}

