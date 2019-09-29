package logica;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bomba extends MapaBasico implements Runnable {

    boolean puesta = true;
    static CopyOnWriteArrayList<Bomba> allBombas = new CopyOnWriteArrayList<Bomba>();
    int size = Img.BOMBSIZE;
    int retraso = 200;
    int temporizadorExplosion = 10;
    Thread pulsate;
    Thread explode;
    //Sonidos sonido;

    public Bomba(Posicion _position) {

        super(Tipos.TipoBloque.BOMBA, _position);
        pulsate = new Thread(this);
        pulsate.start();
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), size, size);
    }

    @Override
    public void run() {
        int timer = 0;
        while (timer < temporizadorExplosion) {
            try {
                Thread.sleep(retraso);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            timer++;
            parpadear();
        }
        explotar();
        AudioClip sonido;
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/musica/explosion.wav"));
        sonido.play();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bomba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void explotar() {
        BomberMan.jugador.bombas.remove(0);
        Fuego.startFire(getPosition());
    }

    public void parpadear() {
        if (getImage().equals(Img.bombaG)) {
            setX(getX() + 5);
            setY(getY() + 5);
            setImage(Img.bombaP);
        } else {
            setX(getX() - 5);
            setY(getY() - 5);
            setImage(Img.bombaG);
        }
    }

}
