/**
 * Created by cau19 on 14/12/10.
 */
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Job;

import java.util.Iterator;
import java.util.StringTokenizer;

public class ratingReviews extends MapReduceBase implements
        Mapper<LongWritable, Text, Text, FloatWritable>,Reducer<Text, FloatWritable, Text, FloatWritable> {

    static  boolean  firstline=true;
    static int counter=0;
    static  int movieID=-1;
    static  int rating=-1;
    private Text word = new Text();

    public static void main(String [] args) throws IOException {

        JobConf conf = new JobConf(ratingReviews.class);
        conf.setJarByClass(ratingReviews.class);
        conf.setJobName("ratingReviews");

        conf.setOutputKeyClass(Text.class);

        conf.setMapperClass(ratingReviews.class);
        conf.setCombinerClass(ratingReviews.class);
        conf.setReducerClass(ratingReviews.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        conf.setOutputValueClass(FloatWritable.class);


        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);

        System.exit(0);





    }

    @Override
    public void map(LongWritable longWritable, Text value, OutputCollector<Text, FloatWritable>output, Reporter reporter) throws IOException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        //  System.out.println("here: " +line);
        String movieId="";
        int ratingId=-5;
        while (tokenizer.hasMoreTokens()) {

            String get = tokenizer.nextToken();
            if (get.equals("UserId")||get.equals("MovieId")|| get.equals("Rating")|| get.equals("Date")) {
                continue;
            }
            else{
                if(1==counter){
                    movieId=get;
                }
                else if(2==counter){
                   // System.out.println(rating);
                    ratingId= Integer.parseInt(get);
                }
                System.out.println(ratingId);
                if(ratingId==-5) {
                    counter++;
                    continue;
                }
                }
                counter++;
                word.set(movieId);

                output.collect(word, new FloatWritable(ratingId));
            }
        counter=0;
            // System.out.println(get);






        /* while (tokenizer.hasMoreTokens()) {
            String get = tokenizer.nextToken();
            if (firstline) {
                if (get.equals("MovieId")) {
                    movieID = counter;
                   // System.out.println(movieID);


                } else if (get.equalsIgnoreCase("rating")) {
                    rating = counter;
                }
                counter++;

            }
            else{
                if(movieID==counter){
                    movieId=get;
                }
                else if(rating==counter){
                   // System.out.println(rating);
                    ratingId= Integer.parseInt(get);

                }
                counter++;
            }
            // System.out.println(get);

        }
        if(!firstline) {
            word.set(movieId);
            output.collect(word, new FloatWritable(ratingId));
        }

        counter=0;
        firstline=false;*/
    }

    @Override
    public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException {
        float sum = 0;

        int counter=0;
        while (values.hasNext()) {
            sum += values.next().get();
            counter++;

        }
        float result =sum/counter;
        output.collect(key, new FloatWritable(result));
    }
}