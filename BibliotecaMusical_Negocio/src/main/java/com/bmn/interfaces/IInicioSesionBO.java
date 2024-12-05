/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmn.interfaces;

import com.bmn.dto.UsuarioDTO;
import com.bmn.dto.UsuarioIniciarSesionDTO;
import com.bmn.excepciones.BOException;

/**
 *
 * @author skevi
 */
public interface IInicioSesionBO {
    
    public void iniciarSesion(UsuarioIniciarSesionDTO usuario) throws BOException;
    
}
