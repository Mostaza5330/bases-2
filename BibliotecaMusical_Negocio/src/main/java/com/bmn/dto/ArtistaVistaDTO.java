/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.dto;

/**
 *
 * @author skevi
 */
public class ArtistaVistaDTO {
    
    private String id;
    private String nombre;
    private String imagen;
    private AlbumVistaDTO albumVista;

    public ArtistaVistaDTO(String nombre) {
        this.nombre = nombre;
    }

    public ArtistaVistaDTO(String id, String nombre, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public ArtistaVistaDTO(String id, String nombre, String imagen, 
            AlbumVistaDTO albumVista) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.albumVista = albumVista;
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

    public AlbumVistaDTO getAlbumVista() {
        return albumVista;
    }

    public void setAlbumVista(AlbumVistaDTO albumVista) {
        this.albumVista = albumVista;
    }

    @Override
    public String toString() {
        return "ArtistaVistaDTO{" + "id=" + id + ", nombre=" + nombre + 
                ", imagen=" + imagen + ", albumVista=" + albumVista + '}';
    }
    
}
