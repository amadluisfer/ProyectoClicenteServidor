package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        try {
            // si no hay archivo todavia se empieza con los datos de prueba
            if (!ArchivoDatos.cargar()) {
                Datos.cargarDatosDePrueba();
                ArchivoDatos.guardar();
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo de datos, se cargan los datos de prueba.");
            Datos.cargarDatosDePrueba();
        }
        // lo primero que se muestra es el inicio de sesión
        new VentanaLogin().setVisible(true);
    }
}
