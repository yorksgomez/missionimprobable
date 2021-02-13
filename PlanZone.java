/**
 * Zone plan's cam views
 */
public class PlanZone extends Zone  {

    /** ---- Variables ---- **/
    private CamView front, side, top;
    private static final int CAM_SIZE = 100, H_STRUT = 40, V_STRUT = 20;
    
    /**
     * generates a basic plan zone
     * @param rows: the rows for the grid
     * @param columns: the columns for the grid
     */
    public PlanZone(int rows, int columns) {
        super("Plan", "white", 480, 170);
        xPosition = 20;
        yPosition = 215;

        front = new CamView(xPosition + H_STRUT, yPosition + V_STRUT * 2, CAM_SIZE, CAM_SIZE, rows, columns, "Front View");
        side = new CamView(xPosition + CAM_SIZE + H_STRUT * 2, yPosition + V_STRUT * 2, CAM_SIZE, CAM_SIZE, rows, columns, "Side View");
        top = new CamView(xPosition + CAM_SIZE * 2 + H_STRUT * 3, yPosition + V_STRUT * 2, CAM_SIZE, CAM_SIZE, rows, columns, "Top View");
        
        init();
    }
    
    /**
     * Updates cam views
     * @param front: disposition
     * @param side: disposition
     * @param top: disposition
     */
    public void updateViews(int[][] front, int[][] side, int[][] top) {
        this.front.changeDisposition(front);
        this.side.changeDisposition(side);
        this.top.changeDisposition(top);
    }
    
    /**
     * Returns front CamView
     * @return front CamView
     */
    public CamView getFront() {
        return front;
    }
    
    /**
     * Returns side CamView
     * @return side CamView
     */
    public CamView getSide() {
        return side;   
    }
    
    /**
     * Returns top CamView
     * @return top CamView
     */
    public CamView getTop() {
        return top;   
    }
    
    /**
     * makes zone visible
     */
    @Override
    public void makeVisible() {
        super.makeVisible();
        
        front.makeVisible();
        side.makeVisible();
        top.makeVisible();
    }
    
    /**
     * makes zone invisible
     */
    @Override
    public void makeInvisible() {
        super.makeInvisible();
        
        front.makeInvisible();
        side.makeInvisible();
        top.makeInvisible();
    }
    
}
