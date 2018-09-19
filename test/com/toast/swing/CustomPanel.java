package com.toast.swing;

import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toast.swing.component.Panel;
import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class CustomPanel extends Panel
{

   public CustomPanel(XmlNode node) throws XmlFormatException
   {
      super(node);

      if (customLabel != null)
      {
         customLabel.setText("Banzaii!");
      }
   }
   
   public void onClick(MouseEvent event)
   {
      JOptionPane.showMessageDialog(
            null,
            "Believe it or not, I'm walkin' on air!");      
   }
   
   public void mouseOver(MouseEvent event)
   {
      System.out.format("Over\n");      
   }
   
   public void mouseOut(MouseEvent event)
   {
      System.out.format("Out\n");      
   }
   
   JButton panicButton;
   
   JLabel customLabel;
}
