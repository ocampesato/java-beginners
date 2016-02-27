import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;


public class SineGradient4 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width      = 800;
   private int height     = 500;

   private int pWidth     = 600;
   private int pHeight    = 500;

   private int maxAmplitude   = 150;
   private int amplitude      = 80;

   private int basePointX = 100;
   private int basePointY = 250;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private int previousX  = -1;
   private int previousY  = -1;

   private int vOffset        = 0;

   private double frequency = 0.8;

   private double theta   = (Math.PI)/180;
   private int startTheta = (int)(0/frequency);
   private int endTheta   = (int)(180/frequency);

   private int drawWaveWidth  = (endTheta-startTheta)/20;

   private int interWaveHGap = 8;

   private int rVal = 255;
   private int gVal = 0;
   private int bVal = 0;


   public SineGradient4()
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

      drawSineWave(offScreenBuffer, maxAmplitude, frequency);
      gc.drawImage(offScreenImage, 0, 0, this);

   } // paint


   public void drawSineWave(Graphics gc, int amplitude, 
                            double frequency)
   {
      int currentBand = 0;
      int vDelta = 0;
      int wWidth = (int)(drawWaveWidth/frequency); 
      int wGap   = (int)(interWaveHGap/frequency); 

      for(int angle=startTheta; angle<endTheta; angle++)
      {
         if( previousX == -1 )
         {
            previousX = currentX;
            previousY = currentY;
         }

         if( angle%wWidth == 0 )
         {
            rVal = 255;
            gVal = (int)(angle*255/(endTheta-startTheta));
            bVal = (int)(angle*255/(endTheta-startTheta));
         }

         gc.setColor(new Color(rVal, gVal, bVal));

         vOffset = (int)(Math.abs((int)(amplitude*
                            Math.sin(frequency*angle*theta))));

         currentX  = basePointX+angle;
         currentY  = basePointY;

         previousX  = currentX;
         previousY  = currentY;

         gc.drawLine(currentX,
                     currentY-vOffset, 
                     currentX,
                     currentY+vOffset);

         previousX = currentX;
         previousY = currentY;

      } // for

   } // drawSineWave

} // SineGradient4
