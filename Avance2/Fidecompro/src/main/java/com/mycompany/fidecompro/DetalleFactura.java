package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;

// una linea de la factura
public class DetalleFactura implements Mostrable, Serializable {

    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    // el precio se guarda al momento de la venta, así si después cambia no afecta la factura
    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getValorVenta();
    }

    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

    // el IVA se calcula sobre el monto ya con descuento
    public double calcularImpuesto(double porcentajeDescuento) {
        double conDescuento = calcularSubtotal() * (1 - porcentajeDescuento / 100);
        return conDescuento * producto.obtenerPorcentajeImpuesto();
    }

    // implementación de Mostrable, arma la línea con formato de columnas para la factura
    @Override
    public String mostrarInformacion() {
        return String.format("%4d  %-28s %14s %14s", cantidad, producto.getNombre(),
                Factura.formatearMonto(precioUnitario), Factura.formatearMonto(calcularSubtotal()));
    }

    // Getters y Setters
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}
