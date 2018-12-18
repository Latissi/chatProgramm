/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import static java.util.logging.Level.ALL;
import java.util.logging.Logger;

/**
 *
 * @author nobody
 */
public class OhmLogger
{
  private static Logger logger = null;
  
  private OhmLogger()
  {
  }
  public static Logger getLogger()
  {
    if (logger == null)
    {
      logger = Logger.getLogger("OhmLogger");
      initLogger();
    }
    return logger;
  }
  
  private static void initLogger() 
  {
    try
    {
      //logger.setUseParentHandlers(false);
      Properties props = new Properties();
      InputStream is = OhmLogger.class.getResourceAsStream("logger.properties");
      props.load(is);
      
      String level = props.getProperty("LOG_LEVEL");
      String path = props.getProperty("LOG_DIRECTORY");
      String file = props.getProperty("LOG_DATEI");
      
      String datei = path + File.separator + file;
      FileHandler fh = new FileHandler(datei);
      ConsoleHandler ch = new ConsoleHandler();
      
      
      fh.setFormatter(new OhmFormatter());
      logger.addHandler(fh);
      
      ch.setFormatter(new OhmFormatter());
      
      logger.addHandler(ch);
      
      logger.setLevel(Level.parse(level));
    }
    catch(IOException ix)
    {
      System.err.println(ix);
    }
  }
  
}
