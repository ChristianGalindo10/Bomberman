package logica;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Fuego extends MapaBasico implements Runnable {

    static CopyOnWriteArrayList<Fuego> fuego = new CopyOnWriteArrayList<Fuego>();
    int retraso = 300;
    Thread animacionFuego;
    int direccion;

    
    public Fuego(final Posicion _position, int dir) {
        super(Tipos.TipoBloque.FUEGO, _position);
        direccion = dir;
        switch (dir) {
            case 1:
                setImage(Img.fuegoCentro);
                break;
            case 2:
                setImage(Img.fuegoAbajo);
                break;
            case 3:
                setImage(Img.fuegoIzquierda);
                break;
            case 4:
                setImage(Img.fuegoDerecha);
                break;
            case 5:
                setImage(Img.fuegoArriba);
                break;
        }

        fuego.add(this);
        animacionFuego = new Thread(this);
        animacionFuego.start();
    }

    public static void startFire(Posicion pos) {
        int x = round(pos.getxPos());
        int y = round(pos.getyPos());
        Fuego f = new Fuego(new Posicion(x, y), 1);
        //right = 4
        Posicion right = new Posicion(x + 50, y);
        if (BomberMan.mapa.brickAtPosition(right) == 0) {
            new Fuego(right, 4);
        }
        //down = 2
        Posicion down = new Posicion(x, y + 50);
        if (BomberMan.mapa.brickAtPosition(down) == 0) {
            new Fuego(down, 2);
        }
        Posicion left = new Posicion(x - 50, y);
        if (BomberMan.mapa.brickAtPosition(left) == 0) {
            new Fuego(left, 3);
        }
        Posicion up = new Posicion(x, y - 50);
        if (BomberMan.mapa.brickAtPosition(up) == 0) {
            new Fuego(up, 5);
        }
    }

    public void romperBloque() {
        if (direccion != 1) {
            return;
        }
        int x = getX();
        int y = getY();
        Posicion abajo = new Posicion(x, y + 50);
        Posicion derecha = new Posicion(x + 50, y);
        Posicion izquierda = new Posicion(x - 50, y);
        Posicion arriba = new Posicion(x, y - 50);
        BomberMan.mapa.breakBrickAtPosition(arriba);
        BomberMan.mapa.breakBrickAtPosition(abajo);
        BomberMan.mapa.breakBrickAtPosition(izquierda);
        BomberMan.mapa.breakBrickAtPosition(derecha);
        System.out.println("Break Bricks Called");

    }

    public CopyOnWriteArrayList<Fuego> getFuego() {
        return fuego;
    }
    
    

    public void matarEnemigos() {
        if (direccion != 1) {
            return;
        }
        ArrayList<Enemigo> serEliminado = new ArrayList<>();
        int x = this.getX();
        int y = this.getY();

        for (Enemigo enemy : BomberMan.enemigos) {
            Rectangle enemyBounds = enemy.getBounds(enemy.direction);
            Rectangle t = new Rectangle(x + 50, y, 50, 50);
            if (t.intersects(enemyBounds)) {
                serEliminado.add(enemy);
            }
            t = new Rectangle(x - 50, y, 50, 50);
            if (t.intersects(enemyBounds)) {
                serEliminado.add(enemy);
            }
            t = new Rectangle(x, y + 50, 50, 50);
            if (t.intersects(enemyBounds)) {
                serEliminado.add(enemy);
            }
            t = new Rectangle(x, y - 50, 50, 50);
            if (t.intersects(enemyBounds)) {
                serEliminado.add(enemy);
            }
        }

        Rectangle playerBounds = BomberMan.jugador.getBounds(BomberMan.jugador.direccion);
        Rectangle t = new Rectangle(x + 50, y, 50, 50);
        if (t.intersects(playerBounds)) {
            BomberMan.jugador.die();
        }
        t = new Rectangle(x - 50, y, 50, 50);
        if (t.intersects(playerBounds)) {
            BomberMan.jugador.die();
        }
        t = new Rectangle(x, y + 50, 50, 50);
        if (t.intersects(playerBounds)) {
            BomberMan.jugador.die();
        }
        t = new Rectangle(x, y - 50, 50, 50);
        if (t.intersects(playerBounds)) {
            BomberMan.jugador.die();
        }
        //}

        for (final Enemigo i : serEliminado) {
            i.die();
        }
    }

    public static int round(int x) {
        for (int i = 0; i < 1000; i = i + 50) {
            if (x < i) {
                x = i - 50;
                break;
            }
        }
        return x;
    }

    @Override
    public void run() {
        final Fuego c = this;
        try {
            Thread.sleep(retraso);
            fuego.remove(c);
            romperBloque();
            matarEnemigos();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}
