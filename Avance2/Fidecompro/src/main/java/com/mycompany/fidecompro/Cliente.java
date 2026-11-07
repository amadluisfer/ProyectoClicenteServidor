package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.time.LocalDate;

// hereda de Persona, se le agregan el id, la dirección y si está activo
public class Cliente extends Persona implements Comparable<Cliente> {

    // es static para que todos los clientes compartan el mismo contador y no se repita el id
    private static int contador = 1;

    private int id;
    private String direccion;
    private boolean activo;
    private LocalDate fechaRegistro;

    // los datos comunes se envían a Persona con super(), el id se asigna solo con el contador
    public Cliente(TipoIdentificacion tipo, String identificacion, String nombre, String telefono, String correo, String direccion) {
        super(nombre, telefono, correo, tipo, identificacion);
        this.id = contador++;
        this.direccion = direccion;
        this.activo = true;
        this.fechaRegistro = LocalDate.now();
    }

   // permite ajustar el contador después de leer el archivo, para que los nuevos clientes sigan con el siguiente id y no se repitan
    public static void actualizarContador(int ultimoId) {
        if (ultimoId >= contador) {
            contador = ultimoId + 1;
        }
    }

    // usa el mostrarInformacion de Persona y le agrega la dirección
    @Override
    public String mostrarInformacion() {
        return "Cliente: " + super.mostrarInformacion() + " Dirección: " + direccion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    // permite ordenar los clientes por nombre
    @Override
    public int compareTo(Cliente otro) {
        return nombre.compareToIgnoreCase(otro.nombre);
    }
}
