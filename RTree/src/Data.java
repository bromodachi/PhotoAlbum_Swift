import java.util.Random;

/**
 * Created by cau19 on 14/11/25.
 */
public class Data {
    /**
     * should probably be called point instead of node UPDATE: Class is now called Data
     * TODO:remove rValue when done with it
     */
    int x;
    int y;
    long rValue;
    char[] random_id = new char[500];

    public Data(int x, int y){
        this.x=x;
        this.y=y;

        Random random = new Random();
        for(int i=0; i<random_id.length;i++ ) {
            random_id[i] = (char)(random.nextInt(26) + 'a');
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;

    }
    public boolean equals(Object o){
        if(o == null){return false;}
        if(!(o  instanceof Data)){ return false;}
        Data castMe= (Data) o;
        if(castMe.x==this.x){
            if(castMe.y==this.y){
                return true;
            }
            return false;
        }
        return false;
    }
    public String toString(){
        return x+","+y;
    }

    public void setrValue(long rValue){
        this.rValue=rValue;
    }
}
