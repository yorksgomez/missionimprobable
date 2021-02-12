import java.util.Arrays;

public class Mission {
    
    /** ---- Variables ---- **/
    
    private int length, width, stolen;
    private int[][] base, idea;
    private boolean visible;
    private Text mainTitle, stolenText;
    private WareHouseZone warehouse;
    private PlanZone plan;
    private ResultZone result;
    
    /**
     * Constructor for objects of class Mission
     */
    
    public Mission(int length, int width) {
        this.length = length;
        this.width = width;
        stolen = 0;
        base = new int[length][width];
        idea = new int[length][width];
        
        init();
    }
    
    /**
     * Create the plan zone and result zone and ddd titles, spaces  
     */
    
    private void init() {
        mainTitle = new Text("Mission Improbable");
        mainTitle.changeSize(26);
        mainTitle.move(120, 35);
        
        warehouse = new WareHouseZone(width, length);
        plan = new PlanZone(width, length);
        result = new ResultZone();
    }
    
    private int[][] rotateDisposition(int[] maxs, int m, int n) {
        int[][] newdis = new int[n][m];
        
        for(int i = 0; i < maxs.length; i++) {
            int x = maxs[i];
            
            for(int j = 0; j < x; j++) {
                newdis[newdis[i].length - j - 1][i] = 1;
            }
            
        }
        
        return newdis;
    }
    
    /** 
    *Generate the matrix with front view
    */
    
    private int[][] calcFront(int[][] disposition) {
        int maxs[] = new int[disposition[0].length];
        int[][] newdis;
        
        for(int i = 0; i < disposition.length; i++) {
            
            for(int j = 0; j < disposition[i].length; j++) {
                
                if(maxs[j] < disposition[i][j])
                    maxs[j] = disposition[i][j];
                
            }
            
        }
        
        newdis = rotateDisposition(maxs, disposition.length, disposition[0].length);
        return newdis;
    }
    
    
    /** 
    *Generate the matrix with side view
    */
    
    private int[][] calcSide(int[][] disposition) {
        int[] maxs = new int[disposition.length];
        int[][] newdis = new int[disposition.length][disposition[0].length];
        
        for(int i = 0; i < maxs.length; i++) {
            int[] copy = Arrays.copyOf(disposition[i], disposition[i].length);
            Arrays.sort(copy);
            maxs[i] = copy[copy.length - 1];
        }
        
        newdis = rotateDisposition(maxs, disposition.length, disposition[0].length);
        return newdis;
    }
    
    
    /** 
    *Return the matrix with top view
    */
    
    private int[][] calcTop(int[][] disposition) {
        return disposition;
    }
    
    /**
    * Update all three views, top, side and front
    */
    
    private void updateWareViews() {
        int[][] front = calcFront(base),
                side = calcSide(base),
                top = calcTop(base);
                
        warehouse.updateViews(front, side, top);
    }
    
    private void updateStolen(int stolen) {
        this.stolen = stolen;
        result.changeStolen(stolen);
    }
    
    /**
    * Save a box in the warehouse, given by row and column
    */
    
    public void store(int row, int column) {
           base[row - 1][column - 1]++;
           updateWareViews();
    }
    
     /**
    * Extract a box of the warehouse, given by row and column
    */
    
    public void steal(int row, int column){
        base[row-1][column-1]--;
        updateWareViews();
    }
    
    /**
    * Generates a copy of the views to be part of the plan
    */
    
    public void copy() {
        updateStolen(0);
        idea = Arrays.copyOf(base, base.length);
        
        plan.getFront().changeDisposition(warehouse.getFront().getDisposition());
        plan.getSide().changeDisposition(warehouse.getSide().getDisposition());
        plan.getTop().changeDisposition(warehouse.getTop().getDisposition());
    }
    
    public int stolen() {
        return stolen;   
    }
    
    public void makeVisible() {
        visible = true;
        
        mainTitle.makeVisible();
        warehouse.makeVisible();
        plan.makeVisible();
        result.makeVisible();
    }
    
    public void makeInvisible() {
        mainTitle.makeInvisible();
        warehouse.makeInvisible();
        plan.makeInvisible();
        result.makeInvisible();
        
        visible = false;   
    }
    
}
