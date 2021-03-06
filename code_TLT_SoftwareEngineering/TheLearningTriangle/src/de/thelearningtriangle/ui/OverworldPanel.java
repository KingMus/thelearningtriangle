package de.thelearningtriangle.ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.thelearningtriangle.core.exceptions.NoMapException;
import de.thelearningtriangle.core.model.overworld.TriangleOverworld;
import de.thelearningtriangle.core.model.triangle.TrianglePosition;

@SuppressWarnings("serial")
public class OverworldPanel extends JPanel
{

	private TriangleOverworld	overworld;
	private int					windowSize;

	public OverworldPanel(TriangleOverworld overworld, int windowSize)
	{
		this.overworld = overworld;
		this.windowSize = windowSize;
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		try
		{
			int size = overworld.getSize();

			drawOverworld(g, size);
			drawTriangle(g, size);

		} catch (NoMapException e)
		{
			e.printStackTrace();
		}

	}

	private void drawTriangle(Graphics g, int size)
	{

		for (TrianglePosition trianglePosition : overworld.getTrianglePositions())
		{
			g.drawImage(ImageLoader.triangle, trianglePosition.getPoint().getX() * windowSize / size,
					trianglePosition.getPoint().getY() * windowSize / size, windowSize / size, windowSize / size, this);

			int fontsize = 50 - size < 15 ? 15 : 50 - size;
			Font arial = new Font("Arial", Font.BOLD, fontsize);
			g.setFont(arial);

			g.drawString(
					trianglePosition.getLearningTriangle().getEnergy() + " | "
							+ trianglePosition.getLearningTriangle().getDistance(),
					(trianglePosition.getPoint().getX() * windowSize / size),
					(trianglePosition.getPoint().getY() * windowSize / size) - (windowSize / size) / 5);
			// Position is calculated through world size

		}
	}

	private void drawOverworld(Graphics g, int size) throws NoMapException
	{

		for (int rowNumber = 0; rowNumber < size; rowNumber++)
		{
			for (int columnNumber = 0; columnNumber < size; columnNumber++)
			{
				g.drawImage(overworld.getField(columnNumber, rowNumber).getFieldType().getImage(),
						columnNumber * windowSize / size, rowNumber * windowSize / size, windowSize / size,
						windowSize / size, this);
			}
		}
	}

}
