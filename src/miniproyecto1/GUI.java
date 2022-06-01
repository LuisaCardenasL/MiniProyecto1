package miniproyecto1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author invitado
 */
public class GUI extends JFrame {
    private JButton bInicio;
    private BotonesCodigo bCodigo[];
    private JPanel pOeste, pEste, pBotones, pCentro, pBotonInicio, pPuntuacion;
    private JLabel lNivel, lEstado, lPuntuacion, lImagen;
    private JTextField tfPuntos;
    private ArrayList<Integer> codigo;
    
    public GUI()
    {
        initComponents();
        codigo = new ArrayList<>();
        //contruccion del frame
        setTitle("Juego Abrir Caja");
        setSize(600,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initComponents(){
        bInicio = new JButton("Iniciar");
        bCodigo = new BotonesCodigo[12];
        
        lEstado = new JLabel("estado aqui");
        lNivel = new JLabel("nivel aqui");
        lPuntuacion = new JLabel("Puntuacion: ");
        lImagen = new JLabel(new ImageIcon("src/imagenes/cajaFuerte.png"));
        
        tfPuntos = new JTextField();
        
        pEste = new JPanel(new BorderLayout());
        pOeste = new JPanel(new BorderLayout());
        pBotones = new JPanel(new GridLayout(4,3,3,3));
        pCentro = new JPanel(new GridLayout(1,2));
        pBotonInicio = new JPanel(new FlowLayout());
        pPuntuacion = new JPanel (new GridLayout(1,2));
        
        for (int i =0; i < bCodigo.length; i++)
        {
            bCodigo[i] = new BotonesCodigo(i);
            pBotones.add(bCodigo[i]);
            
            if ( 0<=i && i<3){
                bCodigo[i].setBackground(new Color(93, 173, 226));
            }
            if ( 3<=i && i<6){
                bCodigo[i].setBackground(new Color(231, 76, 60));
            }
            if ( 6<=i && i<9){
                bCodigo[i].setBackground(new Color(241, 196, 15));
            }
            if ( 9<=i && i<12){
                bCodigo[i].setBackground(new Color(39, 174, 96));
            }
        }
        
        
        pBotonInicio.add(bInicio);
        
        pPuntuacion.add(lPuntuacion);
        pPuntuacion.add(tfPuntos);
        
        pOeste.add(pBotonInicio, BorderLayout.NORTH);
        pOeste.add(lImagen, BorderLayout.CENTER);
        
        pEste.add(pPuntuacion, BorderLayout.NORTH);
        pEste.add(pBotones, BorderLayout.CENTER);
        
        pCentro.add(pOeste);
        pCentro.add(pEste);
        
        //escuchas
        ManejaEvento evento = new ManejaEvento();
        bInicio.addActionListener(evento);
        bCodigo[11].addActionListener(evento);
        
        
        add(lNivel, BorderLayout.NORTH);
        add(pCentro,BorderLayout.CENTER);
        add(lEstado, BorderLayout.SOUTH);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI obj = new GUI();
    }
    
    public void limpiarGUI (){
    }
    
    class ManejaEvento implements ActionListener{
        int puntos, topeCodigo;
        boolean pulsoBien;
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bInicio){
                puntos = 0;
                topeCodigo = 11;
                
                int aleatorio = (int)(Math.random()*topeCodigo);
                codigo.add(aleatorio);
                
                //desactivar boton iniciar
                bInicio.setEnabled(false);
                
                //iniciar juego
                juego(2);
            } if (e.getSource() == bCodigo){
                pulsoBien = false;
                String oprimido = e.getActionCommand();
                for (int i = 0; i < bCodigo.length; i++){
                    if (oprimido.equals(bCodigo[i].getText())){
                        pulsoBien = true;
                        break;
                    }
                }
                if(pulsoBien){
                    puntos += 100;
                    tfPuntos.setText(""+puntos);
                }
            }
        } //fin actionPerformed
        
        public boolean validarJugada (int codigoJugado){
            for (int i = 0; i < codigo.size(); i++){
                if (codigo.get(i) == codigoJugado){
                    try {
                        if(codigo.get(i) <= 0 && codigo.get(i) < 3){
                            bCodigo[i].setBackground(new Color (174, 214, 241));
                            Thread.sleep(1000);
                            bCodigo[i].setBackground(null);
                            return true;
                        }if(codigo.get(i) <= 3 && codigo.get(i) < 6){
                            bCodigo[i].setBackground(new Color (250, 219, 216));
                            Thread.sleep(1000);
                            bCodigo[i].setBackground(null);
                            return true;
                        }if(codigo.get(i) <= 6 && codigo.get(i) < 9){
                            bCodigo[i].setBackground(new Color (252, 243, 207));
                            Thread.sleep(1000);
                            bCodigo[i].setBackground(null);
                            return true;
                        }if(codigo.get(i) <= 9 && codigo.get(i) < 12){
                            bCodigo[i].setBackground(new Color (213, 245, 227));
                            Thread.sleep(1000);
                            bCodigo[i].setBackground(null);
                            return true;
                        }
                        return true;
                    } catch (InterruptedException ex) {
                    }
                }
            }//fin for
            return false;
        }
        
        public void juego (int segundos){
            Timer timer = new Timer();
            
            //tarea
            TimerTask tarea = new TimerTask() {
                int codigoJugado;
                boolean marcoMal = false;
                
                @Override
                public void run() {
                    if(pulsoBien == false){
                    }
                }
            };//cierra el timer
        }
    }
    
}
