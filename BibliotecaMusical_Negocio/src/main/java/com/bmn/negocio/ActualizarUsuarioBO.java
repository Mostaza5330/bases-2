/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IUsuarioDAO;
import com.bmd.entities.Usuario;
import com.bmn.dto.UsuarioActualizarDTO;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IActualizarUsuarioBO;
import com.bmn.singletonUsuario.UsuarioST;
import com.bmn.utilerias.Hasher;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class ActualizarUsuarioBO implements IActualizarUsuarioBO{
    
    private IUsuarioDAO usuarioDAO;
    private Hasher hasher;

    public ActualizarUsuarioBO(IUsuarioDAO usuarioDAO, Hasher hasher) {
        this.usuarioDAO = usuarioDAO;
        this.hasher = hasher;
    }

    /**
     * Este es el metodo principal de la clase aqui se verifica cada uno de
     * los pasos echos para actualizar el usuario.
     * 
     * @param usuario
     * @throws BOException 
     */
    @Override
    public void ActualizarUsuario(UsuarioActualizarDTO usuario) throws BOException {
        verificarCamposVacios(usuario);
        verificarCompatibilidadContraseña(usuario);
        verificarFormatoCorreo(usuario);
        verificarDisponibilidadCorreo(usuario);
        hashearContrasena(usuario);
        agregarId(usuario);
        procesarActualizarUsuario(usuario);
    }
    
    private void verificarCamposVacios(UsuarioActualizarDTO usuario) throws BOException {
        if (usuario.getNombre().isEmpty()) {
            throw new BOException("Porfavor rellene el campo de 'Nombre'");
        }
        if (usuario.getCorreo().isEmpty()) {
            throw new BOException("Porfavor rellene el campo de 'Correo'");
        }
        if (usuario.getContrasena().isBlank()) {
            throw new BOException("Porfavor rellene el campo de 'Correo'");
        }
        if (usuario.getContrasenaConfirmar().isEmpty()) {
            throw new BOException("Porfavor rellene el campo de confirmacion "
                    + "del correo");
        }
        if (usuario.getImagenPerfil().isEmpty()) {
            throw new BOException("Porfavor rellene el campo de 'Correo'");
        }
    }
    
    private void verificarCompatibilidadContraseña(UsuarioActualizarDTO usuario) throws BOException {
        if (!usuario.getContrasena().equalsIgnoreCase(usuario.getContrasenaConfirmar())) {
            throw new BOException("Las contraseñas no coinciden");
        }
    }
    
    private void verificarFormatoCorreo(UsuarioActualizarDTO usuario) throws BOException {
        if (!usuario.getCorreo().matches("^[\\w.%+-]+@gmail\\.com$")) {
            throw new BOException("Se debe de incluir @gmail al "
                    + "final del correo");
        }
    }
    
    private void verificarDisponibilidadCorreo(UsuarioActualizarDTO usuario) throws BOException {
        try{
            if (!usuario.getCorreo().equalsIgnoreCase(UsuarioST.getInstance().getCorreo())) {
                if (usuarioDAO.verificarExistenciaCorreo(usuario.getCorreo())) {
                    throw new BOException("El correo proporcionado ya se encuentra "
                            + "ocupado por otro usuario");
                }
            }
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private void hashearContrasena(UsuarioActualizarDTO usuario) throws BOException {
        
        String contrasena = usuario.getContrasena();
        
        usuario.setContrasena(hasher.hashPassword(contrasena));
        
    }
    
    private void agregarId (UsuarioActualizarDTO usuario) throws BOException {
        ObjectId id = UsuarioST.getInstance().getId();
        
        usuario.setId(id.toString());
    }
    
    private void procesarActualizarUsuario(UsuarioActualizarDTO usuarioDTO) throws BOException  {
        try{
            //convertimos de dto a entidad
            Usuario usuario = convertirDTO(usuarioDTO);
            
            //guardamos en la persistencia
            usuarioDAO.actualizarUsuario(usuario);
            
            reasignarUsuarioSingletone(usuario);
            
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private Usuario convertirDTO(UsuarioActualizarDTO usuarioDTO){
        Usuario usuario = new Usuario.Builder().
                setId(new ObjectId(usuarioDTO.getId())).
                setNombre(usuarioDTO.getNombre()).
                setCorreo(usuarioDTO.getCorreo()).
                setContrasena(usuarioDTO.getContrasena()).
                setImagenPerfil(usuarioDTO.getImagenPerfil()).
                build();
        return usuario;
    }
    
    public void reasignarUsuarioSingletone(Usuario usuario){
        UsuarioST.setUsuario(usuario);
    }
    
}
