/**
 * 
 */
package fdi.ucm.server.importparser.csv;

import java.util.ArrayList;
import fdi.ucm.server.modelComplete.ImportExportDataEnum;
import fdi.ucm.server.modelComplete.ImportExportPair;
import fdi.ucm.server.modelComplete.LoadCollection;
import fdi.ucm.server.modelComplete.collection.CompleteCollectionAndLog;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class LoadCollectionCSV extends LoadCollection {

	private static ArrayList<ImportExportPair> Parametros;
	private ArrayList<String> Log;
	private CSVImporter cSVImporter;
	


	public ArrayList<ImportExportPair> getConfiguracion() {
		if (Parametros==null)
		{
			ArrayList<ImportExportPair> ListaCampos=new ArrayList<ImportExportPair>();
			ListaCampos.add(new ImportExportPair(ImportExportDataEnum.File, "CSV"));
			ListaCampos.add(new ImportExportPair(ImportExportDataEnum.Text, "Description Field"));
			Parametros=ListaCampos;
			return ListaCampos;
		}
		else return Parametros;
	}



	@Override
	public CompleteCollectionAndLog processCollecccion(ArrayList<String> DateEntrada) {
		Log=new ArrayList<String>();
		cSVImporter=new CSVImporter(Log);
		
		if (DateEntrada!=null)	
		{
			String FileCSV = DateEntrada.get(0);
			String Description = DateEntrada.get(1);
			
			cSVImporter.ProcessFile(FileCSV,Description);
		}
		
		
		
		
		return new CompleteCollectionAndLog(cSVImporter.getCollection(),Log);
	}



	@Override
	public String getName() {
		return "CSV import";
	}



	@Override
	public boolean getCloneLocalFiles() {
		return false;
	}
	




}
