/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.bmn.pruebas;

import com.bmn.dto.FavoritoDTO;
import com.bmn.dto.constantes.Genero;
import com.bmn.dto.constantes.Tipo;
import com.bmn.excepciones.BOException;
import com.bmn.factories.BOFactory;
import com.bmn.negocio.AgregarCancionFavoritaBO;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class PruebaAgregarCancionFavorita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        
        AgregarCancionFavoritaBO agregar = BOFactory.agregarCancionFavoritaFactory();
        
        FavoritoDTO favorito1 = new FavoritoDTO.Builder().
            setIdUsuario(new ObjectId("675115941049164060addf81")).
            setIdReferencia("604c77f2a1b5b7844c8b4571").
            setTipo(Tipo.CANCION).
            setGenero(Genero.Afrobeat).
            setNombreCancion("Canción 2"). // No aplica para álbumes
            setFechaAgregacion(LocalDate.of(2024, 3, 20)).
            build();
        
        agregar.agregarCancionFavorita(favorito1);
        
    }
    
}
