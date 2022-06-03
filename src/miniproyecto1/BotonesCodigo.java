package miniproyecto1;

import java.awt.Color;
import javax.swing.JButton;

/**
 * Clase de botones
 * @author invitado
 */
public class BotonesCodigo extends JButton{
    private int idBoton;
    
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
