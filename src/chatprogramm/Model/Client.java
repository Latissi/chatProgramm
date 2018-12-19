/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Model;

import Util.OhmLogger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nobody
 */
public class Client extends Communicator implements Runnable
{
    private final int PORT;
    private final String SERVER_IP;
    private Socket s;
    private Thread thd;
    private boolean stopReceiving;
    private static Logger lg;
    
  public Client(String ServerIP, int port) throws IOException
  {
      this.PORT = port;
      this.SERVER_IP = ServerIP;
      this.s = new Socket(SERVER_IP, PORT);
      messageBuffer = new ArrayList<String>();
      stopReceiving = false;
      lg = OhmLogger.getLogger();
      
  }
  
  private void connect() throws IOException
  {
      InputStream iStream = s.getInputStream();
      OutputStream oStream = s.getOutputStream();
      
      InputStreamReader isr = new InputStreamReader(iStream);
      OutputStreamWriter osr = new OutputStreamWriter(oStream);
      
      this.reader = new BufferedReader(isr);
      this.writer = new PrintWriter(osr);
      
      lg.info("Stream initialisiert");
      lg.info("Nachricht senden");
      
      writer.println("SYN");
      writer.flush();
      lg.info("Warte auf Bestätigung");
      
      String quittung = reader.readLine();
      
      lg.info("Client: Quittung empfangen - " + quittung);
  }
  
    @Override
  public void start ()
  {
      if(thd == null){
        thd = new Thread(this);
        thd.start();
      }
  }
  
    @Override
  public void stopp()
  {
      stopReceiving = true;
  } 

  public void run()
  {
        try {
            connect();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(!stopReceiving)
        {
            receive();
            lg.info("Nachricht empfangen");
        }
  }
}
