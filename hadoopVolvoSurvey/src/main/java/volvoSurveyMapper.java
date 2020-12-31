//volvoSurveyMapper

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class volvoSurveyMapper extends Mapper<LongWritable, Text, Text, IntWritable> { //habria que poner text tmb

  private CarDataRecordParser parser = new CarDataRecordParser();

  @Override
  public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {

	  /*SEPARATING RECORD PARTS*/
	  
	  parser.parse(value);
	  
	  /*ERRORS IN SOME FIELD OF THE RECORDS*/
	  
	  if(parser.isValidRecord() == false) {
		  
		  
		  /*It is declined completely due to considering it unrealiable*/
		  
		  
	  /*NO ERRORS 1 CAR PER THIS RECORD*/
		  
	  }else if(parser.numOfCars() == 1) {
		  
		  	/*If it is not electric, ok*/
		  if(parser.getTypeCar1().compareTo("E") != 0) {
			  
			  /*It has to have less than 450.000km and be newer than 2000*/
			  if(parser.isAfter2000(parser.getTypeCar1()) == true && parser.isLessThan450000(parser.getTypeCar1()) == true) {
				 
				  //Precondition: Convetional or hybrids with this satisfyed conditions
				  
				  if(parser.getTypeCar1().compareTo("C") == 0) { //if Conventional: we write in the context
					  
					  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.getHorsePowerCar1())));
					  
					  
				  }else if(parser.getTypeCar1().compareTo("H") == 0) { //if Hybrid: we calculate the effective power and write in the context
					  
					 					  
					  context.write(new Text(parser.getCity()),new IntWritable(parser.hyEffectivePowerCar1()));
					  
					  					  
				  }				  
				  				  
			  }			  
			  
		  }
	  
		  
	  /*NO ERRORS 2 CARS PER THIS RECORD*/
		  
	  }else {
		  
		  if(parser.getTypeCar1().compareTo("E") == 0 && parser.getTypeCar2().compareTo("E") == 0) {
			  
			  /*both are electric == record declined*/
		  
			  
		  }else {
			  //means, one is electric, the other is or none
			  
			  if(parser.getTypeCar1().compareTo("E") == 0) {//if one is electric we write the other in the context
				  
				  /*It has to have less than 450.000km and be newer than 2000*/
				  if(parser.isAfter2000(parser.getTypeCar2()) == true && parser.isLessThan450000(parser.getTypeCar2()) == true) {
					  //Since no elect+hyb is conventional
					  
					  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.getHorsePowerCar2())));

				  }
				  
				  
			  }else if(parser.getTypeCar2().compareTo("E") == 0) { //if the other is electric we do the other way round
				  			  
				  
				  if(parser.isAfter2000(parser.getTypeCar1()) == true && parser.isLessThan450000(parser.getTypeCar1()) == true) {
					  //Since no hybrid+electric is conventional
					  
					  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.getHorsePowerCar1())));

				  }
				  
				  
				  
				  
			  }else {
				  
				  //or the 2 conventional or the two hybrids (no persons in the survey with Hybrid and conventional)
				  
				  if(parser.getTypeCar1().compareTo("C") == 0 && parser.getTypeCar2().compareTo("C") == 0) {
					  
					  //We need to check if both of them satisfy the age and kms
					  
					  if(parser.isAfter2000(parser.getTypeCar1()) == true) { //first has good age
						  if(parser.isLessThan450000(parser.getTypeCar1()) == true) { //first has good kms
							  	//first is ok
							  if(parser.isAfter2000(parser.getTypeCar2()) == true) { //second has good age
								  if(parser.isLessThan450000(parser.getTypeCar2()) == true) { //second has good kms
									  	//second is ok and we pass the mean to the reducer
							  
									  	/*We take both cars powers and do the mean*/
									  int pw1 = Integer.parseInt(parser.getHorsePowerCar1());
									  int pw2 = Integer.parseInt(parser.getHorsePowerCar2());
									  
									  int mean = (pw1+pw2)/2
									  
									  context.write(new Text(parser.getCity()),new IntWritable(mean));

									  
									  
									  
								  }else {//second has not good kms (declined)
									   
									  //pasa el 1
									  
									  
									  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.getHorsePowerCar1())));

									  
									  
									  
								  }
							  
							  }else { //second has not good age (declined)
								  
								  //pasa el 1
								  
								  
								  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.getHorsePowerCar1())));

								  
								  
							  }
							  
						  }else{ //first has not good kms (declined)
							  
							  //second is ok?
							  
							  if(parser.isAfter2000(parser.getTypeCar2()) == true && parser.isLessThan450000(parser.getTypeCar2()) == true) { 
								  	//yes
								  
								  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.getHorsePowerCar2())));
 
								  
								  
							  }
							  
							  
						  }

					  }else { //first not good age (declined)
						  
						//second is ok?
						  
						  if(parser.isAfter2000(parser.getTypeCar2()) == true && parser.isLessThan450000(parser.getTypeCar2()) == true) { 
							  	//yes
							  
							  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.getHorsePowerCar2())));
 
							  
							  
						  }
						  
					  }
					  
					  
				  }else if(parser.getTypeCar1().compareTo("H") == 0 && parser.getTypeCar2().compareTo("H") == 0) {
					  
					//We need to check if both of them satisfy the age and kms
					  
					  if(parser.isAfter2000(parser.getTypeCar1()) == true) { //first has good age
						  if(parser.isLessThan450000(parser.getTypeCar1()) == true) { //first has good kms
							  	//first is ok
							  if(parser.isAfter2000(parser.getTypeCar2()) == true) { //second has good age
								  if(parser.isLessThan450000(parser.getTypeCar2()) == true) { //second has good kms
									  	//second is ok and we pass the mean to the reducer
							  
									  	/*We take both cars powers and do the mean*/
									  int pw1 = Integer.parseInt(parser.hyEffectivePowerCar1());
									  int pw2 = Integer.parseInt(parser.hyEffectivePowerCar2());
									  
									  int mean = (pw1+pw2)/2
									  
									  context.write(new Text(parser.getCity()),new IntWritable(mean));

									  
									  
									  
								  }else {//second has not good kms (declined)
									   
									  //pasa el 1
									  
									  
									  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.hyEffectivePowerCar1())));

									  
									  
									  
								  }
							  
							  }else { //second has not good age (declined)
								  
								  //pasa el 1
								  
								  
								  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.hyEffectivePowerCar1())));

								  
								  
							  }
							  
						  }else{ //first has not good kms (declined)
							  
							  //second is ok?
							  
							  if(parser.isAfter2000(parser.getTypeCar2()) == true && parser.isLessThan450000(parser.getTypeCar2()) == true) { 
								  	//yes
								  
								  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.hyEffectivePowerCar2())));
 
								  
								  
							  }
							  
							  
						  }

					  }else { //first not good age (declined)
						  
						//second is ok?
						  
						  if(parser.isAfter2000(parser.getTypeCar2()) == true && parser.isLessThan450000(parser.getTypeCar2()) == true) { 
							  	//yes
							  
							  context.write(new Text(parser.getCity()),new IntWritable(Integer.parseInt(parser.hyEffectivePowerCar2())));
 
							  
							  
						  }
						  
					  } 
					  
					  
				  }
				 
			  }			  
			  
		  }			  
		  
		 
	  }
  
	
  }
}

