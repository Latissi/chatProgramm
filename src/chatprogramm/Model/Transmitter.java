/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Properties;

/**
 *
 * @author nobody
 */
public class Transmitter extends Observable
{
  private Thread thd;
  
  
  public Transmitter()
  {

  }
  
  public void initialize() throws IOException{
    Properties props = new Properties();
    InputStream is = this.getClass().getResourceAsStream("config/settings.properties");
    
    props.load(is);
    
    int port = Integer.parseInt(props.getProperty("PORT"));
    String type = props.getProperty("TYPE");
    
    if (type == "Server")
    {
      thd = new Thread(new Server(port));
    }
  }

  
}
