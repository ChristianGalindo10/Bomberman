
package logica;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import logica.Tipos.Movimiento;

/**
 *
 * @author USUARIO
 */
public class Jugador extends Movible {
   
    Movimiento direccion = Movimiento.STOP;
    int rangoFuego = 2;
    int velocidad = 2;
    int totalBombas = 1;
    ArrayList<Bomba> bombas = new ArrayList<Bomba>();
    int dx = 0;
    int dy = 0;
    Image[] UP_IMAGES = Img.UP_IMAGES;
    Image[] DOWN_IMAGES = Img.DOWN_IMAGES;
    Image[] LEFT_IMAGES = Img.LEFT_IMAGES;
    Image[] RIGHT_IMAGES = Img.RIGHT_IMAGES;
    Image[] DIE = Img.DIE;
    int estado = 0;
    private boolean vivo;
    private int playerID;

    public Jugador(Posicion _posicion) {

        super(Tipos.TipoBloque.JUGADOR, _posicion);
        vivo = true;
    }

    public void setdirectionRight() {
        direccion = Movimiento.RIGHT;
    }

    public void setdirectionLeft() {
        direccion = Movimiento.LEFT;
    }

    public void setdirectionUp() {
        this.setImage(UP_IMAGES[0]);
    }

    public void setdirectionDown() {
        this.setImage(DOWN_IMAGES[0]);
    }

    public void cycle() {
        if (!vivo) {
            return;
        }
        switch (direccion) {
            case RIGHT:
                cycleRight();
                break;
            case LEFT:
                cycleLeft();
                break;
            case UP:
                cycleUp();
                break;
            case DOWN:
                cycleDown();
                break;
            case STOP:
                //    setStopImage();
                break;
        }
    }

    private void cycleRight() {
        switch (estado) {
            case 0:
                this.setImage(RIGHT_IMAGES[1]);
                estado = 1;
                break;
            case 1:
                this.setImage(RIGHT_IMAGES[2]);
                estado = 2;
                break;
            case 2:
                this.setImage(RIGHT_IMAGES[3]);
                estado = 3;
                break;
            case 3:
                this.setImage(RIGHT_IMAGES[4]);
                estado = 4;
                break;
            case 4:
                this.setImage(RIGHT_IMAGES[0]);
                estado = 0;
                break;
        }
    }

    private void cycleLeft() {
        switch (estado) {
            case 0:
                this.setImage(LEFT_IMAGES[1]);
                estado = 1;
                break;
            case 1:
                this.setImage(LEFT_IMAGES[2]);
                estado = 2;
                break;
            case 2:
                this.setImage(LEFT_IMAGES[3]);
                estado = 3;
                break;
            case 3:
                this.setImage(LEFT_IMAGES[4]);
                estado = 4;
                break;
            case 4:
                this.setImage(LEFT_IMAGES[0]);
                estado = 0;
                break;
        }
    }

    private void cycleUp() {
        switch (estado) {
            case 0:
                this.setImage(UP_IMAGES[1]);
                estado = 1;
                break;
            case 1:
                this.setImage(UP_IMAGES[2]);
                estado = 2;
                break;
            case 2:
                this.setImage(UP_IMAGES[3]);
                estado = 3;
                break;
            case 3:
                this.setImage(UP_IMAGES[4]);
                estado = 4;
                break;
            case 4:
                this.setImage(UP_IMAGES[0]);
                estado = 0;
                break;
        }
    }

    private void cycleDown() {
        switch (estado) {
            case 0:
                this.setImage(DOWN_IMAGES[1]);
                estado = 1;
                break;
            case 1:
                this.setImage(DOWN_IMAGES[2]);
                estado = 2;
                break;
            case 2:
                this.setImage(DOWN_IMAGES[3]);
                estado = 3;
                break;
            case 3:
                this.setImage(DOWN_IMAGES[4]);
                estado = 4;
                break;
            case 4:
                this.setImage(DOWN_IMAGES[0]);
                estado = 0;
                break;
        }
    }

    public void Stop() {
        if (!vivo) {
            return;
        }
        switch (direccion) {
            case RIGHT:
                this.setImage(RIGHT_IMAGES[0]);
                break;
            case LEFT:
                this.setImage(LEFT_IMAGES[0]);
                break;
            case UP:
                this.setImage(UP_IMAGES[0]);
                break;
            case DOWN:
                this.setImage(DOWN_IMAGES[0]);
                break;
        }
        direccion = Movimiento.STOP;
    }

    public Rectangle getBounds(Movimiento move) {
        Rectangle rect = new Rectangle(getX(), getY() + Img.playerHeight - 30, Img.playerWidth, 30);
        switch (move) {
            case UP:
                rect = new Rectangle(getX(), getY() + Img.playerHeight - 30 - velocidad, Img.playerWidth, 30);
                break;
            case DOWN:
                rect = new Rectangle(getX(), getY() + Img.playerHeight - 30 + velocidad, Img.playerWidth, 30);
                break;
            case LEFT:
                rect = new Rectangle(getX() - velocidad, getY() + Img.playerHeight - 30, Img.playerWidth, 30);
                break;
            case RIGHT:
                rect = new Rectangle(getX() + velocidad, getY() + Img.playerHeight - 30, Img.playerWidth, 30);
                break;
        }
        return rect;
    }

    public void setDireccion(Movimiento direccion) {
        this.direccion = direccion;
    }

   
    
    

    public void die() {
        if (vivo) {
            vivo = false;

            Thread animateDeathofPlayer = new Thread(new Runnable() {
                public void run() {
                    for (int c = 0; c < 6; c++) {
                        try {
                            setImage(Img.DIE[c]);
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            System.out.println("interrupted");
                        }
                    }
                }
            });
            animateDeathofPlayer.start();
        }
    }

    public boolean isAlive() {
        return vivo;
    }

    

    public void plantBomb() {
        int x = getX() + 15;
        int y = getY();
        for (int i = 0; i < 1000; i = i + 50) {
            if (x < i) {
                x = i - 50;
                break;
            }
        }
        for (int i = 0; i < 1000; i = i + 50) {
            if (y < i) {
                y = i;
                break;
            }
        }

        if (totalBombas > bombas.size()) {
            Posicion pos = new Posicion(x, y);
            Bomba bomba = new Bomba(pos);
            bombas.add(bomba);
            Bomba.allBombas.add(bomba);
        }
    }

    public ArrayList<Bomba> getBombas() {
        return bombas;
    }
    
    

    public void tomarPoder(final Poder power) {
        switch (power.getPowerType()) {
            case BOMBAS:
                totalBombas++;
                break;
            case VELOCIDAD:
                velocidad++;
                break;
        }
        
        Poder.allPoderes.remove(power);
        Thread powerTimer = new Thread(new Runnable() {
            public void run() {
                    try {
                        Thread.sleep(power.tiempoPoder);
                        stopPowerUp(power);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
        });
        powerTimer.start();
        
    }
    
    public void stopPowerUp(Poder power){
        switch (power.getPowerType()) {
            case BOMBAS:
                totalBombas--;
                break;
            case VELOCIDAD:
                velocidad--;
                break;
        }
    }

}
