package com.toast.swing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Resource
{
   public static BufferedImage getImage(String name)
   {
      if (images.containsKey(name) == false)
      {
         loadImage(name);
      }
      
      return (images.get(name));
   }
   
   private static void loadImage(String name)
   {
      try
      {
         BufferedImage image = ImageIO.read(Resource.class.getResource(name));
         images.put(name,  image);
      }
      catch (IOException e)
      {
         System.out.printf("Failed to load %s\n.", name);
      }
   }
   
   private static Map<String, BufferedImage> images = new HashMap<>();
}
