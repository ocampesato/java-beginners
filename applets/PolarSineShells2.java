import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;

public class PolarSineShells2 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width      = 800;
   private int height     = 500;

   private int basePointX = 400;
   private int basePointY = 250;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private double offsetX = 0;
   private double offsetY = 0;

   private int sideLength = 4;
   private double radius = 80;
   private double Radius = 160;

   private int maxU = 360; // 180;
   private int maxV = 360; // 180;

   private double sineU = 0;
   private double sineV = 0;

   private double cosineU = 0;
   private double cosineV = 0;

   private int baseAngle = 40;

   private double frequency = 1.0;

   private int startTheta = (int)(0/frequency);
   private int endTheta   = (int)(360/frequency);

   private Color[] rectangleColors = {
      Color.red, Color.green, Color.blue, Color.yellow,
      Color.white, Color.magenta 
   };

   private int colorCount = rectangleColors.length;


   public PolarSineShells2()
   {
   } 
   

   public void init()
   {
      offScreenImage  = this.createImage(width, height);
      offScreenBuffer = offScreenImage.getGraphics();

   } // init 
   

   public void update(Graphics gc)
   {
      paint(gc);

   } // update


   public void paint(Graphics gc)
   {
      offScreenBuffer.setColor(Color.lightGray);
      offScreenBuffer.fillRect(0, 0, width, height);

      drawSpiral(offScreenBuffer, gc);
      gc.drawImage(offScreenImage, 0, 0, this);

   } // paint


   public void drawSpiral(Graphics gc, Graphics gcMain)
   {
      double x=0.0, y=0.0, z=0.0; 

///////////////////////////////////
// 3D -> 2D mapping:
// offsetX = x + z*cos(theta);
// offsetY = y + z*sin(theta);
///////////////////////////////////

      for(int u=0; u<maxU; u++)
      {
         for(int v=0; v<maxV; v++)
         {
            sineU   = Math.sin(u*Math.PI/180);
            sineV   = Math.sin(v*Math.PI/180);

            cosineU = Math.cos(u*Math.PI/180);
            cosineV = Math.cos(v*Math.PI/180);

            for(radius=sideLength; radius<Radius; radius += 8*sideLength)
            {
               x = radius*sineV;
               y = radius*cosineV;
               z = radius*sineU*cosineV;
   
               offsetX = x+z*Math.cos(baseAngle*Math.PI/180);
               offsetY = y+z*Math.sin(baseAngle*Math.PI/180);
   
               currentX = basePointX+(int)offsetX;
               currentY = basePointY+(int)offsetY;
               drawRectangle(gc, u+v, currentX, currentY, 
                                 (int)(radius/(8*sideLength)));
            }
         }
         gcMain.drawImage(offScreenImage, 0, 0, this);
      }

   } // drawSpiral


   public void drawRectangle(Graphics gc, int x, int xCoord, int yCoord, int c)
   {
      gc.setColor(rectangleColors[((x%2)*2+c)%colorCount]);
      gc.draw3DRect(xCoord, yCoord, sideLength, sideLength, true); 

   } // drawRectangle

} // PolarSineShells2
