/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramm.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Fabian
 */

public abstract class Communicator extends Observable
{
  protected BufferedReader reader;
  protected PrintWriter writer;
  protected ArrayList<String> messageBuffer;

  public void Communicator()
  {
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
    for (String s : messageBuffer){
        message += s;
    }
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
    try{
    messageBuffer.add(message);
    }
    catch(Exception ex){
        System.err.println(ex);
    }
    this.setChanged();
    this.notifyObservers();
  }

}
