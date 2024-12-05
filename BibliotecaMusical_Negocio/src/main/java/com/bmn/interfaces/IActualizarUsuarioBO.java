/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bmn.interfaces;

import com.bmn.dto.UsuarioActualizarDTO;
import com.bmn.excepciones.BOException;

/**
 *
 * @author skevi
 */
public interface IActualizarUsuarioBO {
    
    public void ActualizarUsuario(UsuarioActualizarDTO usuario) throws BOException;
    
}
