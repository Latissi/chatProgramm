/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramm.Model;

import Util.OhmLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */

//Ist statt der abstract class (trotz Observable) auch ein Interface mögl.!?
public abstract class Communicator extends Observable
{
  protected BufferedReader reader;
  protected PrintWriter writer;
  protected ArrayList<String> messageBuffer;

  public void Communicator()
  {
    messageBuffer = new ArrayList<String>();
  }

  public void start(){};
  
  public void stopp(){};
    
  public void sendMessage(String message)
  {
    writer.println(message);
    writer.flush();
  }

  ;
  public String getMessage()
  {
    String message = "";
    message = messageBuffer.stream().map((m) -> m + "\n").reduce(message, String::concat);
    messageBuffer.clear();
    return message;
  }

  ;
    
  protected synchronized void receive()
  {
    String message = "";
    try
    {
      message = this.reader.readLine();
    }
    catch (IOException io)
    {
      System.err.println(io);
    }
    messageBuffer.add(message);
    this.setChanged();
    this.notifyObservers();
  }

}
