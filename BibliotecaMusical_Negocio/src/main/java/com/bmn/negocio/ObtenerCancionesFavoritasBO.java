/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IFavoritoDAO;
import com.bmd.entities.Favorito;
import com.bmn.dto.FavoritoDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.dto.constantes.Tipo;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IObtenerCancionesFavoritasBO;
import com.bmn.singletonUsuario.UsuarioST;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class ObtenerCancionesFavoritasBO implements IObtenerCancionesFavoritasBO {

     private IFavoritoDAO favoritoDAO;

    public ObtenerCancionesFavoritasBO(IFavoritoDAO favoritoDAO) {
        this.favoritoDAO = favoritoDAO;
    }
    
    @Override
    public List<FavoritoDTO> obtenerCancionesFavoritas(Genero genero, LocalDate fecha) throws BOException {
        return procesar(genero, fecha);
    }
    
    private List<FavoritoDTO> procesar(Genero genero, LocalDate fecha) throws BOException {
        try {
            ObjectId idUsuario = UsuarioST.getInstance().getId();
            String genero1 = (genero == null) ? null : genero.name();
            LocalDate local = (fecha == null) ? null : fecha;

            System.out.println("Genero: " + genero1);
            System.out.println("Fecha: " + local);

            List<Favorito> canciones = favoritoDAO.obtenerCancionesFavoritas(genero1, local, idUsuario);

            if (canciones == null || canciones.isEmpty()) {
                System.out.println("La lista de canciones es nula o vacía.");
                return null;
            }

            List<FavoritoDTO> cancionesDTO = new ArrayList<>();
            for (Favorito cancion : canciones) {
                cancionesDTO.add(toFavoritoDTO(cancion));
            }

            return cancionesDTO;
        } catch (DAOException ex) {
            throw new BOException("Error al procesar las canciones favoritas: " + ex.getMessage(), ex);
        }
    }

    
    private FavoritoDTO toFavoritoDTO(Favorito favoritoDTO){
        FavoritoDTO favorito = new FavoritoDTO.Builder().
                setIdUsuario(favoritoDTO.getIdUsuario()).
                setIdReferencia(favoritoDTO.getIdReferencia().toString()).
                setNombreCancion((favoritoDTO.getNombreCancion() == null) ? null : favoritoDTO.getNombreCancion()).
                setTipo(Tipo.valueOf(favoritoDTO.getTipo())).
                setGenero(Genero.valueOf(favoritoDTO.getGenero())).
                setFechaAgregacion(favoritoDTO.getFechaAgregacion()).
                build();
        return favorito;
    }
    
}

