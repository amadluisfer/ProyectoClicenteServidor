package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TreeMap;

// Permite guardar las facturas emitidas desde el sistema
public class RegistroFacturas implements Serializable {

    // TreeMap que guarda las facturas ordenadas por su número
    private TreeMap<Integer, Factura> facturas;

    public RegistroFacturas() {
        facturas = new TreeMap<>();
    }

    // Permite que cuando se emite la factura se validen los datos
    //revisa el stock, descuenta las existencias del inventario  y le asigne #de factura y la fecha de emision
    public void emitir(Factura factura) throws StockInsuficienteException {
        if (factura.getCliente() == null || factura.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un cliente y agregar al menos un producto.");
        }
        if (factura.getPorcentajeDescuento() < 0 || factura.getPorcentajeDescuento() > 100)
        {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }
        // primero se revisa que alcance todo, asi no queda nada a medias
        for (DetalleFactura d : factura.getDetalles()) {
            if (d.getCantidad() > d.getProducto().getExistencias()) {
                throw new StockInsuficienteException("Stock insuficiente de " + d.getProducto().getNombre()
                        + ": hay " + d.getProducto().getExistencias() + " unidades.");
            }
        }
        for (DetalleFactura d : factura.getDetalles()) {
            d.getProducto().descontarExistencias(d.getCantidad());
        }
        // se calcula el siguiente número de factura y se le asigna con la fecha y el estado antes de guardarla
        int siguiente = facturas.isEmpty() ? 1 : facturas.lastKey() + 1;
        factura.setNumero(siguiente);
        factura.setFecha(LocalDateTime.now());
        factura.setEstado(EstadoFactura.EMITIDA);
        facturas.put(siguiente, factura);
    }

    ///// revisa si el cliente tiene alguna factura registrada
    public boolean clienteTieneFacturas(Cliente cliente) {
        for (Factura f : facturas.values()) {
            if (f.getCliente().getId() == cliente.getId()) {
                return true;
            }
        }
        return false;
    }

    // revisa si el producto aparece en alguna factura registrada
    public boolean productoEnFacturas(Producto producto) {
        for (Factura f : facturas.values()) {
            for (DetalleFactura d : f.getDetalles()) {
                if (d.getProducto().getCodigo().equals(producto.getCodigo())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int cantidad() {
        return facturas.size();
    }

    // suma el total de las facturas emitidas, se usa en el resumen del menú
    public double totalVendido() {
        
        double total = 0;
        for (Factura f : facturas.values(  )) {
            if (f.getEstado() == EstadoFactura.EMITIDA) {
                total += f.calcularTotal();
            }
        }
        
        return total;
    }
}
