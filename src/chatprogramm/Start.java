/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm;

import chatprogramm.Controller.ConnectController;
import chatprogramm.Controller.ReceiveAdapter;
import chatprogramm.Controller.SendController;
import chatprogramm.Model.Transmitter;
import chatprogramm.View.Fenster;

/**
 * Builder Class
 * @author nobody
 */
public class Start
{
  public Start()
  {
      Fenster frm = new Fenster();
      Transmitter tm = new Transmitter();
      ConnectController cc = new ConnectController(frm, tm);
      SendController sc = new SendController(frm,tm);
      ReceiveAdapter rc = new ReceiveAdapter(frm,tm);
      cc.registerEvents();
      sc.registerEvents();
      rc.registerEvents();
      frm.setVisible(true);

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
    new Start();
  }
}
