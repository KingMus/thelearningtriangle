package de.thelearningtriangle.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class SettingsPanel extends JPanel
{

	public SettingsPanel()
	{

		setLayout(new GridLayout(4, 0));
		
		add(new MenuPanel("Speed", "Health", "Consumption"));
		add(new MenuPanel("Size", "Style", "-leer-"));
		add(new MenuPanel("Speed", "Health", "Consumption"));
		add(new MenuPanel("Energyfield: Energy", "Poisonfield: CycleLength", "PoisonField: Consumption"));
		
		
	}

}
