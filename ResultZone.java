/**
 * Zone with mission improbable information
 */
public class ResultZone extends Zone {

    /** ---- Variables ---- **/
    private Text stolenText, wareText, planText;
    
    /**
     * Generates a basic result zone
     */
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
    
    /**
     * make the zone visible
     */
    @Override
    public void makeVisible() {
        super.makeVisible();
        
        stolenText.makeVisible();
        wareText.makeVisible();
        planText.makeVisible();
    }
    
    /**
     * make the zone invisible
     */
    @Override
    public void makeInvisible() {
        super.makeInvisible();
        
        stolenText.makeInvisible();
        wareText.makeInvisible();
        planText.makeInvisible();
    }
    
    /**
     * change stolen text
     * @param stolen: num to put
     */
    public void changeStolen(int stolen) {
        stolenText.changeValue("Stolen: " + String.valueOf(stolen));   
    }
    
    /**
     * change ware text
     * @param ware: num to put
     */
    public void changeWare(int ware) {
        wareText.changeValue("Warehouse crates: " + String.valueOf(ware));   
    }
    
    /**
     * change plan text
     * @param plan: num to put
     */
    public void changePlan(int plan) {
        planText.changeValue("Plan crates: " + String.valueOf(plan));   
    }

}

