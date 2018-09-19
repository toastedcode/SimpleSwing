package com.toast.swing;

import java.io.IOException;
import java.net.URL;

import com.toast.xml.XmlDocument;
import com.toast.xml.exception.XmlFormatException;
import com.toast.xml.exception.XmlParseException;

public class GuiTest
{
   public static void main(final String args[])
   {
      XmlDocument document = new XmlDocument();
      URL url = SimpleSwing.class.getResource("/resources/layout2.xml");
      String filename = url.getFile();
      
      try
      {
         document.load(filename);
      
         SimpleSwing.create(document.getRootNode(), null);
      }
      catch (IOException e)
      {
         System.out.printf("Failed to load %s\n.", filename);
      }
      catch (XmlFormatException | XmlParseException e)
      {
         System.out.printf("Improper XML in file %s\n.", filename);
      }
   }
}
