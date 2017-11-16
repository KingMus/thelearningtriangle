package de.thelearningtriangle.core.controller;

import de.thelearningtriangle.core.exceptions.NoMapException;
import de.thelearningtriangle.core.model.overworld.TriangleOverworld;

public class DrawController {

	/**
	 * ensure that windowSize divided through worldSize is even (necessary for UI).
	 * If it is, keep everything the same. If it is not, make it even
	 * 
	 * @param overworld
	 * @param windowSize
	 * @author Marco Mueller
	 * @return the new windowSize
	 */
	public int defineWindowSize(TriangleOverworld overworld, int windowSize) {
		try {
			windowSize = windowSize % overworld.getSize() == 0 ? windowSize
					: windowSize + (overworld.getSize() - (windowSize % overworld.getSize()));
		} catch (NoMapException e) {
			e.printStackTrace();
		}
		return windowSize;

	}

}
