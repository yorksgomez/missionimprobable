import java.util.Arrays;

public class Mission {

    private int length, width, stolen;
    private int[][] base, idea;
    private boolean visible;
    private Text mainTitle, stolenText;
    private WareHouseZone warehouse;
    private PlanZone plan;
    private ResultZone result;
    
    public Mission(int length, int width) {
        this.length = length;
        this.width = width;
        stolen = 0;
        base = new int[length][width];
        idea = new int[length][width];
        
        init();
    }
    
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
    
    private int[][] calcTop(int[][] disposition) {
        return disposition;
    }
    
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
    
    public void store(int row, int column) {
           base[row - 1][column - 1]++;
           updateWareViews();
    }
    
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
