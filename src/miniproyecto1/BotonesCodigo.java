/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproyecto1;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author invitado
 */
public class BotonesCodigo extends JButton{
    private int idBoton;
    
    public BotonesCodigo(int idBoton){
        this.idBoton = idBoton;
    }
    
    public int getID(){
        return idBoton;
    }
}
