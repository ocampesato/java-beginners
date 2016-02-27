import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;

public class SineCosine1 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width  = 800;
   private int height = 500;

   private int pWidth     = 500;
   private int pHeight    = 400;

   private int basePointX = 50;
   private int basePointY = 300;

   private int currentSineX   = basePointX;
   private int currentSineY   = basePointY;

   private int currentCosineX = basePointX;
   private int currentCosineY = basePointY;

   private int previousSineX, previousSineY;
   private int previousCosineX, previousCosineY;

   private int offsetX    = 0;
   private int offsetY    = 0;

   private int iteration  = 0;

   private int bTheta     = 30;
   private double wTheta  = (Math.PI)/180;
   private double wPhase  = 90*(Math.PI)/180;

   private int amplitude  = 40;
   private int frequency  = 4;

   private int slantLength = 200;

   private int hGridDelta = 50;
   private int vGridDelta = 50;

   private int xAxisLength = 800;
   private int yAxisLength = 400;
   
   private Color[] waveColors = {
      Color.red, Color.green, Color.blue, Color.yellow,
      Color.pink, Color.magenta
   };


   public SineCosine1()
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

      drawSineCosineWave(offScreenBuffer, gc);

   } // paint


   public void drawSineCosineWave(Graphics gc, Graphics gcMain)
   {
      int currentPeriod=0;
      double deltaY=0;

      previousSineX   = basePointX; 
      previousSineY   = basePointY;
      previousCosineX = basePointX; 
      previousCosineY = basePointY;


      for(int angle=0; angle<pWidth; angle++)
      {
         deltaY = amplitude*Math.sin(frequency*angle*wTheta);

         currentSineX = basePointX+angle;
         currentSineY = basePointY+(int)deltaY; 

         offsetX = (int)(slantLength*Math.cos(bTheta*Math.PI/180));
         offsetY = (int)(slantLength*Math.sin(bTheta*Math.PI/180));

         currentCosineX = basePointX+angle;
         currentCosineY = basePointY+(int)(amplitude*
                            Math.cos(frequency*angle*wTheta));

         drawSineSegment(offScreenBuffer);
         drawCosineSegment(offScreenBuffer);

         currentPeriod = angle*frequency/360;
         if( (angle*frequency >= currentPeriod*360) && 
             (angle*frequency <= currentPeriod*360+90) )
         {
            offScreenBuffer.setColor(waveColors[2]);
         }
         else
         {
            offScreenBuffer.setColor(waveColors[3]);
         }

         connectSineAndCosine(offScreenBuffer);

         previousSineX   = currentSineX;
         previousSineY   = currentSineY;
         previousCosineX = currentCosineX;
         previousCosineY = currentCosineY;

         if(iteration == 0)
         {
            gcMain.drawImage(offScreenImage, 0, 0, this);
         }

      } // for

      // reset initial parameters...
      previousSineX   = basePointX; 
      previousSineY   = basePointY;
      previousCosineX = basePointX; 
      previousCosineY = basePointY;

      if(iteration > 0)
      {
         gcMain.drawImage(offScreenImage, 0, 0, this);
      }

      ++iteration;

   } // drawSineCosineWave


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


   public void drawSineSegment(Graphics gc)
   {
      // draw consecutive points on sine wave...
      gc.setColor(waveColors[0]);

      gc.drawLine(previousSineX,
                  previousSineY, 
                  currentSineX, 
                  currentSineY);

   } // drawSineSegment


   public void drawCosineSegment(Graphics gc)
   {
      // draw consecutive points on cosine wave...
      gc.setColor(waveColors[1]);
      gc.drawLine(previousCosineX+offsetX,
                  previousCosineY-offsetY, 
                  currentCosineX+offsetX, 
                  currentCosineY-offsetY);

   } // drawCosineSegment


   public void connectSineAndCosine(Graphics gc)
   {
      // points between sine wave and cosine wave...
      gc.drawLine(previousSineX,
                  previousSineY, 
                  previousCosineX+offsetX,
                  previousCosineY-offsetY);

   } // connectSineAndCosine

} // SineCosine1
