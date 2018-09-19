package com.toast.swing.component;

import javax.swing.JDialog;

import com.toast.swing.SwingUtils;
import com.toast.xml.XmlNode;
import com.toast.xml.XmlNodeList;
import com.toast.xml.exception.XPathExpressionException;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class Dialog extends JDialog
{
   public Dialog(XmlNode node) throws XmlFormatException
   {
      super();
      
      setResizable(false);
      
      setup(node);
   }
   
   protected void setup(XmlNode node) throws XmlFormatException
   {
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id").getValue());
      }
      
      // width, height
      SwingUtils.setSize(this, node);
      
      // isModal
      if (node.hasAttribute("isModal") == true)
      {
         this.setModal(node.getAttribute("isModal").getBoolValue());
      }
      
      //getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
      
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
