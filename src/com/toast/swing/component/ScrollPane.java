package com.toast.swing.component;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import com.toast.swing.SwingUtils;
import com.toast.swing.XmlUtils;
import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class ScrollPane extends JScrollPane
{
   public ScrollPane(XmlNode node) throws XmlFormatException
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
      
      // horizontalScroll
      if (node.hasAttribute("horizontalScroll"))
      {
         if (Boolean.valueOf(node.getAttribute("horizontalScroll").getValue()) == true)
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
         if (node.getAttribute("verticalScroll").getBoolValue() == true)
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