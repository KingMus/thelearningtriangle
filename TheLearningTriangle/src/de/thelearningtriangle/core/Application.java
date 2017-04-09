package de.thelearningtriangle.core;

import java.util.List;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.opengl.core.DrawableOverworld;

public class Application
{
	
	public static void main(String[] args)
	{
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(32, 42);
		new DrawableOverworld(overworld);
		
		List<Integer> visionVectorFor = overworld.getVisionVectorFor(new Point(5, 5));
		visionVectorFor.forEach(System.out::println);
		
	}
	
}