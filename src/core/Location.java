package core;

import utils.IPrintable;

public class Location implements IPrintable {
    private final String name;
    private final String description;
    private boolean locked;

    public Location(String name, String desc, boolean locked) {
        this.name = name;
        this.description = desc;
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    public String inspect() {
        return "You are in: " + name + ". " + description;
    }

    @Override
    public String getPrintSymbol() {
        return locked ? "[X]" : "[ ]";
    }

    @Override
    public boolean isGrayedOut() {
        return locked;
    }

    @Override
    public String getPrintableString() {
        return name;
    }
}