 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IUsuarioDAO;
import com.bmd.entities.Usuario;
import com.bmn.dto.UsuarioRegistrarDTO;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IRegistrarUsuarioBO;
import com.bmn.utilerias.Hasher;

/**
 *
 * @author skevi
 */
public class RegistrarUsuarioBO implements IRegistrarUsuarioBO{

    private IUsuarioDAO usuarioDAO;
    private Hasher hasher;

    public RegistrarUsuarioBO(IUsuarioDAO usuarioDAO, Hasher hasher) {
        this.usuarioDAO = usuarioDAO;
        this.hasher = hasher;
    }
    
    @Override
    public void registrarUsuario(UsuarioRegistrarDTO usuario) throws BOException {
        validarCamposVacios(usuario);
        verificarFormatoCorreo(usuario);
        validarExistenciaCorreo(usuario);
        validarContrasena(usuario);
        hashearContrasena(usuario);
        procesarRegistrarUsuario(usuario);
    }
    
    private void validarCamposVacios(UsuarioRegistrarDTO usuario) throws BOException {
        if (usuario.getNombre().isEmpty()) {
            throw new BOException("Porfavor escriba un nombre antes de continuar");
        }
        if (usuario.getCorreo().isEmpty()) {
            throw new BOException("Porfavor escriba un correo antes de continuar");
        }
        if (usuario.getContrasena().isEmpty()) {
            throw new BOException("El campo de contraseña no puede estar vacio");
        }
        if (usuario.getContrasenaConfirmar().isEmpty()) {
            throw new BOException("el campo de confirmacion de contraseña no puede estar vacio");
        }
    }
    
    private void verificarFormatoCorreo(UsuarioRegistrarDTO usuario) throws BOException {
        if (!usuario.getCorreo().matches("^[\\w.%+-]+@gmail\\.com$")) {
            throw new BOException("Se debe de incluir @gmail al "
                    + "final del correo");
        }
    }
    
    private void validarExistenciaCorreo(UsuarioRegistrarDTO usuario) throws BOException {
        try{
            if (usuarioDAO.verificarExistenciaCorreo(usuario.getCorreo())) {
                throw new BOException("La correo ya se encuentra ocupada intente con otra");
            }
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private void validarContrasena(UsuarioRegistrarDTO usuario) throws BOException {
        if (!usuario.getContrasena().equalsIgnoreCase(usuario.getContrasenaConfirmar())) {
            throw new BOException("Las contraseñas no coinciden");
        }
    }
    
    private void hashearContrasena(UsuarioRegistrarDTO usuario) throws BOException {
        
        String contrasena = usuario.getContrasena();
        
        usuario.setContrasena(hasher.hashPassword(contrasena));
        
    }
    
    private void procesarRegistrarUsuario(UsuarioRegistrarDTO usuarioDTO) throws BOException {
        try{
            //convertimos a dto
            Usuario usuario = toUsuario(usuarioDTO);
            
            //añadimos al usuario
            usuarioDAO.añadirUsuario(usuario);
            
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private Usuario toUsuario(UsuarioRegistrarDTO usuarioDTO){
        Usuario usuario = new Usuario.Builder().
                setNombre(usuarioDTO.getNombre()).
                setCorreo(usuarioDTO.getCorreo()).
                setContrasena(usuarioDTO.getContrasena()).
                setImagenPerfil(usuarioDTO.getImagenPerfil()).
                build();
        return usuario;
    }
    
}
