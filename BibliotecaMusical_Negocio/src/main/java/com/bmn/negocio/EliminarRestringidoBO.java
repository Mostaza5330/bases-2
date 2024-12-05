/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IUsuarioDAO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IEliminarRestringidoBO;
import com.bmn.singletonUsuario.UsuarioST;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class EliminarRestringidoBO implements IEliminarRestringidoBO {
    
     private IUsuarioDAO usuarioDAO;

    public EliminarRestringidoBO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    @Override
    public void eliminarRestringido(Genero genero) throws BOException {
        verificarRestringido(genero);
    }
    
    private void verificarRestringido(Genero genero) throws BOException{
        try{
            //trensformamos a genero.
            String genero1 = genero.name();
            
            //transformamos a usuario entidad.
            ObjectId usuario = UsuarioST.getInstance().getId();
            
            usuarioDAO.eliminarRestringido(genero1, usuario);
            
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }    
    
}
