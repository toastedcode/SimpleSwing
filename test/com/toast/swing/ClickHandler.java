package com.toast.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ClickHandler implements ActionListener
{
   @Override
   public void actionPerformed(ActionEvent arg0)
   {
      JOptionPane.showMessageDialog(
            null,
            "Push my buttons!");      
   }
}
