package fdi.ucm.server.importparser.csv;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import fdi.ucm.server.modelComplete.collection.CompleteCollection;

public class CSVImporter {

	private CompleteCollection CC;
	private List<String> Logs;

	public CSVImporter(List<String> logs) {
		CC=new CompleteCollection("CSV", "CSV Importdata");
		Logs=logs;
	}

	public void ProcessFile(String fileCSV) {
		try {
			 Scanner sc = new Scanner(new File(fileCSV));
			    //parsing a CSV file into the constructor of Scanner class 
			    sc.useDelimiter(",");
			    //setting comma as delimiter pattern
			    while (sc.hasNext()) {
			      System.out.print(sc.next());
			    }
			    sc.close();
			    //closes the scanner  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public CompleteCollection getCollection() {
		return CC;
	}

	
	public static void main(String[] args) {
		LinkedList<String> SS=new LinkedList<String>();
		new CSVImporter(SS).ProcessFile("/ejemplo/ejemplo.csv");
		for (String string : SS) {
			System.err.println(string);
		}
		if (SS.isEmpty())
			System.out.println("Correcto");
	}
}
