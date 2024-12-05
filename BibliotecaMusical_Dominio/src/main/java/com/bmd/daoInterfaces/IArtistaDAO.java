/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmd.daoInterfaces;

import com.bdm.excepciones.DAOException;
import com.bmd.entities.Artista;
import java.util.List;
import org.bson.types.ObjectId;


/**
 *
 * @author skevi
 */
public interface IArtistaDAO {
    
    public void añadirArtista(Artista artista) throws DAOException;
    
    public Artista buscarPorId(ObjectId idArtista) throws DAOException;
    
    public List<Artista> buscarPorFiltro(String nombre, String genero, ObjectId idUsuario) 
            throws DAOException;
            
}
