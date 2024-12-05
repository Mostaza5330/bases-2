/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IFavoritoDAO;
import com.bmd.entities.Artista;
import com.bmn.dto.ArtistaVistaDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IObtenerArtistasFavoritosBO;
import com.bmn.singletonUsuario.UsuarioST;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class ObtenerArtistasFavoritosBO implements IObtenerArtistasFavoritosBO {

    private IFavoritoDAO favoritoDAO;

    public ObtenerArtistasFavoritosBO(IFavoritoDAO favoritoDAO) {
        this.favoritoDAO = favoritoDAO;
    }
    
    @Override
    public List<ArtistaVistaDTO> obtenerArtistasFavoritos(Genero genero, LocalDate fecha) throws BOException {
        return procesar(genero, fecha);
    }
    
    private List<ArtistaVistaDTO> procesar(Genero genero, LocalDate fecha) throws BOException{
        try{
            
            String genero1 = genero.name();
            
            ObjectId idUsuario = UsuarioST.getInstance().getId();
            
            List<Artista> artistas = favoritoDAO.obtenerArtistasFavoritos(genero1, fecha, idUsuario);
            List<ArtistaVistaDTO> artistasVistaDTO = new ArrayList<>();
            
            for (Artista artista : artistas) {
                artistasVistaDTO.add(toArtistaVista(artista));
            }
            
            return artistasVistaDTO;
            
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private ArtistaVistaDTO toArtistaVista(Artista artista){
        return new ArtistaVistaDTO(
            artista.getId().toString(),
            artista.getNombre(),
            artista.getImagen()
        );
    }
    
}
