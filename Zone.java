
public class Zone {
    
    protected Text title;
    protected Rectangle background;
    protected int width, height, xPosition, yPosition;
    protected boolean visible;
    
    public Zone(String title, String color, int width, int height) {
        this.title = new Text(title);
        this.background = new Rectangle();
        this.background.changeColor(color);
        this.width = width;
        this.height = height;
    }
    
    public void init() {        
        background.changeSize(height - title.getSize() - 10, width - 20);
        background.move(xPosition, yPosition);
        
        title.move(xPosition + 10, yPosition + 20);
    }
    
    public void changeBackground(String background) {
        this.background.changeColor(background);   
    }
    
    public void makeVisible() {
        visible = true;
        
        background.makeVisible();
        title.makeVisible();
    }
    
    public void makeInvisible() {
        title.makeInvisible();
        background.makeInvisible();
    
        visible = false;
    }
    
}
