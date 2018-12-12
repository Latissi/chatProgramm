/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author nobody
 */
public class Server implements Runnable
{
  private BufferedReader reader;
  private Socket s;
  final int PORT;
  public Server(int port)
  {
    PORT = port;
  }
  
  public void connect()
  {
    try 
    {
      ServerSocket ss = new ServerSocket(PORT);
      System.out.println("warte auf Verbindung");
      this.s = ss.accept();
      System.out.println("Verbindung da");
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      this.reader = new BufferedReader(isr);
    }
    catch (Exception ex)
    {
      System.err.println(ex.toString());
    }
  }
  
  public String receive()
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
    return message;
  }

  public void run()
  {
  }
}
