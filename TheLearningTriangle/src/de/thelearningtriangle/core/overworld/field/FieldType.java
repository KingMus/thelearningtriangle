package de.thelearningtriangle.core.overworld.field;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FieldType
{
    NORMAL(NormalField.class, Color.GRAY, 80),
    WALL(WallField.class, Color.BLACK, 85),
    POISON(PoisonField.class, new Color(0.8f, 0.5f, 1.f), 90),
    DEATH(DeathField.class, Color.RED, 95),
    ENERGY(EnergyField.class, Color.YELLOW, 100);
    
    private Class<? extends AbstractField> fieldClass;
    private int chance;
    private Color color;
    
    private FieldType(Class<? extends AbstractField> fieldClass, Color color, int chance)
    {
        this.fieldClass = fieldClass;
        this.chance = chance;
        this.color = color;
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
        List<FieldType> collectedFieldsSortedAndWithoutToHighFields = Arrays
                                                                            .stream(values())
                                                                                .sorted((f1, f2) -> Integer.compare(f1.chance, f2.chance))
                                                                                .filter(field -> field.chance >= randomNumber)
                                                                                .collect(Collectors.toList());
        
        FieldType fieldType = collectedFieldsSortedAndWithoutToHighFields.get(0);
        
        return fieldType;
    }
    
}