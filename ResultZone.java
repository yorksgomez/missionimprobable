
public class ResultZone extends Zone {

    private Text stolenText;
    
    public ResultZone() {
        super("Resuts", "white", 480, 170);
        xPosition = 20;
        yPosition = 370;
    
        stolenText = new Text("Stolen: 0");
        stolenText.move(xPosition + 30, yPosition + 50);
        
        init();  
    }
    
    @Override
    public void makeVisible() {
        super.makeVisible();
        
        stolenText.makeVisible();
    }
    
    @Override
    public void makeInvisible() {
        super.makeInvisible();
        
        stolenText.makeInvisible();
    }
    
    public void changeStolen(int stolen) {
        stolenText.changeValue("Stolen: " + String.valueOf(stolen));   
    }
    

}

