//volvoSurveyMapper

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class volvoSurveyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  private CarDataRecordParser parser = new CarDataRecordParser();

  @Override
  public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {

    
	  /*CAR AGE VALIDATION*/
	  boolean age = 
			  
	  /*CAR TYPE VALIDATION*/
			  
	  /*ERRORS IN SOME FIELD OF THE RECORDS*/
	  
	  
	  /**/
	  
	  
	  
	  
	  
	  
	  
	  parser.parse(value);
    if (parser.isValidTemperature()) {//si en el registro la temperatura es correcta y la calidad es buena escribe en el contexto
      context.write(new Text(parser.getYear()),new IntWritable(parser.getAirTemperature())); //debe pasar la clave valor sobre los que opera el reducer, pero el reducer no piensa ni sabe los buenos/malos
    }
  
  
  
  
  
  
  
  
  
  }
}

