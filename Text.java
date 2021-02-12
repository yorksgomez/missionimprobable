
/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text
{
    private String value, color;
    private int xPosition, yPosition, size;
    private boolean visible;
    
    /**
     * Constructor for objects of class Text
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
    
    public void makeVisible() {
        visible = true;
        draw();
    }
    
    public void makeInvisible() {
        erase();
        visible = false;   
    }
    
    public void changeSize(int size) {
        erase();
        this.size = size;
        draw();
    }
    
    public void changeColor(String color) {
        erase();
        this.color = color;
        draw();
    }
    
    public void changeValue(String value) {
        erase();
        this.value = value;
        draw();
    }
    
    public void move(int xDelta, int yDelta) {
        erase();
        this.xPosition += xDelta;
        this.yPosition += yDelta;
        draw();
    }
    
    private void draw() {
     
        if(visible) {
            Canvas canvas = Canvas.getCanvas();  
            canvas.draw(this, color, this);
        }
        
    }
    
    private void erase() {
            
        if(visible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
        
    }
    
    public int getXPosition() {
        return xPosition;   
    }

    public int getYPosition() {
        return yPosition;   
    }
    
    public String getValue() {
        return value;   
    }
    
    public String getColor() {
        return color;   
    }
    
    public int getSize() {
        return size;   
    }

}
