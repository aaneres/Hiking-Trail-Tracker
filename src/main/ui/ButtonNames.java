package ui;

public enum ButtonNames {

    ADD("Add trail"),
    REMOVE("Remove trail"),
    CHANGE("Change completion"),
    VIEW("View all trails"),
    SAVE("Save"),
    LOAD("Load");
    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
