package Week4;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class BabyNames {
	//finds the total numbers births,girls and boys
	public void totalBirths(FileResource fr) {
		int totalBirths=0;
		int girls=0,boys=0;
		CSVParser p=fr.getCSVParser();
		for(CSVRecord r:p) {
			int born=Integer.parseInt(r.get(2));
			String gender=r.get(1);
			totalBirths+=born;
			if(gender.equals("M")) {
				boys+=born;
			}
			else{
				girls+=born;
			}
		}
		System.out.println("Total: "+totalBirths);
		System.out.println("Boys: "+boys);
		System.out.println("Girls: "+girls);
	}
	//returns the rank of the name in the file for the given gender,with the largest number of births
public long getRank(int year, String name, String gender)
{
		long rank=-1;
		FileResource fr = new FileResource();
		CSVParser p = fr.getCSVParser(false);
		for(CSVRecord r:p){
			String current_name=r.get(0);
			String current_gender=r.get(1);
			if(current_gender.equals(gender) && current_name.equals(name)) {
				rank = r.getRecordNumber();
			}
		}
		return rank;
}
//returns the name of the person in the file at this rank for the given gender
public String getName(int year, int rank, String gender) 
{
		String name="";
		FileResource fr=new FileResource();
		CSVParser p=fr.getCSVParser(false);
		for(CSVRecord record:p) {
			long current_rank=record.getRecordNumber();
			String current_gender=record.get(1);
			String current_name=record.get(0);

			if(current_rank == rank && current_gender.equals(gender)) {
				name=current_name;
			}
		}
		if(name!= "") {
			return name;
		} 
		else {
			return "NO NAME";
		}
}
//finds what name would have been named if they were born in a different year, based on the same popularity
public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		FileResource fr=new FileResource();
		FileResource fr2=new FileResource();
		CSVParser old=fr.getCSVParser(false);
		CSVParser newParser=fr2.getCSVParser(false);
		String new_name = "";
		long popularity = 0;

		for(CSVRecord r : old) {
			String current_name=r.get(0);
			String current_gender=r.get(1);
			if(current_name.equals(name) && current_gender.equals(gender)) {
				popularity=r.getRecordNumber();
			}
		}
		for(CSVRecord r : newParser) {
			String current_gender=r.get(1);
			long current_popularity=r.getRecordNumber();
			if(current_gender.equals(gender) && popularity == current_popularity) {
				new_name = r.get(0);
			}
		}
	System.out.println(name+" born in "+year+" would be "+new_name+" if she was born in "+newYear);
}
//selects a range of files to process and returns  year with the highest rank for the name and gender
public long yearOfHighestRank(String name, String gender) {
		long highestRank=0;
		int resultyear=-1;
		String fileName = "";
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser p=fr.getCSVParser(false);
			for(CSVRecord r : p)
			{
				String current_name=r.get(0);
				String current_gender=r.get(1);
				if(current_name.equals(name) && current_gender.equals(gender)) {
					long current_rank = r.getRecordNumber();					
					if(highestRank == 0) {
						highestRank = current_rank;
						fileName = f.getName();
					} 
					else {
						if(highestRank > current_rank) {
							highestRank = current_rank;
							fileName = f.getName();
						}
					}
				}
			}
		}
		fileName = fileName.replaceAll("[^\\d]", "");
		highestRank = Integer.parseInt(fileName);
		return highestRank;
}
//finds average rank of the name and gender over the selected files
public double getAverageRank(String name, String gender) {
		DirectoryResource dr=new DirectoryResource();
		double total_rank=0.0;
		int total_num = 0;
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser p = fr.getCSVParser(false);
			for(CSVRecord r : p) {
				String current_name=r.get(0);
				String current_gender=r.get(1);
				if(current_name.equals(name) && current_gender.equals(gender)){
					long current_rank = r.getRecordNumber();
					total_rank+= (double)current_rank;
					total_num++;
				}
			}
		}
		double avgRank=total_rank/(double)total_num;
		return avgRank;
}
public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int born=0;
		long rank=getRank(year, name, gender);
		FileResource fr=new FileResource();
		CSVParser p=fr.getCSVParser(false);
		for(CSVRecord r : p) {
			int current_born=Integer.parseInt(r.get(2));
			String current_gender=r.get(1);
			long current_rank=r.getRecordNumber();
			if(gender.equals(current_gender) && rank > current_rank) {
				born += current_born;
			}
		}
		return born;
}
public void testTotlaBirth() {
		FileResource fr=new FileResource();
		totalBirths(fr);
		long rank=getRank(2012, "Mason", "M");
		System.out.println("Rank = "+rank);
		String name=getName(2012, 10, "M");
		System.out.println("Name: "+name);
		whatIsNameInYear("Isabella", 2012, 2014, "F");
		System.out.println(yearOfHighestRank("Mason", "M"));
		System.out.println(getAverageRank("Mason", "M"));		
		System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
	}

	public static void main(String args[]) {
		BabyNames names = new BabyNames();
		names.testTotlaBirth();
	}
}