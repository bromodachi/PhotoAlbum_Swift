/**
 * Created by cau19 on 14/11/25.
 */
public class Rectangle {
    int maxX, maxY =0;
    int  minX= Integer.MAX_VALUE;
    int minY=Integer.MAX_VALUE;
    Page pPointer=null;
    Rectangle rPointer=null;
    String identify;//debugging purposes only
    Node next=null; //null if not more nodes to traverse;
    public void setNext(Node n){
        this.next=n;
    }

    public Rectangle(){

    }

    public Rectangle(int minX, int minY, int maxX, int maxY){
        this.maxX=maxX;
        this.maxY=maxY;
        this.minX=minX;
        this.minY=minY;
    }
    //remove this later
    public Rectangle(int minX, int minY, int maxX, int maxY, String b){
        this.maxX=maxX;
        this.maxY=maxY;
        this.minX=minX;
        this.minY=minY;
        this.identify=b;
    }

    //if it's null, we're at a leaf node
    public Rectangle getPointer(){
        return this.rPointer;
    }

    //if it's null, we're at a branch page
    public Page getPagePointer(){
        return this.pPointer;
    }

    public void setPagePointer(Page p){
        this.pPointer=p;
    }
    public void setRectanglePointer(Rectangle p){
        this.rPointer=p;
    }

    /**
     * if a rectangle contains a certain point
     * @param x
     * @param y
     * @return
     */
    public boolean rectangleContains(int x, int y){
        return this.minX<=x && x<=this.maxX && this.minY<=y && y<=this.maxY;
    }

    /**
     * for range query. The rectangle they pass can intersect
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     * @return
     */
    public boolean intersects(int minX, int maxX, int minY, int maxY){
        return this.maxX >= minX && this.minX <= maxX && this.maxY >= minY && this.minY <= maxY;
    }

    /**
     * For range query. Decided not to go with this since it was wrong :(
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     * @return
     */
    public boolean rectangleBounds(int minX, int maxX, int minY, int maxY){
        //minX<=d.x && d.x<=maxX && minY<=d.y && d.y<=maxY
      //  System.out.println(minX +" this recminX: "+this.minX+" passed max x"+ maxX+ " this rec max X: "+this.maxX);
        //minX<=d.x && d.x<=maxX && minY<=d.y && d.y<=maxY
        return minX<=this.minX && maxX<=this.maxX && minY<=this.minY && maxY<=this.maxY;
    }



    public String toString(){
        return "min X: (" + minX+ " Min Y: "+ minY+ ") Max x: "+ maxX + " Max y: ("+ maxY+") name: "+identify+"\n";
    }

    /**
     * just sets the values of the min and max for x and y.
     * @param r
     */
    public void setValues(Rectangle r){
        if(minX>r.getMinX()){
            minX=r.getMinX();
        }
        if(minY>r.getMinY()){
            minY=r.getMinY();
        }
        if(maxX<r.getMaxX()){
            maxX=r.getMaxX();
        }
        if(maxY<r.getMaxY()){
            maxY=r.getMaxY();
        }
    }

    public int getMinX() {
        return  this.minX;
    }
    public int getMaxX() {
        return  this.maxX;
    }
    public int getMinY() {
        return this.minY;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public void setMaX(int x){
        this.maxX=x;
    }
    public void setMaxY(int y){
        this.maxY=y;
    }
    public void setMinX(int x){
        this.minX=x;
    }
    public void setMinY(int y){
        this.minY=y;
    }
}
