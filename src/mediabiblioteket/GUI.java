package mediabiblioteket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/** 
 * Huvudfönstret i Bibliotekssystemet som användaren interagerar med för att bl.a. söka efter
 * olika mediatyper och låna en specifik media 
 */
public class GUI extends JFrame implements ActionListener
{
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 7991560170685136013L;

	JPanel theMainPanel;

	JPanel theNorthPanel;
	JPanel theNorthButtonPanel;

	JPanel theCenterPanel;
	JPanel theSouthPanel;
	JButton borrowButton;
	JButton infoButton;

	JPanel theWestPanel;
	// JButton theWestLabel;

	JLabel theLeftColumn;
	JLabel theRightColumn;

	JTextField theSearchField;
	JLabel mainMenu;
	JButton searchButton;
	JButton searchBorrowedButton;

	JTextArea theTextArea;
	DefaultListModel theTextAreaModel = new DefaultListModel();
	JList theTextAreaList;
	JScrollPane theScroller;
	
	
	// Radio buttons
	private JPanel theRadioButtonsLabel = new JPanel();
	
	private JRadioButton radioAll = new JRadioButton("All");
	private JRadioButton radioTitle = new JRadioButton("Title");
	private JRadioButton radioID = new JRadioButton("ID");
	
	private ButtonGroup radioButtonGroup = new ButtonGroup();
	
	
	LibraryController theController;
	
	/**
	 * Konstruktorn som skapar hela layouten för inloggningsrutan samt 
	 * huvudfönstret med de olika grafiska komponenterna som användaren kan interagera med via Controller klassen. 
	 */
	public GUI()
	{
		theController = new LibraryController(this);
		
		String userName = JOptionPane.showInputDialog("Enter SSN(Social Security Number: YYMMDD-XXXX)");
		login(userName);
		
		GridLayout thelayout = new GridLayout(1, 3);
		thelayout.setHgap(100);

		setLayout(thelayout);

		// The Main Panel
		theMainPanel = new JPanel(new BorderLayout());
		theMainPanel.setPreferredSize(new Dimension(500, 900));
		// The North Panel
		theNorthPanel = new JPanel(new BorderLayout());
		theNorthPanel.setOpaque(false);
		theNorthButtonPanel = new JPanel();

		// The South Panel
		theSouthPanel = new JPanel();
		borrowButton = new JButton("Borrow");
		borrowButton.addActionListener(this);
		infoButton = new JButton("Info");
		infoButton.addActionListener(this);
		theSouthPanel.add(borrowButton);
		theSouthPanel.add(infoButton);

		// The Left Panel
		theLeftColumn = new JLabel();
		theLeftColumn.setOpaque(true);
		theLeftColumn.setBackground(Color.DARK_GRAY);
		// theLeftColumn.setPreferredSize(new Dimension(100, 600));

		// The Right Panel
		theRightColumn = new JLabel();
		theRightColumn.setOpaque(true);
		theRightColumn.setBackground(Color.DARK_GRAY);
		
		
		theTextArea = new JTextArea();
		theTextArea.requestFocus();
		
		theTextAreaList = new JList();
		theTextArea.add(theTextAreaList);
		theScroller = new JScrollPane(theTextAreaList);
		
		theTextAreaList.setModel(theTextAreaModel);
		

		
		// The radio panel
		theRadioButtonsLabel = new JPanel();
		theRadioButtonsLabel.setLayout(new GridLayout(1,3));
		
		radioAll = new JRadioButton("All");
		radioAll.setSelected(true);
		radioTitle = new JRadioButton("Title");
		radioID = new JRadioButton("ID");
		
		radioButtonGroup = new ButtonGroup();
		
		radioButtonGroup.add(radioAll);
		radioButtonGroup.add(radioTitle);
		radioButtonGroup.add(radioID);
		
		theRadioButtonsLabel.add(radioAll);
		theRadioButtonsLabel.add(radioTitle);
		theRadioButtonsLabel.add(radioID);
		

		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		
		searchBorrowedButton = new JButton("Borrowed");
		searchBorrowedButton.addActionListener(this);
		
		theNorthButtonPanel.add(searchButton);
		theNorthButtonPanel.add(searchBorrowedButton);

		theSearchField = new JTextField();
		mainMenu = new JLabel("<html><h1>Welcome to the Media Library!");

		theSearchField.setPreferredSize(new Dimension(500, 30));

		theNorthPanel.add(mainMenu, BorderLayout.NORTH);
		theNorthPanel.add(theSearchField, BorderLayout.CENTER);
		theNorthPanel.add(theNorthButtonPanel, BorderLayout.EAST);
		theNorthPanel.add(theRadioButtonsLabel, BorderLayout.SOUTH);

		theMainPanel.add(theNorthPanel, BorderLayout.NORTH);
		theMainPanel.add(theScroller, BorderLayout.CENTER);
		
		theMainPanel.add(theSouthPanel, BorderLayout.SOUTH);

		add(theLeftColumn);
		add(theMainPanel);
		add(theRightColumn);

		setMinimumSize(new Dimension(900, 400));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		pack();
		setVisible(true);
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
					JOptionPane.showMessageDialog(null, "Incorrect SSN");
					userName = JOptionPane.showInputDialog("Enter SSN(Social Security Number: YYMMDD-XXXX)");
				}
				else
				{
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
		theTextAreaModel.clear();
	}
	
