/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import logica.Bomba;
import logica.BomberMan;
import logica.Enemigo;
import logica.Fuego;
import logica.Img;
import logica.Jugador;
import logica.Mapa;
import logica.Mover;
import logica.Poder;
import logica.Tipos;

/**
 *
 * @author USUARIO
 */
public class VistaJuego extends JFrame {

    Timer timer;
    Timer timer2;
    JPanel panel;
    Bomba bomba;
    Mapa mapa;
    Jugador jugador;
    BomberMan logica;
    
    public VistaJuego(String title, Mapa mapa) {
        jugador = logica.getJugador();
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creando Panel
        this.mapa = mapa;
        GridLayout gL = new GridLayout(15, 15);
        panel = new JPanel(gL) {
            public void paint(Graphics g) {
                super.paint(g);
                panelPaint(g);
            }
        };

        for (int i = 0; i < mapa.getSize(); i++) {
            for (int j = 0; j < mapa.getSize(); j++) {
                panel.add(mapa.getLabel(i, j));
            }
        }

        panel.setDoubleBuffered(true);
        setFocusable(true);

        this.add(panel);
        this.getContentPane().add(panel, BorderLayout.NORTH);

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.repaint();
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        //  this.revalidate();
        //Timers
        timer = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //for (Jugador i : BomberMan.jugadores) {   
                Mover.Move(jugador);
                //}

                for (Enemigo enem : logica.getEnemigos()) {
                    enem.move();
                }
                panel.repaint();
            }
        });

        timer2 = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //for (Jugador i : BomberMan.jugadores) {       
                jugador.cycle();

                //}
            }
        });

        startTimers();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key != KeyEvent.VK_SPACE) {
                logica.getJugador().Stop();
            }
        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    logica.getJugador().setDireccion(Tipos.Movimiento.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    logica.getJugador().setDireccion(Tipos.Movimiento.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    logica.getJugador().setDireccion(Tipos.Movimiento.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    logica.getJugador().setDireccion(Tipos.Movimiento.DOWN);
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("SPACE");
                    logica.getJugador().plantBomb();
                    break;
            }
        }
    }

    public void panelPaint(Graphics g) {
        //if(BomberMan.jugador.isAlive()==false){
        //timer.stop();
        //timer2.stop();
        //dispose();
        //return;
        //}
        Graphics2D g2d = (Graphics2D) g;
        //Dibujar enemigos
        for (Enemigo enemigo : logica.getEnemigos()) {
            g2d.drawImage(enemigo.getImage(), enemigo.getX(), enemigo.getY(), null);
        }
        //Dibujar jugadores 
        //for (Jugador jugador : BomberMan.jugadores) { 
        g2d.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), null);
        for (Bomba bomba : jugador.getBombas()) {
            g2d.drawImage(bomba.getImage(), bomba.getX(), bomba.getY(), panel);
        }
        //}

        //     g2d.draw3DRect(WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled);
        g.drawRect(jugador.getX(), jugador.getY() + Img.getPlayerHeight() - 30, Img.getPlayerWidth(), 30);
        //   g.drawRect(enemy.getX(), enemy.getY(), 37, 40);

        //for (Fuego f : Fuego.fuego) {
            //g2d.drawImage(f.getImage(), f.getX(), f.getY(), null);
        //}

        //for (Poder power : Poder.allPoderes) {
           // g2d.drawImage(power.getImage(), power.getX(), power.getY(), null);
        //}
        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }

    public void stopTimers() {
        timer.stop();
        timer2.stop();
    }

    public void startTimers() {
        timer.start();
        timer2.start();
    }

}
