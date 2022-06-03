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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * 
 * @author Luisa Maria Cardenas Lopez - 1823494
 * @author Alejandro Tapiero Triana - 202043737
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
        setSize(700,400);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initComponents(){
        bInicio = new JButton("Iniciar");
        bCodigo = new BotonesCodigo[12];
        
        lEstado = new JLabel("");
        lNivel = new JLabel("");
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
                bCodigo[i].setBackground(new Color(69, 90, 100));
            }
            if ( 3<=i && i<6){
                bCodigo[i].setBackground(new Color(40, 116, 166));
            }
            if ( 6<=i && i<9){
                bCodigo[i].setBackground(new Color(155, 89, 182));
            }
            if ( 9<=i && i<12){
                bCodigo[i].setBackground(new Color(236, 64, 122));
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
        for(int i = 0; i<bCodigo.length; i++){
            bCodigo[i].addActionListener(evento);
        }
        
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
    
    /**
     * Metodo para limpiar la GUI y dejarla como se encontraba inicialmente antes de jugar
     */
    public void limpiarGUI (){
        codigoJugadaPc.clear();
        jugadaUsuario.clear();
        tfPuntos.setText("");
        lNivel.setText("");
        lEstado.setText("");
    }
    
    /**
     * Clase manejadora de los eventos de la ventana
     */
    class ManejaEvento implements ActionListener{
        int puntos = 0, topeCodigo = 0, bandera = 0, nivel=1, contadorJugadaPc = 0, rondasNivel = 5;
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
                if(bandera < rondasNivel){
                    lNivel.setText("Nivel: "+nivel);
                    if(codigoJugadaPc.size() == jugadaUsuario.size()){
                        //hacer la validacion de los arreglos posicion a posicion
                        if(codigoJugadaPc.equals(jugadaUsuario)==true){
                            //y si cumplio: +100 puntos, borra jugadaUsuario, y llamar metodo jugar
                            
                                bandera++;
                                lNivel.setText("Nivel: "+nivel);
                                lEstado.setText("Muy bien sigue así, recuerda que el codigo tiene una longitud de "+(contadorJugadaPc+1)+" botones");
                                jugadaUsuario.clear();
                                puntos += 100;
                                tfPuntos.setText(""+puntos);
                                lImagen.setIcon(new ImageIcon("src/imagenes/gif.gif"));
                                Timer ponerImagen = new Timer();
                                TimerTask imagen = new TimerTask() {
                                    @Override
                                    public void run() {
                                        lImagen.setIcon(new ImageIcon("src/imagenes/cajaFuerte.png"));
                                    }
                                        
                                };//cierra timer imagen
                                
                                ponerImagen.schedule(imagen, 250);
                                juego(2);
                            
                        } else{
                            //else : oprimio el que no era reiniar todas las variables
                            JOptionPane.showMessageDialog(null, "Perdiste :(" +
                                                          "\nTus puntos obtenidos fueron: "+puntos+
                                                          "\nNivel alcanzado: "+ nivel +
                                                          "\nLas cantidad de rondas que acertaste fueron: " + (contadorJugadaPc-1));
                            limpiarGUI();
                            bandera=0;
                            puntos = 0;
                            nivel=1; 
                            contadorJugadaPc = 0; 
                            rondasNivel = 5;
                            bInicio.setEnabled(true);
                        }  
                    } //else si ya llego al tope, ya hizo las 5 jugadas del nivel, entonces pasa siguiente nivel, //borrar jugadas usuario y no borrar las del pc, y nivel +5 
                } else if(bandera == rondasNivel){
                    nivel ++;
                    lNivel.setText("Nivel: "+nivel);
                    JOptionPane.showMessageDialog(null, "Pasaste al nivel: "+nivel);
                    puntos += 1000;
                    tfPuntos.setText(""+puntos);
                    
                    jugadaUsuario.clear();
                    bandera = 0;
                }
            }
        } //fin actionPerformed
        
        /**
         * Metodo para realizar el funcionamiento del juego de la pc
         * crea el codigo para el juego e ilumina los codigos
         * @param segundos 
         */
        public void juego (int segundos){
            Timer timer = new Timer();
            
            //tarea
            TimerTask tarea = new TimerTask() {
                
                @Override
                public void run() {
                    topeCodigo = 11;
                
                    int aleatorio = (int)(Math.random()*topeCodigo);
                    codigoJugadaPc.add(aleatorio);
                    contadorJugadaPc++;
                    System.out.println(codigoJugadaPc);
                    
                    for (int i = 0; i< codigoJugadaPc.size(); i++){
                        if(codigoJugadaPc.get(i) >= 0 && codigoJugadaPc.get(i) < 3){
                            try {
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color (176, 190, 197));
                                Thread.sleep(1000);
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color(69, 90, 100));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }if(codigoJugadaPc.get(i) >= 3 && codigoJugadaPc.get(i) < 6){
                            try {
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color (133, 193, 233));
                                Thread.sleep(1000);
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color(40, 116, 166));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }if(codigoJugadaPc.get(i) >= 6 && codigoJugadaPc.get(i) < 9){
                            try {
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color (215, 189, 226));
                                Thread.sleep(1000);
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color(155, 89, 182));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }if(codigoJugadaPc.get(i) >= 9 && codigoJugadaPc.get(i) < 12){
                            try {
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color (248, 187, 208));
                                Thread.sleep(1000);
                                bCodigo[codigoJugadaPc.get(i)].setBackground(new Color(236, 64, 122));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            };//cierra el timer
            timer.schedule(tarea,10);
        }
    }
    
}
