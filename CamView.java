
public class CamView {
    
    private int width, height, x, y, rw, rh;
    private int[][] disposition;
    private Rectangle[][] viewDisposition;
    private boolean visible;
    private Text title;
    
    public CamView(int x, int y, int width, int height, int rows, int columns, String title) {
       this.width = width;
       this.height = height;
       this.x = x;
       this.y = y;
       this.title = new Text(title);
       this.title.move(x + (width - this.title.getSize() * title.length() / 2) / 2, y - 5);
       this.title.changeSize(12);
       
       rw = width / columns;
       rh = height / rows;
       disposition = new int[][]{};
       viewDisposition = new Rectangle[rows][columns];
       
       for(int i = 0; i < rows; i++) {
           Rectangle[] row = viewDisposition[i];
           
           for(int j = 0; j < columns; j++) {               
               Rectangle r = new Rectangle();
               r.move(x + j * rw, y + i * rh);
               r.changeSize(0, 0);
               r.changeColor("orange");
               
               viewDisposition[i][j] = r;
           }
           
       }
       
    }
    
    public void changeDisposition(int[][] disposition) {
        this.disposition = disposition;
        
        updateView();
    }
    
    private void updateView() {
        
        for(int i = 0; i < disposition.length; i++) {
            
            for(int j = 0; j < disposition.length; j++) {
                Rectangle r = viewDisposition[i][j];
                r.changeSize(0, 0);
                int p = disposition[i][j];
                
                if(p > 0)
                    r.changeSize(rh, rw);
                
            }
            
        }
        
    }
    
    public int[][] getDisposition() {
        return disposition;
    }
    
    public void makeVisible() {
        
        for(Rectangle[] row : viewDisposition) {
         
            for(Rectangle r : row)
                r.makeVisible();
            
        }
        
        title.makeVisible();
    }
    
    public void makeInvisible() {
        
        for(Rectangle[] row : viewDisposition) {
         
            for(Rectangle r : row)
                r.makeInvisible();
            
        }
        
        title.makeInvisible();
    }
    
    
}
