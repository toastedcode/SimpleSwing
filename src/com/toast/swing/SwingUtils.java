package com.toast.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.lang.reflect.Field;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

public class SwingUtils
{
   public static void setMaximumSize(Component component, XmlNode node)
   {
      int width = XmlUtils.getInt(node,  "maxWidth", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "maxHeight", Integer.MAX_VALUE);
      
      component.setMaximumSize(new Dimension(width, height));
   }
   
   public static void setPreferredSize(Component component, XmlNode node)
   {
      int width = XmlUtils.getInt(node,  "prefWidth", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "prefHeight", Integer.MAX_VALUE);
      
      component.setPreferredSize(new Dimension(width, height));
   }
   
   public static void setSize(Component component, XmlNode node)
   {
      int width = XmlUtils.getInt(node,  "width", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "height", Integer.MAX_VALUE);
      
      component.setSize(new Dimension(width, height));
   }
   
   public static void setLayout(JComponent component, XmlNode node)
   {
      String layout = XmlUtils.getString(node,  "layout", "box");
      
      switch (layout)
      {
         case "absolute":
         {
            component.setLayout(null);
            break;
         }
         
         case "box":
         default:
         {
            String orientationString = XmlUtils.getString(node, "orientation", "horizontal");
            int orientation = orientationString.equals("vertical") ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS;
            
            component.setLayout(new BoxLayout(component, orientation));            
         }
      }
   }
   
   public static void setText(JButton component, XmlNode node)
   {
      component.setText(XmlUtils.getString(node,  "text", ""));
   }
   
   public static void setBorder(JComponent component, XmlNode node) throws XmlFormatException
   {
      if (node.hasAttribute("border"))
      {
         Color color = null;
         
         try
         {
             Field field = Class.forName("java.awt.Color").getField(node.getAttribute("border").getValue());
             color = (Color)field.get(null);
         }
         catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e)
         {
            System.out.format("Could not create border of color [%s].\n", node.getAttribute("border").getValue());
         }
         
         if (color != null)
         {
            component.setBorder(BorderFactory.createLineBorder(color));
         }
      }
   }
   
   public static Component getChildByName(Component component, String name)
   {
      Component foundComponent = null;
      
      if ((component.getName() != null) &&
          (component.getName().equals(name)))
      {
         foundComponent = component;
      }
      else if (component instanceof Container)
      {
         for (Component childComponent : ((Container)component).getComponents())
         {
            foundComponent = getChildByName(childComponent, name);
            if (foundComponent != null)
            {
               break;
            }
         }
      }
      
      return (foundComponent);
   }
   
   public static Component getAncestorByName(Component component, String name)
   {
      Component foundComponent = null;
      
      if ((component.getName() != null) &&
          (component.getName().equals(name)))
      {
         foundComponent = component;
      }
      else if (component.getParent() != null)
      {
         foundComponent = getAncestorByName(component.getParent(), name);
      }
      
      return (foundComponent);
   }
}
