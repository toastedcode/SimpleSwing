package com.toast.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toast.xml.XmlNode;

@SuppressWarnings("serial")
public class CustomPanel extends Panel
{

   public CustomPanel(XmlNode node)
   {
      super(node);
      
      if (panicButton != null)
      {
         panicButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               JOptionPane.showMessageDialog(
                  null,
                  "Panic at the disco!");
            }
         });
         
         if (customLabel != null)
         {
            customLabel.setText("Banzaii!");
         }
      }
   }
   
   JButton panicButton;
   
   JLabel customLabel;
}
