package com.toast.swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.toast.xml.XmlNode;

@SuppressWarnings("serial")
public class Panel extends JPanel
{
   public Panel(XmlNode node)
   {
      super();
      
      setup(node);
   }
   
   protected void setup(XmlNode node)
   {
      SwingUtils.setLayout(this, node);
      
      SwingUtils.setBorder(this,  node);
      
      SwingUtils.setMaximumSize(this,  node);
      
      if (node.hasAttribute("background"))
      {
         BufferedImage image = Resource.getImage(node.getAttribute("background"));
         if (image  != null)
         {
            backgroundImage = image;
         }
      }
      
      SwingUtils.createChildren(this,  node);
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
