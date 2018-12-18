/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramm.Model;

import java.util.Observable;

/**
 *
 * @author Fabian
 */

//Ist statt der abstract class (trotz Observable) auch ein Interface mögl.!?
public abstract class Communicator extends Observable {
    public void start(){};
    public void stopp(){};
    public void sendMessage(String message){};
    public String getMessage(){return "";};
    
}
