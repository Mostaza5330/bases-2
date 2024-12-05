/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import com.bdm.conexion.ConexionMongo;
import com.bdm.excepciones.DAOException;
import com.bmd.conexionIntefaces.IConexionMongo;
import com.bmd.dao.UsuarioDAO;
import com.bmd.daoInterfaces.IUsuarioDAO;
import com.bmd.entities.Usuario;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DAOException {
        
        ConexionMongo conexionMongo = ConexionMongo.getInstance();
        MongoCollection<Usuario> collection = conexionMongo.getCollection("usuarios", Usuario.class);
        System.out.println("Conexión a MongoDB exitosa: " + (collection != null));
            
        IConexionMongo conexion = new ConexionMongo();
        IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);  
        
    Usuario usuario1 = new Usuario.Builder()
        .setId(new ObjectId("604c77f2a1b5b7844c8b4561"))
        .setNombre("Juan Pérez")
        .setCorreo("juan.perez@example.com")
        .setContrasena("password123")
        .setImagenPerfil("url_to_image1.jpg")
        .setFavoritos(new ArrayList<>())
        .build();

    Usuario usuario2 = new Usuario.Builder()
        .setId(new ObjectId("604c77f2a1b5b7844c8b4562"))
        .setNombre("María López")
        .setCorreo("maria.lopez@gmail.com")
        .setContrasena("password456")
        .setImagenPerfil("url_to_image2.jpg")
        .setFavoritos(new ArrayList<>())
        .build();

    Usuario usuario3 = new Usuario.Builder()
        .setId(new ObjectId("604c77f2a1b5b7844c8b4563"))
        .setNombre("Carlos Gómez")
        .setCorreo("carlos.gomez@example.com")
        .setContrasena("password789")
        .setImagenPerfil("url_to_image3.jpg")
        .setFavoritos(new ArrayList<>())
        .setGenerosRestringidos(Arrays.asList("Afrobeat"))
        .build();
    
//        usuarioDAO.eliminarRestringido("Afrobeat", new ObjectId("675115941049164060addf81"));
//
//        usuarioDAO.añadirUsuario(usuario1);
//        usuarioDAO.añadirUsuario(usuario2);
//        usuarioDAO.añadirUsuario(usuario3);

        
    }
    
}
