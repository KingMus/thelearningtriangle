package de.thelearningtriangle.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import de.thelearningtriangle.core.overworld.TriangleOverworld;

@SuppressWarnings("serial")
public class MainWindow extends JFrame
{

	private OverworldPanel	overworldPanel;
	private SettingsPanel	settingsPanel;

	public MainWindow(TriangleOverworld overworld)
	{

		overworldPanel = new OverworldPanel(overworld);
//		settingsPanel = new SettingsPanel();

		this.setSize(806, 829);
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
