
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cau19 on 14/11/27.
 */
public class BulkLoad {

    Node root;
    private int entries;

    /**
     * Pass the number of entries a node should hold for the rectangles
     * @param entries
     */
    public BulkLoad(int entries){
        this.entries=entries;
    }
    public Node buildTree(Page [] createMe){
        List<Node> level= createLeaves(createMe);
        //if we still more than 1 node we still have an upper level to build
        while(level.size()>1 ){
            level=buildUpper(level);
        }
        //set the root to the first node
        root=level.get(0);
        //  System.out.println(root);
        //testing purposes
       //  int counter=0;
       // Node curr=root;
      /* System.out.println("Root: \n"+root);
        while(curr.hasNext()){
            curr= curr.getRecAtIndex(0).next;
            System.out.println(curr);
            counter++;
        }
        System.out.println(curr.getRecAtIndex(0).pPointer);*/
        // System.out.println(counter);
        //return the root,
        return root;
    }

    /**
     * creates the upper level that each node contains a rectangle that points to a node
     * @param lowerLevel
     * @return
     */
    private List<Node> buildUpper(List<Node> lowerLevel) {
        //LinkedLists saves more heap space
        //create a linkedList to hold the nodes
        List<Node> upperLevel = new LinkedList<Node>();
        //make a node with the maximum entries
        Node curr = new Node(entries);
        //add it to the linkedList so it doesn't go away
        //when we move on to the next node
        upperLevel.add(curr);
        //Loop the lower level and connect them
        for (Node current : lowerLevel) {
            //if it's full, make a new Node and add it to the linkedList
            if (curr.isFull()) {
                curr = new Node(entries);
                upperLevel.add(curr);
            }
            //create a new MBR for the given node
            Rectangle addMe = getRectangle(current.rectangles);
            //let the rectangle point to the next node
            addMe.setNext(current);
            //add the rectangle to the current node
            curr.addRectangle(addMe);
        }
         return upperLevel;
    }

    /**
     * Creates a rectangle to bound a set of given rectangles
     * @param rectangles
     * @return
     */
    private Rectangle getRectangle(Rectangle[] rectangles) {

        //create a new rectangle to be a MBR
        Rectangle lastR = new Rectangle();
        //for each rectangle, find the min and max for x and y respectively
        for (Rectangle r: rectangles){
            if(r==null){
                break;
            }
            //set values will handle
            lastR.setValues(r);
        }
        return lastR;
    }

    /**
     * create leaves and the rectangles point to pages, not nodes.
     * @param pages
     * @return
     */
    private List<Node> createLeaves(Page[] pages){
        //LinkedLists saves more heap space
        List<Node> leaves= new LinkedList<Node>();
        Node currentNode= new Node(entries);
        leaves.add(currentNode);
        for (Page p: pages){
            //if full, create a new node, and we'll keep adding things
            if(currentNode.isFull()){
                currentNode = new Node(entries);
                leaves.add(currentNode);
            }
            Rectangle add=new Rectangle(p.minX, p.minY, p.maxX, p.maxY);
            //set the rectangle to a page
            add.setPagePointer(p);
            //add the rectangle to the node
            currentNode.addRectangle(add);
        }
        return leaves;


    }

    /**
     * Probably should remove this
     * @return
     */
    public Node getRoot(){
            return this.root;
    }
    /*for (int i=0; i<blah.size();i++) {
            //public Rectangle(int minX, int minY, int maxX, int maxY){
            Page p = pages[i];
            String temp="R"+i;
            blah.add(new Rectangle(p.minX, p.minY, p.maxX, p.maxY, temp));
            blah.get(i).setPagePointer(p);
        }*/

}

