package com.toast.swing.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import com.toast.swing.XmlUtils;
import com.toast.xml.XmlNode;

@SuppressWarnings("serial")
public class TextField extends JTextField
{
   public TextField(XmlNode node)
   {
      super();
      
      setup(node);
   }
   
   protected void setup(XmlNode node)
   {
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id"));
      }
      
      // size
      int width = XmlUtils.getInt(node,  "width", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "height", Integer.MAX_VALUE);
      setMaximumSize(new Dimension(width, height));
      setPreferredSize(new Dimension(width, height));
      
      // text
      setText(XmlUtils.getString(node,  "text", ""));
      
      // textColor
      setForeground(XmlUtils.getColor(node, "textColor", Color.BLACK));
   }
}
