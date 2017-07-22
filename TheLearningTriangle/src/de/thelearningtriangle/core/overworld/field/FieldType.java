package de.thelearningtriangle.core.overworld.field;

import java.awt.Color;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.thelearningtriangle.ui.ImageLoader;

public enum FieldType {

	NORMAL(NormalField.class, Color.GRAY, ImageLoader.normalField, 70, 1),
	WALL(WallField.class, Color.BLACK, ImageLoader.wallField, 75, 2),
	POISON(PoisonField.class, new Color(0.8f, 0.5f, 1.f), ImageLoader.poisonField, 80, 3),
	DEATH(DeathField.class, Color.RED, ImageLoader.deathField,85, 4),
	ENERGY(EnergyField.class, Color.YELLOW, ImageLoader.energyField, 100, 5);

	public static int MAX_CHANCE = 100;
	private Class<? extends AbstractField> fieldClass;
	private int chance;
	

	private Color color;
	private Image image;
	private int id;

	private FieldType(Class<? extends AbstractField> fieldClass, Color color, Image image, int chance, int id) {
		this.fieldClass = fieldClass;
		this.chance = chance;
		this.color = color;
		this.image = image;
		this.id = id;
	}

	public Color getColor() {
		return this.color;
	}

	public AbstractField newInstance() {
		try {
			return fieldClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't create a field of this FieldType!");
		}
	}

	public static FieldType getRandomFieldType(Random random) {
		int rndNumberWithinChance = random.nextInt(FieldType.MAX_CHANCE);
		return FieldType.getFieldTypeFor(rndNumberWithinChance);
	}

	public static FieldType getFieldTypeFor(int randomNumber) {
		List<FieldType> collectedFieldsSortedAndWithoutToHighFields = Arrays
				.stream(values())
				.sorted((f1, f2) -> Integer.compare(f1.chance, f2.chance))
				.filter(field -> field.chance >= randomNumber)
				.collect(Collectors.toList());

		FieldType fieldType = collectedFieldsSortedAndWithoutToHighFields.get(0);

		return fieldType;
	}
	
	public Image getImage()
	{
		return image;
	}

	public int getId() {
		return id;
	}
	public Class<? extends AbstractField> getFieldClass() {
		return fieldClass;
	}

	public static int getIdFor(AbstractField field) {
		return Stream
				.of(values())
				.filter(fielyType -> fielyType.getFieldClass().equals(field.getClass()))
				.findFirst()
				.get()
				.getId();
	}
}