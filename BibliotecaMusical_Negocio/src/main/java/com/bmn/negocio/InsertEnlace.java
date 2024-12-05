package com.bmn.negocio;

import com.bdm.inserciones.InsertaAlbumes;
import com.bdm.inserciones.InsertArtistas;
import com.bmd.entities.Artista;
import com.bmd.entities.Album;

import java.util.List;

public class InsertEnlace {

    public static void main(String[] args) {
        // Crear instancia de InsertArtistas y obtener la lista de artistas
        InsertArtistas insertArtistas = new InsertArtistas();
        List<Artista> artistas = insertArtistas.crearArtistas();

        // Crear instancia de InsertaAlbumes y obtener la lista de álbumes
        InsertaAlbumes InsertaAlbumes = new InsertaAlbumes();
        List<Album> albumes = InsertaAlbumes.crearAlbums(artistas);

        // Crear instancia de NegocioArtistas para manejar las inserciones
        NegocioArtistas negocioArtistas = new NegocioArtistas();
        negocioArtistas.insertarArtistasYalbumes(artistas, albumes);
    }
}
