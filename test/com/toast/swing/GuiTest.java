package com.toast.swing;

import java.net.URL;

import com.toast.xml.XmlDocument;

public class GuiTest
{
   public static void main(final String args[])
   {
      XmlDocument document = new XmlDocument();
      URL url = SimpleSwing.class.getResource("/resources/layout.xml");
      String filename = url.getFile();
      document.load(filename);
      
      SimpleSwing.create(document.getRootNode());
   }
}
