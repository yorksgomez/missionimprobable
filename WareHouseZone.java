

public class WareHouseZone extends Zone {

    private CamView front, side, top;
    private static final int CAM_SIZE = 100, H_STRUT = 40, V_STRUT = 20;
    
    public WareHouseZone(int rows, int columns) {
        super("Warehouse", "gray", 480, 170);
        xPosition = 20;
        yPosition = 60;
        front = new CamView(xPosition + H_STRUT, yPosition + V_STRUT * 2, CAM_SIZE, CAM_SIZE, rows, columns, "Front View");
        side = new CamView(xPosition + CAM_SIZE + H_STRUT * 2, yPosition + V_STRUT * 2, CAM_SIZE, CAM_SIZE, rows, columns, "Side View");
        top = new CamView(xPosition + CAM_SIZE * 2 + H_STRUT * 3, yPosition + V_STRUT * 2, CAM_SIZE, CAM_SIZE, rows, columns, "Top View");
        
        init();
    }
    
    public void updateViews(int[][] front, int[][] side, int[][] top) {
        this.front.changeDisposition(front);
        this.side.changeDisposition(side);
        this.top.changeDisposition(top);
    }
    
    public CamView getFront() {
        return front;
    }
    
    public CamView getSide() {
        return side;   
    }
    
    public CamView getTop() {
        return top;   
    }
    
    @Override
    public void makeVisible() {
        super.makeVisible();
        
        front.makeVisible();
        side.makeVisible();
        top.makeVisible();
    }
    
    @Override
    public void makeInvisible() {
        super.makeInvisible();
        
        front.makeInvisible();
        side.makeInvisible();
        top.makeInvisible();
    }

}
