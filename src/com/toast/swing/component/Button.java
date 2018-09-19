package com.toast.swing.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.toast.swing.Resource;
import com.toast.swing.XmlUtils;
import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class Button extends JButton
{
   //
   // Experimental !!!
   //
   
   //@SwingFactory(keyword = "button")
   static Component createButton(XmlNode node) throws XmlFormatException
   {
      JButton button = new JButton();
      
      // id
      if (node.hasAttribute("id"))
      {
         button.setName(node.getAttribute("id").getValue());
      }
      
      // size
      int width = XmlUtils.getInt(node,  "width", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "height", Integer.MAX_VALUE);
      button.setMaximumSize(new Dimension(width, height));
      button.setPreferredSize(new Dimension(width, height));
      
      // text
      button.setText(XmlUtils.getString(node,  "text", ""));
      button.setHorizontalTextPosition(JButton.CENTER);
      button.setVerticalTextPosition(JButton.CENTER);
      
      // textColor
      button.setForeground(XmlUtils.getColor(node, "textColor", Color.BLACK));
      
      // icon
      String imageString = XmlUtils.getString(node,  "image", "");
      if (imageString.length() > 0)
      {
         BufferedImage image = Resource.getImage(XmlUtils.getString(node,  "image", ""));
         if (image != null)
         {
            button.setIcon(new ImageIcon(image));
         }
      }
      
      return (button);
   }
   
   public Button(XmlNode node) throws XmlFormatException
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
      setHorizontalTextPosition(JButton.CENTER);
      setVerticalTextPosition(JButton.CENTER);
      
      // textColor
      setForeground(XmlUtils.getColor(node, "textColor", Color.BLACK));
      
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
      
      // onclick
      if (node.hasAttribute("onclick"))
      {
         try
         {
            Class<?> listenerClass = Class.forName(node.getAttribute("onclick").getValue());
            Constructor<?> ctor = listenerClass.getConstructor();
            ActionListener listener = (ActionListener)ctor.newInstance();

            this.addActionListener((ActionListener)listener);
         }
         catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | 
                IllegalAccessException | InstantiationException | IllegalArgumentException e)
         {
            System.out.format("Failed to set class [%s] as action listener.", node.getAttribute("onclick"));
         }
      }
   }
}