	/**
	 * Sätter text till Textarean
	 * @param addText. Texten som ska till i Textarean
	 */
	public void setTheTextArea(String addText)
	{
		theTextAreaModel.addElement(addText);
	}
	/**
	 * Lyssnar på Event ifrån användaren.Här kan låntagaren välja att 
	 * 1.Söka bland media
	 * 2.Söka djupgående information om ett visst media
	 * 3.Låna ett visst media
	 * 4.Returnera ett lånat media
	 **/
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(searchButton==e.getSource())
		{
			String theInput = theSearchField.getText();
			if(theController.checkUserInput(theInput))
			{
				borrowButton.setText("Borrow");
				clearTheTextArea();
				if(radioID.isSelected())
				{
					if(theController.checkInputOnlyDigits(theInput))
					{
						Media temp = theController.getMedia(theInput);
						theController.mediaSearchResults.add(temp);
						if(temp!=null)
							setTheTextArea(temp.toString());
					}
				}
				else if(radioTitle.isSelected())
				{
					theController.searchMediaTitleByString(theInput);
				}
				else if(radioAll.isSelected())
				{
					theController.searchMediaAllByString(theInput);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The textbox contains incorrect characters");
			}
			
		}
		else if(infoButton==e.getSource())
		{
			String selectedText = (String) theTextAreaList.getSelectedValue();
			System.out.println(selectedText);
		if(selectedText!=null)
			theController.showSelectedMediaInfo(selectedText);
		}
		else if(borrowButton==e.getSource())
		{
				if(borrowButton.getText().equals("Borrow"))
				{
				String selectedText = (String) theTextAreaList.getSelectedValue();
				
				Media selectedMedia = theController.getMediaFromSearchResult(selectedText);
				
				if(selectedText!=null && selectedMedia!=null)
				{
					if(selectedMedia.isBorrowed())
					{
						JOptionPane.showMessageDialog(null, "Cannot borrow, already borrowed");
					}
					else
					{
						theController.borrowMedia(selectedMedia);
						theTextAreaModel.set(theTextAreaList.getSelectedIndex(), selectedMedia.toString());
					}
				}
				else
				{
					System.out.println("Its null");
				}
			}
			else
			{
				String selectedText = (String) theTextAreaList.getSelectedValue();
				Media selectedMedia = theController.getMediaFromSearchResult(selectedText);
				
				if(selectedText!=null && selectedMedia!=null)
				{
					if(selectedMedia.isBorrowed()==false)
					{
						JOptionPane.showMessageDialog(null, "Cannot returm, already returned");
					}
					else
					{
						theController.returnMedia(selectedMedia);
						theTextAreaModel.set(theTextAreaList.getSelectedIndex(), selectedMedia.toString());
					}
				}
				
			}
			
			
		}
		else if(searchBorrowedButton==e.getSource())
		{
			clearTheTextArea();
			theController.searchBorrowed();
			borrowButton.setText("Return");
		}
		
	}
	/**
	 * Programstarten som öppnar applikationen
	 */
	public static void main(String[] arguments)
	{
		new GUI();
	}



}
