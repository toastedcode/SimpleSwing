package com.toast.swing;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toast.xml.XmlNode;
import com.toast.xml.XmlNodeList;

@SuppressWarnings("serial")
public class Frame extends JFrame
{
   public Frame(XmlNode node)
   {
      super();
      
      setup(node);
   }
   
   protected void setup(XmlNode node)
   {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      SwingUtils.setSize(this, node);
      
      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
      
      XmlNodeList childNodes = node.getNodes("./*");
      
      if (childNodes.getLength() > 1)
      {
         System.out.format("Warning! Frame contains more than one content pane.\n");
      }
      
      this.setContentPane((JPanel)SimpleSwing.create(childNodes.item(0)));
      
      setVisible(true);
   }
}
