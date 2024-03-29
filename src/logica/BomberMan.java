package logica;


import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import presentacion.VistaJuego;

public class BomberMan {

    private int valor;
    static Jugador jugador;
    static CopyOnWriteArrayList<Enemigo> enemigos = new CopyOnWriteArrayList<Enemigo>();
    static final int SIZE = 15;
    static int height;
    static int width;
    static Mapa mapa;
    static MapaGui frame;
    JFrame menu;
    static int[][] intMap = new int[15][15];

    public static void main(String args[]) {
        new BomberMan().startGame();

    }

    public CopyOnWriteArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }
    
    

    public void startGame() {
        System.out.println("Starting Game");
        mapa = new Mapa(construirMapa(), SIZE);
        addEnemies(3);
        addPlayers();
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

    public void addPlayers() {
        jugador = new Jugador(new Posicion(60, 30));
    }

    public int[][] construirMapa(){
        int[][] intMap1 = new int[][]{
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
        int[][] intMap2 = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 2, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 2, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 2, 2, 0, 2, 0, 0, 0, 0, 2, 2, 0, 0, 1},
        {1, 0, 2, 2, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 2, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 2, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 1},
        {1, 0, 2, 2, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1},
        {1, 0, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 2, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
        int[][] intMap3 = new int[][]{
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
        generarValor();
        if(valor==1){
            intMap=intMap1;
        }else{
            if(valor==2){
                intMap=intMap2;
            }else{
                if(valor==3){
                    intMap=intMap3;
                }
            }
        }
        return intMap;
    }
    
    public void generarValor(){
        valor = (int) (Math.random() * 3) + 1;
    }
    
    public Jugador getJugador(){
        return this.jugador;
    }
    

}
