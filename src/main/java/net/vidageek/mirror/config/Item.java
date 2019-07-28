package net.vidageek.mirror.config;

public enum Item {
    REFLECTION_PROVIDER("provider.class");
    
    private final String key;

    private Item(String str) {
        this.key = str;
    }

    public String getPropertyKey() {
        return this.key;
    }
}
