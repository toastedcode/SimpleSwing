package com.toast.swing.component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.toast.swing.SwingUtils;
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
      
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id"));
      }
      
      SwingUtils.setSize(this, node);
      
      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
      
      XmlNodeList childNodes = node.getNodes("./*");
      
      if (childNodes.getLength() > 1)
      {
         System.out.format("Warning! Frame contains more than one content pane.\n");
      }
   }
}
