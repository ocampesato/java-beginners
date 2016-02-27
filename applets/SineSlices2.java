import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;


public class SineSlices2 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width      = 800;
   private int height     = 500;

   private int basePointX = 100;
   private int basePointY = 300;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private int previousX  = -1;
   private int previousY  = -1;

   private double slantX   = 0;
   private double slantY   = 0;

   private int currAmplitude = 0;
   private int minAmplitude  = 0;
   private int maxAmplitude  = 300;
   private int midAmplitude  = (minAmplitude+maxAmplitude)/2;

   private int currentColor  = 0;
   private int colorCount    = 4;
   private int[][] colorWeights = new int[colorCount][3];
   private int[] currentWeights = new int[3];

   private int ampDirection  = 1;
   private int ampDelta      = 5;
   private double frequency  = 2.0;

   private int baseAngle   = 0;
   private int slantLength = maxAmplitude;

   private int startAngle = 0;
   private int endAngle   = (int)(180/frequency); 

   private double deltaY  = 0;


   public SineSlices2()
   {
   } 
   

   public void init()
   {
      offScreenImage  = this.createImage(width, height);
      offScreenBuffer = offScreenImage.getGraphics();

      initializeColorWeights();

   } // init 
   

   public void initializeColorWeights()
   {
      // red
      colorWeights[0][0] = 255;
      colorWeights[0][1] = 0;
      colorWeights[0][2] = 0;

      // green 
      colorWeights[1][0] = 0;
      colorWeights[1][1] = 255;
      colorWeights[1][2] = 0;

      // blue
      colorWeights[2][0] = 0;
      colorWeights[2][1] = 0;
      colorWeights[2][2] = 255;

      // yellow
      colorWeights[3][0] = 255;
      colorWeights[3][1] = 255;
      colorWeights[3][2] = 0;

   } // initializeColorWeights


   public void update(Graphics gc)
   {
      paint(gc);

   } // update


   public void paint(Graphics gc)
   {
      offScreenBuffer.setColor(Color.lightGray);
      offScreenBuffer.fillRect(0, 0, width, height);

      for(int k=0; k<slantLength; k++)
      {
         drawSineWave(offScreenBuffer, k);
         updateCoordinates();
      }

      gc.drawImage(offScreenImage, 0, 0, this);

   } // paint


   public void updateCoordinates()
   {
      currAmplitude += ampDirection*ampDelta;

      if( currAmplitude > midAmplitude )
      {
         currAmplitude = midAmplitude;
         ampDirection  *= -1;
         resetCurrentWeights();
      }

      if( currAmplitude < minAmplitude )
      {
         currAmplitude = minAmplitude;
         ampDirection  *= -1;
         resetCurrentWeights();
      }

   } // updateCoordinates


   public void resetCurrentWeights()
   {
       ++currentColor; 
       currentColor %= 3; 

       for(int w=0; w<3; w++)
       {
          currentWeights[w] = colorWeights[currentColor][w];
       }

   } // resetCurrentWeights


   public void drawSineWave(Graphics gc, int k)
   {
      slantX = k*Math.cos(baseAngle*Math.PI/180);
      slantY = k*Math.sin(baseAngle*Math.PI/180);

      previousX = basePointX; 
      previousY = basePointY;

      for(int angle=startAngle; angle<endAngle; angle++)
      {
         deltaY = currAmplitude*Math.sin(frequency*angle*Math.PI/180);

         currentX = basePointX+angle;
         currentY = basePointY-(int)deltaY; 

         currentX += (int)slantX;
         currentY -= (int)slantY;

         // draw consecutive points on sine wave...
         gc.setColor(new Color(currentWeights[0],
                               currentWeights[1],
                               currentWeights[2]));
   
         gc.drawLine(previousX,
                     previousY,
                     currentX,
                     currentY);

         previousX = currentX;
         previousY = currentY;
      }

   } // drawSineWave

} // SineSlices2
