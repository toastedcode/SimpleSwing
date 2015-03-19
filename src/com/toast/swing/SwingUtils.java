package com.toast.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import com.toast.xml.XmlNode;
import com.toast.xml.XmlNodeList;

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
      String orientationString = XmlUtils.getString(node, "orientation", "horizontal");
      int orientation = orientationString.equals("vertical") ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS;
      
      component.setLayout(new BoxLayout(component, orientation));
   }
   
   public static void setText(JButton component, XmlNode node)
   {
      component.setText(XmlUtils.getString(node,  "text", ""));
   }
   
   public static void setBorder(JComponent component, XmlNode node)
   {
      if (node.hasAttribute("border"))
      {
         Color color = null;
         
         try
         {
             Field field = Class.forName("java.awt.Color").getField(node.getAttribute("border"));
             color = (Color)field.get(null);
         }
         catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e)
         {
            System.out.format("Could not create border of color [%s].\n", node.getAttribute("border"));
         }
         
         if (color != null)
         {
            component.setBorder(BorderFactory.createLineBorder(color));
         }
      }
   }
   
   public static void createChildren(JComponent parent, XmlNode node)
   {
      XmlNodeList childNodes = node.getNodes("./*");
      for (int i = 0; i < childNodes.getLength(); i++)
      {
         XmlNode childNode = childNodes.item(i);
         
         Component child = SimpleSwing.create(childNode);
         if (child != null)
         {
            parent.add(child);
            
            if (childNode.hasAttribute("id"))
            {
               setField(parent, child, childNode.getAttribute("id"));
            }
         }
      }  
   }
   
   private static void setField(Component parent, Component child, String fieldName)
   {
      try
      {
         Class<?> parentClass = parent.getClass();
         
         Field field = parentClass.getDeclaredField(fieldName);
         
         field.set(parent, child);
      }
      catch (NoSuchFieldException | IllegalAccessException e)
      {
         System.out.format("Failed to set field [%s].\n", fieldName);
      }
   }
}
