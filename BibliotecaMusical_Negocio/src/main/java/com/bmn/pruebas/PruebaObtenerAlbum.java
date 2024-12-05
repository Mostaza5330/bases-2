/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.AlbumDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerAlbumBO;

/**
 *
 * @author skevi
 */
public class PruebaObtenerAlbum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        ObtenerAlbumBO album = BOFactory.obtenerAlbumFactory();
        
        AlbumDTO albume = album.obtenerAlbum("604c77f2a1b5b7844c8b4571");
        
        System.out.println(albume.toString());
        
    }
    
}
