package bomberman;

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
        RANGO, 
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
