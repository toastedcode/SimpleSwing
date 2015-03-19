package com.toast.swing;

import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;

import com.toast.xml.XmlNode;

public class SimpleSwing
{
   public static Component create(XmlNode node)
   {
      Component component = null;
      
      if (node.getName().equals("strut"))
      {
         component = createStrut(node);
      }
      else if (node.getName().equals("glue"))
      {
         component = createGlue(node);
      }
      else
      {
         try
         {
            Class<?> componentClass = null;
                  
            if (node.hasAttribute("class"))
            {
               //
               // Create a custom SimpleSwing components.
               //
               
               componentClass = Class.forName(node.getAttribute("class"));               
            }
            else
            {
               //
               // Create one of the standard SimpleSwing components.
               //
               
               componentClass = getClass(node.getName());
            }
            
            if (componentClass != null)
            {
               Constructor<?> ctor = componentClass.getConstructor(XmlNode.class);
               component = (Component)ctor.newInstance(node);
            }
         }
         catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | 
               InstantiationException | ClassNotFoundException e)
         {
            System.out.printf("Failed to create component [%s].\n", node.getName());
         }
      }
      
      return (component);
   }
   
   private static Class<?> getClass(String className)
   {
      if (componentClasses.isEmpty())
      {
         componentClasses.put("frame", Frame.class);
         componentClasses.put("panel", Panel.class);
         componentClasses.put("button", Button.class);
         componentClasses.put("label", Label.class);
      }
      
      return (componentClasses.get(className));
   }
   
   private static Component createStrut(XmlNode node)
   {
      Component component = null;
      
      String orientation = XmlUtils.getString(node,  "orientation", "horizontal");
      int size = XmlUtils.getInt(node,  "size", 0);
      
      if (orientation.equals("horizontal"))
      {
         component = Box.createHorizontalStrut(size);
      }
      else
      {
         component = Box.createVerticalStrut(size);         
      }
      
      return (component);
   }
   
   private static Component createGlue(XmlNode node)
   {
      Component component = null;
      
      String orientation = XmlUtils.getString(node,  "orientation", "horizontal");
      
      if (orientation.equals("horizontal"))
      {
         component = Box.createHorizontalGlue();
      }
      else
      {
         component = Box.createVerticalGlue();         
      }
      
      return (component);      
   }
   
   public static Map<String, Class<?>> componentClasses = new HashMap<>();
}
