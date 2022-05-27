package miniproyecto1;

import java.util.Random;

/**
 *
 * @author invitado
 */
public class Codigo {
    private int arCodigo;
    
    public int getCodigo(){
        /*
            Método que genera valor aleatorio a los números de la lista del código.
        */
        Random aleatorio = new Random();
        arCodigo = aleatorio.nextInt(12);
        return arCodigo;
    }    
    
}
