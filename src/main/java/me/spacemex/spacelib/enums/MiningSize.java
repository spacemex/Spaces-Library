package me.spacemex.spacelib.enums;

public enum MiningSize {
    THREE_THREE(3, 1),
    THREE_THREE_THREE(3, 3),
    FIVE_FIVE(5, 1),
    FIVE_FIVE_THREE(5, 3),
    FIVE_FIVE_FIVE(5,5);
    private int radius;
    private int depth;
    MiningSize(int radius, int depth){
        this.radius=radius;
        this.depth=depth;
    }
    public int getRadius(){
        return radius;
    }
    public int getDepth() {
        return depth;
    }
}
