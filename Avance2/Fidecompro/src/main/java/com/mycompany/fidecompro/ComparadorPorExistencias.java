package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.util.Comparator;

// ordena de menos a mas existencias
public class ComparadorPorExistencias implements Comparator<Producto> {

    // se usa en la lista de productos bajo el mínimo, los más urgentes quedan arriba
    @Override
    public int compare(Producto a, Producto b) {
        return Integer.compare(a.getExistencias(), b.getExistencias());
    }
}
