
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class Graphs
{
	JFrame frame;			//JFrame that holds the JPanels
	Map map;				//Map that points to the Map function
    BufferedImage img;
    String title;
    //JImageComponent jimg;
	
	public Graphs(Map mps)
	{
		map = mps;
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridLayout layout = new GridLayout(mps.getboundary(), mps.getboundary());
		frame.setLayout(layout);
		updateGraph();
		frame.pack();
		frame.setVisible(true);
	}

    public Graphs(Map mps, String tit)
    {
        map = mps;
        title = tit;
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridLayout layout = new GridLayout(mps.getboundary(), mps.getboundary());
        frame.setLayout(layout);
        updateGraph();
        frame.pack();
        frame.setVisible(true);
        try
        {
            grabScreenShot();
        }
        catch(Exception e)
        {
            System.out.println("Did not get screenshot");
        }
    }
	
	public Graphs(Map mps, int xbound, int ybound)
	{
		map = mps;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout(ybound, xbound));
		updateGraph();
		frame.pack();
		frame.setVisible(true);
	}
	
	//grabs the JPanel() from the blocks
	public void updateGraph()
	{
		int xbound = map.getboundary(), ybound = map.getboundary(), i = 0, j = 0;
		for(i = 0; i < ybound; i++)
		{
			for(j = 0; j < xbound; j++)
			{
				frame.add(map.getPaneling(i, j));
			}
		}
	}

    private void grabScreenShot() throws Exception
    {
        BufferedImage image = new BufferedImage(frame.getContentPane().getSize().width ,frame.getContentPane().getSize().height, BufferedImage.TYPE_INT_RGB);
        frame.getContentPane().paint(image.getGraphics());
        try{
            ImageIO.write(image, "png", new File(title + ".png"));
            System.out.println("Image was created");
        }
        catch (IOException e){
            System.out.println("Had trouble writing the image.");
            throw e;
        }
        img = image;
    }

    private void addImage()
    {
        //frame.getContentPane().paint(img);
    }

    class ImagePanel extends JPanel{
        BufferedImage image = img;
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(image != null){
                g.drawImage(image, 0, 0, this);
            }
        }
    }
}