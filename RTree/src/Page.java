

/**
 * Created by cau19 on 14/11/25.
 */
public class Page {
    Data[]entries;
    int counter=0;
    int numEntries;
    int maxX, maxY =0;
    int  minX= Integer.MAX_VALUE;
    int minY=Integer.MAX_VALUE;


    public Page(int maxEntries){
        entries= new Data[maxEntries];
    }
    public int getMaxX(){
        return this.maxX;

    }
    public int getMinX(){
        return this.minX;
    }
    public int getMinY(){
        return this.minY;
    }
    public int getMaxY(){
        return this.maxY;
    }

    public void addValue(Data add){
        //set the minX as we add the node
        if(minX>add.getX()){
            minX=add.getX();
        }
        if(minY>add.getY()){
            minY=add.getY();
        }
        if(maxX<add.getX()){
            maxX=add.getX();
        }
        if(maxY<add.getY()){
            maxY=add.getY();
        }
        entries[counter]=add;
        counter++;
        numEntries++;
    }

    public boolean entryExists(int x, int y){
        for (Data d: entries){
            if(d.getX()==x && d.getY()==y){
                return true;
            }
        }
        return false;

    }
    public boolean entryBound(int minX, int minY, int maxX, int maxY){
        for (Data d: entries){
            //return minX<=this.minX && maxX<=this.maxX && minY<=this.minY && maxY<=this.maxY;
            //minX<=d.x && d.x<=maxX && minY<=d.y && d.y<=maxY
            if(minX<=d.x && d.x<=maxX && minY<=d.y && d.y<=maxY){
                return true;
            }
        }
        return false;

    }
    public Data getEntry(int minX, int minY, int maxX, int maxY){
        for (Data d: entries){
            if(minX<=d.x && d.x<=maxX && minY<=d.y && d.y<=maxY){
                return d;
            }
        }
        return null;

    }

    public int getNumEntries(){
        return this.numEntries;
    }

    public String toString(){
        String entry="";
        for (Data entry1 : entries) {
            entry = entry + "\n X: " + entry1.getX() + " y: " + entry1.getY();
        }
        return entry;
    }
}
