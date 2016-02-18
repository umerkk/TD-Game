import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValidatorTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	int[][] mapArray;
	JLabel lblStatus = new JLabel("Status");

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValidatorTestFrame frame = new ValidatorTestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Deletes a path element from the map.
	 * 
	 * @param maparr the map array from which a path element has to be deleted.
	 * @param pathnumbr the element to be deleted.
	 */
	private void deletePath(int[][] maparr, int pathnumbr)
	{
		for(int i = 0; i < maparr.length; i++)
		{
			for(int j = 0; j < maparr[0].length; j++)
			{
				if(pathnumbr < maparr[i][j])
				{
					maparr[i][j]--;
				}
				else if(pathnumbr == maparr[i][j])
				{
					maparr[i][j] = -1;
				}
			}
		}
	}
	
	private void createMap()
	{
		mapArray = new int[6][7];
		for(int k=0;k<=mapArray.length-1;k++)
		{
			for(int i=0;i<mapArray[k].length-1;i++)
			{
				mapArray[k][i] =-1;
			}
			
		}
		lblStatus.setText("Array Created");
	}

	private void populateMap()
	{
		mapArray[1][2] = 0; //Entry
		mapArray[1][3] = 1;
		mapArray[1][4] = 2;
		mapArray[1][5] = 3;
		mapArray[2][5] = 4;
		mapArray[3][5] = 5;
		mapArray[3][4] = 6;
		mapArray[3][3] = 7;
		mapArray[3][2] = 8;
		mapArray[3][1] = 9;
		mapArray[4][1] = 10;
		mapArray[5][1] = 11; //Exit Point
		
		lblStatus.setText("Map Populated with Dummy Data");
	}
	
	private void validateMap()
	{
		//Map Validate Method
		int startPointOneD=-1;
		int startPointTwoD=-1;
		int mapDataPoint = 0; // 0 for start and the n+1
		int MapExtiPoint = 99;
		int mapItemsCount = 0; //Minimum 3 to be a valid map.
		
		boolean validationLoop = true;
		boolean isMapValid = false;
		
		String validationErrorMessage = "";
		
		int[][] mapArrayCopy = mapArray;
		for(int k=0;k<=mapArrayCopy.length-1;k++)
		{
			for(int i=0;i<mapArrayCopy[k].length-1;i++)
			{
				//Do the magic
				// what if there are 2 start points?
				if(mapArrayCopy[k][i] == 0)
				{
					startPointOneD = k;
					startPointTwoD = i;
					mapItemsCount++;
				}
			}
			
		}
		lblStatus.setText(String.valueOf(startPointOneD)+" - "+String.valueOf(startPointTwoD)); //Just for debugging
		
		
		while(validationLoop) //Loop to follow the map path defined in the Map array for validation.		
		{		
			try {
				if(mapArrayCopy[startPointOneD+1][startPointTwoD]== mapDataPoint+1)
				{
					mapDataPoint++;
					startPointOneD++;

				} else
					if(mapArrayCopy[startPointOneD-1][startPointTwoD]== mapDataPoint+1)
					{
						mapDataPoint++;
						startPointOneD--;

					} else
						if(mapArrayCopy[startPointOneD][startPointTwoD+1]== mapDataPoint+1)
						{
							mapDataPoint++;
							startPointTwoD++;
						} else
							if(mapArrayCopy[startPointOneD][startPointTwoD-1]== mapDataPoint+1)
							{
								mapDataPoint++;
								startPointTwoD--;
							} 
							/*else
							{
								isMapValid=false;
								break;
							}*/
				if(mapDataPoint == 11)
				{
					isMapValid  =true;
					validationLoop = false;
					lblStatus.setText("Valid Map");
				}
				continue;


			} catch (ArrayIndexOutOfBoundsException ea)
			{
				isMapValid=false;
				validationErrorMessage = "Index Out of Bond exception.";
				lblStatus.setText(validationErrorMessage);
				continue;
			}
		}

	}

	/**
	 * Create the frame.
	 */
	public ValidatorTestFrame() {
		lblStatus.setBounds(372, 321, 359, 89);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateMapArray = new JButton("Create Map Array");
		btnCreateMapArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMap();
			}
		});
		btnCreateMapArray.setBounds(431, 115, 199, 29);
		contentPane.add(btnCreateMapArray);
		
		JButton btnPopulateWithMap = new JButton("Populate with Map Data");
		btnPopulateWithMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateMap();
			}
		});
		btnPopulateWithMap.setBounds(417, 178, 248, 29);
		contentPane.add(btnPopulateWithMap);
		
		JButton btnValidateMap = new JButton("Validate Map");
		btnValidateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateMap();
			}
		});
		
		
		btnValidateMap.setBounds(451, 240, 160, 29);
		contentPane.add(btnValidateMap);
		
	
		contentPane.add(lblStatus);
	}
}
