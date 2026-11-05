package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

// excepción propia, se lanza cuando se quiere vender más de lo que hay en inventario
public class StockInsuficienteException extends Exception {

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
