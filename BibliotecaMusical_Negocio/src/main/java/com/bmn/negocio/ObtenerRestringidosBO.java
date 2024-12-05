/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IUsuarioDAO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IObtenerRestringidosBO;
import com.bmn.singletonUsuario.UsuarioST;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class ObtenerRestringidosBO implements IObtenerRestringidosBO {

    private IUsuarioDAO usuarioDAO;

    public ObtenerRestringidosBO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    @Override
    public List<Genero> obtenerRestringidos() throws BOException {
        return procesar();
    }
    
    private List<Genero> procesar() throws BOException {
        try{
            
            ObjectId idUsuario = UsuarioST.getInstance().getId();
            
            List<String> baneados = usuarioDAO.obtenerRestringidos(idUsuario);
            List<Genero> baneadosDTO = new ArrayList<>();
            
            for (String genero : baneados) {
                baneadosDTO.add(Genero.valueOf(genero));
            }
            
            return baneadosDTO;
            
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    } 
    
}
