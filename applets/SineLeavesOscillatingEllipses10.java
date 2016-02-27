import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;

public class SineLeavesOscillatingEllipses10 extends java.applet.Applet implements
                   Serializable
{
   private Graphics offScreenBuffer = null;
   private Image    offScreenImage  = null;

   private int width      = 800;
   private int height     = 500;

   private int basePointX = 300;
   private int basePointY = 250;

   private int currentX   = basePointX;
   private int currentY   = basePointY;

   private int offsetX    = 0;
   private int offsetY    = 0;

   private int radius     = 0;
   private int Radius     = 200;

   private double sineValue = 0.0;
   private double frequency = 1.0;

   private double theta   = (Math.PI)/180;
   private int startTheta = (int)(0/frequency);
   private int endTheta   = (int)(360/frequency);

   private int eHeight    = 20;
   private int eWidth     = 40;
   private int minEHeight = 20;
   private int maxEHeight = 80;

   private int angleSpan = endTheta-startTheta;

   private Color[] ellipseColors = {
      Color.red, Color.green, Color.blue, Color.yellow,
      Color.white, Color.magenta 
   };

   private int colorCount = ellipseColors.length;


   public SineLeavesOscillatingEllipses10()
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

      offScreenBuffer.setColor(Color.red);
      drawSpiral(offScreenBuffer, gc);
      gc.drawImage(offScreenImage, 0, 0, this);

   } // paint


   public void drawSpiral(Graphics gc, Graphics gcMain)
   {
      for(int angle=startTheta; angle<endTheta; angle++)
      {
         radius  = (int)(Radius*Math.sin(7*angle*Math.PI/180));
         sineValue = Math.abs(Math.sin(3*angle*Math.PI/180));
         radius    = (int)(Radius*Math.sqrt(sineValue));

         offsetX = (int)(radius*Math.cos(angle*Math.PI/180));
         offsetY = (int)(radius*Math.sin(angle*Math.PI/180));

         currentX = basePointX+offsetX;
         currentY = basePointY+offsetY;

         drawEllipse(gc, angle, currentX, currentY);
         gcMain.drawImage(offScreenImage, 0, 0, this);
      }

   } // drawSpiral


   public void drawEllipse(Graphics gc, int x, int xCoord, int yCoord)
   {
      eHeight = x*maxEHeight/angleSpan+minEHeight;

      gc.setColor(ellipseColors[x%colorCount]);
    //gc.fillOval(xCoord, yCoord, eHeight, eWidth); 
      gc.fillOval(xCoord, yCoord, eWidth, eHeight); 

   } // drawEllipse

} // SineLeavesOscillatingEllipses10
