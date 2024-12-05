/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.ObtenerRestringidosBO;
import java.util.List;

/**
 *
 * @author skevi
 */
public class PruebaObtenerRestringidos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            ObtenerRestringidosBO restringidos = BOFactory.obtenerRestringidosFactory();

            List<Genero> generos = restringidos.obtenerRestringidos();

            for (Genero genero : generos) {
                System.out.println(genero.name());
            }
        
        }
        catch(BOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
