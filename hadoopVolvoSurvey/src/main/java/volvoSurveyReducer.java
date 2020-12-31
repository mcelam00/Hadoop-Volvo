//volvoSurveyReducer
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class volvoSurveyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values,Context context)throws IOException, InterruptedException {
    
   int mean = 0;
   int partialSum = 0;
   int counted = 0;

   for (IntWritable value : values) {
	   counted++;
	   partialSum = partialSum + value.get();
   }
   
   mean = partialSum/counted;
   
   context.write(key, new IntWritable(mean));
  
  }
}

