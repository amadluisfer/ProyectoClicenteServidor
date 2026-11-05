package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

// hereda de Producto, para limpieza e higiene, se le agregan marca y presentación
public class ArticuloHogar extends Producto {

    private String marca;
    private String presentacion;

    // los datos en común se envían a Producto y los últimos dos son del artículo
    public ArticuloHogar(String codigo, String nombre, Categoria categoria, double valorCompra, double valorVenta,
            int existencias, int stockMinimo, String marca, String presentacion) {
        super(codigo, nombre, categoria, valorCompra, valorVenta, existencias, stockMinimo);
        this.marca = marca;
        this.presentacion = presentacion;
    }

    // pagan el IVA normal de 13%
    @Override
    public double obtenerPorcentajeImpuesto() {
        return 0.13;
    }

    @Override
    public String obtenerTipo() {
        return "Art. hogar";
    }

    // Getters
    public String getMarca() {
        return marca;
    }

    public String getPresentacion() {
        return presentacion;
    }
}
