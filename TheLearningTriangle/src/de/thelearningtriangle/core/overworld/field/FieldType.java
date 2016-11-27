package de.thelearningtriangle.core.overworld.field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FieldType
{
	NORMAL(NormalField.class, 60),
	WALL(WallField.class, 70),
	POISON(PoisonField.class, 75),
	DEATH(DeathField.class, 80),
	ENERGY(EnergyField.class, 100);
	
	private Class<? extends AbstractField> fieldClass;
	private int chance;
	
	private FieldType(Class<? extends AbstractField> fieldClass, int chance)
	{
		this.fieldClass = fieldClass;
		this.chance = chance;
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
				.filter(field -> field.chance < randomNumber)
				.collect(Collectors.toList());
		
		int lastElement = collectedFieldsSortedAndWithoutToHighFields.size() - 1;
		FieldType fieldType = collectedFieldsSortedAndWithoutToHighFields.get(lastElement);
		
		return fieldType;
	}
	
}