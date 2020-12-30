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
			
			conventionaletElectricRecord(pw); 
			hybridRecord(pw);
			
			
			
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
		
		pw.print(randomCity()+"/"+type+"/"+randomDate()+"/"+randomHps()+"/"+randomKMs());
		
		
		
	}

	/**
	 * Method that generates a random number of kms for the cars.
	 * If 2 cars are to be written there would be 2 kms values separated by an & symbol.
	 */
	
	private String randomKMs() {

		
		
		
		
		
		return null;
	}

	/**
	 * Method that generates a random number of cars (1 or 2) and a value of HP for each of them.
	 * If 2 cars are to be written they would be separated by an @ symbol to simplify the map-reduce technique (1 pair key+value).
	 */
	
	private String randomHps() {
		
		String hps = "";
		
		
		
		
		return hps;
	}

	/**
	 * Method that generates a random date between 01 January 1970 and 01 January 2021
	 */
	
	private String randomDate() {

		int year;
		int month;
		int day;
		String date = "";
		
		
		
		
		
		
		return date;
	}

	/**
	 * Method that generates a random city between Uppsala, Stockholm and Gothenburg
	 * 
	 */
	
	private String randomCity() {

		String city = "";
		
		
		
		
		
		
		
		return city;
	}

	/**
	 * Method that prints a record for hybrid car
	 * @param pw PrintWritter to write in the file
	 */
	
	private void hybridRecord(PrintWriter pw) {

		String type = "H";
		
		pw.print(randomCity()+"/"+type+"/"+randomDate()+"/"+randomHybHps()+"/"+randomKMs());
		
				
		
		
	}
	
	/**
	 * Method that generates a random number of cars (1 or 2) and a value of HP for each of them taking into account that hybrid cars have 2 engines (i.e. 2 hps).
	 * If 2 cars are to be written they would be separated by an @ symbol to simplify the map-reduce technique (1 pair key+value).
	 * As they are hyprid and have 2 hps the 2 values would be concatenated using a + sign.
	 */

	private String randomHybHps() {
		
		String hps = "";
		
		
		
		
		return hps;
	}
	

}
