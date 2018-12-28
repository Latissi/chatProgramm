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
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author nobody
 */
public class Transmitter extends Observable implements Observer
{
  private Communicator com;
  private static Logger lg;
  private String message;
  
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
        this.registerEvents();
        return 1;
    }
    else if("Client".equals(type)){
        com = new Client(serverIP, port);
        com.start();
        this.registerEvents();
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
  
  private void registerEvents()
  {
      this.com.addObserver(this);
  }
  
  public String getMessage()
  {
      String str = "Answer:\n" + this.message + "\n";
      return str;
  }
  
    @Override
    public void update(Observable o, Object arg) {
        try{
        message = com.getMessage();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        this.setChanged();
        this.notifyObservers();
    }
  
}
