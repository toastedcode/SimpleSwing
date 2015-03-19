package com.toast.swing;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.toast.xml.XmlNode;

@SuppressWarnings("serial")
public class Label extends JLabel
{
   public Label(XmlNode node)
   {
      super();
      
      setup(node);
   }
   
   protected void setup(XmlNode node)
   {
      // maximum width/height
      SwingUtils.setMaximumSize(this,  node);
      
      // preferred width/height
      SwingUtils.setPreferredSize(this,  node);
      
      // text
      setText(XmlUtils.getString(node,  "text", ""));
      
      // alignment
      // TODO: For now, assume center.
      setHorizontalAlignment(SwingConstants.CENTER);
      
      // border
      // TODO: For now, assume line.
      SwingUtils.setBorder(this,  node);
      
      // icon
      String imageString = XmlUtils.getString(node,  "image", "");
      if (imageString.length() > 0)
      {
         BufferedImage image = Resource.getImage(XmlUtils.getString(node,  "image", ""));
         if (image != null)
         {
            setIcon(new ImageIcon(image));
         }
      }
   }
}