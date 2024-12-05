/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IUsuarioDAO;
import com.bmd.entities.Usuario;
import com.bmn.dto.UsuarioIniciarSesionDTO;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IInicioSesionBO;
import com.bmn.singletonUsuario.UsuarioST;
import com.bmn.utilerias.Hasher;

/**
 *
 * @author skevi
 */
public class InicioSesionBO implements IInicioSesionBO {
    
    private IUsuarioDAO usuarioDAO;
    private Hasher hasher;

    public InicioSesionBO(IUsuarioDAO usuarioDAO, Hasher hasher) {
        this.usuarioDAO = usuarioDAO;
        this.hasher = hasher;
    }

    @Override
    public void iniciarSesion(UsuarioIniciarSesionDTO usuario) throws BOException {
        validarCamposVacios(usuario);
        buscarCorreo(usuario);
        verificarContrasena(usuario);
    }
    
    public void validarCamposVacios(UsuarioIniciarSesionDTO usuario) throws BOException {
        if(usuario.getCorreo().isEmpty()) {
            throw new BOException("La correo no puede estar vacia.");
        }
        if (usuario.getContrasena().isEmpty()) {
            throw new BOException("La contaseña no puede estar vacia.");
        }
    }
    
    public void buscarCorreo(UsuarioIniciarSesionDTO usuarioDTO) throws BOException {
        try{
            Usuario usuario = usuarioDAO.buscaPorCorreo(usuarioDTO.getCorreo());
            
            UsuarioST.setUsuario(usuario);
            
            //le asignamos la contraseña obtenida al nuevo nuestro dto
            usuarioDTO.setContrasenaObtenida(usuario.getContrasena());
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    public void verificarContrasena(UsuarioIniciarSesionDTO usuario) throws BOException {
        if (!hasher.verifyPassword(usuario.getContrasena(), 
                usuario.getContrasenaObtenida())) {
            throw new BOException("La contaseña no puede estar vacia.");
        }
    }
    
}
