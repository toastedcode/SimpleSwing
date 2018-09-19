package com.toast.swing.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.toast.swing.Resource;
import com.toast.swing.SwingUtils;
import com.toast.swing.XmlUtils;
import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class Panel extends JPanel
{
   public Panel(XmlNode node) throws XmlFormatException
   {
      super();
      
      setup(node);
   }
   
   public void setBackgroundImage(BufferedImage image)
   {
      backgroundImage = image;
   }
   
   protected void setup(XmlNode node) throws XmlFormatException
   {
      SwingUtils.setLayout(this, node);
      
      SwingUtils.setBorder(this,  node);
      
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id").getValue());
      }
      
      // size
      int width = XmlUtils.getInt(node,  "width", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "height", Integer.MAX_VALUE);
      setMaximumSize(new Dimension(width, height));
      
      // color
      if (node.hasAttribute("color"))
      {
         setBackground(XmlUtils.getColor(node, "color", getBackground()));
      }
      
      if (node.hasAttribute("background"))
      {
         BufferedImage image = Resource.getImage(node.getAttribute("background").getValue());
         if (image  != null)
         {
            backgroundImage = image;
         }
      }
   }
   
   @Override
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
     
      if (backgroundImage != null)
      {
         g.drawImage(backgroundImage, 0, 0, null);
      }
   }
   
   private BufferedImage backgroundImage;
}
