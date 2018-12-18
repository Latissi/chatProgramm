/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Controller;

import chatprogramm.Model.Communicator;
import chatprogramm.Model.Transmitter;
import chatprogramm.View.Fenster;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author nobody
 */
public class ReceiveAdapter implements Observer
{
    private final Fenster view;
    private final Transmitter tm;
    private Communicator com;
    
  public ReceiveAdapter(Fenster view, Transmitter tm)
  {
      this.view = view;
      this.tm = tm;
  }
  
  public void registerEvents()
  {
      this.tm.addObserver(this);
  }
  
  private void updateObservers()
  {
      this.com = tm.getCommunicator();
      this.com.addObserver(this);
  }
  

  @Override
  public void update(Observable o, Object arg)
  {
      
      if(o == this.tm){
          updateObservers();
      }
      else{
          String m = com.getMessage();
          this.view.getTaChat().append("Answer:\n" + m);
      }

  }
}
