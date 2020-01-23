import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Wrestler {
 String name;
 String school;
 double weight;
 int gender;
 int points;

// CONSTRUCTOR METHODS++++++++++++++++++++++++++++++++
public Wrestler (String n, String s, int w,int g,int p)
{
name=n;
school=s;
weight=w;
gender=g;
points=p;
}

public Wrestler ()
{
	this.name = "##NO DATA##";
    this.school = "##NO DATA##";
    this.weight = 0;
    this.gender = 0;
    this.points = 0;
}

// INSTANCE METHODS ++++++++++++++++++++++++++++++++
public static void savetoFile (Wrestler [] array) throws IOException
{
    FileWriter fileWriter = new FileWriter("Sorted.txt");
    BufferedWriter outStream= new BufferedWriter(fileWriter);
    PrintWriter printWriter = new PrintWriter(outStream);
    for(int i=array.length-1;i>0;i--)
	{
	printWriter.println("-------------------");
	printWriter.print("NAME:\t"); 
	printWriter.println(array[i].name);
	
	if(array[i].gender == 1) printWriter.println("MALE");
	else if (array[i].gender == 2) printWriter.println("FEMALE");
	else printWriter.println("UNKNOWN");
	
    printWriter.print("SCHOOL:"); 
    printWriter.println(array[i].school);
	
    printWriter.print("WEIGHT:"); 
    printWriter.println(array[i].weight);
	
    printWriter.print("POINTS:"); 
    printWriter.println(array[i].points);
	}
    printWriter.close();
}
public static Wrestler [] sorter(Wrestler ar[])
{
	for (int i = 0; i < ar.length-1; i++)
	   {
	      int min = i;
	      for (int j = i+1; j < ar.length; j++)
	            if (ar[j].points < ar[min].points) min = j;
	      Wrestler temp = ar[i];
	      ar[i] = ar[min];
	      ar[min] = temp;
	   }     
   return ar;
   }
}
