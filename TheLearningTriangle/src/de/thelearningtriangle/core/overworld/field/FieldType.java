package de.thelearningtriangle.core.overworld.field;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FieldType
{
	NORMAL(NormalField.class, Color.GRAY, 80, 1),
	WALL(WallField.class, Color.BLACK, 85, 2),
	POISON(PoisonField.class, new Color(0.8f, 0.5f, 1.f), 90, 3),
	DEATH(DeathField.class, Color.RED, 95, 4),
	ENERGY(EnergyField.class, Color.YELLOW, 100, 5);
	
	private Class<? extends AbstractField> fieldClass;
	private int chance;
	private Color color;
	private int id;
	
	private FieldType(Class<? extends AbstractField> fieldClass, Color color, int chance, int id)
	{
		this.fieldClass = fieldClass;
		this.chance = chance;
		this.color = color;
		this.id = id;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public AbstractField createNewFieldInstance()
	{
		try
		{
			return fieldClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Can't create a field of this FieldType!");
		}
	}
	
	public static FieldType getFieldTypeFor(int randomNumber)
	{
		List<FieldType> collectedFieldsSortedAndWithoutToHighFields = Arrays.stream(values())
				.sorted((f1, f2) -> Integer.compare(f1.chance, f2.chance))
				.filter(field -> field.chance >= randomNumber)
				.collect(Collectors.toList());
		
		FieldType fieldType = collectedFieldsSortedAndWithoutToHighFields.get(0);
		
		return fieldType;
	}
	
	public int getId()
	{
		return id;
	}
	
	public Class<? extends AbstractField> getFieldClass()
	{
		return fieldClass;
	}
	
	public static int getIdFor(AbstractField field)
	{
		return Stream.of(values())
				.filter(fielyType -> fielyType.getFieldClass().equals(field.getClass()))
				.findFirst()
				.get()
				.getId();
	}
}