import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;

public class SineLava2 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width      = 800;
   private int height     = 500;

   private int basePointX = 100;
   private int basePointY = 250;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private double deltaY  = 0;
   private double vDelta  = 0;

   private int eWidth     = 40;
   private int eHeight    = 240;
   private int eThickness = 10;

   private int amplitude  = 80;
   private double frequency = 1.6;

   private int startAngle = 0;
   private int endAngle   = (int)(450/frequency);
   private int minEndAngle = (int)(90/frequency);
   private int maxEndAngle = (int)(810/frequency);

   private int endAngleDelta     = (int)(6/frequency);
   private int endAngleDirection = 1;

   private int maxCount = 800;

   private int lineHGap = 4;
   private int minLineHGap = 4;
   private int maxLineHGap = 12;

   private int lineHGapDirection = 1;
   private int lineHGapDelta     = 1;

   private Color[] ellipseColors = {
      Color.red, Color.green, Color.blue, Color.yellow, 
      Color.magenta, Color.white, Color.darkGray
   };

   private int rVal = 0;
   private int gVal = 0;
   private int bVal = 0;

   public SineLava2()
   {
      init();
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
      lineHGap += lineHGapDirection*lineHGapDelta;

      if( lineHGap > maxLineHGap )
      {
         lineHGap = maxLineHGap;
         lineHGapDirection *= -1;
      }

      if( lineHGap < minLineHGap )
      {
         lineHGap = minLineHGap;
         lineHGapDirection *= -1;
      }

      endAngle += endAngleDelta*endAngleDirection;

      if( endAngle > maxEndAngle )
      {
         endAngle = maxEndAngle;
         endAngleDirection *= -1;
      }

      if( endAngle < minEndAngle )
      {
         endAngle = minEndAngle;
         endAngleDirection *= -1;
      }

   } // updateCoordinates


   public void drawSineWave(Graphics gc)
   {
      for(int angle=startAngle; angle<endAngle; angle++)
      {
         vDelta   = amplitude*Math.sin(frequency*angle*Math.PI/180);
         vDelta   = Math.abs(vDelta);
         currentX = basePointX+angle;

         rVal = angle*255/endAngle;
         gVal = 0;
         bVal = 0;

         gc.setColor(new Color(rVal, gVal, bVal));
         gc.fillOval(basePointX+angle-startAngle,
                     basePointY-(int)vDelta,
                     (int)(2*vDelta),
                     eWidth);

         if( angle % lineHGap == 0 )
         {
            gc.setColor(Color.white);
            gc.fillOval(basePointX+angle-startAngle,
                        basePointY-(int)vDelta,
                        (int)(2*vDelta),
                        eWidth);
         }
      }
   } // drawSineWave
} // SineLava2
