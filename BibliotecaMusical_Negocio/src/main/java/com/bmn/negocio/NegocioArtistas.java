package com.bmn.negocio;

import com.bdm.conexion.ConexionMongo;
import com.bdm.excepciones.DAOException;
import com.bmd.entities.Artista;
import com.bmd.entities.Album;
import com.bmd.dao.AlbumDAO;
import com.bmd.dao.ArtistaDAO;
import com.bmn.excepciones.BOException;

import java.util.List;

public class NegocioArtistas {

    private AlbumDAO albumDAO;
    private ArtistaDAO artistaDAO;

    public NegocioArtistas() {
        ConexionMongo conexion = ConexionMongo.getInstance();
        this.albumDAO = new AlbumDAO(conexion);
        this.artistaDAO = new ArtistaDAO(conexion);
    }

    public void insertarArtistasYalbumes(List<Artista> artistas, List<Album> albumes) throws BOException{
        try{
        // Imprimir el tamaño de las listas
        System.out.println("Número de artistas: " + artistas.size());
        System.out.println("Número de álbumes: " + albumes.size());

//        // Imprimir los nombres de los artistas
//        for (Artista artista : artistas) {
//            System.out.println("Artista: " + artista.getNombre());
//        }

//        // Imprimir los nombres de los álbumes
//        for (Album album : albumes) {
//            System.out.println("Álbum: " + album.getNombre());
//        }

        // Inserta los artistas en MongoDB
        for (Artista artista : artistas) {
            artistaDAO.añadirArtista(artista);
        }

        // Inserta los álbumes en MongoDB
        for (Album album : albumes) {
            albumDAO.añadirAlbum(album);
        }

        System.out.println("Inserción de artistas solistas y álbumes completada exitosamente");
        }
        catch(DAOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
