import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;


public class SineLineOscillatingEllipticLava1 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width      = 800;
   private int height     = 500;

   private int basePointX = 50;
   private int basePointY = 250;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private int eWidth  = 80;
   private int minEWidth  = 40;
   private int maxEWidth  = 160;

   private int eWidthDelta  = 4;
   private int eWidthDirection = 1;

   private int eHeight = 40;
   private int minEHeight  = 40;
   private int maxEHeight  = 160;

   private int eHeightDelta  = 6;
   private int eHeightDirection = 1;

   private int waveWidth  = 200;
   private int minWaveWidth = 10;
   private int maxWaveWidth = 240;
   private int waveWidthDelta = 2;
   private int waveWidthDirection = 1;

   private int lineAngle  = 20;

   private int offsetX    = 0;
   private int offsetY    = 0;

   private int amplitude  = 40;
   private int frequency  = 6;

   private int maxCount   = 300;
   private double deltaY  = 0;

   private Color[] lineColors = { 
       Color.red, Color.blue, Color.green, Color.white, Color.yellow
   };


   public SineLineOscillatingEllipticLava1()
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
      for(int tick=0; tick<maxCount; tick++)
      {
         offScreenBuffer.setColor(Color.lightGray);
         offScreenBuffer.fillRect(0, 0, width, height);
   
         drawSineWave(offScreenBuffer);
         gc.drawImage(offScreenImage, 0, 0, this);
         updateCoordinates();
      }

   } // paint


   public void updateCoordinates()
   {
      eWidth += eWidthDelta*eWidthDirection;
      if( eWidth < minEWidth )
      {
         eWidth = minEWidth;
         eWidthDirection *= -1;
      }

      if( eWidth > maxEWidth )
      {
         eWidth = maxEWidth;
         eWidthDirection *= -1;
      }

      eHeight += eHeightDelta*eHeightDirection;
      if( eHeight < minEHeight )
      {
         eHeight = minEHeight;
         eHeightDirection *= -1;
      }

      if( eHeight > maxEHeight )
      {
         eHeight = maxEHeight;
         eHeightDirection *= -1;
      }

      waveWidth += waveWidthDelta*waveWidthDirection;
      if( waveWidth > maxWaveWidth )
      {
         waveWidth = maxWaveWidth;
         waveWidthDirection *= -1;
      }
   
      if( waveWidth < minWaveWidth )
      {
         waveWidth = minWaveWidth;
         waveWidthDirection *= -1;
      }

   } // updateCoordinates();


   public void drawSineWave(Graphics gc)
   {
      for(int angle=0; angle<waveWidth; angle++)
      {
         gc.setColor(lineColors[angle%2]);

         deltaY   = amplitude*Math.sin(frequency*angle*Math.PI/180);
         currentX = basePointX+angle;
         currentY = basePointY-(int)deltaY; 

         offsetX = (int)(angle*Math.cos(lineAngle*Math.PI/180)); 
         offsetY = (int)(angle*Math.sin(lineAngle*Math.PI/180)); 

         gc.fillOval(currentX+offsetX, 
                     currentY+offsetY, 
                     offsetX, 
                     offsetY);
      }

   } // drawSineWave

} // SineLineOscillatingEllipticLava1
