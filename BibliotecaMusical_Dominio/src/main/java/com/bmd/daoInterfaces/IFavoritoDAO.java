/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmd.daoInterfaces;

import com.bdm.excepciones.DAOException;
import com.bmd.entities.Album;
import com.bmd.entities.Artista;
import com.bmd.entities.Favorito;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public interface IFavoritoDAO {
    
    public void agregarFavorito(Favorito favorito) throws DAOException;
    
    public boolean isFavorito(ObjectId idReferencia, ObjectId idUsuario) 
            throws DAOException;
    
    public boolean verificarCancionFavorita(String nombreCancion, 
            ObjectId idReferencia, ObjectId idUsuario) throws DAOException;
    
    public void eliminarFavorito(ObjectId idReferencia, ObjectId idUsuario, String tipo) 
            throws DAOException;
    
    public void eliminarFavoritoPorGenero(String genero, ObjectId idUsuario) 
            throws DAOException;
    
    public List<Artista> obtenerArtistasFavoritos(String genero, 
            LocalDate fechaAgregacion, ObjectId idUsuario) throws DAOException;
    
    public List<String> obtenerCancionesFavoritas(String genero, 
            LocalDate fechaAgregacion, ObjectId idUsuario) throws DAOException;
    
    public List<Album> obtenerAlbumesFavoritos(String genero, 
            LocalDate fechaAgregacion, ObjectId idUsuario) throws DAOException;
    
    public void eliminarCancionFavorita(String nombreCancion, 
            ObjectId idUsuario) throws DAOException;
    
}
