/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.bdm.conexion.ConexionMongo;
import com.bdm.excepciones.DAOException;
import com.bmd.conexionIntefaces.IConexionMongo;
import com.bmd.dao.ArtistaDAO;
import com.bmd.daoInterfaces.IArtistaDAO;
import com.bmd.entities.Artista;
import com.bmd.entities.Integrante;
import com.bmd.entities.Usuario;
import com.mongodb.client.MongoCollection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class pruebasArtista {
    
    public static void main(String[] args) throws DAOException{
        
        ConexionMongo conexionMongo = ConexionMongo.getInstance();
        MongoCollection<Usuario> collection = conexionMongo.getCollection("usuarios", Usuario.class);
        System.out.println("Conexión a MongoDB exitosa: " + (collection != null));
        
        IConexionMongo conexion = new ConexionMongo();
        IArtistaDAO artistaDAO = new ArtistaDAO(conexion);  
  
        Integrante integrante1 = new Integrante();
        integrante1.setNombre("Carlos García");
        integrante1.setFechaIngreso(LocalDate.of(2015, 6, 1));
        integrante1.setFechaSalida(null); // Actualmente en la banda
        integrante1.setRol("Vocalista");
        integrante1.setEstadoActividad(true);

        Integrante integrante2 = new Integrante();
        integrante2.setNombre("Luis Rodríguez");
        integrante2.setFechaIngreso(LocalDate.of(2018, 3, 15));
        integrante2.setFechaSalida(LocalDate.of(2022, 12, 31)); // Ex-integrante
        integrante2.setRol("Guitarrista");
        integrante2.setEstadoActividad(false);

        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add(integrante1);
        integrantes.add(integrante2);

        Artista banda1 = new Artista();
        banda1.setId(new ObjectId("604c77f2a1b5b7844c8b4581")); // ObjectId válido
        banda1.setTipoArtista("BANDA");
        banda1.setNombre("Los Hermanos Flores");
        banda1.setImagen("url_to_banda1_image.jpg");
        banda1.setGenero("Cumbia");
        banda1.setIntegrantes(integrantes);
        banda1.setalbumes(new ArrayList<>());

        Artista banda2 = new Artista();
        banda2.setId(new ObjectId("604c77f2a1b5b7844c8b4582")); // ObjectId válido
        banda2.setTipoArtista("BANDA");
        banda2.setNombre("Grupo Fantasma");
        banda2.setImagen("url_to_banda2_image.jpg");
        banda2.setGenero("Latin Funk");
        banda2.setIntegrantes(integrantes);
        banda2.setalbumes(new ArrayList<>());

        Artista solista1 = new Artista();
        solista1.setId(new ObjectId("604c77f2a1b5b7844c8b4583")); // ObjectId válido
        solista1.setTipoArtista("SOLISTA");
        solista1.setNombre("Juan Gabriel");
        solista1.setImagen("url_to_solista1_image.jpg");
        solista1.setGenero("Ranchera");
        solista1.setIntegrantes(new ArrayList<>());
        solista1.setalbumes(new ArrayList<>());

        Artista solista2 = new Artista();
        solista2.setId(new ObjectId("604c77f2a1b5b7844c8b4584")); // ObjectId válido
        solista2.setTipoArtista("SOLISTA");
        solista2.setNombre("Selena");
        solista2.setImagen("url_to_solista2_image.jpg");
        solista2.setGenero("Afrobeat");
        solista2.setIntegrantes(new ArrayList<>());
        solista2.setalbumes(new ArrayList<>());
 
        
        artistaDAO.añadirArtista(banda1);
        artistaDAO.añadirArtista(banda2);
        artistaDAO.añadirArtista(solista1);
        artistaDAO.añadirArtista(solista2);
        
    }
    
}
