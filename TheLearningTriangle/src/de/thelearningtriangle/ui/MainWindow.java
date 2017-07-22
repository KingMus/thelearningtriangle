package de.thelearningtriangle.ui;

import javax.swing.JFrame;

public class MainWindow extends JFrame
{

	OverworldPanel overworldPanel = new OverworldPanel();
	
	public MainWindow()
	{

		this.setSize(900, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(overworldPanel);
		this.setVisible(true);

	}
}
