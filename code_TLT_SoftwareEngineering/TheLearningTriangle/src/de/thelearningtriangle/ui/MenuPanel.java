package de.thelearningtriangle.ui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPanel extends JPanel
{

	private Image		menuIcon;
	private JLabel		labelTop, labelMiddle, labelDown;
	private JTextField	txtTop, txtMiddle, txtDown;

	public MenuPanel(String labelTopString, String labelMiddleString, String labelDownString)
	{

		labelTop = new JLabel(labelTopString);
		labelMiddle = new JLabel(labelMiddleString);
		labelDown = new JLabel(labelDownString);

		init();

	}

	private void init()
	{

		setLayout(new GridLayout(0, 2));
		
		add(getLabelTop());
		add(getTxtTop());
		add(getLabelMiddle());
		add(getTxtMiddle());
		add(getLabelDown());
		add(getTxtDown());
	
	}

	public JLabel getLabelTop()
	{
		return labelTop;
	}

	public JLabel getLabelMiddle()
	{
		return labelMiddle;
	}

	public JLabel getLabelDown()
	{
		return labelDown;
	}

	public Image getMenuIcon()
	{
		return menuIcon;
	}

	public JTextField getTxtTop()
	{
		if(txtTop == null) {
			txtTop = new JTextField();
		}
		
		return txtTop;
	}

	public JTextField getTxtMiddle()
	{
		if(txtMiddle == null) {
			txtMiddle = new JTextField();
		}
		return txtMiddle;
	}

	public JTextField getTxtDown()
	{
		if(txtDown == null) {
			txtDown = new JTextField();
		}
		return txtDown;
	}

}
