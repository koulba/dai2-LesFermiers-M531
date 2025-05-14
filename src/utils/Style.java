package utils;

public enum Style {
    
    NORMAL(0),
    BOLD(1),
    ITALIC(3),
    UNDERLINE(4),
    STRIKETHROUGH(9);

    private final String code;

    private Style(Integer code) {
        this.code = code.toString();
    }

    @Override
    public String toString() {
        return code;
    }
}
