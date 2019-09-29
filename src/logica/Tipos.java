package logica;

public class Tipos {

    public enum TipoBloque {
        ROMPIBLE,
        IRROMPIBLE,
        VACIO,
        BOMBA,
        FUEGO,
        PODER,
        JUGADOR,
        ENEMIGO
    };

    public enum Poderes {
        VELOCIDAD, 
        BOMBAS                
    };

    public enum Movimiento {
        UP,
        LEFT,
        RIGHT,
        DOWN,
        STOP,
        LUGAR_BOMBA;
    };
}
