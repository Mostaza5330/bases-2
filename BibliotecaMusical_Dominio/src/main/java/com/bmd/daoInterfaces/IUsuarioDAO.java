/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmd.daoInterfaces;

import com.bdm.excepciones.DAOException;
import com.bmd.entities.Usuario;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public interface IUsuarioDAO {
    
    public void añadirUsuario(Usuario usuario) throws DAOException;
    
    public Usuario buscaPorCorreo(String correo) throws DAOException;
    
    public boolean verificarExistenciaCorreo(String correo) throws DAOException;
    
    public void actualizarUsuario(Usuario usuario) throws DAOException;
    
    public void añadirRestringido(String genero, ObjectId idUsuario) throws DAOException;
    
    public void eliminarRestringido(String genero, ObjectId idUsuario) throws DAOException;
    
    public List<String> obtenerRestringidos(ObjectId idUsuario) throws DAOException;
    
    public boolean verificarExistenciaRestringido(String genero, ObjectId idUsuario) throws DAOException;
    
}
