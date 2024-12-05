/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.ArtistaVistaDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerArtistasFiltradosBO;
import java.util.List;

/**
 *
 * @author skevi
 */
public class PruebaObtenerArtistasFiltrados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        
        ObtenerArtistasFiltradosBO filtro = BOFactory.obtenerArtistasFiltradosFactory();
        
        List<ArtistaVistaDTO> artistas = filtro.obtenerArtistasFiltrados("", Genero.Cumbia);
        
        for (ArtistaVistaDTO artista : artistas) {
            System.out.println(artista.toString());
        }
    }
    
}
