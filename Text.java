
/**
 * Generates a Text to be drawn in the canvas
 */
public class Text {
    
    /** ---- Variables ---- **/
    private String value, color;
    private int xPosition, yPosition, size;
    private boolean visible;
    
    /**
     * Constructor for objects of class Text
     * @param value: text to be set
     */
    public Text(String value) {
        this.value = value;
        color = "black";
        xPosition = 0;
        yPosition = 0;
        size = 16;
        visible = false;
        
        draw();
    }
    
    /**
     * Makes text visible
     */
    public void makeVisible() {
        visible = true;
        draw();
    }

    /**
     * Makes text invisible
     */
    public void makeInvisible() {
        erase();
        visible = false;   
    }
    
    /**
     * Change text size
     * @param size: new size
     */
    public void changeSize(int size) {
        erase();
        this.size = size;
        draw();
    }
    
    /**
     * Change text color
     * @param color: new color
     */
    public void changeColor(String color) {
        erase();
        this.color = color;
        draw();
    }
    
    /**
     * Change text value
     * @param value: new value
     */
    public void changeValue(String value) {
        erase();
        this.value = value;
        draw();
    }
    
    /**
     * Change text position
     * @param xDelta: x to move
     * @param yDelta: y to move
     */
    public void move(int xDelta, int yDelta) {
        erase();
        this.xPosition += xDelta;
        this.yPosition += yDelta;
        draw();
    }
    
    /**
     * draws the text
     */
    private void draw() {
     
        if(visible) {
            Canvas canvas = Canvas.getCanvas();  
            canvas.draw(this, color, this);
        }
        
    }
    
    /**
     * erases the text
     */
    private void erase() {
            
        if(visible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
        
    }
    
    /**
     * get x position
     * @return the x position value
     */
    public int getXPosition() {
        return xPosition;   
    }

    /**
     * get y position
     * @return the y position value
     */
    public int getYPosition() {
        return yPosition;   
    }
    
    /**
     * get value
     * @return the text value
     */
    public String getValue() {
        return value;   
    }
    
    /**
     * get color
     * @return the color value
     */
    public String getColor() {
        return color;   
    }
    
    /**
     * get size
     * @return the text size value
     */
    public int getSize() {
        return size;   
    }

}
