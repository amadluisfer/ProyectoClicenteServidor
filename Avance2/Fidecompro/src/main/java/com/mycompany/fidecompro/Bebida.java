package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

// hereda de Producto, se le agregan el volumen y si es tipo retornable
public class Bebida extends Producto {

    private int volumenMl;
    private boolean retornable;

    // los datos en comn se envían a Producto, los últimos dos son para las bebidas
    public Bebida(String codigo, String nombre, Categoria categoria, double valorCompra, double valorVenta,
            int existencias, int stockMinimo, int volumenMl, boolean retornable) {
        super(codigo, nombre, categoria, valorCompra, valorVenta, existencias, stockMinimo);
        this.volumenMl = volumenMl;
        this.retornable = retornable;
    }

    // las bebidas siempre pagan al 13% de IVA
    @Override
    public double obtenerPorcentajeImpuesto() {
        return 0.13;
    }

    @Override
    public String obtenerTipo() {
        return "Bebida";
    }

    // Getters
    public int getVolumenMl() {
        return volumenMl;
    }

    public boolean isRetornable() {
        return retornable;
    }
}
