import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tournament {
	static String Text = null;
	static String Name = null;
	static JTextField field = new JTextField(20);
	static JTextPane pane = new JTextPane();
	static String Found;
	static int FoundLine;
	static Wrestler [] roster;
	static Wrestler [] sorted;
	static School [] schoolNames;
	static ArrayList<String> Schools = new ArrayList<String>();
	static ArrayList<Double> Weights = new ArrayList<Double>();
	static String gender;
	static Font Times = new Font ("Times New Roman", Font.PLAIN, 30);	
	public static Wrestler [] fillRoster (String fileName)
	{
		int totalCounter = 0;
		
		
		try {
			File file = new File("Names.txt");
			FileReader fr = new FileReader (file);
			LineNumberReader lnr = new LineNumberReader (fr);
            while (lnr.readLine() !=null) {         
            	totalCounter++;
            }
            lnr.close();
           
        } 
		catch (Exception ex) {
            ex.printStackTrace();
        }//END COUNTING # OF ENTRIES
		
		Wrestler [] roster = new Wrestler [totalCounter];
		
		for(int i = 0;i< roster.length; i++)
		{
			roster[i] = new Wrestler ();
		}
		int counter = 0;
		String [] data = new String [totalCounter];
		
		try {
            File file = new File("Names.txt");
             Scanner input = new Scanner(file);
             
             for(int i = 0; i< data.length ; i++) {
            	 data[i] = input.nextLine();
             }
            
			
	        input.close();
		}
		catch (Exception ex) {
	        ex.printStackTrace();
	    }
	     //END CREATING STRINGS FROM INPUT     	     
                
            for(counter =0 ; counter< roster.length ; counter++)
                {
                
                String temp = "";
                
                
                int check = 0;
			   int comma=0;
                for(int i = 0; i< data[counter].length(); i++) {
                	
                	if (data[counter].charAt(i)==','||i==data[counter].length()-1) {
                		comma++;
                		if(check == 0) { 
                			roster[counter].name = temp.toUpperCase();
                			check++;
                		}
                		else if (check ==1 ) {
                			roster [counter].school = temp.toUpperCase();
                			check++;
                		}
                		else if(check == 2){
                			roster[counter].weight = Double.parseDouble(temp);
                			check++;
                		}
                		else {
                			temp += data[counter].charAt(i);
                			String GenderUp=temp.toUpperCase();
                		if (GenderUp.contains("FEMALE"))
                			roster[counter].gender = 2;
                		else if(GenderUp.contains("MALE"))
                			roster[counter].gender = 1;
                		check = 0;
                		}
                			
                		temp = "";
                	}
                	else
                	{
                		temp += data[counter].charAt(i);
                	}
            		if(comma==4) //Set to one higher than amount of commas needed in line
            		{
            			break;
            		}
                	}
                }
		
		return roster;
	}
	public static boolean checker(boolean check,Wrestler [] roster)
	{
             for(int i=0; i<roster.length;i++)
             {
            	 if(roster[i].name.contains(Text))
            	 {
            		 Name=roster[i].name;
            		 check=true;
            		 Found=roster[i].name;
            		 FoundLine=i;
            		 break;
            	 }
             }
             if(check==false)
             {
            	 Found="NOT FOUND";           	 
             }
		return check;
	}
	public static void fillSchools(Wrestler [] array)
	{
		for(int i=0;i<array.length;i++)
		{
		if(Schools.contains(array[i].school))
		{
		}
		else
		{
			Schools.add(array[i].school);
		}
		}
	}
	public static void fillWeights(Wrestler [] array)
	{
		for(int i=0;i<array.length;i++)
		{
		if(Weights.contains(array[i].weight))
		{
		}
		else
		{
			Weights.add(array[i].weight);
		}
		}
		
		for (int i = 0; i < Weights.size()-1; i++)
		   {
		      int min = i;
		      for (int j = i+1; j < Weights.size(); j++)
		            if (Weights.get(j) < Weights.get(min)) min = j;
		      Double temp = Weights.get(i);
		      Weights.set(i, Weights.get(min));
		      Weights.set(min, temp);
		   } 
	}
	public static void main(String[] args) throws IOException {
		JFrame frame=new JFrame("Tournament");
		frame.setIconImage(new ImageIcon("Logo.png").getImage());
	    frame.setSize(700,600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//	    frame.setResizable(false);
	    JPanel panel = new JPanel (); 
	    Wrestler [] rost = fillRoster("Names.txt");
		JSpinner spinner=new JSpinner(new SpinnerNumberModel(0,0,1000,1));
		fillSchools(rost);
		fillWeights(rost);
	    pane.setEditable(false);
	    pane.setFont(Times);
	    field.setFont(Times);
	    JBox box = JBox.vbox(field);
	    JBox.setSize(box, 500, 40);
	    JBox.setSize(spinner, 50, 50);
	    spinner.setFont(Times);
	    frame.add(panel);
	    panel.add(box,BorderLayout.NORTH);
	    panel.setBackground(Color.WHITE);
	    JButton button = new JButton("Search");
	    JButton save = new JButton("Save");
	    JButton one = new JButton("1");
	    JButton two = new JButton("2");
	    JButton three = new JButton("3");
	    panel.add(button,BorderLayout.NORTH);
	    panel.add(pane,BorderLayout.CENTER);
	    JBox point=JBox.hbox(one,two,three);
	    frame.getRootPane().setDefaultButton(button);
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
Text = field.getText();
Text=Text.toUpperCase();
if(checker(false,rost)==true)
{
	if(rost[FoundLine].gender == 1) gender=("MALE");
	else if (rost[FoundLine].gender == 2) gender=("FEMALE");
	else gender=("UNKNOWN");
	String stuff=("NAME:"+rost[FoundLine].name+"\n"+gender+"\nSCHOOL:"+rost[FoundLine].school+"\nWEIGHT:"+rost[FoundLine].weight);
	pane.setText(stuff);
spinner.setValue(rost[FoundLine].points);
    panel.add(spinner,BorderLayout.SOUTH);
    panel.add(point,BorderLayout.SOUTH);
    panel.add(save,BorderLayout.SOUTH);
}
else
{
	pane.setText("NOT FOUND");
	panel.remove(spinner);
	panel.remove(save);
	panel.remove(point);
	panel.revalidate();
	panel.repaint();
}
			}
        });
        spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {	
				rost[FoundLine].points=(Integer)spinner.getValue();
			}
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	sorted=Wrestler.sorter(rost);
								try {
									Wrestler.savetoFile(sorted);
								} catch (IOException e1) {
									e1.printStackTrace();
								} 
								School [] schoolNames=new School [Schools.size()];
								for(int i=0;i<schoolNames.length;i++)
								{
								schoolNames[i]=new School (Schools.get(i),sorted);
								}
								schoolNames=School.sorter(schoolNames);
								for(int i=0;i<schoolNames.length;i++)
								{
								schoolNames[i].femaleWrestlers=Wrestler.sorter(schoolNames[i].femaleWrestlers);
								schoolNames[i].maleWrestlers=Wrestler.sorter(schoolNames[i].maleWrestlers);
								}
								try {
									School.savetoFile(schoolNames,Weights);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
            }
            });
        one.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	rost[FoundLine].points+=1;
        	spinner.setValue(rost[FoundLine].points);
        }
        });
        two.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	rost[FoundLine].points+=2;
        	spinner.setValue(rost[FoundLine].points);
        }
        });
        three.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	rost[FoundLine].points+=3;
        	spinner.setValue(rost[FoundLine].points);
        }
        });
        frame.setVisible(true);
	}
}