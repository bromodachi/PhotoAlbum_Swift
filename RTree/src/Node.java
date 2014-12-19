/**
 * Created by cau19 on 14/11/26.
 * Holds the list of rectangles
 */
public class Node {
    Rectangle [] rectangles;
    int counter=0;
    Node next;
    int numEntries;

    public Node(int numEntries){
        this.numEntries=numEntries;
        this.rectangles=new Rectangle[numEntries];
    }
    public boolean isFull(){
        return counter==rectangles.length;
    }

    public void addRectangle(Rectangle r){
        rectangles[counter]=r;
        counter++;
    }
    public void setNext(Node n){
        this.next=n;
    }

    public Rectangle getRecAtIndex(int i){
        return rectangles[i];
    }

    /**
     * debugging purposes only
     * @return
     */
    public String toString(){
        String blah="";
        int counter=0;
        while(true){
            if(counter==numEntries){
                break;
            }
            blah=blah+" "+rectangles[counter];
            counter++;
        }
        return blah;
    }

    /**
     * at least the first entry has a rectangle
     * should it be for an index?
     * @return
     */
    public boolean hasNext(){
        if(rectangles==null){
            return false;
        }
        if(rectangles[0].next==null){
            return false;
        }
        return true;

    }

    /**
     *has next for a certain index
     * @param index
     * @return
     */
    public boolean hasNext(int index){
        if(rectangles==null){
            return false;
        }
        if(rectangles[index].next==null){
            return false;
        }
        return true;

    }

}
