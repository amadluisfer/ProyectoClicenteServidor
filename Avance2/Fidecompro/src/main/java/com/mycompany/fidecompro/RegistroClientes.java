package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// guarda los clientes y permite agregarlos, eliminarlos y buscarlos por nombre o identificación
public class RegistroClientes implements Serializable {

    // HashMap que guarda los clientes usando su id como llave para encontrarlos rápido
    private Map<Integer, Cliente> clientes;

    public RegistroClientes() {
        clientes = new HashMap<>();
    }

    // no deja registrar dos clientes con la misma identificación
    public void agregar(Cliente cliente) {
        if (existeIdentificacion(cliente.getIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un cliente con la identificación indicada.");
        }
        clientes.put(cliente.getId(), cliente);
    }

    public void eliminar(Cliente cliente) {
        clientes.remove(cliente.getId());
    }

    public boolean existeIdentificacion(String identificacion) {
        for (Cliente c : clientes.values( )) {
            if (c.getIdentificacion().equals(identificacion)) {
                
                return true;
            }
        }
        
        return false;
    }

    // buscar4 un cliente por su cédula completa, si no existe lanza la excepción
    public Cliente buscarPorIdentificacion(String identificacion) throws RegistroNoEncontradoException {
        for (Cliente c : clientes.values()) {
            if (c.getIdentificacion(). equals(identificacion)) {
                
                return c;
            }
        }
        throw new RegistroNoEncontradoException("No existe un cliente con la identificación " + identificacion + ".");
    }

    // busca por parte del nombre o de la identificacion, no discrmiina las mayusculas
    public List<Cliente> buscar(String texto) {
        List<Cliente> resultado = new ArrayList<>();
        
        String t = texto.toLowerCase();
        for (Cliente c : clientes.values()) {
            if (c.getNombre().toLowerCase().contains(t) || c.getIdentificacion().contains(t)) {
                resultado.add(c);
            }
        }
        // se ordenan por nombre con una expresión lambda
        Collections.sort(resultado);
        return resultado;
    }

    //// devuelve todos los clientes ordenados por nombre, reutilizando buscar con texto vacío
    public List<Cliente> listar() {
        return buscar("");
    }
}
