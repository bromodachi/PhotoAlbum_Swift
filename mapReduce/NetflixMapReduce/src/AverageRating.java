/**
 * Created by cau19 on 14/12/19.
 */
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class AverageRating {

    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, FloatWritable> {
        private Text word = new Text();

        public void map(LongWritable key, Text value, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException {
            String line = value.toString();
            line = line.substring(line.indexOf('\n')+1);
            StringTokenizer tokenizer = new StringTokenizer(line);
            int i=0;
            System.out.println("test");
            while(tokenizer.hasMoreTokens()){
                String tok= tokenizer.nextToken();
                if (tok.equals("UserId")||tok.equals("MovieId")|| tok.equals("Rating")|| tok.equals("Date")) {
                    continue;
                }

                if(i == 1) {
                    word.set(tok);
                    i++;
                }
                else if(i == 2) {
                    output.collect(word, new FloatWritable(Integer.parseInt(tok)));
                    i++;
                }else {
                    i++;
                }
            }

        }
    }

    public static class Reduce extends MapReduceBase implements Reducer<Text, FloatWritable, Text, FloatWritable> {
        public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException {
            float sum = 0;
            int counter = 0;
            while (values.hasNext()) {
                sum += values.next().get();
                counter++;
            }
            output.collect(key, new FloatWritable(sum/counter));
        }
    }

    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(AverageRating.class);
        conf.setJobName("averagerating");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Reduce.class);
        conf.setReducerClass(Reduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputValueClass(FloatWritable.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);

        System.exit(0);
    }
}