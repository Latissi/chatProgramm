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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nobody
 */
public class ConnectController implements ActionListener
{
  private Transmitter tm;
  private Fenster view;
  public ConnectController(Fenster view, Transmitter tm)
  {
      this.tm = tm;
      this.view = view;

  }
  
  public void registerEvents()
  {
      view.getCbServerClient().addActionListener(this);
      view.getBtnConnect().addActionListener(this);
      
  }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        int success = 0;
        
        if(src == view.getBtnConnect()){
            if (view.getCbServerClient().getSelectedItem().equals("Server")){
                try {
                    success = tm.initialize("Server", "");
                } catch (IOException ex) {
                    Logger.getLogger(ConnectController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if (view.getCbServerClient().getSelectedItem().equals("Client")){
                try {
                    success = tm.initialize("Client", view.getTfIPaddress().getText());
                } catch (IOException ex) {
                    Logger.getLogger(ConnectController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            switch(success){
                    case 1 : view.getBtnConnect().setEnabled(false);
                             view.getBtnSend().setEnabled(true);
                             view.getCbServerClient().setEnabled(false);
                             view.getTfInput().setEnabled(true);
                             view.getLblStatus().setText("Server is running");
                             break;
                    case 2: view.getBtnConnect().setEnabled(false);
                            view.getBtnSend().setEnabled(true);
                            view.getTfIPaddress().setEnabled(false);
                            view.getCbServerClient().setEnabled(false);
                            view.getTfInput().setEnabled(true);
                            view.getLblStatus().setText("Connected to Server");
                            break;
                    default: System.err.println("Error initializing Server/Client");
                             break;
            }
        }
        if (src == view.getCbServerClient()){
            if(view.getCbServerClient().getSelectedItem().equals("Server")){
                view.getTfIPaddress().setOpaque(false);
                view.getTfIPaddress().setEnabled(false);
            }
            if(view.getCbServerClient().getSelectedItem().equals("Client")){
                view.getTfIPaddress().setOpaque(true);
                view.getTfIPaddress().setEnabled(true);
            }
        }
    }
}
