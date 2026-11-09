package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// Permite guardar las categorías del sistema y por medio de ellas se llega a todos los productos
public class Inventario implements Serializable {

    private List<Categoria> categorias;

    public Inventario() {
        categorias = new ArrayList<>();
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
    public List<Categoria> getCategorias() {
        return categorias;
    }

    // recorre cada categoría y sus productos,
    // si no aprece listado entonces lanza la excepción
    public Producto buscarProducto(String codigo) throws RegistroNoEncontradoException {
        for (Categoria c : categorias) {
            for (Producto p : c.getProductos()) {
                if (p.getCodigo().equalsIgnoreCase(codigo)) {
                    return p;
                }
            }
        }
        throw new RegistroNoEncontradoException("No existe un producto con el código " + codigo + ".");
    }

    // reutiliza "buscarProducto" para validar que no se repita el código
    public boolean existeCodigo(String codigo) {
        try {
            buscarProducto(codigo);
            return true;
        } catch (RegistroNoEncontradoException e) {
            return false;
        }
    }

    // el producto se guarda dentro de la lista de su categoría
    public void agregarProducto(Producto producto) {
        producto.getCategoria().agregarProducto(producto);
    }

    public void eliminarProducto(Producto producto) {
        producto.getCategoria().eliminarProducto(producto.getCodigo());
    }

    // todos los productos ordenados por nombre: usa el "compareTo" de Producto
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        for (Categoria c : categorias) {
            lista.addAll(c.getProductos());
        }
        Collections.sort(lista);
        return lista;
    }

    // solo los activos que llegaron al mínimo
    // se ordenan con con ComparadorPorExistencias
    public List<Producto> productosBajoMinimo() {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : listarProductos()) {
            if (p.isActivo() && p.necesitaReabastecer()) {
                lista.add(p);
            }
        }
        lista.sort(new ComparadorPorExistencias());
        return lista;
    }
}
