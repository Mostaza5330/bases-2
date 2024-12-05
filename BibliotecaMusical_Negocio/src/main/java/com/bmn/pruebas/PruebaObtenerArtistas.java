/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.ArtistaDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerArtistaBO;

/**
 *
 * @author skevi
 */
public class PruebaObtenerArtistas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        
        ObtenerArtistaBO obtener = BOFactory.obtenerArtistaFacory();
        
        ArtistaDTO artista = obtener.ObtenerArtista("604c77f2a1b5b7844c8b4583");
        
        System.out.println(artista.toString());
        
    }
    
}
