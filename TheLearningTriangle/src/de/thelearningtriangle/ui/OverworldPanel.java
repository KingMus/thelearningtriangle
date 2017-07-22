package de.thelearningtriangle.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class OverworldPanel extends JPanel
{

	public void paint(Graphics g)
	{
		super.paint(g);

		g.drawImage(ImageLoader.normalField, 80, 80, 80, 80, this);
		g.drawRect(279, 280, 80, 80);

	}

}
