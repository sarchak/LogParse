import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class LogParseReducerAll extends MapReduceBase
  implements Reducer<Text, LongWritable, Text, LongWritable> {

  public void reduce(Text key, Iterator<LongWritable> values,
      OutputCollector<Text, LongWritable> output, Reporter reporter)
      throws IOException {
    
    long minval,maxval;
    minval = maxval  = values.next().get();
    long val = 0;
    while (values.hasNext()) {
	val = values.next().get();
	if(val > maxval)
		val = maxval;
	if(val < minval)
		val = minval;
    }
    if(val != 0)
	    output.collect(key, new LongWritable(maxval - minval));
    else
	    output.collect(key, new LongWritable(-999999999));

  }
}
