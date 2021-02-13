
public class ResultZone extends Zone {

    private Text stolenText, wareText, planText;
    
    public ResultZone() {
        super("Resuts", "white", 480, 170);
        xPosition = 20;
        yPosition = 370;
    
        stolenText = new Text("Stolen: 0");
        stolenText.move(xPosition + 30, yPosition + 50);
        
        wareText = new Text("Warehouse crates: 0");
        wareText.move(xPosition + 30, yPosition + 80);
        
        planText = new Text("Plan crates: 0");
        planText.move(xPosition + 30, yPosition + 110);
        
        init();  
    }
    
    @Override
    public void makeVisible() {
        super.makeVisible();
        
        stolenText.makeVisible();
        wareText.makeVisible();
        planText.makeVisible();
    }
    
    @Override
    public void makeInvisible() {
        super.makeInvisible();
        
        stolenText.makeInvisible();
        wareText.makeInvisible();
        planText.makeInvisible();
    }
    
    public void changeStolen(int stolen) {
        stolenText.changeValue("Stolen: " + String.valueOf(stolen));   
    }
    
    public void changeWare(int ware) {
        wareText.changeValue("Warehouse crates: " + String.valueOf(ware));   
    }
    
    public void changePlan(int plan) {
        planText.changeValue("Plan crates: " + String.valueOf(plan));   
    }
    

}

