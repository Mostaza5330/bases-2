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
import com.bmn.negocio.AgregarFavoritoBO;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author skevi
 */
public class PruebaAgregarFavorito {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        AgregarFavoritoBO favorito = BOFactory.agregarFavoritoFactory();
        
        
        FavoritoDTO favorito2 = new FavoritoDTO.Builder().
        setIdUsuario(new ObjectId("674fc2f4f736902f3ddd3e5f")).
        setIdReferencia("674fc2f4f736902f3ddd3e5f").
        setTipo(Tipo.ALBUM).
        setGenero(Genero.Pop).
        setNombreCancion(null). // No aplica para álbumes
        setFechaAgregacion(LocalDate.of(2022, 3, 20)).
                build();
        
        favorito.agregarFavorito(favorito2);
        
    }
    
}
