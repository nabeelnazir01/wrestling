import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class School {
String nameOfSchool;
Wrestler [] maleWrestlers;
Wrestler [] femaleWrestlers;
int totalSchoolPts;

public School (String name,Wrestler [] male,Wrestler [] female,int total)
{
	nameOfSchool=name;
	maleWrestlers=male;
	femaleWrestlers=female;
	totalSchoolPts= total;
}
public School ()
{
	this.nameOfSchool="##NO DATA##";
	this.maleWrestlers=null;
	this.femaleWrestlers=null;
	this.totalSchoolPts= 0;
}
public School (String name, Wrestler [] list) {
	this.nameOfSchool = name;
	int counterm=0;
	int counterf=0;
	for(int i=0;i<list.length; i++)
	{
		if(list[i].school.equals(name)) {
			this.totalSchoolPts+=list[i].points;
		}
		if(list[i].gender==1&list[i].school.equals(this.nameOfSchool))
		{
			counterm++;
		}
		if(list[i].gender==2&list[i].school.equals(this.nameOfSchool))
		{
			counterf++;
		}
	}
	this.maleWrestlers=new Wrestler [counterm];
	this.femaleWrestlers=new Wrestler [counterf];
	counterm=0;
	counterf=0;
	for(int i=0;i<this.maleWrestlers.length; i++)
	{
		this.maleWrestlers[i]=new Wrestler();
	}
	for(int i=0;i<this.femaleWrestlers.length; i++)
	{
		this.femaleWrestlers[i]=new Wrestler();
	}
	for(int i=0;i<list.length; i++)
	{
		if(list[i].gender==1&list[i].school.equals(this.nameOfSchool))
		{
			this.maleWrestlers[counterm]=list[i];
			counterm++;
		}
		if(list[i].gender==2&list[i].school.equals(this.nameOfSchool))
		{
			this.femaleWrestlers[counterf]=list[i];
			counterf++;
		}
	}
}
public static School [] sorter(School ar[])
{
	for (int i = 0; i < ar.length-1; i++)
	   {
	      int min = i;
	      for (int j = i+1; j < ar.length; j++)
	            if (ar[j].totalSchoolPts < ar[min].totalSchoolPts) min = j;
	      School temp = ar[i];
	      ar[i] = ar[min];
	      ar[min] = temp;
	   }
   return ar;
}
public static void savetoFile (School [] array,ArrayList<Double> Weights) throws IOException
{
    FileWriter fileWriter = new FileWriter("Sorted.txt", true);
    BufferedWriter outStream= new BufferedWriter(fileWriter);
    PrintWriter printWriter = new PrintWriter(outStream);
    for(int i=array.length-1;i>0;i--)
	{	
	printWriter.println("-------------------");
    printWriter.print("SCHOOL:"); 
    printWriter.println(array[i].nameOfSchool);
    printWriter.print("TOTAL POINTS:"); 
    printWriter.println(array[i].totalSchoolPts);
    printWriter.println("-------------------");

    printWriter.println("MALE WRESTLERS");
    printWriter.println("-------------------");
    for(int e=array[i].maleWrestlers.length-1;e>0;e--)
	{	

        printWriter.print("NAME:"); 
        printWriter.println(array[i].maleWrestlers[e].name);
    	
        printWriter.print("WEIGHT:"); 
        printWriter.println(array[i].maleWrestlers[e].weight);
    	
        printWriter.print("POINTS:"); 
        printWriter.println(array[i].maleWrestlers[e].points);
//    	}
	}
    printWriter.println("-------------------");
    printWriter.println("FEMALE WRESTLERS");
    printWriter.println("-------------------");
    for(int e=array[i].femaleWrestlers.length-1;e>0;e--)
	{	

        printWriter.print("NAME:"); 
        printWriter.println(array[i].femaleWrestlers[e].name);
    	
        printWriter.print("WEIGHT:"); 
        printWriter.println(array[i].femaleWrestlers[e].weight);
    	
        printWriter.print("POINTS:"); 
        printWriter.println(array[i].femaleWrestlers[e].points);

	}

	}   
    printWriter.close();
}
}
