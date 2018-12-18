/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Model;

import Util.OhmLogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author nobody
 */
public class Transmitter extends Observable
{
  private Communicator com;
  private static Logger lg;
  
  public Transmitter()
  {
      lg = OhmLogger.getLogger();
      lg.info("Logger initialisiert");
  }
  
  public int initialize(String type, String serverIP) throws IOException{
    Properties props = new Properties();
    InputStream is = this.getClass()
            .getResourceAsStream("config/settings.properties");
    
    props.load(is);
    
    int port = Integer.parseInt(props.getProperty("PORT"));
    
    if ("Server".equals(type)){
        com = new Server(port);
        com.start();
        this.setChanged();
        this.notifyObservers();
        return 1;
    }
    else if("Client".equals(type)){
        com = new Client(serverIP, port);
        com.start();
        this.setChanged();
        this.notifyObservers();
        return 2;
    }
    else{
        return 0;
    }
  }
  
  public void send(String s)
  {
      com.sendMessage(s);
  }
  
  public Communicator getCommunicator()
  {
      return com;
  }
  
}
