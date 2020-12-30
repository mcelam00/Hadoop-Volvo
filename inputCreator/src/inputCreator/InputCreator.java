package inputCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Class in charge of the creation of an input file following the specifications provided
 * @author mcelam00
 */


public class InputCreator {
	
	String filePath = "";

	public static void main(String[] args) {
		
			System.out.println("Welcome to the Input creator, now, the generation of the input file will start, this could take some minutes");

			InputCreator nuevo = new InputCreator();
			nuevo.savePath();
			System.out.println("input file succesfully generated");

			
	}
	
	/**
	 * Method that brings in a dialog for us to select the path in which we want to save the input file
	 **/
	 
	private void savePath() {
		
		final JFileChooser fc = new JFileChooser(); //dialog box creation
		
				
		int returnVal = fc.showSaveDialog(null); //show it
		
		
		if(returnVal == JFileChooser.APPROVE_OPTION){  //if we click on accept

			File file = fc.getSelectedFile();
			
			//we save the path plus the file name we wish to include
			filePath = file.getAbsolutePath(); 
			
			//append of the .txt extension just in case
			if((filePath.contains(".txt")) == false) {
			
				filePath = filePath +".txt";
			
			}
			
		}else { //If we interrupt the saving proccess
			
			return; 
			
		}
		
		
		File f = new File(filePath); //Verification of an existing equally-named file
		
		if(f.exists()) { //if it exists
		
			int respuestaBoton = JOptionPane.showConfirmDialog(null, "Warning. A file with the same name already exists.\n Do you want to replace it?"); //showconfirm dialog es el tipo de ventana que sale
		
			
			if(respuestaBoton == 0) { //if the replace is wished
				
				//CALL TO THE FILEWRITTER
				this.generateFile();
				
			}else { //abort
				
				return;
				
			}
			
		}else { //if it does not exist
			
			//CALL TO THE FILEWRITTER
			this.generateFile();

		}
	
		
	}
	
	/**
	 * Method that writes the file itself, by using the previous stored path
	 */
		
	private void generateFile() {
		
		FileWriter file = null; //Declaration of the FileWriter
	
	
		try {
			
			PrintWriter pw;
			
			file = new FileWriter(filePath); //new FileWriter to which we give the file in which we want to write. (like the paper)
			
			pw = new PrintWriter(file); //new PrintWritter in order to write in the file (like a pencil)
			
			//pw.println(); // pw.print(); function used to print one line in the indicated pathfile
			
			/*BEGGINING OF THE PRINTING*/
			
			int i = 0;
			
			while (i < 50) {
				conventionaletElectricRecord(pw); 
				pw.println();
				hybridRecord(pw);
					if(i < 49) {
						pw.println();
					}
						
					i++;
			}
			
			
		}catch(Exception e) {
			
			/*Included to avoid exception traces*/
			
		}finally { /*Ensuring the correct close of the file*/
			
			try {
				
				if(null != file) {
					
					file.close();
					
				}
				
			}catch(Exception e2) {
				
				/*Included to avoid exception traces*/
			
			}
		}
			
		JOptionPane.showMessageDialog(null, "The Input file has been correctly saved", "Input Creator v1",JOptionPane.INFORMATION_MESSAGE);
		
		
	
	
	}

	/**
	 * Method that prints a record for electric/conventional car
	 * @param pw PrintWritter to write in the file
	 */

	private void conventionaletElectricRecord(PrintWriter pw) {

		String type = "";
		
		int num = (int) Math.floor(Math.random()*2);  //random number between 0 and 1
		
		switch(num) { //depending on the random number one of the options will be chosen.
		
			case 0:
				type = "C"; //conventional car			
				break;
			case 1:
				type = "E"; //electric car
				break;
						
		}
		
		//number of cars that this family unit (represented by this record) has
		
		int cars = (int) Math.floor(Math.random()*2);  //1 or 2 cars

		pw.print(randomCity()+"/"+type+"/"+randomDate()+"/"+randomHps(cars)+"/"+randomKMs(cars));
				
	}

	/**
	 * Method that generates a random number of kms for the cars.
	 * If 2 cars are to be written there would be 2 kms values separated by an & symbol.
	 */
	
	private String randomKMs(int cars) {
		
		String km = "";
		
		if(cars == 1) {
			int km1 = (int) Math.floor(Math.random()*500000);  //random number between 0 and 999999
			int km2 = (int) Math.floor(Math.random()*500000);  //random number between 0 and 999999
			
			km = String.valueOf(km1)+"&"+String.valueOf(km2);
		
		}else {
		
			int km1 = (int) Math.floor(Math.random()*500000);  //random number between 60 and 300
			km = String.valueOf(km1);
		}
		
		return km;
	}

