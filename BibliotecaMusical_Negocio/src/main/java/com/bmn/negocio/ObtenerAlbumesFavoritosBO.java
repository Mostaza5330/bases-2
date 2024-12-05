/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IFavoritoDAO;
import com.bmd.entities.Album;
import com.bmn.dto.AlbumVistaDTO;
import com.bmn.dto.ArtistaVistaDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IObtenerAlbumesFavoritosBO;
import com.bmn.singletonUsuario.UsuarioST;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class ObtenerAlbumesFavoritosBO implements IObtenerAlbumesFavoritosBO {
   
    private IFavoritoDAO favoritoDAO;

    public ObtenerAlbumesFavoritosBO(IFavoritoDAO favoritoDAO) {
        this.favoritoDAO = favoritoDAO;
    }

    
    @Override
    public List<AlbumVistaDTO> obtenerAlbumesFavoritos(Genero genero, LocalDate fechaAgregacion) throws BOException {
        return procesar(genero, fechaAgregacion);
    }
    
    //aqui obtendras y convertiras los albumes
    private List<AlbumVistaDTO> procesar(Genero genero, LocalDate fechaAgregacion) throws BOException {
        try{
           
            //obtenemos el id del usuario logeado
            ObjectId idUsuario = UsuarioST.getInstance().getId();
            
            String genero1 = (genero == null) ? null : genero.name();
            
            LocalDate fecha = (fechaAgregacion == null) ? null : fechaAgregacion;
            
            //lista traida de la base de datos;
            List<Album> albumes = favoritoDAO.obtenerAlbumesFavoritos(genero1, fechaAgregacion, idUsuario);
            List<AlbumVistaDTO> albumesDTO = new ArrayList<>();
            
            for (Album album : albumes) {
                albumesDTO.add(toAlbumvistaDTO(album));
            }
            
            return albumesDTO;
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private AlbumVistaDTO toAlbumvistaDTO(Album album){
        return new AlbumVistaDTO(
            album.getId().toString(),
            album.getNombre(),
            album.getImagenPortada(),
                new ArtistaVistaDTO(
                    album.getArtista().getNombre()
                )
        );
    }
    
}
