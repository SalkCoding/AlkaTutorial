package net.alkaonline.alkatutorial.tutorialstr;

public class Page {

    private final String[] str;
    private final int firstBlank;

    public Page(int first, String... messages) {
        str = messages;
        firstBlank = first;
    }

    public String[] getString() {
        return str;
    }

    public int getFirstBlank() {
        return firstBlank;
    }

}
