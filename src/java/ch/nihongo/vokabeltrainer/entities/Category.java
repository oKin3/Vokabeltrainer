package ch.nihongo.vokabeltrainer.entities;

/**
 *
 * @author Niko Reichardt
 */
public enum Category {

    FRUIT("Fruit"),
    MEDICAL("Medical"),
    FOOD("Food"),
    ANIMAL("Animal");
    
    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
