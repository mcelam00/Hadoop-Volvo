package inputCreator;

import java.io.File;

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
	 * Method that brings in a dialog for us to selct the path in which we want to save the input file
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
				
			}else { //abort
				
				return;
				
			}
			
		}else { //if it does not exist
			
			//CALL TO THE FILEWRITTER

		}
	
		
	}
	
		
	

}
