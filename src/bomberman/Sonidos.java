/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;
import java.applet.AudioClip;

/**
 *
 * @author USUARIO
 */
public class Sonidos {
    
    AudioClip sonido;
    public void explosion(){
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/explosion.wav"));
        sonido.play();
    
}
}
