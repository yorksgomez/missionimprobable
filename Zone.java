/**
 * basic zone
 */
public class Zone {
    
    /** ---- Variables ---- **/
    protected Text title;
    protected Rectangle background;
    protected int width, height, xPosition, yPosition;
    protected boolean visible;
    
    /**
     * Generates a basic zone
     * @param title: zone title text
     * @param color: background color
     * @param width: size
     * @param height: size
     */
    public Zone(String title, String color, int width, int height) {
        this.title = new Text(title);
        this.background = new Rectangle();
        this.background.changeColor(color);
        this.width = width;
        this.height = height;
    }
    
    /**
     * Join components
     */
    public void init() {        
        background.changeSize(height - title.getSize() - 10, width - 20);
        background.move(xPosition, yPosition);
        
        title.move(xPosition + 10, yPosition + 20);
    }
    
    /**
     * chane background color
     * @param background: color to set
     */
    public void changeBackground(String background) {
        this.background.changeColor(background);   
    }
    
    /**
     * makes zone visible
     */
    public void makeVisible() {
        visible = true;
        
        background.makeVisible();
        title.makeVisible();
    }
    
    /**
     * makes zone invisible
     */
    public void makeInvisible() {
        title.makeInvisible();
        background.makeInvisible();
    
        visible = false;
    }
    
}
