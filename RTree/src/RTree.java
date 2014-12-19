import java.io.*;
import java.util.*;

/**
 * Created by cau19 on 14/11/25.
 */
public class RTree {
    static BufferedReader reader;
    ArrayList<Data> nodes= new ArrayList<Data>();
    int MAX_ENTRIES;
    int totalPagesTraversed=0;
    int numOfEntries;
    int howManyPoints=0;
    int howManySearchResults=0;
    int compare=0; //testing purposes only
    Page [] pages;
    Node root;


    /**
     * notes M is the number of entries and m is the minimum of entries
     *TODO: Keep track of I/O costs
     */

    public static void main (String []args){

        if(!validateArgs(args)){
            System.out.println("Please enter a file you construct the R tree");
            return;
        }
        RTree blah= new RTree();
        blah.go();

   }
    class NodeCompare implements Comparator<Data> {

        @Override
        public int compare(Data node, Data node2) {
            Long test= node.rValue;
            Long test2 = node2.rValue;
            return test.compareTo(test2);
        }
    }

    public void go(){
        //should this be dynamic?
        //calculate the number of entries for the pages
        //for this text file, it's 8
        numOfEntries=(4096)/((2*(Integer.SIZE/ Byte.SIZE)+500));
        //System.out.println(numOfEntries);

        constructArrayList();

        sortArrayList();

        createPages();

        addData();
        //TODO:  Clean up the bulk loader
        BulkLoad blah =new BulkLoad(numOfEntries);

        this.root=blah.buildTree(pages);
       // System.out.println(root);
     //   List<Data> test = new ArrayList<Data>();
        //System.out.println(searchValues(root, 16, 401));
       // System.out.println(totalPagesTraversed);
      //  totalPagesTraversed=0;
        //Node node, int minX, int minY, int maxX, int maxY
      //  searchPrintRange( root, 114 ,467, 5000,5000);
     //   checkBound();
      //  System.out.println(totalPagesTraversed);

       // System.out.println(test.size());
                //minX, minY, maxX, maxY
        //List <Data> test, Node node, int minX, int minY, int maxX, int maxY)
        //createRectangle();createTree();
        readUserInput();


       // checkIfAllPointsReturnTrue();
       // System.out.println(searchValues(root, 5000, 5000));


    }

