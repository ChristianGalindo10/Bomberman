
package bomberman;


public class Tipos {
   

    

    public enum TipoBloque{
        ROMPIBLE, 
        IRROMPIBLE,
        VACIO,
        BOMBA,
        FUEGO,
        PODER,
        JUGADOR,
        ENEMIGO 
    };
    
    

    public enum Poderes{
        
        VELOCIDAD,               //when player take this power its speed increases
        RANGO,               //by taking this power the range of explosion fire is increased
        BOMBAS                //This power up increase the number of bombs that a player can place simultaneously
    };
    
    public enum Movimiento{
        
        UP,
        LEFT,
        RIGHT,
        DOWN,
        STOP,
        LUGAR_BOMBA;
    };
}
