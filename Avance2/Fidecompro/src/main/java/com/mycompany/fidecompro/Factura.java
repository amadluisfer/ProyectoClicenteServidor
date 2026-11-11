package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// clase principal de la venta, tiene al cliente, el vendedor y tambien contiene lista de detalles 
public class Factura implements Mostrable, Serializable {

    private int numero;
    private LocalDateTime fecha;
    private Cliente cliente;
    private Usuario vendedor;
    private MetodoPago metodoPago;
    private EstadoFactura estado;
    private double porcentajeDescuento;
    private List<DetalleFactura> detalles;

    // crea la factura con el vendedor, pago en efectivo y sin productos
    public Factura(Usuario vendedor) {
        this.vendedor = vendedor;
        this.metodoPago = MetodoPago.EFECTIVO;
        this.detalles = new ArrayList<>();
    }

    // agrega un producto a la factura, si ya estaba se suma la cantidad en la misma línea 
    // y siempre se valida que haya stock suficiente al momento de agregar el producto 
    public void agregarDetalle(Producto producto, int cantidad) throws StockInsuficienteException {
        DetalleFactura existente = null;
        //// busca si el producto ya está en la factura
        for (DetalleFactura d : detalles) {
            if (d.getProducto().getCodigo().equals(producto.getCodigo())) {
                existente = d;
            }
        }
        int total = cantidad;
        if (existente != null) {
            total += existente.getCantidad();
        }
        
       
        if (total > producto.getExistencias()) {
            throw new StockInsuficienteException("Stock insuficiente de " + producto.getNombre() //// si no hay suficientes existencias lanza la excepción
                    + ": hay " + producto.getExistencias() + " unidades.");
        }
        
        if (existente != null) {
            existente.setCantidad(total);
        } else {
            detalles.add(new DetalleFactura(producto, cantidad));
        }
    }

    public void quitarDetalle(int indice) {
        detalles.remove(indice);
    }

    // suma de todas las líneas sin impuesto
    public double calcularSubtotal() {
        double suma = 0;
        for (DetalleFactura d : detalles) {
            suma += d.calcularSubtotal();
        }
        return suma;
    }

    public double calcularDescuento() {
        return calcularSubtotal() * porcentajeDescuento / 100;
    }

    // cada línea calcula su IVA según es el tipo de producto
    public double calcularImpuesto() {
        double suma = 0;
        for (DetalleFactura d : detalles) {
            suma += d.calcularImpuesto(porcentajeDescuento);
        }
        
        return suma;
    }

    // total = subtotal - descuento + IVA
    public double calcularTotal() {
        
        return calcularSubtotal() - calcularDescuento() + calcularImpuesto();
    }

    // la factura no se borra, solo cambia de estado
    public void anular() {
        estado = EstadoFactura.ANULADA;
    }

    public String getNumeroFormateado() {
        
        return String.format("FC-%06d", numero);
    }

    // es static para poder usarlo desde las ventanas sin tener una factura, formato en colones
    public static String formatearMonto(double monto) {
        return String.format("₡%,.2f", monto);
    }

    // implementación de Mostrable
    //Arma el texto completo de la factura con encabezado, las líneas y los totales
    @Override
    public String mostrarInformacion() {
        String linea = "=".repeat(66) + "\n";
        String texto = linea;
        texto += "              FIDECOMPRO - VENTAS AL POR MAYOR\n";
        texto += linea;
        texto += "Factura N.: " + getNumeroFormateado() + "     Fecha: "
                + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n";
        texto += "Vendedor  : " + vendedor.getNombre() + "\n";
        texto += "Cliente   : " + cliente.getNombre() + "\n";
        texto += "Identific.: " + cliente.getIdentificacion() + "   Tel: " + cliente.getTelefono() + "\n";
        texto += "-".repeat(66) + "\n";
        texto += String.format("%4s  %-28s %14s %14s%n", "Cant", "Descripción", "P.Unit.", "Total");
        for (DetalleFactura d : detalles) {
            texto += d.mostrarInformacion() + "\n";
        }
        texto += "-".repeat(66) + "\n";
        
        texto += String.format("%50s %15s%n", "Subtotal :", formatearMonto(calcularSubtotal()));
        texto += String.format("%50s %15s%n", "Descuento " + porcentajeDescuento + "% :", "-" + formatearMonto(calcularDescuento()));
        texto += String.format("%50s %15s%n", "IVA :", formatearMonto(calcularImpuesto()));
        texto += String.format("%50s %15s%n", "TOTAL :", formatearMonto(calcularTotal()));
        texto += "Método de pago: " + metodoPago + "   Estado: " + estado + "\n";
        texto += linea;
        
        return texto;
    }

    // guarda la factura en un .txt dentro de una carpeta definida
    public String generarArchivo(String carpeta) throws IOException {
        File archivo = new File(carpeta, getNumeroFormateado() + ".txt");
        try (FileWriter escritor = new FileWriter(archivo, StandardCharsets.UTF_8)) {
            escritor.write(mostrarInformacion());
        }
        return archivo.getAbsolutePath();
    }

    // Getters y Setters
    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
    
    //Define el porcentaje de descuento sobre la factua
    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }
}
