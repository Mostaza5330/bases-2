/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import com.bdm.conexion.ConexionMongo;
import com.bdm.excepciones.DAOException;
import com.bmd.conexionIntefaces.IConexionMongo;
import com.bmd.dao.AlbumDAO;
import com.bmd.daoInterfaces.IAlbumDAO;
import com.bmd.entities.Album;
import com.bmd.entities.Artista;
import com.bmd.entities.Usuario;
import com.mongodb.client.MongoCollection;
import java.time.LocalDate;
import java.util.Arrays;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class pruebasAlbum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DAOException {
        ConexionMongo conexionMongo = ConexionMongo.getInstance();
        MongoCollection<Usuario> collection = conexionMongo.getCollection("usuarios", Usuario.class);
        System.out.println("Conexión a MongoDB exitosa: " + (collection != null));
        
        IConexionMongo conexion = new ConexionMongo();
        IAlbumDAO albumDAO = new AlbumDAO(conexion);  
        

        Album album1 = new Album();
        album1.setId(new ObjectId("604c77f2a1b5b7844c8b4571")); // ObjectId válido
        album1.setNombre("Éxitos de Cumbia");
        album1.setImagenPortada("amor_prohibido.jpg");
        album1.setFechaLanzamiento(LocalDate.of(2020, 1, 15));
        album1.setGenero("Cumbia");
        album1.setArtista(new Artista.Builder().setId(new ObjectId("604c77f2a1b5b7844c8b4572")).
                setNombre("the killa").build()); // ObjectId válido
        album1.setCanciones(Arrays.asList("Canción 1", "Canción 2", "Canción 3"));

        Album album2 = new Album();
        album2.setId(new ObjectId("604c77f2a1b5b7844c8b4573")); // ObjectId válido
        album2.setNombre("Clásicos del Rock");
        album2.setImagenPortada("entre_a_mi_mundo.jpg");
        album2.setFechaLanzamiento(LocalDate.of(2018, 10, 5));
        album2.setGenero("Afrobeat");
        album2.setArtista(new Artista.Builder().setId(new ObjectId("604c77f2a1b5b7844c8b4574")).
                setNombre("the strokes").build()); // ObjectId válido
        album2.setCanciones(Arrays.asList("Canción A", "Canción B", "Canción C"));

        
        albumDAO.añadirAlbum(album1);
        albumDAO.añadirAlbum(album2);

    }
    
}
