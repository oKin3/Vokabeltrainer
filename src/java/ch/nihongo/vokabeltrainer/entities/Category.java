package ch.nihongo.vokabeltrainer.entities;

/**
 *
 * @author Niko Reichardt
 */
public enum Category {

    FRUIT("Fruit"),
    VEGETABLES("Vegetables"),
    MEDICAL("Medical"),
    FOOD("Food"),
    ANIMAL("Animal"),
    TRANSPORT("Transport");

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
