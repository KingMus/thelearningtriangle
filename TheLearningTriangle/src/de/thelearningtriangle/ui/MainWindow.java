package de.thelearningtriangle.ui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import de.thelearningtriangle.core.overworld.TriangleOverworld;

@SuppressWarnings("serial")
public class MainWindow extends JFrame
{

	private OverworldPanel	overworldPanel;
	private SettingsPanel	settingsPanel;

	public MainWindow(TriangleOverworld overworld, int windowSize)
	{

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

	public OverworldPanel getOverworldPanel()
	{
		return overworldPanel;
	}
}
