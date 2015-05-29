package com.toast.swing;

import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toast.swing.component.Button;
import com.toast.swing.component.Dialog;
import com.toast.swing.component.Frame;
import com.toast.swing.component.Label;
import com.toast.swing.component.Panel;
import com.toast.swing.component.ScrollPane;
import com.toast.swing.component.TextField;
import com.toast.xml.XmlNode;
import com.toast.xml.XmlNodeList;

public class SimpleSwing
{
   public static Component create(XmlNode node)
   {
      return (create(node, null));
   }
   
   public static Component create(XmlNode node, Component parent)
   {
      Component component = null;
      
      if (node.getName().equals("frame"))
      {
         component = createFrame(node);         
      }
      else
      {
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
                  try
                  {
                     // First, try for a Component(XmlNode node) constructor
                     Constructor<?> ctor = componentClass.getConstructor(XmlNode.class);
                     component = (Component)ctor.newInstance(node);
                  }
                  catch (NoSuchMethodException e)
                  {
                     // Next, try for a Component() constructor
                     Constructor<?> ctor = componentClass.getConstructor();
                     component = (Component)ctor.newInstance();                     
                  }
               }
            }
            catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | 
                  InstantiationException | ClassNotFoundException e)
            {
               System.out.printf("Failed to create component [%s].\n%s", node.getName(), e.toString());
            }
         }
         
         if (component != null)
         {
            // Add into the component tree.
            if (parent != null)
            {
               if (parent instanceof JFrame)
               {
                  ((JFrame)parent).setContentPane((JPanel)component);
               }
               else if (parent instanceof JScrollPane)
               {
                  ((JScrollPane)parent).getViewport().add(component);
               }
               else if (parent instanceof JDialog)
               {
                  ((JDialog)parent).add(component);
               }               
               else
               {
                  ((JComponent)parent).add(component);
               }
            }
            
            // Set fields.
            if (node.hasAttribute("id"))
            {
               setField(component, node.getAttribute("id"));
            }
            
            // Set listeners.
            ListenerUtils.setListeners(component, node);
            
            // Create children.
            createChildren(component, node);
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
         componentClasses.put("scrollPane", ScrollPane.class);
         componentClasses.put("button", Button.class);
         componentClasses.put("label", Label.class);
         componentClasses.put("input", TextField.class);
         componentClasses.put("dialog", Dialog.class);
      }
      
      return (componentClasses.get(className));
   }
   
   private static Component createFrame(XmlNode node)
   {
      JFrame frame = new Frame(node);
      
      createChildren(frame, node);
      
      frame.setVisible(true);
      
      return (frame);
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
   
   private static void createChildren(Component parent, XmlNode node)
   {
      XmlNodeList childNodes = node.getNodes("./*");
      for (int i = 0; i < childNodes.getLength(); i++)
      {
         XmlNode childNode = childNodes.item(i);
         
         SimpleSwing.create(childNode, parent);
      }  
   }
   
   private static void setField(Component child, String fieldName)
   {
      Component parent = child.getParent();
      
      while (parent != null)
      {
         try
         {
            Class<?> parentClass = parent.getClass();
            
            Field field = parentClass.getDeclaredField(fieldName);
            
            field.set(parent, child);
            
            break;
         }
         catch (NoSuchFieldException | IllegalAccessException e)
         {
            //System.out.format("Failed to set field [%s].\n", fieldName);
            parent = parent.getParent();
         }
      }
   }
  
   public static Map<String, Class<?>> componentClasses = new HashMap<>();
}
