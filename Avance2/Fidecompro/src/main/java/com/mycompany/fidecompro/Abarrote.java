package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.time.LocalDate;

// hereda de Producto y se le agregan fecha de vencimiento y si es de canasta BAsica
public class Abarrote extends Producto {

    private LocalDate fechaVencimiento;
    private boolean canastaBasica;

    // los primeros datos son para la clase base y los últimos dos son del abarrote
    public Abarrote(String codigo, String nombre, Categoria categoria, double valorCompra, double valorVenta,
            int existencias, int stockMinimo, LocalDate fechaVencimiento, boolean canastaBasica) {
        super(codigo, nombre, categoria, valorCompra, valorVenta, existencias, stockMinimo);
        this.fechaVencimiento = fechaVencimiento;
        this.canastaBasica = canastaBasica;
    }

    // La regla es que los de canasta basica pagan 1%, los demas 13%
    @Override
    public double obtenerPorcentajeImpuesto() {
        if (canastaBasica) {
            return 0.01;
        }
        return 0.13;
    }

    @Override
    public String obtenerTipo() {
        return "Abarrote";
    }

    // Getters
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public boolean isCanastaBasica() {
        return canastaBasica;
    }
}
