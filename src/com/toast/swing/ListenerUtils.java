package com.toast.swing;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

public class ListenerUtils
{
   public static void setListeners(final Component component, XmlNode node) throws XmlFormatException
   {
      if (node.hasAttribute("onMouseClick"))
      {
         StringTokenizer tokenizer = new StringTokenizer(node.getAttribute("onMouseClick").getValue(), ".");
         
         if (tokenizer.countTokens() <= 2)
         {
            Component listener = component;
            if (tokenizer.countTokens() == 2)
            {
               listener = SwingUtils.getAncestorByName(component,  tokenizer.nextToken());
            }
            
            if (listener != null)
            {
               String methodName = tokenizer.nextToken();
   
               setOnMouseClick(component, listener, methodName);
            }
            else
            {
               System.out.format("Invalid listener [%s].\n", node.getAttribute("onMouseClick"));               
            }
         }
         else
         {
            System.out.format("Invalid listener [%s].\n", node.getAttribute("onMouseClick"));            
         }
      }
      
      if (node.hasAttribute("onMouseOver"))
      {
         StringTokenizer tokenizer = new StringTokenizer(node.getAttribute("onMouseOver").getValue(), ".");
         
         if (tokenizer.countTokens() <= 2)
         {
            Component listener = component;
            if (tokenizer.countTokens() == 2)
            {
               listener = SwingUtils.getAncestorByName(component,  tokenizer.nextToken());
            }
            
            if (listener != null)
            {
               String methodName = tokenizer.nextToken();
   
               setOnMouseOver(component, listener, methodName);
            }
            else
            {
               System.out.format("Invalid listener [%s].\n", node.getAttribute("onMouseOver").getValue());               
            }            
         }
         else
         {
            System.out.format("Invalid listener [%s].\n", node.getAttribute("onMouseOver").getValue());            
         }
      }
      
      if (node.hasAttribute("onMouseOut"))
      {
         StringTokenizer tokenizer = new StringTokenizer(node.getAttribute("onMouseOut").getValue(), ".");
         
         if (tokenizer.countTokens() <= 2)
         {
            Component listener = component;
            if (tokenizer.countTokens() == 2)
            {
               listener = SwingUtils.getAncestorByName(component,  tokenizer.nextToken());
            }
            
            if (listener != null)
            {
               String methodName = tokenizer.nextToken();
   
               setOnMouseOut(component, listener, methodName);
            }
            else
            {
               System.out.format("Invalid listener [%s].\n", node.getAttribute("onMouseOut"));            
            }            
         }
         else
         {
            System.out.format("Invalid listener [%s].\n", node.getAttribute("onMouseOut"));            
         }
      }  
   }
   
   public static void setOnMouseClick(Component component, final Component listener, String methodName)
   {
      try
      {
         final Method method = listener.getClass().getMethod(methodName, MouseEvent.class);
         
         component.addMouseListener(new MouseListener()
         {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event)
            {
               try
               {
                  method.invoke(listener, event);
               }
               catch (InvocationTargetException | IllegalAccessException e)
               {
                  System.out.format("Invalid listener.\n");                             
               }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent arg0) {}

            @Override
            public void mouseExited(java.awt.event.MouseEvent arg0) {}

            @Override
            public void mousePressed(java.awt.event.MouseEvent arg0) {}

            @Override
            public void mouseReleased(java.awt.event.MouseEvent arg0) {}
         });            
      }
      catch (NoSuchMethodException e)
      {
         System.out.format("Invalid listener [%s].\n", methodName);                   
      }         
   }
   
   public static void setOnMouseOver(final Component component, final Component listener, String methodName)
   {
      try
      {
         final Method method = listener.getClass().getMethod(methodName, MouseEvent.class);
         
         component.addMouseListener(new MouseListener()
         {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {}

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event)
            {
               try
               {
                  method.invoke(listener, event);
               }
               catch (InvocationTargetException | IllegalAccessException e)
               {
                  System.out.format("Invalid listener.\n");                             
               }                  
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {}

            @Override
            public void mousePressed(java.awt.event.MouseEvent event) {}

            @Override
            public void mouseReleased(java.awt.event.MouseEvent event) {}
         });            
      }
      catch (NoSuchMethodException e)
      {
         System.out.format("Invalid listener [%s].\n", methodName);                   
      }         
   }
   
   public static void setOnMouseOut(final Component component, final Component listener, String methodName)
   {
      try
      {
         final Method method = listener.getClass().getMethod(methodName, MouseEvent.class);
         
         component.addMouseListener(new MouseListener()
         {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {}

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {}

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) 
            {
               try
               {
                  method.invoke(listener, event);
               }
               catch (InvocationTargetException | IllegalAccessException e)
               {
                  System.out.format("Invalid listener.\n");                             
               }                  
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent event) {}

            @Override
            public void mouseReleased(java.awt.event.MouseEvent event) {}
         });            
      }
      catch (NoSuchMethodException e)
      {
         System.out.format("Invalid listener [%s].\n", methodName);                   
      }         
   }
}
