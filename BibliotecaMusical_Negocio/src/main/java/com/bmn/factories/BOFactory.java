/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.factories;

import com.bdm.conexion.ConexionMongo;
import com.bmd.dao.AlbumDAO;
import com.bmd.dao.ArtistaDAO;
import com.bmd.dao.FavoritoDAO;
import com.bmd.dao.UsuarioDAO;
import com.bmn.negocio.ActualizarUsuarioBO;
import com.bmn.negocio.AgregarCancionFavoritaBO;
import com.bmn.negocio.AgregarFavoritoBO;
import com.bmn.negocio.AgregarRestringidoBO;
import com.bmn.negocio.EliminarRestringidoBO;
import com.bmn.negocio.InicioSesionBO;
import com.bmn.negocio.ObtenerAlbumBO;
import com.bmn.negocio.ObtenerAlbumesFavoritosBO;
import com.bmn.negocio.ObtenerAlbumesFiltradosBO;
import com.bmn.negocio.ObtenerArtistaBO;
import com.bmn.negocio.ObtenerArtistasFavoritosBO;
import com.bmn.negocio.ObtenerArtistasFiltradosBO;
import com.bmn.negocio.ObtenerCancionesFavoritasBO;
import com.bmn.negocio.ObtenerRestringidosBO;
import com.bmn.negocio.RegistrarUsuarioBO;
import com.bmn.utilerias.Hasher;

/**
 *
 * @author skevi
 */
public class BOFactory {

    /**
     * Es privado para evitar instancias externas
     */
    private BOFactory() {

    }

    //de aqui en adelante se crean fabricas por caso de uso.
    public static ActualizarUsuarioBO actualizarUsuarioFactory() {
        ConexionMongo conexion = new ConexionMongo();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        Hasher hasher = new Hasher();
        return new ActualizarUsuarioBO(usuarioDAO, hasher);
    }

    public static AgregarFavoritoBO agregarFavoritoFactory() {
        ConexionMongo conexion = new ConexionMongo();
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new AgregarFavoritoBO(favoritoDAO);
    }

    public static AgregarRestringidoBO agregarRestringidoFactory() {
        ConexionMongo conexion = new ConexionMongo();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new AgregarRestringidoBO(usuarioDAO, favoritoDAO);
    }

    public static EliminarRestringidoBO eliminarRestringidoFactory() {
        ConexionMongo conexion = new ConexionMongo();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        return new EliminarRestringidoBO(usuarioDAO);
    }

    public static InicioSesionBO inicioSesionFactory() {
        ConexionMongo conexion = new ConexionMongo();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        Hasher hasher = new Hasher();
        return new InicioSesionBO(usuarioDAO, hasher);
    }

    public static ObtenerAlbumBO obtenerAlbumFactory() {
        ConexionMongo conexion = new ConexionMongo();
        AlbumDAO albumDAO = new AlbumDAO(conexion);
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new ObtenerAlbumBO(albumDAO, favoritoDAO);
    }

    public static ObtenerAlbumesFavoritosBO obtenerAlbumesFavoritosFactory() {
        ConexionMongo conexion = new ConexionMongo();
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new ObtenerAlbumesFavoritosBO(favoritoDAO);
    }

    public static ObtenerAlbumesFiltradosBO obtenerAlbumesFiltradosFactory() {
        ConexionMongo conexion = new ConexionMongo();
        AlbumDAO albumDAO = new AlbumDAO(conexion);
        return new ObtenerAlbumesFiltradosBO(albumDAO);
    }

    public static ObtenerArtistaBO obtenerArtistaFacory() {
        ConexionMongo conexion = new ConexionMongo();
        ArtistaDAO artistaDAO = new ArtistaDAO(conexion);
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new ObtenerArtistaBO(artistaDAO, favoritoDAO);
    }

    public static ObtenerArtistasFavoritosBO obtenerArtistasFavoritosFactory() {
        ConexionMongo conexion = new ConexionMongo();
        ArtistaDAO artistaDAO = new ArtistaDAO(conexion);
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new ObtenerArtistasFavoritosBO(favoritoDAO);
    }

    public static ObtenerArtistasFiltradosBO obtenerArtistasFiltradosFactory() {
        ConexionMongo conexion = new ConexionMongo();
        ArtistaDAO artistaDAO = new ArtistaDAO(conexion);
        return new ObtenerArtistasFiltradosBO(artistaDAO);
    }

    public static ObtenerCancionesFavoritasBO obtenerCancionesFavoritosFactory() {
        ConexionMongo conexion = new ConexionMongo();
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new ObtenerCancionesFavoritasBO(favoritoDAO);
    }

    public static ObtenerRestringidosBO obtenerRestringidosFactory() {
        ConexionMongo conexion = new ConexionMongo();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        return new ObtenerRestringidosBO(usuarioDAO);
    }

    public static RegistrarUsuarioBO registrarUsuarioFactory() {
        ConexionMongo conexion = new ConexionMongo();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        Hasher hasher = new Hasher();
        return new RegistrarUsuarioBO(usuarioDAO, hasher);
    }

    public static AgregarCancionFavoritaBO agregarCancionFavoritaFactory() {
        ConexionMongo conexion = new ConexionMongo();
        FavoritoDAO favoritoDAO = new FavoritoDAO(conexion);
        return new AgregarCancionFavoritaBO(favoritoDAO);
    }
    
}
