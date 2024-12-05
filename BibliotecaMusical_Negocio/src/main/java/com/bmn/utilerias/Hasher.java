/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bmn.utilerias;
    
import org.mindrot.jbcrypt.BCrypt;
    
/**
 *
 * @author skevi
 */
public class Hasher {

    /**
     * 
     */
    public Hasher() {
    }
    
    /**
     * Hashes the given password in the parameter and return the hashed chain 
     * in a String type.
     * 
     * @param password Password given by the user int a String type.
     * @return Hashed password in a String type.
     */
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt()); // Genera el hash con un salt aleatorio
    }

    /**
     * Method that verifies that the string of the password matches the 
     * stored hashed password of the user.
     *
     * @param password Password given by the user int a String type.
     * @param storedHash The stored password of the user.
     * @return true if the password matches and false if it did not.
     */
    public boolean verifyPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
}
