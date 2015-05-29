package com.toast.swing.component;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import com.toast.swing.SwingUtils;
import com.toast.swing.XmlUtils;
import com.toast.xml.XmlNode;

@SuppressWarnings("serial")
public class ScrollPane extends JScrollPane
{
   public ScrollPane(XmlNode node)
   {
      super();
      
      setup(node);
   }

   protected void setup(XmlNode node)
   {
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id"));
      }
      
      // size
      int width = XmlUtils.getInt(node,  "width", Integer.MAX_VALUE);
      int height = XmlUtils.getInt(node,  "height", Integer.MAX_VALUE);
      setMaximumSize(new Dimension(width, height));
      
      // horizontalScroll
      if (node.hasAttribute("horizontalScroll"))
      {
         if (Boolean.valueOf(node.getAttribute("horizontalScroll")) == true)
         {
            setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);         
         }
         else
         {
            setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         }
      }
      
      // verticalScroll
      if (node.hasAttribute("verticalScroll"))
      {
         if (Boolean.valueOf(node.getAttribute("verticalScroll")) == true)
         {
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);         
         }
         else
         {
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
         }
      }
   }
}