package com.toast.swing.component;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import com.toast.swing.SwingUtils;
import com.toast.xml.XmlNode;
import com.toast.xml.XmlNodeList;

@SuppressWarnings("serial")
public class Dialog extends JDialog
{
   public Dialog(XmlNode node)
   {
      super();
      
      setResizable(false);
      
      setup(node);
   }
   
   protected void setup(XmlNode node)
   {
      // id
      if (node.hasAttribute("id"))
      {
         setName(node.getAttribute("id"));
      }
      
      // width, height
      SwingUtils.setSize(this, node);
      
      // isModal
      if (node.hasAttribute("isModal") == true)
      {
         this.setModal(Boolean.valueOf(node.getAttribute("isModal")));
      }
      
      //getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
      
      XmlNodeList childNodes = node.getNodes("./*");
      
      if (childNodes.getLength() > 1)
      {
         System.out.format("Warning! Frame contains more than one content pane.\n");
      }
   }
}
