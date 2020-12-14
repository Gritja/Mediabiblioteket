package mediabiblioteket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javafx.application.Application;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** 
 * Huvudfönstret i Bibliotekssystemet som användaren interagerar med för att bl.a. söka efter
 * olika mediatyper och låna en specifik media 
 */
public class GUI extends Application 
{
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 7991560170685136013L;

	//javafx login/ssn-stuff:
	Parent root;
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUserName;
	@FXML
	private Button loginBtn;
	//biblioteket fxml
	@FXML
	private TextField searchMedia;
	@FXML
	private Button searchBtn;
	@FXML
	private Button searchBorrowed;
	
	LibraryController theController;
	
	/**
	 * Konstruktorn som skapar hela layouten för inloggningsrutan samt 
	 * huvudfönstret med de olika grafiska komponenterna som användaren kan interagera med via Controller klassen. 
	 */
	public GUI()
	{
		
		theController = new LibraryController(this);
//		String userName = txtUserName.getText();
		System.out.println("GOGOGO");
	}
	
	/**
	 * Kollar vid inloggningen ifall om en person finns registrerad i Bibliotekssystemet.
	 * @param userName. Personnummer för låntagaren
	 * @return. True om personnummer existerar, annars False.
	 */
	public void login(String userName)
	{
		while(true)
		{
			if(theController.checkUserInput(userName))
			{
				if(!theController.checkIfBorrowerExist(userName))
				{
					Alert alert = new Alert(AlertType.WARNING, "Incorrect SSN!", ButtonType.OK);
					alert.showAndWait();
					System.out.println("NEIN");
					//JOptionPane.showMessageDialog(null, "Incorrect SSN");
					//userName = JOptionPane.showInputDialog("Enter SSN(Social Security Number: YYMMDD-XXXX)");
				}
				else
				{
					System.out.println("HAI");
					
//					 String sceneFile = "/mediabiblioteket/MBL.fxml";
//					    Parent root = null;
//					    URL    url  = null;
//					    try
//					    {
//					        url  = getClass().getResource( sceneFile );
//					        root = FXMLLoader.load( url );
//					        System.out.println( "  fxmlResource = " + sceneFile );
//					    }
//					    catch ( Exception ex )
//					    {
//					        System.out.println( "Exception on FXMLLoader.load()" );
//					        System.out.println( "  * url: " + url );
//					        System.out.println( "  * " + ex );
//					        System.out.println( "    ----------------------------------------\n" );
//					    }
					
//					FXMLLoader loader = new FXMLLoader(getClass().getResource("/mediabiblioteket/MBL.fxml"));
//					loader.setController(new MainController(path));
					//Pane mainPane = loader.load();
					try {
						root = FXMLLoader.load(getClass().getResource("/mediabiblioteket/MBL.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			        Stage primaryStage = new Stage();
			        
					VBox vbox = new VBox(root);
				    Scene scene = new Scene(vbox);
			        primaryStage.setScene(scene);
			        primaryStage.show();
					break;
				}
			
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Incorrect characters");
				userName = JOptionPane.showInputDialog("Enter SSN(Social Security Number: YYMMDD-XXXX)");
			}
			
		}
		
		
			
		
	}
	/**
	 * Rensar Textarean från all resultat
	 */
	public void clearTheTextArea()
	{
		//theTextAreaModel.clear();
	}
	
	/**
	 * Sätter text till Textarean
	 * @param addText. Texten som ska till i Textarean
	 */
	public void setTheTextArea(String addText)
	{
		//theTextAreaModel.addElement(addText);
	}
	/**
	 * Lyssnar på Event ifrån användaren.Här kan låntagaren välja att 
	 * 1.Söka bland media
	 * 2.Söka djupgående information om ett visst media
	 * 3.Låna ett visst media
	 * 4.Returnera ett lånat media
	 **/
	public void actionPerformed(ActionEvent e)
	{
		loginBtn.setOnAction((ActionEvent event) -> {
			login(txtUserName.getText());
		});
		
//		searchBtn.setOnAction((ActionEvent j) -> {
//			login(txtUserName.getText());
//		});
		
	}	
//		if(searchButton==e.getSource())
//		{
//			String theInput = theSearchField.getText();
//			if(theController.checkUserInput(theInput))
//			{
//				borrowButton.setText("Borrow");
//				clearTheTextArea();
//				if(radioID.isSelected())
//				{
//					if(theController.checkInputOnlyDigits(theInput))
//					{
//						Media temp = theController.getMedia(theInput);
//						theController.mediaSearchResults.add(temp);
//						if(temp!=null)
//							setTheTextArea(temp.toString());
//					}
//				}
//				else if(radioTitle.isSelected())
//				{
//					theController.searchMediaTitleByString(theInput);
//				}
//				else if(radioAll.isSelected())
//				{
//					theController.searchMediaAllByString(theInput);
//				}
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(null, "The textbox contains incorrect characters");
//			}
//			
//		}
//		else if(infoButton==e.getSource())
//		{
//			String selectedText = (String) theTextAreaList.getSelectedValue();
//			System.out.println(selectedText);
//		if(selectedText!=null)
//			theController.showSelectedMediaInfo(selectedText);
//		}
//		else if(borrowButton==e.getSource())
//		{
//				if(borrowButton.getText().equals("Borrow"))
//				{
//				String selectedText = (String) theTextAreaList.getSelectedValue();
//				
//				Media selectedMedia = theController.getMediaFromSearchResult(selectedText);
//				
//				if(selectedText!=null && selectedMedia!=null)
//				{
//					if(selectedMedia.isBorrowed())
//					{
//						JOptionPane.showMessageDialog(null, "Cannot borrow, already borrowed");
//					}
//					else
//					{
//						theController.borrowMedia(selectedMedia);
//						theTextAreaModel.set(theTextAreaList.getSelectedIndex(), selectedMedia.toString());
//					}
//				}
//				else
//				{
//					System.out.println("Its null");
//				}
//			}
//			else
//			{
//				String selectedText = (String) theTextAreaList.getSelectedValue();
//				Media selectedMedia = theController.getMediaFromSearchResult(selectedText);
//				
//				if(selectedText!=null && selectedMedia!=null)
//				{
//					if(selectedMedia.isBorrowed()==false)
//					{
//						JOptionPane.showMessageDialog(null, "Cannot returm, already returned");
//					}
//					else
//					{
//						theController.returnMedia(selectedMedia);
//						theTextAreaModel.set(theTextAreaList.getSelectedIndex(), selectedMedia.toString());
//					}
//				}
//				
//			}
//			
//			
//		}
//		else if(searchBorrowedButton==e.getSource())
//		{
//			clearTheTextArea();
//			theController.searchBorrowed();
//			borrowButton.setText("Return");
//		}
//		
//	}
	/**
	 * Programstarten som öppnar applikationen
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		root = FXMLLoader.load(getClass().getResource("/mediabiblioteket/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
		new GUI();
	}


}
