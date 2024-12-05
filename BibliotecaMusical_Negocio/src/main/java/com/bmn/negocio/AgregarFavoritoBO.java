/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IFavoritoDAO;
import com.bmd.entities.Favorito;
import com.bmn.dto.FavoritoDTO;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IAgregarFavoritoBO;
import com.bmn.singletonUsuario.UsuarioST;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class AgregarFavoritoBO implements IAgregarFavoritoBO {
    
    private IFavoritoDAO favoritoDAO;

    public AgregarFavoritoBO(IFavoritoDAO favoritoDAO) {
        this.favoritoDAO = favoritoDAO;
    }

    @Override
    public boolean agregarFavorito(FavoritoDTO favorito) throws BOException {
        verificarCampos(favorito);
        return verificarFavorito(favorito);
    }
    
    private void verificarCampos(FavoritoDTO favorito)throws BOException {
        if (favorito.getIdUsuario() == null) {
            throw new BOException("La referencia al usuario no puede estar vacia.");
        }
        if (favorito.getIdReferencia().isEmpty()) {
            throw new BOException("La referencia no puede estar vacia.");
        }
        if (favorito.getTipo().name().isEmpty()) {
            throw new BOException("El tipo no puede estar vacio.");
        }
        if (favorito.getFechaAgregacion() == null) {
            throw new BOException("La fecha de agregacion no puede estar vacia.");
        }
    }
    
    private boolean verificarFavorito(FavoritoDTO favoritoDTO) throws BOException {
        try{
            //transformo de dto a entidad.
            Favorito favorito = toFavorito(favoritoDTO);
            
            ObjectId idReferencia = favorito.getIdReferencia();
            
            //transformamos al dto a entidad al usuario.
            ObjectId idUsuario = UsuarioST.getInstance().getId();
            
            // si no existe dentro de los favoritos del usuario 
            // lo guardamos
            if (favoritoDAO.isFavorito(idReferencia, idUsuario)) { 
                //si no esta en los favoritos del usuario lo eliminamos.
                String tipo = favorito.getTipo();
                
                System.out.println("se va a elimianar");
                favoritoDAO.eliminarFavorito(idReferencia, idUsuario, tipo); 
                System.out.println("se elimiano");
            }
            else{
                
                System.out.println("se va a agregar");
                
                favoritoDAO.agregarFavorito(favorito); 
                return true; //retornamos verdadero porque se agrego.
                
            }
        }catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
        return false;
    }

    private Favorito toFavorito(FavoritoDTO favoritoDTO){
        Favorito favorito = new Favorito.Builder().
                setIdUsuario(favoritoDTO.getIdUsuario()).
                setIdReferencia(new ObjectId(favoritoDTO.getIdReferencia())).
                setNombreCancion((favoritoDTO.getNombreCancion() == null) ? null : favoritoDTO.getNombreCancion()).
                setTipo(favoritoDTO.getTipo().name()).
                setFechaAgregacion(favoritoDTO.getFechaAgregacion()).
                build();
        return favorito;
    }
    
}
