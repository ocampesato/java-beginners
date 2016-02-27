import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.Serializable;

public class SineLeavesOscillatingEllipsesLoop9 extends java.applet.Applet implements
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

   private int branch     = 0;
   private int radius     = 0;
   private int Radius     = 160;

   private double sineValue = 0.0;
   private double frequency = 1.0;

   private double theta   = (Math.PI)/180;
   private int startTheta = (int)(0/frequency);
   private int endTheta   = (int)(360/frequency);

   private int rowOffsetX = 0;
   private int rowOffsetY = 0;

   private int slantLength = 64;
   private int slantAngle  = 0;

   private int rowCount = 8;
   private int columnCount = 8;

   private int cellWidth   = slantLength/columnCount;
   private int cellHeight  = slantLength/rowCount;

   private Color[] cellColors = {
      Color.red, Color.green, Color.blue, 
      Color.white, Color.yellow
   };

   private int colorCount = cellColors.length;


   public SineLeavesOscillatingEllipsesLoop9()
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

   } // paint


   public void drawSpiral(Graphics gc, Graphics gcMain)
   {
      for(int angle=startTheta; angle<endTheta; angle++)
      {
         radius  = (int)(Radius*Math.sin(5*angle*Math.PI/180));
         sineValue = Math.abs(Math.sin(3*angle*Math.PI/180));
         radius    = (int)(Radius*Math.sqrt(sineValue));

         if( radius == 0 ) { ++branch; }

         offsetX = (int)(radius*Math.cos(angle*Math.PI/180));
         offsetY = (int)(radius*Math.sin(angle*Math.PI/180));

         currentX = basePointX+offsetX;
         currentY = basePointY+offsetY;

         drawFrontGrid(gc);
         gcMain.drawImage(offScreenImage, 0, 0, this);
      }

   } // drawSpiral


  public void drawFrontGrid(Graphics gc)
  {
     for(int row=0; row<rowCount; row++)
     {
        for(int col=0; col<columnCount; col++)
        {
           drawFrontCell(gc, row, col);
        }
     }
 
  } // drawFrontGrid
 

  public void drawFrontCell(Graphics gc, int row, int col)
  {
     rowOffsetX = currentX+col*cellWidth;
     rowOffsetY = currentY+row*cellHeight;

     gc.setColor(cellColors[((row+col)%2+branch)%colorCount]);
     gc.fillRect(rowOffsetX, rowOffsetY, cellWidth, cellHeight);

  } // drawFrontCell

} // SineLeavesOscillatingEllipsesLoop9
