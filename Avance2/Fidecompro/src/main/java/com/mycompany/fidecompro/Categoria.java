package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//cada categoría guarda su propia lista de productos
public class Categoria implements Serializable {

    private String nombre;
    private List<Producto> productos;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // permite quitar de la lista de la categoría el producto según el código que se recibe por parámetro
    public void eliminarProducto(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                productos.remove(i);
                break;
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // permite que en las listas de categorías se muestre el nombre de la categoría
    @Override
    public String toString() {
        return nombre;
    }
}
