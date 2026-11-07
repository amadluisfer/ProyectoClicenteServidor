package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

// excepción propia que se lanza cuando el usuario o la contraseña son incorrectos al iniciar sesión
public class CredencialesInvalidasException extends Exception {
    // el mensaje se envía a la clase Exception con super()
    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }
}
