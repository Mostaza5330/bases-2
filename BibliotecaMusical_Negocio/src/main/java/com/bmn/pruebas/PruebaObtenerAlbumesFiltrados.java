/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.AlbumVistaDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerAlbumesFiltradosBO;
import java.util.List;

/**
 *
 * @author skevi
 */
public class PruebaObtenerAlbumesFiltrados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        
        ObtenerAlbumesFiltradosBO obtener = BOFactory.obtenerAlbumesFiltradosFactory();
        
        List<AlbumVistaDTO> albumes = obtener.BuscarPorFiltro("cumbia", null, null);
        
        for (AlbumVistaDTO album : albumes) {
            System.out.println(album.toString());
        }
        
    }
    
}
