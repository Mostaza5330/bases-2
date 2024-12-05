/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmd.daoInterfaces;

import com.bdm.excepciones.DAOException;
import com.bmd.entities.Album;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public interface IAlbumDAO {
    
    public void añadirAlbum(Album album) throws DAOException;
    
    public Album obtenerAlbum(ObjectId idAlbum) throws DAOException;
    
    public List<Album>  BuscarPorFiltro(String nombre, LocalDate fecha, 
            String genero, ObjectId idUsuario) throws DAOException;
    
}
