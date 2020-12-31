//CarDataRecordParser
import org.apache.hadoop.io.Text;

public class CarDataRecordParser {
 
	 private String city;	 //would be the key
	 private String type[];	//since we could have 2 cars I represent is as an array, if only one is full the other would not even exist
	 private String buildDate[]; 
	 private String horsePower[];
	 private String kilometres[];
	 private int numOfCars; //just to keep info of the number of cars using the size of the arrays
	
  public void parse(String record) {
    
	  /*HERE WE HAVE 1 LINE OF THE FILE IN STRING FORMAT*/
	  
	  //We are going to split the fields by the "/" so that we can separate each of them
	  
	  String [] holeRecord;
	  
	  holeRecord = record.split("/"); //in this way we achieve, in each position of the array one record 0 would be the city; 1 the type; 2 the dates...
	  
	  this.city = holeRecord[0];
	  
		  if(areThere2Cars(holeRecord[3]) == true) {	//If there are two cars, both array positions would be created
			  
			  	this.numOfCars = 2; //set the atribute first of all for later methods
			 
			  /**TYPES**/
				 this.type = holeRecord[1].split("+"); //holeRecord[1] would be the String representing the types concatenated by a +; so, we split them and save them likewise we did with the "/"
			  
			  /**BUILD DATES**/
				 this.buildDate = holeRecord[2].split("@");
			  
			  /**HORSE POWERS**/
				 this.horsePower = holeRecord[3].split("@");
			  
			  /**KILOMETRES**/
				 this.kilometres	= holeRecord[4].split("&");
			  
		  }else { //whereas if there is only one car, just an array of 1 would be created
			  
			  	this.numOfCars = 1; //set the atribute first of all for later methods

			  
			  /*ARRAY CREATION*/
			  	 this.type = new String[1];
				 this.buildDate = new String[1]; 
				 this.horsePower = new String[1];
				 this.kilometres = new String[1];
			  
			  /**TYPE**/
				 this.type[0] = holeRecord[1];
			  
			  /**BUILD DATE**/
				 this.buildDate[0] = holeRecord[2];
			  
			  /**HORSE POWERS**/
				 this.horsePower[0] = holeRecord[3];
			  
			  /**KILOMETRES**/
				 this.kilometres[0] = holeRecord[4];
			 			  
		  }
	  
	  
	   
  }
  
  
  			/*MISCELLANY*/
  
  /**
   * Parse from Text hadooop format to String format
   * @param record
   */
  
  public void parse(Text record) {
    parse(record.toString());
  }

  /**
   * It looks for an @ all over the record of the horsepowers and if it finds it, that would mean 2 cars
   * @return true if there are 2 cars, false in case just one
   */
  
  private boolean areThere2Cars(String horsepowers) {
	  
	  boolean flag = false;
	  
	  for (int i = 0; i < this.horsepowers.length(); i++){ 
		  
		  char c = this.horsepowers.charAt(i); //I take char per char and look for the @ 
		  
		  if(c == '@') {
			  flag = true;
			  break;
		  }
					  
	  }
	  	  
	  return flag;
	  	  
  }
  
  
  
  			/*VALIDATIONS AND VERIFICATIONS*/
  
  /*RECORD FIELDS VALIDATION*/
	    
  
  public boolean isValidRecord() {
	
	  boolean flag = true; //by default is valid unless --- is found in any field
	  
	  if(this.numOfCars == 1) {
		  
		  if(this.type[0].charAt(0) == '-' || this.buildDate[0].charAt(0) == '-' || this.horsePower[0].charAt(0) == '-' || this.kilometres[0].charAt(0) == '-') { //invalid field
			  flag = false;
		  }
		  
	  }else { //2cars, so we have 2 Strings in each array, so we look the first character of each of both strings to see if it si - meaning that the second and third chars would be -- also constituting the invalid field
		  
		  if(this.type[0].charAt(0) == '-' || this.buildDate[0].charAt(0) == '-' || this.horsePower[0].charAt(0) == '-' || this.kilometres[0].charAt(0) == '-') { //invalid field
			  flag = false;
		  }else if (this.type[1].charAt(0) == '-' || this.buildDate[1].charAt(0) == '-' || this.horsePower[1].charAt(0) == '-' || this.kilometres[1].charAt(0) == '-') {
			  flag = false;
		  }
	  
	  }
	  
	return flag;	  
	  
  }
  
  
  /*GETTER NUMBER OF CARS*/
  
  
  public int numOfCars() {
	  return this.numOfCars;
  }

  
  /*GETTER TYPE OF CAR 1*/
  
  
  public String getTypeCar1() {
	  return this.type[0];
  }
  
  
  /*GETTER TYPE OF CAR 2*/
  
  
  public String getTypeCar2() {
	  return this.type[1];
  }
  
 
  /*CAR AGE VALIDATION*/

  
  public boolean isAfter2000(String type) {
	  //Depending on the car to validate one validation or the other
  
	  boolean flag = true;
	  
	  if(type.compareTo(this.type[0]) == 0) { //is vehicle 1
		  
		  if(this.buildDate[0].charAt(3) != '2') { //Character in pos 3 is the first of the year if its not 2 not valid
		  
			  flag = false;
			  
		  }
		  
		  
	  }else { //is vehicle 2
		  
		  
		  if(this.buildDate[1].charAt(3) != '2') { //Character in pos 3 is the first of the year if its not 2 not valid
			  
			  flag = false;
			  
		  }
		  
		  
		  
	  }
  
  
	  return flag;
  
  
  }
  
  
  /*CAR KMS VALIDATION*/

  
  public boolean isLessThan450000(String type) {
	  
	 boolean flag = true;

	  if(type.compareTo(this.type[0]) == 0) { //is vehicle 1
		  
		  if(Integer.parseInt(this.kilometres[0]) > 450000) { //more than 450 000 not valid record
			  flag = false;
  
		  }
		  
		  
	  }else { //is vehicle 2
		  
			  
			if(Integer.parseInt(this.kilometres[1]) > 450000) { //more than 450 000 not valid record
				  flag = false;
		  
			}
		  
	  }
	  
  
	  return flag;
  
  
  }
  
  
  /*COMPUTES THE EFFECTIVE POWER OF THE HYBRID ENGINE Car 1*/

  
  public int hyEffectivePowerCar1() {
	  
	  String power[] = this.horsePower[0].split("+");
	  	
	  int pw1 = Integer.parseInt(power[0]);
	  int pw2 = Integer.parseInt(power[1]);
	  
	  return (pw1+pw2);	  
  }
  
  
  /*COMPUTES THE EFFECTIVE POWER OF THE HYBRID ENGINE Car 2*/
  
public int hyEffectivePowerCar2() {
	  
	  String power[] = this.horsePower[1].split("+");
	  	
	  int pw1 = Integer.parseInt(power[0]);
	  int pw2 = Integer.parseInt(power[1]);
	  
	  return (pw1+pw2);	  
  }
  
  
  
  			/*GETTING THE KEY AND THE VALUE TO THE MAPPER*/
  
  public String getCity() { //key
    return this.city;
  }

  public String getHorsePowerCar1() { //value
    return this.horsePower[0];
  }
  public String getHorsePowerCar2() { //value'
	    return this.horsePower[1];
	  }
  
}

