/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.dao.FavoritoDAO;
import com.bmd.daoInterfaces.IArtistaDAO;
import com.bmd.entities.Album;
import com.bmd.entities.Artista;
import com.bmd.entities.Integrante;
import com.bmn.dto.ArtistaDTO;
import com.bmn.dto.ArtistaDTO.AlbumMuestraDTO;
import com.bmn.dto.IntegranteDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.dto.constantes.Rol;
import com.bmn.dto.constantes.TipoArtista;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IObtenerArtistaBO;
import com.bmn.singletonUsuario.UsuarioST;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class ObtenerArtistaBO implements IObtenerArtistaBO {
    
    private IArtistaDAO artistaDAO;
    private FavoritoDAO favoritoDAO;

    public ObtenerArtistaBO(IArtistaDAO artistaDAO, FavoritoDAO favoritoDAO) {
        this.artistaDAO = artistaDAO;
        this.favoritoDAO = favoritoDAO;
    }

    @Override
    public ArtistaDTO ObtenerArtista(String idArtista) throws BOException {
        verificarCampos(idArtista);
        return procesar(idArtista);
    }
    
    private void verificarCampos(String idArtista) throws BOException {
        if (idArtista.isEmpty()) {
            throw new BOException("El id no puede estar vacio.");
        }
    }
    
    private ArtistaDTO procesar(String idArtista) throws BOException {
        try{
            //obtenemos el artista 
            Artista artista = artistaDAO.buscarPorId(new ObjectId(idArtista));
            
            //obtenemos el id del usuario registrado
            ObjectId idUsuario = UsuarioST.getInstance().getId();
            
            boolean favorito = verificarFavorito(new ObjectId(idArtista), idUsuario);
            
            ArtistaDTO artistaDTO = toArtistaDTO(artista);
            artistaDTO.setFavorito(favorito);
            return artistaDTO;
            
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private boolean verificarFavorito(ObjectId idArtista, ObjectId idUsuario) throws BOException {
        try{
            if (favoritoDAO.isFavorito(idArtista, idUsuario)) {
                return true;
            }
            else{
                return false;
            }
        }
        catch(DAOException ex){
            throw new BOException(ex.getMessage());
        }
    }
    
    private ArtistaDTO toArtistaDTO(Artista artista){
        ArtistaDTO artistaDTO = new ArtistaDTO.Builder().
                setId(artista.getNombre()).
                setTipoArtista(TipoArtista.valueOf(artista.getTipoArtista())).
                setNombre(artista.getNombre()).
                setImagen(artista.getImagen()).
                setGenero(Genero.valueOf(artista.getGenero())).
                setIntegrantes(toIntegranteDTO(artista.getIntegrantes())).
                setalbumes(toAlbumMuestraDTO(artista.getalbumes())).
                build();
        return artistaDTO;
    }
    
    private List<IntegranteDTO> toIntegranteDTO(List<Integrante> integrantes){
        List<IntegranteDTO> integrantesDTO = new ArrayList<>();
        
        for (Integrante integrante1 : integrantes) {
            IntegranteDTO integranteDTO = new IntegranteDTO.Builder().
                    setNombre(integrante1.getNombre()).
                    setFechaIngreso(integrante1.getFechaIngreso()).
                    setFechaSalida(integrante1.getFechaSalida()).
                    setRol(Rol.valueOf(integrante1.getRol())).
                    setEstadoActividad(integrante1.isEstadoActividad()).
                    build();
            integrantesDTO.add(integranteDTO);
        }
        return integrantesDTO;
    }
    
    private List<AlbumMuestraDTO> toAlbumMuestraDTO(List<Album> albumes){
        List<AlbumMuestraDTO> albumMuestaDTO = new ArrayList<>();
        
        for (Album album : albumes) {
            AlbumMuestraDTO albumMuestra = 
                    new AlbumMuestraDTO(album.getId().toString(), album.getNombre(), 
                            album.getImagenPortada());
            albumMuestaDTO.add(albumMuestra);
        }
        return albumMuestaDTO;
    }
    
}
