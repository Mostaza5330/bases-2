/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.constantes.Genero;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.EliminarRestringidoBO;

/**
 *
 * @author skevi
 */
public class PruebaEliminarRestringido {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        EliminarRestringidoBO restringido = BOFactory.eliminarRestringidoFactory();
        
        restringido.eliminarRestringido(Genero.Afrobeat);
        
        }
        catch(BOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