    /**
     * checks if all the values are true. Debuging purposes only
     */
    public void checkIfAllPointsReturnTrue(){
        String line="";
        String copy="";
        try {
            reader = new BufferedReader(new FileReader("project3dataset30k-1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((line= reader.readLine())!=null){
                copy=line;
                //  int x= Integer.parseInt((line.substring(0, line.charAt(',')-1)));
                String x[] =copy.split(",");

                int valueX= Integer.parseInt(x[0]);
                int valueY= Integer.parseInt(x[1]);
                System.out.println(searchValues(root, valueX, valueY));
                if(!searchValues(root, valueX, valueY)){
                    System.out.println("We have a false");
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void checkBound(int minX, int minY, int maxX, int maxY){
        String line="";
        String copy="";
        try {
            reader = new BufferedReader(new FileReader("project3dataset30k-1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((line= reader.readLine())!=null){
                copy=line;
                //  int x= Integer.parseInt((line.substring(0, line.charAt(',')-1)));
                String x[] =copy.split(",");

                int valueX= Integer.parseInt(x[0]);
                int valueY= Integer.parseInt(x[1]);
                entryBound(minX,minY, maxX, maxY, valueX, valueY);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Reading through file: "+compare);
        compare=0;
    }
    public  void entryBound(int minX, int minY, int maxX, int maxY, int x, int y){
        //return minX<=this.minX && maxX<=this.maxX && minY<=this.minY && maxY<=this.maxY;
        //minX<=d.x && d.x<=maxX && minY<=d.y && d.y<=maxY
        if(minX<=x && x<=maxX && minY<=y && y<=maxY){
            compare++;
        }

    }

    /**
     * Read a user input for a single or range query.
     */
    private void readUserInput() {
        BufferedReader reader= new BufferedReader( new InputStreamReader(System.in));
        String input ="";

        //TODO: check for bad inputs
        while(true) {
            System.out.println("Please enter a choice\n" +
                    "Type \"range\" for range query\nType \"point\" for point query\n" +
                    "Type \"q\" to quit");

            try {
                input = reader.readLine();
                if(input.equals("q")){
                    break;
                }
                /**
                 * point queries
                 *
                 */
                if(input.equals("point")) {
                    System.out.println("Enter a point you would like to search for\nInputs should be input like the file. I.e. #,#\n" +
                            "Example: 123,345\n");
                    input=reader.readLine();
                    String copy = input;
                    try {
                        String x[] = copy.split(",");
                        int valueX = Integer.parseInt(x[0]);
                        int valueY = Integer.parseInt(x[1]);
                        System.out.println(searchValues(root, valueX, valueY));
                        System.out.println("I/O: "+totalPagesTraversed+"\n");
                        totalPagesTraversed=0;
                    }catch(Exception e){
                        System.out.println("Invalid inputs");
                    }

                }
                if(input.equals("range")){
                    System.out.println("\nEnter a set of points you would like to search " +
                            "\nA piar follow by a space follow by another number\nI.e. #,# [space] #,#\n" +
                            "Example: 123,345 345,800" +
                            "\n or even a single point is fine. Just don't enter any spaces" +
                            "\n i.e. #,#\n Example: 123,345");
                    input=reader.readLine();
                    String copy = input;
                    if(copy.contains(" ")) {
                        String values[] = copy.split(" ");
                        try {
                            String min[] = values[0].split(",");
                            String max[] = values[1].split(",");
                            int xMin = Integer.parseInt(min[0]);
                            int yMin = Integer.parseInt(min[1]);
                            int xMax = Integer.parseInt(max[0]);
                            int yMax = Integer.parseInt(max[1]);
                            searchPrintRange( root, xMin,yMin, xMax,yMax);

                            System.out.println("I/O: "+totalPagesTraversed+"\n");
                            System.out.println("How many search Results: "+howManySearchResults);
                            howManySearchResults=0;
                            totalPagesTraversed=0;
                            checkBound(xMin,yMin, xMax,yMax);
                           // System.out.println("xMin " + xMin + " yMin " + yMin
                            //        + " xMax: " + xMax + " yMax " + yMax);
                        }catch(Exception e){
                            System.out.println("Invalid inputs");
                        }

                    }
                    else{
                        try{
                            String values[] = copy.split(",");
                            int xMin = Integer.parseInt(values[0]);
                            int yMin = Integer.parseInt(values[1]);
                            int xMax = Integer.parseInt(values[0]);
                            int yMax = Integer.parseInt(values[1]);
                            searchPrintRange( root, xMin,yMin, xMax,yMax);
                            System.out.println("I/O: "+totalPagesTraversed+"\n");
                        //    System.out.println(howManySearchResults);
                            howManySearchResults=0;
                            totalPagesTraversed=0;
                        //   checkBound(xMin,yMin, xMax,yMax);
                        }catch(Exception e){
                            System.out.println("Invalid inputs");
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(input);
        }

    }

    /**
     * Searches a single given point recursively.
     * @param node
     * @param valueX
     * @param valueY
     * @return
     */
    private boolean searchValues(Node node,int valueX, int valueY) {
        if(!node.hasNext()){
            for(Rectangle r: node.rectangles){
                if(r==null){
                    //no contained in this node, look else where
                    return false;
                }
                totalPagesTraversed++;
                if(r.pPointer.entryExists(valueX, valueY)){

                    return true;
                }
            }
            //it's not contained in this rectangle, look at the next one
            return false;
        }
        for(Rectangle r: node.rectangles){
            if(r==null){
                return false;
            }
            if(r.rectangleContains(valueX, valueY)){
                if(searchValues(r.next, valueX, valueY)){
                    return true;
                }
                else{
                    //not needed, but I like it
                    continue;
                }
            }
        }
        return false;
    }

    /**
     * Recursively search the R tree to find the given rectangles.
     * Note: Rectangles can intersect
     * @param node
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     */
    private void searchPrintRange( Node node, int minX, int minY, int maxX, int maxY){
        if(!node.hasNext()){
            for(Rectangle r: node.rectangles){
                if(r==null){
                    //no contained in this node, look else where
                    return;
                }
                //int minX, int minY, int maxX, int maxY

                totalPagesTraversed++;
                if(r.pPointer.entryBound(minX, minY, maxX, maxY)){

                  //  System.out.println("Should be here");

                    getListOfResults(r.pPointer, minX, minY, maxX, maxY);
                }
            }
            //it's not contained in this rectangle, look at the next one
            return;
        }
        for(Rectangle r: node.rectangles){
            if(r==null){
                return;
            }
            //int minX, int maxX, int minY, int maxY
          //  System.out.println("hihi");
          //  int minX, int minY, int maxX, int maxY
            //int minX, int maxX, int minY, int maxY
            //if a rectangle is contained or intersects
            // should the containedBy be used?
            if( r.intersects(minX, maxX, minY, maxY)) {
                searchPrintRange(r.next, minX, minY, maxX, maxY);
            }

        }

        return ;

    }

    /**
     * prints the data point for a range query
     * @param p
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     */
    private void getListOfResults(Page p, int minX, int minY, int maxX, int maxY){
        for (Data d: p.entries){
            if(minX<=d.x && d.x<=maxX && minY<=d.y && d.y<=maxY){
                System.out.println(d);
                howManySearchResults++;
            }
        }

    }

    /**
     * Create the pages at first
     */
    private void createPages() {
        //TODO:  make it dynamic
        //testing purposes only
        //System.out.println(howManyPoints/numOfEntries);
       // System.out.println(nodes.size());
        pages = new Page[nodes.size()/numOfEntries];
        for (int i = 0;  i<pages.length;i++){
            pages[i]=new Page(numOfEntries);
        }
    }

    /**
     * adds the data point to a page
     */
    public void addData(){
        int counter= 0;
        for (Page page : pages) {
            //TODO: 8 should not be there.
            while(page.getNumEntries()!=numOfEntries) {
                page.addValue(nodes.get(counter));
                counter++;
            }

        }
        /*for(Page page: pages){
            System.out.println(page.toString());}*/

        //30,000 was returned
        //System.out.println(counter);

    }

    /**
     * sorts and remove duplicates
     */
    private void sortArrayList() {
        nodes=removeDuplicates(nodes);
        NodeCompare comparator = new NodeCompare();
        Collections.sort(nodes, comparator);
        //System.out.println(nodes.size());

    }


    /**
     * removes duplicates by using a data structure that doesn't allow duplicates O(n) time
     * @param a
     * @return
     */
    public  ArrayList<Data> removeDuplicates(ArrayList<Data> a){
        //naive approach
        //is this really a good approach? Using a data structure that doesn't allow duplicates?
        HashSet<Data> hs = new HashSet<Data>();
        hs.addAll(a);
        a.clear();
        a.addAll(hs);

        return a;
    }

    /**
     * constructs the arrayList and then pass it later to create the pages
     */
    private void constructArrayList(){
        String line;
        String copy;
        try {
            while ((line= reader.readLine())!=null){
                copy=line;
                //  int x= Integer.parseInt((line.substring(0, line.charAt(',')-1)));
                String x[] =copy.split(",");

                int valueX= Integer.parseInt(x[0]);
                int valueY= Integer.parseInt(x[1]);
                Data add= new Data(valueX, valueY);
                add.setrValue(HilbertMethod.getHilbertValue(add.x, add.y));
                nodes.add(add);
                howManyPoints++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: close reader
        // System.out.println(howManyPoints);


    }

    /**
     * valides the arguments are at least one, the file name
     * @param args
     * @return
     */
    private static boolean validateArgs(String[] args) {
        if(args.length!=1){
            System.out.println("Incorrent arguements. \n Need to enter a file.txt file");
            return false;
        }

            try {
                reader = new BufferedReader(new FileReader(args[0]));
            } catch (FileNotFoundException e) {
               System.out.println("An error has occurred");
                return false;
            }


        return true;

    }
}
