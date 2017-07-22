package de.thelearningtriangle.ui;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageLoader
{
	public static Image	energyField, deathField, normalField;
	public static Image	wallField;
	public static Image	poisonField;
	public static Image	triangle;
	
	
	// "Classic" / "RPG"

	public ImageLoader(String pfad, String style)
	{

		ImageIcon energyFieldIcon = new ImageIcon(pfad + "/Images/Fields/Style-"+style+"_Energy_Field.png");
		energyField = energyFieldIcon.getImage();
		ImageIcon deathFieldIcon = new ImageIcon(pfad + "/Images/Fields/Style-"+style+"_Death_Field.png");
		deathField = deathFieldIcon.getImage();
		ImageIcon normalFieldIcon = new ImageIcon(pfad + "/Images/Fields/Style-"+style+"_Normal_Field.png");
		normalField = normalFieldIcon.getImage();
		ImageIcon wallFieldIcon = new ImageIcon(pfad + "/Images/Fields/Style-"+style+"_Wall_Field.png");
		wallField = wallFieldIcon.getImage();
		ImageIcon poisonFieldIcon = new ImageIcon(pfad + "/Images/Fields/Style-"+style+"_Poison_Field.png");
		poisonField = poisonFieldIcon.getImage();
		
		ImageIcon triangleIcon = new ImageIcon(pfad + "/Images/LearningTriangle.png");
		triangle = triangleIcon.getImage();

	}

}
