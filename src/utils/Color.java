package utils;

public enum Color {
    
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    WHITE(37);

    private final static int HIGH = 60;

    private final int code;

    private Color(int code) {
        this.code = code;
    }

    public String toColorString(boolean highInstensity) {
        return Integer.toString(highInstensity ? code + HIGH : code);
    }

    public String toBackgroundString(boolean highInstensity){
        return Integer.toString(10 + (highInstensity ? code + HIGH : code));
    }

}
