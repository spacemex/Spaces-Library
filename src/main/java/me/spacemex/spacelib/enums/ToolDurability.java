package me.spacemex.spacelib.enums;

public enum ToolDurability {
    WOODEN(59),
    STONE(131),
    COPPER(64),
    IRON(250),
    GOLD(32),
    DIAMOND(1561),
    EMERALD(1433),
    NETHERITE(2031);

    private final int durability;
    ToolDurability(final int durability){
        this.durability=durability;
    }
    public int getDurability() {
        return durability;
    }
    public int multiplyDurability(int value){
        return this.durability * value;
    }
}
