package fdi.ucm.server.importparser.csv;

import fdi.ucm.server.modelComplete.collection.CompleteCollection;

public class CSVImporter {

	private CompleteCollection CC;

	public CSVImporter(LoadCollectionCSV loadCollectionCSV) {
		CC=new CompleteCollection("CSV", "CSV Importdata");
	}

	public void ProcessFile(String fileCSV) {
		// TODO Auto-generated method stub
		
	}

	public CompleteCollection getCollection() {
		return CC;
	}

}
