
package logica;

import java.io.Serializable;
import javax.swing.JLabel;

public class Mapa implements Serializable{

    public int[][] intMap;
    private int rows, cols;
    public JLabel[][] map;
    private Posicion[][] position;
    int size;

    public Mapa(int[][] intMap, int _size) {
        this.intMap = intMap;
        size = _size;
        map = new JLabel[size][size];
        position = new Posicion[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                position[i][j] = new Posicion(j * 50, i * 50);
                switch (intMap[i][j]) {
                    case (0):
                        map[i][j] = new JLabel(Img.sueloDefecto);
                        break;
                    case (1):
                        map[i][j] = new JLabel(Img.irrompibleIcono);
                        break;
                    case (2):
                        map[i][j] = new JLabel(Img.rompibleIcono);
                        break;
                }
            }
        }
    }

    
    public void breakBrickAt(final int i, final int j) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int timer = 0;
                for (int c = 0; c < 8; c++) {
                    try {
                        map[i][j].setIcon(Img.rompibleColor[c]);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                    timer++;
                }
                map[i][j].setIcon(Img.sueloDefecto);
                bonus(new Posicion(j*50,i*50));
            }
        });
        t.start();
        intMap[i][j] = 0;
        map[i][j].setIcon(Img.sueloDefecto);

    }

    public JLabel getLabel(int i, int j) {
        return map[i][j];
    }
    //Returna coordenadas de bloque

    public int getX(int i, int j) {
        return (position[i][j].getxPos());
    }

    public int getY(int i, int j) {
        return (position[i][j].getyPos());
    }

    
    public int brickAtPosition(Posicion pos) {
        int x = pos.getxPos();
        int y = pos.getyPos();
        x= round(x);
        y=round(y);
        System.out.println("Rounderd off: " + x + " " + y);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (position[i][j].getxPos() == x && position[i][j].getyPos() == y) {
                    return intMap[i][j];
                }
            }
        }
        return 99;
    }
    public void breakBrickAtPosition(Posicion pos){
        int x = pos.getxPos();
        int y = pos.getyPos();
        x= round(x);
        y=round(y);
        System.out.println("Rounderd off: " + x + " " + y);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (position[i][j].getxPos() == x && position[i][j].getyPos() == y) {
                    if(intMap[i][j]==2){
                    breakBrickAt(i,j);
                    return;
                    }
                    else{
                        System.out.println("ERROR!. Brick not breackable");
                    }
                }
            }
        }
    }

    public int round(int x) {
        for (int i = 0; i < 1000; i = i + 50) {
            if (x < i) {
                x = i - 50;
                break;
            }
        }
        return x;
    }

    
    private void bonus(Posicion pos) {
        //Numero random entre 0 y 10
        int r = 0 + (int) (Math.random() * ((10 - 0) + 1));
        if(r>3){
            //Generar Numero 1,2,3
            int p = 1 + (int) (Math.random() * ((3 - 1) + 1));
            switch(p){
                case 1:
                    new Poder(pos, Tipos.Poderes.VELOCIDAD);
                    break;
                case 3:
                    new Poder(pos, Tipos.Poderes.BOMBAS);
                    break;
            }
        }
    }
    
    public int getSize(){
        return size;
    }
}
