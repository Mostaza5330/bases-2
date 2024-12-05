/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmd.dao;

import com.bdm.excepciones.DAOException;
import com.bmd.conexionIntefaces.IConexionMongo;
import com.bmd.daoInterfaces.IAlbumDAO;
import com.bmd.entities.Album;
import com.bmd.entities.Usuario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.nin;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class AlbumDAO implements IAlbumDAO {
    
    /**
     * Interfaz de la conexion a mongo
     */
    private IConexionMongo conexion;

    public AlbumDAO(IConexionMongo conexion) {
        this.conexion = conexion;
    }

    /**
     * Busca un album basandose en el id del album, del objeto artista que se 
     * tiene como atributo unicamente se traeria informacion necesaria como:
     * id, nombre y imagen.
     * 
     * @param idAlbum Identificador del album a traer, 
     * @return Retorna un objeto con la informacion del album.
     * @throws DAOException En caso de excepcion en la consulta.
     */
    @Override
    public Album obtenerAlbum(ObjectId idAlbum) throws DAOException {
        try {
            MongoCollection<Album> collection = conexion.getCollection("albumes", Album.class);

            // Proyección para incluir solo los campos necesarios del Artista
            Bson projection = fields(
                include("id", "nombre", "imagen_portada", "fecha_lanzamiento", "genero", "canciones"),
                Projections.computed("artista", fields(include("_id", "nombre", "imagen")))
            );

            // Consulta del álbum por ID
            Album album = collection.find(eq("_id", idAlbum))
                                    .projection(projection)
                                    .first();

            return album;
        } catch (Exception e) {
            throw new DAOException("Error al obtener el álbum", e);
        }
    }


    /**
     * Metodo que devuelve una lista de albumes de manera filtrada por los 
     * parametros del metodo, cada parametro es opcional, por lo que si 
     * es nulo no se considera para la busqueda, este metodo unicamente 
     * trae informacion muestral del album, como nombre e imagen y del objeto
     * artista que tiene como parametro unicamente se trae el nombre del 
     * artista.
     * 
     * El id del usuario que hay como parametro se usa para excluir de la
     * busqueda aquellos albumes que sean del genero que el usuario tiene
     * restringidos.
     * 
     * @param nombre Nombre del album.
     * @param fecha Fecha de lanzamiento del album.
     * @param genero Genero del album.
     * @param idUsuario
     * @return
     * @throws com.bdm.excepciones.DAOException
     */
    @Override
    public List<Album> BuscarPorFiltro(String nombre, LocalDate fecha, String genero, ObjectId idUsuario) throws DAOException {
        try {
            MongoCollection<Album> collection = conexion.getCollection("albumes", Album.class);

            List<Bson> filtros = new ArrayList<>();

            // Añadir filtros opcionales
            if (nombre != null && !nombre.isEmpty()) {
                // Usar una expresión regular para buscar coincidencias parciales en los nombres
                filtros.add(Filters.regex("nombre", ".*" + Pattern.quote(nombre) + ".*", "i"));
            }
            if (fecha != null) {
                filtros.add(eq("fecha_lanzamiento", fecha));
            }
            if (genero != null && !genero.isEmpty()) {
                filtros.add(eq("genero", genero));
            }
            if (idUsuario != null) {
                // Filtro para excluir géneros restringidos por el usuario
                MongoCollection<Usuario> usuarioCollection = conexion.getCollection("usuarios", Usuario.class);
                Usuario usuario = usuarioCollection.find(eq("_id", idUsuario)).first();
                if (usuario != null && usuario.getGenerosRestringidos() != null && !usuario.getGenerosRestringidos().isEmpty()) {
                    filtros.add(nin("genero", usuario.getGenerosRestringidos()));
                }
            }

            // Proyección para incluir solo los campos necesarios del Álbum y del Artista
            Bson projection = fields(
                include("id", "nombre", "imagen_portada"),
                Projections.computed("artista", fields(include("id", "nombre", "imagen")))
            );

            // Consulta con los filtros y la proyección
            List<Album> albumes;
            if (filtros.isEmpty()) {
                // Si no hay filtros, hacer una consulta sin filtros
                albumes = collection.find()
                                    .projection(projection)
                                    .into(new ArrayList<>());
            } else {
                // Consulta con filtros y proyección
                albumes = collection.find(and(filtros))
                                    .projection(projection)
                                    .into(new ArrayList<>());
            }

            return albumes;
        } catch (Exception e) {
            throw new DAOException("Error al buscar álbumes por filtro", e);
        }
    }



    @Override
    public void añadirAlbum(Album album) throws DAOException {
        try {
            MongoCollection<Album> collection = conexion.getCollection("albumes", Album.class);
            collection.insertOne(album);
        } catch (Exception e) {
            throw new DAOException("Error al añadir el álbum", e);
        }
    }


}
