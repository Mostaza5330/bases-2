/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.dto;

import com.bmn.dto.constantes.Rol;
import java.time.LocalDate;

/**
 *
 * @author skevi
 */
public class IntegranteDTO {
   
    private final String nombre;
    private final LocalDate fechaIngreso;
    private final LocalDate fechaSalida;
    private final Rol rol;
    private final boolean estadoActividad;

    private IntegranteDTO(Builder builder) {
        this.nombre = builder.nombre;
        this.fechaIngreso = builder.fechaIngreso;
        this.fechaSalida = builder.fechaSalida;
        this.rol = builder.rol;
        this.estadoActividad = builder.estadoActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isEstadoActividad() {
        return estadoActividad;
    }
    
    @Override
    public String toString() {
        return "Integrante{" +
               "nombre='" + nombre + '\'' +
               ", fechaIngreso=" + fechaIngreso +
               ", fechaSalida=" + fechaSalida +
               ", rol=" + rol +
               ", estadoActividad=" + estadoActividad +
               '}';
    }

    public static class Builder {
        private String nombre;
        private LocalDate fechaIngreso;
        private LocalDate fechaSalida;
        private Rol rol;
        private boolean estadoActividad;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
            return this;
        }

        public Builder setFechaSalida(LocalDate fechaSalida) {
            this.fechaSalida = fechaSalida;
            return this;
        }

        public Builder setRol(Rol rol) {
            this.rol = rol;
            return this;
        }

        public Builder setEstadoActividad(boolean estadoActividad) {
            this.estadoActividad = estadoActividad;
            return this;
        }

        public IntegranteDTO build() {
            return new IntegranteDTO(this);
        }
    }

}
