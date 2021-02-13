import java.util.Arrays;

/**
 * Executes the Mission Improbable game
 */
public class Mission {

    /** ---- Variables ---- **/
    private int length, width, stolen, warenum, plannum, last[];
    private int[][] base, idea;
    private boolean visible, ok;
    private Text mainTitle, stolenText;
    private WareHouseZone warehouse;
    private PlanZone plan;
    private ResultZone result;
    private static final int LAST_NONE = 0,
                             LAST_STEAL = 1,
                             LAST_ARRAGE = 2;
    
    /**
     * Constructor for objects of class Mission
     */
    public Mission(int length, int width) {
        this.length = length;
        this.width = width;
        stolen = warenum = plannum = 0;
        ok = true;
        base = new int[length][width];
        idea = new int[length][width];
        last = new int[5];
        
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
    
    /**
     * Rotate a converted disposition (maxes of a row or column)
     * @param maxs: list with max values
     * @param m: size of matrix
     * @param n: size of matrix
     * @return maxes converted into a matrix
     */
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
    *  Return the matrix with front view
    *  @param disposition: the current disposition to convert
    *  @return converted disposition
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
    *  Return the matrix with side view
    *  @param disposition: the current disposition to convert
    *  @return converted disposition
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
    *  Return the matrix with top view
    *  @param disposition: the current disposition to convert
    *  @return converted disposition
    */
    private int[][] calcTop(int[][] disposition) {
        int[][] newdis = new int[disposition.length][disposition[0].length];
        
        for(int i = 0; i < newdis.length; i++) {
         
            for(int j = 0; j < newdis[i].length; j++) {
            
                if(disposition[i][j] > 0)
                    newdis[i][j] = 1;
                
            }
            
        }
        
        return newdis;
    }
    
    /**
     * Copy a disposition
     * @param d: distribution to copy
     * @return the copy
     */
    private int[][] copyDisposition(int[][] d) {
        int copy[][] = new int[d.length][];
        
        for(int i = 0; i < d.length; i++) {
            copy[i] = new int[d[i].length];
            
            for(int j = 0; j < d[i].length; j++)
                copy[i][j] = d[i][j];
            
        }
        
        return copy;
    }
    
    /**
    * Update all three views, top, side and front from WareHouse
    */
    private void updateWareViews() {
        int[][] front = calcFront(base),
                side = calcSide(base),
                top = calcTop(base);
                
        warehouse.updateViews(front, side, top);
    }
    
    /**
     * Update the plan cameras views
     */
    private void updatePlanView() {
        int[][] front = calcFront(idea),
                side = calcSide(idea),
                top = calcTop(idea);
                
        plan.updateViews(front, side, top);
    }
    
    /**
     * checks if two dispositions are equal
     * @param a: first disposition
     * @param b: second disposition
     * @return true if the dispositions are equal
     */
    private boolean areDispositionEqual(int[][] a, int[][] b) {
        
        for(int i = 0; i < a.length; i++) {
            
            for(int j = 0; j < a[i].length; j++) {
                
                if(a[i][j] != b[i][j])
                    return false;
                
            }
            
        }
        
        return true;
    }
    
    /**
     * Checks if warehouse's layout and plan's layout are equivalents
     * @return true is they're equal
     */
    private boolean areBaseAndIdeaEquals() {
        return areDispositionEqual(plan.getFront().getDisposition(), warehouse.getFront().getDisposition()) && 
               areDispositionEqual(plan.getSide().getDisposition(), warehouse.getSide().getDisposition()) && 
               areDispositionEqual(plan.getTop().getDisposition(), warehouse.getTop().getDisposition());
    }
    
    /**
     * Updates the plan's section background
     */
    private void updatePlanColor() {
        
        if(areBaseAndIdeaEquals()) {
            plan.changeBackground("green");
        } else {
            plan.changeBackground("red");   
        }   
        
        plan.makeVisible();
    }
    
    /**
     * update the stolen num of boxes
     * @param stolen: num to set
     */
    private void updateStolen(int stolen) {
        this.stolen = stolen;
        result.changeStolen(stolen);
    }

    /**
     * update the warehouse num of boxes
     * @param ware: num to set
     */
    private void updateWarenum(int ware) {
        this.warenum = ware;
        result.changeWare(ware);
    }
    
    /**
     * update the plan num of boxes
     * @param plan: num to set
     */
    private void updatePlannum(int plan) {
        this.plannum = plan;
        result.changePlan(plan);
    }
    
    /**
    * Save a box in the warehouse, given by row and column
    * @param row: point of the box
    * @param column: point of the box
    */
   public void store(int row, int column) {
        base[row - 1][column - 1]++;
        updateWarenum(warenum + 1);
        updateWareViews();
    }
    
    /**
    * Save a box in the warehouse, given by row and column
    * @param crate: position of the box
    */    
    public void store(int[] crate) {
        store(crate[0], crate[1]);   
    }
    
    /**
    * Generates a copy of the views to be part of the plan
    */
    public void copy() {
        updateStolen(0);
        updatePlannum(warenum);
        idea = Arrays.copyOf(base, base.length);
        
        int[][] f = copyDisposition(warehouse.getFront().getDisposition()),
                s = copyDisposition(warehouse.getSide().getDisposition()),
                t = copyDisposition(warehouse.getTop().getDisposition());
        
        plan.getFront().changeDisposition(f);
        plan.getSide().changeDisposition(s);
        plan.getTop().changeDisposition(t);
        updatePlanColor();
    }

    /**
    * Extract a box of the plan
    * @param row: point of the box
    * @param column: point of the box
    */
    public void steal(int row, int column) {
        
        if(idea[row - 1][column - 1] > 0) {
            idea[row - 1][column - 1]--;
            last[0] = LAST_STEAL;
            last[1] = row - 1;
            last[2] = column - 1;
            
            updatePlanView();
            updateStolen(stolen + 1);
            updatePlannum(plannum - 1);
            updatePlanColor();
            plan.makeVisible();
            ok = true;
        } else {
            ok = false;   
        }
            
    }
    
    /**
     * Steal a box
     * @param crate: position of the box
     */
    public void steal(int[] crate) {
        steal(crate[0], crate[1]);   
    }
    
    /**
     * Return the last action (steal or arrage) made
     */
    public void returnCrate() {
        
        if(last[0] == LAST_STEAL) {
            idea[last[1]][last[2]]++;
            updateStolen(stolen - 1);
            updatePlannum(plannum + 1);
            updatePlanView();
            updatePlanColor();
        } else if(last[0] == LAST_ARRAGE) {
            idea[last[1]][last[2]]++;
            idea[last[3]][last[4]]--;
            updatePlanView();
            updatePlanColor();
        }
        
        last[0] = LAST_NONE;
    }
    
    /**
     * Moves a box
     * @param from: the point where the box is
     * @param to: the point to put the box
     */
    public void arrage(int[] from, int[] to) {
        
        if(idea[from[0]][from[1]] > 0) {
            last[0] = LAST_ARRAGE;
            last[1] = from[0];
            last[2] = from[1];
            last[3] = to[0];
            last[4] = to[1];
            
            idea[from[0]][from[1]]--;
            idea[to[0]][to[1]]++;
            updatePlanView();
            updatePlanColor();
            ok = true;
        } else {
            ok = false;   
        }
        
    }
    
    /**
    * Returns the number of stolen boxes
    * @return int with the number
    */
    public int stolen() {
        return stolen;   
    }
    
    /**
     * Get the warehouse's layout
     * @return matrix with warehouse
     */
    public int[][] warehouse() {
        return base;
    }
    
    /**
     * Get the plan's layout
     * @return matrix with layout
     */
    public int[][] layout() {
        return idea;   
    }

    /**
    * Makes visible the graphic of the boxes and the one of the plan to rob them. Also the title and the number of stolen boxes
    */
    public void makeVisible() {
        visible = true;
        
        mainTitle.makeVisible();
        warehouse.makeVisible();
        plan.makeVisible();
        result.makeVisible();
    }
    
    /**
    * Makes invisible the graphic of the boxes and the one of the plan to rob them. Also the title and the number of stolen boxes
    */
    public void makeInvisible() {
        mainTitle.makeInvisible();
        warehouse.makeInvisible();
        plan.makeInvisible();
        result.makeInvisible();
        
        visible = false;   
    }
    
    /**
     * Stop the mission project
     */
    public void finish() {
        System.exit(0);
    }
    
    /**
     * Returns if an action (steal or arrage) was correct
     * @return true if it was, false if wasn't
     */
    public boolean ok() {
        return ok;   
    }
    
}
