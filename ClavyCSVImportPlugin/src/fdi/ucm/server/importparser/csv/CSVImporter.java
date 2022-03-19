package fdi.ucm.server.importparser.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import fdi.ucm.server.modelComplete.collection.CompleteCollection;
import fdi.ucm.server.modelComplete.collection.document.CompleteDocuments;
import fdi.ucm.server.modelComplete.collection.document.CompleteElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteTextElement;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteGrammar;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteTextElementType;

public class CSVImporter {

	private CompleteCollection CC;
	private List<String> Logs;
	public static final String delimiter = ",";


	public CSVImporter(List<String> logs) {
		CC=new CompleteCollection("CSV", "CSV Importdata");
		Logs=logs;
	}

	public void ProcessFile(String fileCSV, String description) {
		
		
		try {
		      File file = new File(fileCSV);
		      
		      CompleteGrammar G=new CompleteGrammar(file.getName(), file.getName(), CC);
		      CC.getMetamodelGrammar().add(G);
		      
		      FileReader fr = new FileReader(file);
		      BufferedReader br = new BufferedReader(fr);
		      String line = " ";
		      String[] tempArr;
		      boolean PrimeraCategoria=true;
		      LinkedList<CompleteTextElementType> listaElementos=new LinkedList<CompleteTextElementType>();
		      int docuemntoConteo=1;
		      while ((line = br.readLine()) != null) {
		        tempArr = line.split(delimiter);
		        
		        CompleteDocuments CD=null;
		        if (!PrimeraCategoria)
		        	{
		        	CD=new CompleteDocuments(CC,"Documento ->"+docuemntoConteo++,"");
	        		CC.getEstructuras().add(CD);
		        	}
		        
		        for (int i = 0; i < tempArr.length; i++) {
		        	String tempStr=tempArr[i]; 
		        	
		        	if (PrimeraCategoria)
		        	{
		        	CompleteTextElementType textValue=new CompleteTextElementType(tempStr, G);
		        	G.getSons().add(textValue);
		        	
		        	listaElementos.add(i, textValue);
		        	}else
		        	{
		        		
		        		CompleteTextElementType Estees=listaElementos.get(i);
		        		CD.getDescription().add(new CompleteTextElement(Estees, tempStr));
		        		
		        		
		        		if (Estees.getName().toLowerCase().equals(description.toLowerCase()))
		        			CD.setDescriptionText(tempStr);
		        	}
		        	
		        	
		        	
		          
		        }
		        
		        if (PrimeraCategoria)
		        	PrimeraCategoria=false;
//		        else
//		        	System.out.println();
		      }
		      br.close();
		    }
		    catch(IOException ioe) {
		      ioe.printStackTrace();
		      Logs.add(ioe.getMessage());
		    }
		
		
	}

	public CompleteCollection getCollection() {
		return CC;
	}

	
	public static void main(String[] args) {
		LinkedList<String> SS=new LinkedList<String>();
		CSVImporter csVimpo=new CSVImporter(SS);
		csVimpo.ProcessFile("ejemplo/ejemplo.csv", "id");
		CompleteCollection CC= csVimpo.getCollection();
		
		for (CompleteDocuments docume : CC.getEstructuras()) {
			System.out.println("docu__>"+docume.getDescriptionText());
			for (CompleteElement eleme : docume.getDescription()) {
				System.out.println("-----"+eleme.getHastype().getName()+
						":"+((CompleteTextElement)eleme).getValue());
			}
		}
		
		for (String string : SS) {
			System.err.println(string);
		}
		if (SS.isEmpty())
			System.out.println("Correcto");
	}
}
