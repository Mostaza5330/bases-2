/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmd.dao;

import com.bdm.excepciones.DAOException;
import com.bmd.conexionIntefaces.IConexionMongo;
import com.bmd.daoInterfaces.IUsuarioDAO;
import com.bmd.entities.Usuario;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class UsuarioDAO implements IUsuarioDAO {
    
    private IConexionMongo conexion;

    public UsuarioDAO(IConexionMongo conexion) {
        this.conexion = conexion;
    }

    /**
     * Metodo para agregar un usuario 
     * 
     * @param usuario
     * @throws DAOException 
     */
    @Override
    public void añadirUsuario(Usuario usuario) throws DAOException {
        try {
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);
            collection.insertOne(usuario);
        } catch (Exception e) {
            throw new DAOException("Error al añadir el usuario", e);
        }
    }


    /**
     * Metodo para buscar un usuario por correo
     * 
     * @param correo
     * @return
     * @throws DAOException 
     */
    @Override
    public Usuario buscaPorCorreo(String correo) throws DAOException {
        try {
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);
            Usuario usuario = collection.find(eq("correo", correo)).first();
            return usuario;
        } catch (Exception e) {
            throw new DAOException("Error al buscar el usuario por correo", e);
        }
    }

    /**
     * Verifica si el correo existe dentro de la base de datos.
     * 
     * @param correo
     * @return
     * @throws DAOException 
     */
    @Override
    public boolean verificarExistenciaCorreo(String correo) throws DAOException {
        try {
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);
            long count = collection.countDocuments(eq("correo", correo));
            return count > 0;
        } catch (Exception e) {
            throw new DAOException("Error al verificar la existencia del correo", e);
        }
    }


    /**
     * Metodo que actualiza el usuario.
     * 
     * @param usuario
     * @throws DAOException 
     */
    @Override
    public void actualizarUsuario(Usuario usuario) throws DAOException {
        try {
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);
            collection.replaceOne(eq("_id", usuario.getId()), usuario);
        } catch (Exception e) {
            throw new DAOException("Error al actualizar el usuario", e);
        }
    }


    /**
     * Añade un genero restringido a la lista de restringidos del usuario.
     * 
     * @param genero
     * @param idUsuario
     * @throws DAOException 
     */
    @Override
    public void añadirRestringido(String genero, ObjectId idUsuario) throws DAOException {
        try {
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);
            collection.updateOne(eq("_id", idUsuario), 
                                 Updates.addToSet("restringidos", genero));
        } catch (Exception e) {
            throw new DAOException("Error al añadir el género restringido", e);
        }
    }


    /**
     * Elimina un genero restringido de la lista de restringidos del usuario
     * 
     * @param genero
     * @param idUsuario
     * @throws DAOException 
     */
    @Override
    public void eliminarRestringido(String genero, ObjectId idUsuario) throws DAOException {
        try {
            // Validación de Parámetros
            if (genero == null || genero.isEmpty()) {
                throw new DAOException("El parámetro género no puede ser nulo o vacío");
            }
            if (idUsuario == null) {
                throw new DAOException("El parámetro idUsuario no puede ser nulo");
            }

            // Obtener la colección de usuarios
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);

            // Construir el filtro para encontrar al usuario
            Bson filtro = eq("_id", idUsuario);

            // Construir la actualización para eliminar el género de la lista "restringidos"
            Bson update = Updates.pull("restringidos", genero);

            // Realizar la actualización
            UpdateResult result = collection.updateOne(filtro, update);
            System.out.println("Result Modified Count: " + result.getModifiedCount());

            // Verificar si se modificó algún documento
            if (result.getModifiedCount() == 0) {
                throw new DAOException("No se encontró el género restringido especificado para el usuario");
            }

            System.out.println("Género restringido eliminado exitosamente: " + genero);

        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
            throw new DAOException("Error al eliminar el género restringido", e);
        }
    }



    /**
     * Obtiene una lista de todos los generos baneados por el usuario.
     * 
     * @param idUsuario
     * @return
     * @throws DAOException 
     */
    @Override
    public List<String> obtenerRestringidos(ObjectId idUsuario) throws DAOException {
        try {
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);
            Usuario usuario = collection.find(eq("_id", idUsuario)).first();
            if (usuario == null) {
                throw new DAOException("Usuario no encontrado");
            }
            return usuario.getGenerosRestringidos();
        } catch (Exception e) {
            throw new DAOException("Error al obtener los géneros restringidos", e);
        }
    }

    /**
     * Verifica si el genero dado en el parametro ya existe dentro de la 
     * lista de restringidos del usuario.
     * 
     * @param genero
     * @param idUsuario
     * @return 
     * @throws com.bdm.excepciones.DAOException 
     */
    @Override
    public boolean verificarExistenciaRestringido(String genero, ObjectId idUsuario) throws DAOException {
        try {
            MongoCollection<Usuario> collection = conexion.getCollection("usuarios", Usuario.class);
            Usuario usuario = collection.find(eq("_id", idUsuario)).first();
            if (usuario == null) {
                throw new DAOException("Usuario no encontrado");
            }

            // Verificar si la lista de géneros restringidos no es nula
            List<String> generosRestringidos = usuario.getGenerosRestringidos();
            if (generosRestringidos == null || generosRestringidos.isEmpty()) {
                // Si la lista es nula o está vacía, retornamos false indicando que el género no existe en la lista
                return false;
            }

            for (String generoRestringido : generosRestringidos) {
                if (generoRestringido.equalsIgnoreCase(genero)) {
                    return true;
                }
            }
            
        } catch (Exception e) {
            throw new DAOException("Error al verificar la existencia del género restringido", e);
        }
        return false;
    }




    
}
