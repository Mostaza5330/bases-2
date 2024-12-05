/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.negocio;

import com.bdm.excepciones.DAOException;
import com.bmd.daoInterfaces.IArtistaDAO;
import com.bmd.entities.Artista;
import com.bmn.dto.ArtistaVistaDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.interfaces.IObtenerArtistasFiltradosBO;
import com.bmn.singletonUsuario.UsuarioST;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class ObtenerArtistasFiltradosBO implements IObtenerArtistasFiltradosBO {

    private IArtistaDAO artistaDAO;

    public ObtenerArtistasFiltradosBO(IArtistaDAO artistaDAO) {
        this.artistaDAO = artistaDAO;
    }
    
    /**
     * 
     * @param nombre
     * @param genero
     * @return
     * @throws BOException 
     */
    @Override
    public List<ArtistaVistaDTO> obtenerArtistasFiltrados(String nombre, Genero genero) throws BOException {
        return procesar(nombre, genero);
    }
    
    
    private List<ArtistaVistaDTO> procesar(String nombre, Genero genero) throws BOException {
        try{
            
            String genero1 = (genero == null) ? null : genero.name();
            
            String nombre1 = (nombre == null || nombre.isEmpty()) ? null : nombre;
            
            ObjectId idUsuario = new ObjectId("675115941049164060addf81");
            
            List<Artista> artistas = artistaDAO.buscarPorFiltro(nombre1, genero1, idUsuario);
            List<ArtistaVistaDTO> artistasDTO = new ArrayList<>();
            
            for (Artista artista : artistas) {
                artistasDTO.add(toArtistaVista(artista));
            }
            
            return artistasDTO;
            
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
