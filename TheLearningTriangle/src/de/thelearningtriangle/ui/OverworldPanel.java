package de.thelearningtriangle.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import de.thelearningtriangle.core.overworld.NoMapException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.opengl.figure.DrawableFigure;
import de.thelearningtriangle.opengl.figure.FieldFigure;

public class OverworldPanel extends JPanel
{

	private TriangleOverworld overworld;

	public OverworldPanel(TriangleOverworld overworld)
	{
		this.overworld = overworld;
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		try
		{
			int size = overworld.getSize();

			for (int rowNumber = 0; rowNumber < size; rowNumber++)
			{
				for (int columnNumber = 0; columnNumber < size; columnNumber++)
				{

					g.drawImage(overworld.getField(columnNumber, rowNumber).getFieldType().getImage(),
							rowNumber * 800 / size, columnNumber * 800 / size, 800 / size, 800 / size, this);
				}
			}

		} catch (NoMapException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
