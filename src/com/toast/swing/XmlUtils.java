package com.toast.swing;

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
}
