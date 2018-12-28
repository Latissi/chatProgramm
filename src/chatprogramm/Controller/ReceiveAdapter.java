/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Controller;

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
    
  public ReceiveAdapter(Fenster view, Transmitter tm)
  {
      this.view = view;
      this.tm = tm;
  }
  
  public void registerEvents()
  {
      this.tm.addObserver(this);
  }
  
  

    @Override
    public void update(Observable o, Object arg) 
    {
        this.view.getTaChat().append(tm.getMessage());
    }
}
