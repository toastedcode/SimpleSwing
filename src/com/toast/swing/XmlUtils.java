package com.toast.swing;

import java.awt.Color;
import java.lang.reflect.Field;

import com.toast.xml.XmlNode;

public class XmlUtils
{
   public static int getInt(XmlNode node, String attribute, int defaultValue)
   {
      int value = defaultValue;
      
      if (node.hasAttribute(attribute))
      {
         value = Integer.valueOf(node.getAttribute(attribute));
      }
      
      return (value);
   }
   
   public static String getString(XmlNode node, String attribute, String defaultValue)
   {
      String value = defaultValue;
      
      if (node.hasAttribute(attribute))
      {
         value = node.getAttribute(attribute);
      }
      
      return (value);
   }
   
   public static boolean getBool(XmlNode node, String attribute, boolean defaultValue)
   {
      boolean value = defaultValue;
      
      if (node.hasAttribute(attribute))
      {
         value = Boolean.valueOf(node.getAttribute(attribute));
      }
      
      return (value);
   }
   
   public static Color getColor(XmlNode node, String attribute, Color defaultValue)
   {
      Color value = defaultValue;
      
      try
      {
         if (node.hasAttribute(attribute))
         {
            Field field = Class.forName("java.awt.Color").getField(node.getAttribute(attribute).toLowerCase());
            value = (Color)field.get(null);      
         }
      }
      catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e)
      {
         value = null;
      }
      
      return (value);
   }
}
