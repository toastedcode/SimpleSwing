package com.toast.swing.component;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.toast.swing.Resource;
import com.toast.swing.SwingUtils;
import com.toast.swing.XmlUtils;
import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class Label extends JLabel
{
   public Label(XmlNode node) throws XmlFormatException
   {
      super();
      
      setup(node);
   }
   
   protected void setup(XmlNode node) throws XmlFormatException
   {
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id").getValue());
      }
      
      // size
      int width = XmlUtils.getInt(node,  "width", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "height", Integer.MAX_VALUE);
      setMaximumSize(new Dimension(width, height));
      setPreferredSize(new Dimension(width, height));
      
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