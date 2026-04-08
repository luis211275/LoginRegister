package org.example;

import org.example.conection.ConnectionBBDD;

public class Main {
    static void main() {
        try {
            ConnectionBBDD.getConnection();
            System.out.println("Conexión correcta a PostgreSQL.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        Usuario usuario = new Usuario();

        // Llama a la función para traer los usuarios
        usuario.listarUsuarios();
        // Creamos un objeto Usuario para usar sus métodos.
        usuario.insertarUsuario(1,"Luis", "Lopez-Nuño", "luis456","18", "544545D", "luis@gmail.com", "362581458");
        usuario.insertarUsuario(2,"Ana", "Sanchez", "ana456", "40", "5465545D", "ana@gmail.com", "3698752");
        usuario.insertarUsuario(3,"Juan", "Marcos", "juan456", "50", "5465995D", "Juan@gmail.com", "999999");

        // llama al metodo borrar por nombre
        usuario.deleteByName("Ana"); // Comentarlo cuando quieras probar el update o buscar otro nombre
        // Actualizar Usuario
        usuario.actualizarUsuario("Juan","JOHN");

    }
}