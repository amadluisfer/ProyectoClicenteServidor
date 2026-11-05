package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

// excepción propia, se lanza cuando el usuario o la contraseña no coinciden
public class CredencialesInvalidasException extends Exception {

    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }
}