	/**
	 * Method that generates a random number of cars (1 or 2) and a value of HP for each of them.
	 * If 2 cars are to be written they would be separated by an @ symbol to simplify the map-reduce technique (1 pair key+value).
	 */
	
	private String randomHps(int cars) {
		
		String hps = "";
		
		if(cars == 1) {
			
			int h1 = (int) ((Math.random() * (300 - 60)) + 60);  //random number between 60 and 300
			int h2 = (int) ((Math.random() * (300 - 60)) + 60);  //random number between 60 and 300
			
			
			hps = String.valueOf(h1)+"@"+String.valueOf(h2);
			
		}else {
			
			int num = (int) ((Math.random() * (300 - 60)) + 60);  //random number between 60 and 300

			hps = String.valueOf(num);
			
		}
			
		return hps;
	}

	/**
	 * Method that generates a random date between January 1990 and January 2021
	 */
	
	private String randomDate() {

		int year;
		int month;
		String monthS = "";
		String date = "";
		
		year = (int) ((Math.random() * (2021 - 1990)) + 1990);  //random number between 1990 and 2021 both inclusive 
		
		month = (int) Math.floor(Math.random()*12);  //random number between 0 and 11
		
		switch(month) { //depending on the random number one of the options will be chosen.
		
			case 0:
				monthS = "Jan";			
				break;
			case 1:
				monthS = "Feb";
				break;
			case 2:
				monthS = "Mar";
				break;
			case 3:
				monthS = "Aprl";
				break;
			case 4:
				monthS = "May";
				break;
			case 5:
				monthS = "Jun";
				break;
			case 6:
				monthS = "Jul";
				break;
			case 7:
				monthS = "Aug";
				break;
			case 8:
				monthS = "Sep";
				break;
			case 9:
				monthS = "Oct";
				break;
			case 10:
				monthS = "Nov";
				break;
			case 11:
				monthS = "Dec";
				break;
		
		}
		
		date = monthS+String.valueOf(year);
		
		
		return date;
	}

	/**
	 * Method that generates a random city between Uppsala, Stockholm and Gothenburg
	 * 
	 */
	
	private String randomCity() {

		String city = "";
		
		int num = (int) Math.floor(Math.random()*3);  //random number between 0 and 2
		
		switch(num) { //depending on the random number one of the options will be chosen.
		
			case 0:
				city = "Uppsala";			
				break;
			case 1:
				city = "Stockholm";
				break;
			case 2:
				city = "Gothenburg";
				break;
				
		}
				
		return city;
	}

	/**
	 * Method that prints a record for hybrid car
	 * @param pw PrintWritter to write in the file
	 */
	
	private void hybridRecord(PrintWriter pw) {

		String type = "H";
		
		
		//number of cars that this family unit (represented by this record) has
		
		int cars = (int) Math.floor(Math.random()*2); 	
		
		pw.print(randomCity()+"/"+type+"/"+randomDate()+"/"+randomHybHps(cars)+"/"+randomKMs(cars));

		
	}
	
	/**
	 * Method that generates a random number of cars (1 or 2) and a value of HP for each of them taking into account that hybrid cars have 2 engines (i.e. 2 hps).
	 * If 2 cars are to be written they would be separated by an @ symbol to simplify the map-reduce technique (1 pair key+value).
	 * As they are hyprid and have 2 hps the 2 values would be concatenated using a + sign.
	 * @param cars 
	 */

	private String randomHybHps(int cars) {
		
		String hps = "";
		
		if(cars == 1) {
			
			int h1 = (int) ((Math.random() * (300 - 60)) + 60);  //random number between 60 and 300
			int h1_1 = (int) ((Math.random() * (100 - 6)) + 6);  //random number between 1 and 100
			int h2 = (int) ((Math.random() * (300 - 60)) + 60);  //random number between 60 and 300
			int h2_1 = (int) ((Math.random() * (100 - 6)) + 6);  //random number between 1 and 100
			
			hps = String.valueOf(h1)+"+"+String.valueOf(h1_1)+"@"+String.valueOf(h2)+"+"+String.valueOf(h2_1);
			
		}else {
			
			int num = (int) ((Math.random() * (300 - 60)) + 60);  //random number between 60 and 300
			int num_1 = (int) ((Math.random() * (100 - 6)) + 6);  //random number between 1 and 100

			hps = String.valueOf(num)+"+"+String.valueOf(num_1);
			
		}
			
		return hps;
	}
	

}
