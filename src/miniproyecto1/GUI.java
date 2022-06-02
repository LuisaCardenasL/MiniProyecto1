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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ArrayList<Integer> codigoJugadaPc, jugadaUsuario;
    
    public GUI()
    {
        initComponents();
        codigoJugadaPc = new ArrayList<>();
        jugadaUsuario = new ArrayList<>();
        //contruccion del frame
        setTitle("Juego Abrir Caja");
        setSize(600,300);
        setResizable(false);
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
        codigoJugadaPc.clear();
        jugadaUsuario.clear();
    }
    
    class ManejaEvento implements ActionListener{
        int puntos, topeCodigo, bandera, aux, contadorJugadaPc, nivel = 5;
        boolean pulsoBien;
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bInicio){
                
                //desactivar boton iniciar
                bInicio.setEnabled(false);
                
                //iniciar juego
                juego(2);
            } else {
                int botonOprimido = ((BotonesCodigo)e.getSource()).getID();
                jugadaUsuario.add(botonOprimido);
                
                //if si las jugadas estan dentro de las del nivel,  nivel <=
                //else si ya llego al tope, ya hizo las 5 jugadas del nivel, entonces pasa siguiente nivel, //borrar jugadas usuario y no borrar las del pc, y nivel +5
                    if(codigoJugadaPc.size() == jugadaUsuario.size()){
                        //hacer la validacion de los arreglos posicion a posicion
                            //y si cumplio: +100 puntos, borra jugadaUsuario, y llamar metodo jugar
                            //else : oprimio el que no era reiniar todas las variables
                    }  
              
            /*if (e.getSource() == bCodigo){
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
                }}*/
            }
        } //fin actionPerformed
        
        public boolean validarJugada (int codigoJugado){
            for (int i = 0; i < codigoJugadaPc.size(); i++){
                if (codigoJugadaPc.get(i) == codigoJugado){
                    try {
                        if(codigoJugadaPc.get(i) <= 0 && codigoJugadaPc.get(i) < 3){
                            bCodigo[i].setBackground(new Color (174, 214, 241));
                            Thread.sleep(1000);
                            bCodigo[i].setBackground(null);
                            return true;
                        }if(codigoJugadaPc.get(i) <= 3 && codigoJugadaPc.get(i) < 6){
                            bCodigo[i].setBackground(new Color (250, 219, 216));
                            Thread.sleep(1000);
                            bCodigo[i].setBackground(null);
                            return true;
                        }if(codigoJugadaPc.get(i) <= 6 && codigoJugadaPc.get(i) < 9){
                            bCodigo[i].setBackground(new Color (252, 243, 207));
                            Thread.sleep(1000);
                            bCodigo[i].setBackground(null);
                            return true;
                        }if(codigoJugadaPc.get(i) <= 9 && codigoJugadaPc.get(i) < 12){
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
                
                @Override
                public void run() {
                    puntos = 0;
                    topeCodigo = 11;
                
                    int aleatorio = (int)(Math.random()*topeCodigo);
                    codigoJugadaPc.add(aleatorio);
                    contadorJugadaPc++;
                    
                    for (int i = 0; i< codigoJugadaPc.size(); i++){
                        if(i >= 0 && i < 3){
                            try {
                                bCodigo[i].setBackground(new Color (174, 214, 241));
                                Thread.sleep(1000);
                                bCodigo[i].setBackground(new Color(93, 173, 226));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }if(i >= 3 && i < 6){
                            try {
                                bCodigo[i].setBackground(new Color (250, 219, 216));
                                Thread.sleep(1000);
                                bCodigo[i].setBackground(null);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }if(i >= 6 && i < 9){
                            try {
                                bCodigo[i].setBackground(new Color (252, 243, 207));
                                Thread.sleep(1000);
                                bCodigo[i].setBackground(null);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }if(i >= 9 && i < 12){
                            try {
                                bCodigo[i].setBackground(new Color (213, 245, 227));
                                Thread.sleep(1000);
                                bCodigo[i].setBackground(null);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            };//cierra el timer
            timer.schedule(tarea,10 );
        }
    }
    
}
