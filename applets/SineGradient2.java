import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;


public class SineGradient2 extends java.applet.Applet implements
                   Serializable
{
   public Graphics offScreenBuffer = null;
   public Image    offScreenImage  = null;
   public int      width           = 800;
   public int      height          = 500;

   private int pWidth     = 600;
   private int pHeight    = 500;

   private int vOffset        = 0;
   private int maxAmplitude   = 300;
   private int amplitude      = 80;

   private double frequency = 0.8;

   private double theta   = (Math.PI)/180;
   private int startTheta = (int)(0/frequency);
   private int endTheta   = (int)(180/frequency);

   private int drawWaveHeight = 10;
   private int drawWaveWidth  = (endTheta-startTheta)/20;

   private int interWaveVGap = 8;
   private int interWaveHGap = 8;

   private int basePointX = 100;
   private int basePointY = 300;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private int previousX=-1, previousY=-1;

   private int rVal = 255;
   private int gVal = 0;
   private int bVal = 0;

   

   public SineGradient2()
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

      drawSineWave(offScreenBuffer, maxAmplitude/2, frequency);
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
            if( currentBand % 2 == 0 )
            {
               rVal = 255;
               gVal = (int)(angle*255/(endTheta-startTheta));
               bVal = (int)(angle*255/(endTheta-startTheta));
            }
            else
            {
               rVal = (int)(angle*255/(endTheta-startTheta));
               gVal = (int)(angle*255/(endTheta-startTheta));
               bVal = 255;
            }

            ++currentBand;
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

} // SineGradient2
