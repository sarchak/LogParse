import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Date;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class LogParseMapperAll extends MapReduceBase
  implements Mapper<LongWritable, Text, Text, LongWritable> {

  private static final int MISSING = 9999;
  
  public void map(LongWritable key, Text value,
      OutputCollector<Text, LongWritable> output, Reporter reporter)
      throws IOException {

    String line = value.toString();
    StringTokenizer st = new StringTokenizer(line, "=   ");
    /* This would be the event type */
    String first = st.nextToken();
    /* This would be the id */
    String id = st.nextToken();

    String check = new String();
    int n = 0;
    /* Generate the timestamp from the input */
    while(st.hasMoreTokens() && n != 6) {
           check += st.nextToken();
           check += " ";
           n++;
    }
    /* Create a new date object */
    Date d = new Date(check);
 
    /* Insert key as event + user
       Insert Value as timestamp */
    
    output.collect(new Text(id), new LongWritable(d.getTime()));
  }
}
