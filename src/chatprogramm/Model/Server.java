/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Model;

import Util.OhmLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nobody
 */
public class Server extends Communicator implements Runnable
{
  private BufferedReader reader;
  private PrintWriter writer;
  private Thread thd;
  private Socket s;
  private final int PORT;
  private boolean stopReceiving;
  private ArrayList<String> messageBuffer;
  private static Logger lg;
  
  public Server(int port)
  {
      this.PORT = port;
      this.messageBuffer = new ArrayList<String>();
      stopReceiving = false;
      lg = OhmLogger.getLogger();
  }
  
  
  private void connect() throws IOException
  {
      ServerSocket ss = new ServerSocket(PORT);
      lg.info("Warte auf Verbindung");
      this.s = ss.accept();
      lg.info("Verbindung da");
      
      InputStream is = s.getInputStream();
      OutputStream os = s.getOutputStream();
      
      InputStreamReader isr = new InputStreamReader(is);
      OutputStreamWriter osr = new OutputStreamWriter(os);
      
      this.reader = new BufferedReader(isr);
      this.writer = new PrintWriter(osr);
      
      lg.info("Stream initialisiert");
      lg.info("Warte auf Nachricht");
      
      String nachricht = reader.readLine();
      lg.info("Nachricht empfangen: " + nachricht);
      
      writer.println("SYN-ACK");
      writer.flush();
  }
  
  @Override
  public void start(){
    if (thd == null){
        thd = new Thread(this);
        thd.start();
      }  
  }
  
  
  @Override
  public void stopp()
  {
      stopReceiving = true;
  }
  
   public void send(String message)
  {
      this.writer.println(message);
      this.writer.flush();
  }
   
  @Override
  public String getMessage()
  {
      String message = "";
      message = messageBuffer.stream().map((m) -> m +"\n").reduce(message, String::concat);
      messageBuffer.clear();
      return message;
  }

  
  private synchronized void receive()
  {
    String message = " ";
    try 
    {
      message = this.reader.readLine();
    }
    catch (IOException ioex)
    {
      System.err.println(ioex);
    }
    messageBuffer.add(message);
    this.setChanged();
    this.notifyObservers();
  }

  public void run()
  {
      try {
          connect();
      } catch (IOException ex) {
          lg.info("Test");
      }
      while(!stopReceiving){
        receive();
    }
  }

    @Override
    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }
}
