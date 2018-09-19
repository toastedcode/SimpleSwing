package com.toast.swing.component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.toast.swing.SwingUtils;
import com.toast.xml.XmlNode;
import com.toast.xml.XmlNodeList;
import com.toast.xml.exception.XPathExpressionException;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class Frame extends JFrame
{
   public Frame(XmlNode node) throws XmlFormatException
   {
      super();
      
      setup(node);
   }
   
   protected void setup(XmlNode node) throws XmlFormatException
   {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id").getValue());
      }
      
      SwingUtils.setSize(this, node);
      
      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

      try
      {
         XmlNodeList childNodes = node.getNodes("./*");
         
         if (childNodes.size() > 1)
         {
            System.out.format("Warning! Frame contains more than one content pane.\n");
         }
      }
      catch (XPathExpressionException e)
      {
         // TODO
      }
   }
}
