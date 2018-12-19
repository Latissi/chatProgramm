/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatprogramm.Controller;

import chatprogramm.Model.Transmitter;
import chatprogramm.View.Fenster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nobody
 */
public class SendController implements ActionListener
{
    private Fenster view;
    private Transmitter tm;
  public SendController(Fenster view, Transmitter tm)
  {
      this.view = view;
      this.tm = tm;
  }
  
  public void registerEvents()
  {
      this.view.getTfInput().addActionListener(this);
      this.view.getBtnSend().addActionListener(this);
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String message;
        if(src == view.getTfInput() || src == view.getBtnSend()){
            message = view.getTfInput().getText();
            tm.send(message);
            view.getTaChat().append("You:\n" + message + "\n");
            view.getTfInput().setText("");
        }
    }
}
