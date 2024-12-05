/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.AlbumVistaDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerAlbumesFavoritosBO;
import java.util.List;

/**
 *
 * @author skevi
 */
public class PruebaObtenerAlbumesFavoritos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        
        ObtenerAlbumesFavoritosBO favoritos = BOFactory.obtenerAlbumesFavoritosFactory();
        
        List<AlbumVistaDTO> albumes = favoritos.obtenerAlbumesFavoritos(null, null);
        
        for (AlbumVistaDTO album : albumes) {
            System.out.println(album.toString());
        }
        
    }
    
}
