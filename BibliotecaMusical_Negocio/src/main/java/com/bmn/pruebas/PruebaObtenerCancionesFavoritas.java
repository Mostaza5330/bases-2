/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.CancionDTO;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerCancionesFavoritasBO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author skevi
 */
public class PruebaObtenerCancionesFavoritas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        ObtenerCancionesFavoritasBO favoritas = BOFactory.obtenerCancionesFavoritosFactory();
        
        List<CancionDTO> canciones = favoritas.obtenerCancionesFavoritas(null, LocalDate.of(2024, 1, 25));
        
        for (CancionDTO cancion : canciones) {
            System.out.println(cancion.toString());
        }
    }
    
}
