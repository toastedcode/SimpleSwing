package com.toast.swing;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.toast.swing.component.Panel;
import com.toast.xml.XmlNode;
import com.toast.xml.exception.XmlFormatException;

@SuppressWarnings("serial")
public class CollapsiblePanel extends Panel
{

   public CollapsiblePanel(XmlNode node)  throws XmlFormatException
   {
      super(node);
   }
   
   public void collapse(MouseEvent event)
   {
      contentPanel.setVisible(!contentPanel.isVisible());
      revalidate();
      repaint();
   }
   
   JPanel contentPanel = null;
}