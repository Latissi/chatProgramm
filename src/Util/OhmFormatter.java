/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author nobody
 */
public class OhmFormatter extends SimpleFormatter
{
  private static final DateFormat df =
                        new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");



  @Override
  public String format(LogRecord record)
  {
    //java.util.logging.SimpleFormatter.format = 
     StringBuilder builder = new StringBuilder(1000);
     builder.append("| ")
             .append(df.format(new Date(record.getMillis()))).append(" | ");
     builder.append(record.getLevel()).append(" | ");
     builder.append(record.getSourceClassName()).append(" | ");
     builder.append(formatMessage(record)).append("|");
     return builder.toString();  
  }
  

}
