package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// cada categoría guarda su propia lista de productos
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

    // quita el producto que tenga ese código
    public void eliminarProducto(String codigo) {
        productos.removeIf(p -> p.getCodigo().equalsIgnoreCase(codigo));
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // para que el combo muestre el nombre
    @Override
    public String toString() {
        return nombre;
    }
}
