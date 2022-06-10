package OnScreen;


public abstract class Bar { //super class for the two bars I'm creating
    protected int x, y, width, height; // to make these variables reachable in the subclasses

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
