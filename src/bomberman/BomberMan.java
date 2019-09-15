/*
 * In this class you will instentialte other classes and game playe will be
 * implemented here.
 */
package bomberman;

import bomberman.Tipos.TipoBloque;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BomberMan {

    static CopyOnWriteArrayList<Jugador> jugadores = new CopyOnWriteArrayList<Jugador>();
    static CopyOnWriteArrayList<Enemigo> enemigos = new CopyOnWriteArrayList<Enemigo>();
    static final int SIZE = 15;
    static int height;
    static int width;
    static Mapa mapa;
    static MapaGui frame;
    JFrame menu;
    static int[][] intMap = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 2, 0, 2, 0, 0, 0, 0, 2, 2, 0, 0, 1},
        {1, 0, 1, 2, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 1},
        {1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static void main(String args[]) {
//        new menu();

        new BomberMan().startGame();

    }

    public void startGame() {
        System.out.println("Starting Game");
        mapa = new Mapa(intMap, SIZE);
        addEnemies(3);
        addPlayers(1);
        frame = new MapaGui("Bomberman", mapa);

        for (int ii = 0; ii < intMap.length; ii++) {
            for (int jj = 0; jj < intMap.length; jj++) {
                if (intMap[ii][jj] == 1) {
                    int xxx = jj*50;
                    int yyy = ii*50;
                    System.out.println(xxx+","+yyy);
                }
            }
        }
    }

    public void addEnemies(int n) {
        for (int i = 0; i < n; i++) {
            enemigos.add(new Enemigo(new Posicion(550 + 5, 350 + 5)));
        }
    }

    public void addPlayers(int n) {
        if (n >= 1) {
            jugadores.add(new Jugador(new Posicion(60, 30)));
        }
        if (n >= 2) {
            jugadores.add(new Jugador(new Posicion(660, 630)));
        }
    }

    public static void saveGame() {
        ArrayList saveData = new ArrayList();
        ArrayList<Posicion> enemyPositions = new ArrayList<Posicion>();
        ArrayList<Posicion> playerPositions = new ArrayList<Posicion>();
//        ArrayList<Position> enemyPositions = new ArrayList<Position>();
        for (Enemigo i : enemigos) {
            enemyPositions.add(i.getPosition());
        }
        for (Jugador j : jugadores) {
            playerPositions.add(j.getPosition());
        }
        saveData.add(enemyPositions);
        saveData.add(playerPositions);
        saveData.add(intMap);
        try {
            FileOutputStream fileOut1 = new FileOutputStream("gameSaveData.pos");
            ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
            out1.writeObject(saveData);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void loadGame() {
        ArrayList saveData = null;
        try {
            FileInputStream fileIn = new FileInputStream("gameSaveData.pos");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            saveData = (ArrayList) in.readObject();
        } catch (Exception i) {
            i.printStackTrace();
        }

        ArrayList<Posicion> enemyPositions = (ArrayList) saveData.get(0);
        ArrayList<Posicion> playerPositions = (ArrayList) saveData.get(1);

        CopyOnWriteArrayList<Enemigo> newEnemigos = new CopyOnWriteArrayList<Enemigo>();
        CopyOnWriteArrayList<Jugador> newJugadores = new CopyOnWriteArrayList<Jugador>();
        //   Map newMap = ((Map)saveData.get(2));
        for (Posicion i : enemyPositions) {
            newEnemigos.add(new Enemigo(i));
        }
        enemigos = newEnemigos;

        for (Posicion i : playerPositions) {
            newJugadores.add(new Jugador(i));
        }
        jugadores = newJugadores;
        intMap = (int[][]) saveData.get(2);
        mapa = new Mapa(intMap, SIZE);
        frame.stopTimers();
        frame.dispose();
        frame = new MapaGui("Bomberman by Hassan", mapa);
    }
}
