package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// guarda los usuarios del sistema y valida el inicio de sesión
public class RegistroUsuarios implements Serializable {

    // la llave es el nombre de usuario
    private Map<String, Usuario> usuarios;

    public RegistroUsuarios() {
        usuarios = new HashMap<>();
    }

    public void agregar(Usuario usuario) {
        usuarios.put(usuario.getNombreUsuario(), usuario);
    }

    // busca el usuario por la llave del mapa y valida la contraseña, si falla lanza la excepción
    public Usuario iniciarSesion(String nombreUsuario, String contrasena) throws CredencialesInvalidasException {
        Usuario u = usuarios.get(nombreUsuario);
        if (u == null || !u.validarCredenciales(nombreUsuario, contrasena)) {
            throw new CredencialesInvalidasException("Usuario o contraseña incorrectos.");
        }
        return u;
    }
}
