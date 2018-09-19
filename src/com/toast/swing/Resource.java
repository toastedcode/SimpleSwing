package com.toast.swing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.toast.xml.XmlDocument;
import com.toast.xml.exception.XmlParseException;

public class Resource
{
   public static File getFile(String name)
   {
      if (files.containsKey(name) == false)
      {
         loadFile(name);
      }
      
      return (files.get(name));      
   }
   
   public static BufferedImage getImage(String name)
   {
      if (images.containsKey(name) == false)
      {
         loadImage(name);
      }
      
      return (images.get(name));
   }
   
   public static XmlDocument getXmlDocument(String name)
   {
      if (documents.containsKey(name) == false)
      {
         loadXmlDocument(name);
      }
      
      return (documents.get(name));
   }
   
   private static File loadFile(String name)
   {
      File file = null;
      
      file = new File(Resource.class.getResource(name).getPath());
      files.put(name,  file);
      
      return (file);
   }
   
   private static BufferedImage loadImage(String name)
   {
      BufferedImage image = null;
      
      try
      {
         image = ImageIO.read(Resource.class.getResourceAsStream(name));
         images.put(name,  image);
      }
      catch (IOException | IllegalArgumentException e)
      {
         System.out.printf("Failed to load %s\n.", name);
      }
      
      return (image);
   }
   
   private static XmlDocument loadXmlDocument(String name)
   {
      XmlDocument document = new XmlDocument();
      
      try
      {
         document.load(name);
         documents.put(name,  document);
      }
      catch (IllegalArgumentException | IOException | XmlParseException e)
      {
         System.out.printf("Failed to load %s\n.", name);
         document = null;
      }
      
      return (document);
   }
   
   private static Map<String, File> files = new HashMap<>();
   
   private static Map<String, BufferedImage> images = new HashMap<>();
   
   private static Map<String, XmlDocument> documents = new HashMap<>();
}