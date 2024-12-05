package com.bmn.negocio;

import com.bmd.entities.Artista;
import com.bmd.entities.Album;
import com.bdm.conexion.ConexionMongo;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public class NegocioArtistas {

    private MongoCollection<Document> collectionArtista;
    private MongoCollection<Document> collectionAlbum;

    public NegocioArtistas() {
        ConexionMongo conexion = ConexionMongo.getInstance();
        collectionArtista = conexion.getCollection("artistas");
        collectionAlbum = conexion.getCollection("albumes");
    }

    public void insertarArtistasYalbumes(List<Artista> artistas, List<Album> albumes) {
        // Imprimir el tamaño de las listas
        System.out.println("Número de artistas: " + artistas.size());
        System.out.println("Número de álbumes: " + albumes.size());

        // Imprimir los nombres de los artistas
        for (Artista artista : artistas) {
            System.out.println("Artista: " + artista.getNombre());
        }

        // Imprimir los nombres de los álbumes
        for (Album album : albumes) {
            System.out.println("Álbum: " + album.getNombre());
        }

        // Inserta los artistas en MongoDB
        for (Artista artista : artistas) {
            Document document = new Document()
                .append("_id", artista.getId()) // Utilizar el ObjectId generado
                .append("tipoArtista", artista.getTipoArtista())
                .append("nombre", artista.getNombre())
                .append("imagen", artista.getImagen())
                .append("genero", artista.getGenero());
            collectionArtista.insertOne(document);
            System.out.println("Artista insertado: " + artista.getNombre());
        }

        // Inserta los álbumes en MongoDB
        for (Album album : albumes) {
            try {
                Document document = new Document()
                    .append("_id", album.getId()) // Utilizar el ObjectId generado
                    .append("nombre", album.getNombre())
                    .append("imagenPortada", album.getImagenPortada())
                    .append("fechaLanzamiento", album.getFechaLanzamiento())
                    .append("genero", album.getGenero())
                    .append("artista", new Document("_id", album.getArtista().getId()) // Utilizar el ObjectId del artista generado
                                         .append("tipoArtista", album.getArtista().getTipoArtista())
                                         .append("nombre", album.getArtista().getNombre())
                                         .append("imagen", album.getArtista().getImagen())
                                         .append("genero", album.getArtista().getGenero()))
                    .append("canciones", album.getCanciones());
                collectionAlbum.insertOne(document);
                System.out.println("Álbum insertado: " + album.getNombre());
            } catch (Exception e) {
                System.err.println("Error al insertar álbum: " + album.getNombre() + " - " + e.getMessage());
            }
        }

        System.out.println("Inserción de artistas solistas y álbumes completada exitosamente");
    }
}
