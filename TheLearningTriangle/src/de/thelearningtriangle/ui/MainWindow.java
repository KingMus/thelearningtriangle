package de.thelearningtriangle.ui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import de.thelearningtriangle.core.controller.DrawController;
import de.thelearningtriangle.core.exceptions.NoMapException;
import de.thelearningtriangle.core.model.overworld.TriangleOverworld;

@SuppressWarnings("serial")
public class MainWindow extends JFrame
{

	private OverworldPanel	overworldPanel;
	private SettingsPanel	settingsPanel;
	private DrawController drawController;
	
	private int windowSize = 900;

	public MainWindow(TriangleOverworld overworld)
	{

		drawController = new DrawController();
		
		windowSize = drawController.calculateWindowSize(overworld, windowSize);
		
		
		overworldPanel = new OverworldPanel(overworld, windowSize);
//		settingsPanel = new SettingsPanel();

		this.setSize(windowSize+6, windowSize+29);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout());
		this.setTitle("The Learning Triangle");
		this.add(overworldPanel);
		this.setVisible(true);

	}


	public int getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(int windowSize) {
		this.windowSize = windowSize;
	}

	public OverworldPanel getOverworldPanel()
	{
		return overworldPanel;
	}
}
