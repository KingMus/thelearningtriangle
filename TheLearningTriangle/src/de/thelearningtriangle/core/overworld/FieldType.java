package de.thelearningtriangle.core.overworld;

public enum FieldType
{
    NORMAL(NormalField.class),
    WALL(WallField.class),
    POISON(PoisonField.class),
    DEATH(DeathField.class),
    ENERGY(EnergyField.class);
    
    private Class<? extends AbstractField> fieldClass;
    
    private FieldType(Class<? extends AbstractField> fieldClass)
    {
        this.fieldClass = fieldClass;
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
}