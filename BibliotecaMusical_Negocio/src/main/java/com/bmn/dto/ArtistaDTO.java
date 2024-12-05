
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.dto;

import com.bmn.dto.constantes.Genero;
import com.bmn.dto.constantes.TipoArtista;
import java.util.List;

/**
 *
 * @author skevi
 */
public class ArtistaDTO {
    
    private String id;
    private TipoArtista tipoArtista;
    private String nombre;
    private String imagen;
    private Genero genero;
    private List<IntegranteDTO> integrantes;
    private List<AlbumMuestraDTO> albumes;
    private boolean favorito;

    private ArtistaDTO(Builder builder) {
        this.id = builder.id;
        this.tipoArtista = builder.tipoArtista;
        this.nombre = builder.nombre;
        this.imagen = builder.imagen;
        this.genero = builder.genero;
    }

    public String getId() {
        return id;
    }

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public Genero getGenero() {
        return genero;
    }

    public List<IntegranteDTO> getIntegrantes() {
        return integrantes;
    }

    public List<AlbumMuestraDTO> getalbumes() {
        return albumes;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return "id: " + id + 
                ", tipoArtista: " + tipoArtista + 
                ", nombre: " + nombre + 
                ", imagen: " + imagen + 
                ", genero: " + genero +
                ", favorito: " + favorito;
    }
    
    public static class Builder {
        private String id;
        private TipoArtista tipoArtista;
        private String nombre;
        private String imagen;
        private Genero genero;
        private List<IntegranteDTO> integrantes;
        private List<AlbumMuestraDTO> albumes;
        
        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setTipoArtista(TipoArtista tipoArtista){
            this.tipoArtista = tipoArtista;
            return this;
        }
        
        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setImagen(String imagen) {
            this.imagen = imagen;
            return this;
        }

        public Builder setGenero(Genero genero) {
            this.genero = genero;
            return this;
        }

        public Builder setIntegrantes(List<IntegranteDTO> integrantes) {
            this.integrantes = integrantes;
            return this;
        }

        public Builder setalbumes(List<AlbumMuestraDTO> albumes) {
            this.albumes = albumes;
            return this;
        }

        public ArtistaDTO build() {
            return new ArtistaDTO(this);
        }
    }


    public static class AlbumMuestraDTO {
        
        private String id;
        private String nombre;
        private String imagen_portada;

        public AlbumMuestraDTO(String id, String nombre, String imagen_portada) {
            this.id = id;
            this.nombre = nombre;
            this.imagen_portada = imagen_portada;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getImagen_portada() {
            return imagen_portada;
        }

        public void setImagen_portada(String imagen_portada) {
            this.imagen_portada = imagen_portada;
        }
        
    }
    
    
}
