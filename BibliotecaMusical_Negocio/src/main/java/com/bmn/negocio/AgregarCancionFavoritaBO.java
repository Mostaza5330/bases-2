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
import com.bmn.interfaces.IAgregarCancionFavoritoBO;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class AgregarCancionFavoritaBO implements IAgregarCancionFavoritoBO {

    IFavoritoDAO favoritoDAO;

    public AgregarCancionFavoritaBO(IFavoritoDAO favoritoDAO) {
        this.favoritoDAO = favoritoDAO;
    }
    
    @Override
    public void agregarCancionFavorita(FavoritoDTO favorito) throws BOException {
        verificarCampos(favorito);
        procesar(favorito);
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
    
    private void procesar(FavoritoDTO favorito) throws BOException{
        try{
        String cancion = favorito.getNombreCancion();
        
        ObjectId idReferencia = new ObjectId(favorito.getIdReferencia());
        
        ObjectId idUsuario = favorito.getIdUsuario();
                
        if (favoritoDAO.verificarCancionFavorita(cancion, idReferencia, idUsuario)) {
            System.out.println("si existe se va a eliminar");
            favoritoDAO.eliminarCancionFavorita(cancion, idUsuario);
            System.out.println("se elimino la cancion");
        }
        else{
            System.out.println("no existe se va a agregar");
            favoritoDAO.agregarFavorito(toFavorito(favorito));
            System.out.println("se agrego favorito");
        }
        
        }catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
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
