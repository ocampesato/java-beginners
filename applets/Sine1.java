import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;


public class Sine1 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width      = 800;
   private int height     = 500;

   private int pWidth     = 600;
   private int pHeight    = 500;

   private int basePointX = 100;
   private int basePointY = 250;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private int amplitude  = 40;
   private int frequency  = 1;

   private int rVal       = 0;
   private int gVal       = 0;
   private int bVal       = 255;
   
   private int startAngle = 0;
   private int endAngle   = pWidth; // 90

   private int previousX  = -1;
   private int previousY  = -1;

   private int hGridDelta = 50;
   private int vGridDelta = 50;

   private int xAxisLength = 800;
   private int yAxisLength = 400;


   public Sine1()
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

      drawGrid(offScreenBuffer);
      drawAxes(offScreenBuffer);
      drawSineWave(offScreenBuffer);

      gc.drawImage(offScreenImage, 0, 0, this);

   } // paint


   public void drawSineWave(Graphics gc)
   {
      double deltaY=0;

      gc.setColor(new Color(rVal, gVal, bVal));

      previousX = basePointX; 
      previousY = basePointY;

      for(int angle=startAngle; angle<endAngle; angle++)
      {
         deltaY   = amplitude*Math.sin(frequency*angle*Math.PI/180);
         currentX = basePointX+angle;
         currentY = basePointY-(int)deltaY; 

         // draw consecutive points on sine wave...
         gc.drawLine(previousX,
                     previousY, 
                     currentX, 
                     currentY);

         previousX = currentX;
         previousY = currentY;
      }

   } // drawSineWave


   public void drawGrid(Graphics gc)
   {
      gc.setColor(Color.white);

      for(int row=0; row<600/vGridDelta; ++row)
      {
         for(int col=0; col<800/hGridDelta; ++col)
         {
            gc.draw3DRect(basePointX+col*hGridDelta,
                        basePointY-yAxisLength/2+row*vGridDelta,
                        hGridDelta,
                        vGridDelta,
                        true);
         }
      }

   } // drawGrid


   public void drawAxes(Graphics gc)
   {
      gc.setColor(Color.red);

      // horizontal axis...
      gc.drawLine(0,
                  basePointY, 
                  basePointX+xAxisLength,  
                  basePointY);

      // vertical axis...
      gc.drawLine(basePointX,
                  basePointY-yAxisLength/2, 
                  basePointX,  
                  basePointY+yAxisLength/2);

   } // drawAxes

} // Sine1


