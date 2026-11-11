package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;

// clase padre de todos los productos
//cada tipo calcula su propio IVA
public abstract class Producto implements Mostrable, Comparable<Producto>, Serializable {

    protected String codigo;
    protected String nombre;
    protected Categoria categoria;
    protected double valorCompra;
    protected double valorVenta;
    protected int existencias;
    protected int stockMinimo;
    
    protected boolean activo;

    // constructor con los datos que tienen todos los productos, las subclases lo llaman usando super()
    public Producto(String codigo, String nombre, Categoria categoria, double valorCompra,
            double valorVenta, int existencias, int stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.valorCompra = valorCompra;
        this.valorVenta = valorVenta;
        this.existencias = existencias;
        this.stockMinimo = stockMinimo;
        this.activo = true;
    }

    // método abstracto, cada tipo de producto define su porcentaje de IVA
    public abstract double obtenerPorcentajeImpuesto();

    public abstract String obtenerTipo();

    // se usa al emitir la factura, si no alcanza el stock lanza la excepción
    public void descontarExistencias(int cantidad) throws StockInsuficienteException {
        if (cantidad > existencias) {
            throw new StockInsuficienteException("Stock insuficiente de " + nombre + ": hay " + existencias + " unidades.");
        }
        existencias -= cantidad;
    }

    //Revisa si las existencias llegaron al mínimo, se usa para generar una alerta de stock bajo
    public boolean necesitaReabastecer() {
        return existencias <= stockMinimo;
    }

    // implementación de Mostrable, el tipo y el IVA dependen de la subclase correspondiente 
    @Override
    public String mostrarInformacion() {
        return codigo + " - " + nombre + " (" + obtenerTipo() + ") precio: " + valorVenta
                + " IVA: " + (int) (obtenerPorcentajeImpuesto() * 100) + "% stock: " + existencias;
    }

    
    @Override
    public int compareTo(Producto otro) {
        return nombre.compareToIgnoreCase(otro.nombre);
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        
        this.stockMinimo = stockMinimo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
