/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Exceptions;

/**
 *
 * @author omarb
 */
public class PatronNotFoundException extends RuntimeException{
    
    public PatronNotFoundException(String message) {
        super(message);
    }
}
