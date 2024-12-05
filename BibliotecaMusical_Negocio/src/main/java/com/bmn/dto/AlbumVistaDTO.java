/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.dto;

/**
 *
 * @author skevi
 */
public class AlbumVistaDTO {
    
    private String id; 
    private String nombre;
    private String imagen;
    private ArtistaVistaDTO artistaVista;

    public AlbumVistaDTO(String id, String nombre, String imagen, 
            ArtistaVistaDTO artistaVista) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.artistaVista = artistaVista;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArtistaVistaDTO getArtistaVista() {
        return artistaVista;
    }

    public void setArtistaVista(ArtistaVistaDTO artistaVista) {
        this.artistaVista = artistaVista;
    }

    @Override
    public String toString() {
        return "AlbumVistaDTO{" + "id=" + id + ", nombre=" + nombre + 
                ", imagen=" + imagen + ", artistaVista=" + artistaVista + '}';
    }
    
}
