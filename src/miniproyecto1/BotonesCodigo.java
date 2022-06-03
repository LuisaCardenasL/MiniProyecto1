package miniproyecto1;

import java.awt.Color;
import javax.swing.JButton;

/**
 * @author Luisa Maria Cardenas Lopez - 1823494 - cardenas.luisa@correounivalle.edu.co
 * @author Alejandro Tapiero Triana - 202043737 - alejandro.tapiero@correounivalle.edu.co
 */
public class BotonesCodigo extends JButton{
    private int idBoton;
    
    /**
     * Constructor de la clase
     * @param idBoton 
     */
    public BotonesCodigo(int idBoton){
        this.idBoton = idBoton;
    }
    
    /**
     * Metodo que nos sirve para obtener cual es el identificador del boton
     * @return identificador del boton
     */
    public int getID(){
        return idBoton;
    }
}
