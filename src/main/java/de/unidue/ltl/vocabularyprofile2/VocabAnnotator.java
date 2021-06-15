package de.unidue.ltl.vocabularyprofile2;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.ltl.escrito.core.types.VocabularyProfile_Type;
import de.unidue.ltl.escrito.core.types.VocabularyProfile;



public class VocabAnnotator extends JCasAnnotator_ImplBase{

	
	Map<String, String> vocab1;
	
	 @Override
	  public void initialize(final UimaContext context) throws ResourceInitializationException {
	    super.initialize(context);
		vocab1 = new HashMap<String, String>();
		String level= "";
		for (int i = 0; i < 12; i++) {
			switch (i) {
			case 0:
				level = "Rang12";
				break;
			case 1:
				level = "Rang11";
				break;
			case 2:
				level = "Rang10";
				break;
			case 3:
				level = "Rang9";
				break;
			case 4:
				level = "Rang8";
				break;
			case 5:
				level = "Rang7";
				break;
			case 6:
				level = "Rang6";
				break;
			case 7:
				level = "Rang5";
				break;
			case 8:
				level = "Rang4";
				break;
			case 9:
				level = "Rang3";
				break;
			case 10:
				level = "Rang2";
				break;
			case 11:
				level = "Rang1";
				break;

			default:
				break;
			}
			
			try
	        {
				
	            FileInputStream file = new FileInputStream(new File("D:\\BA\\CV.xlsx"));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(i);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                 
	                while (cellIterator.hasNext()) 
	                {
	                    Cell cell = cellIterator.next();
	                    //Check the cell type and format accordingly
	                    switch (cell.getCellType()) 
	                    {
	                        case Cell.CELL_TYPE_NUMERIC:
	                            System.out.print(cell.getNumericCellValue());
	                            break;
	                        case Cell.CELL_TYPE_STRING:
	                        	vocab1.put(cell.getStringCellValue().toLowerCase(), level);
	                        	

	                            break;
	                    }
	                }

	            }
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
		}
		/*
		 * for (String i : vocab.keySet()) { System.out.println("key: " + i + " value: "
		 * + vocab.get(i)); }
		 */
		
	}
	
	
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		for (Token t : tokens){
			String token = t.getCoveredText().toLowerCase();
			if (vocab1.containsKey(token)){
				VocabularyProfile vp = new VocabularyProfile(aJCas);
				vp.setName("Crowdsourcing");
				vp.setBegin(t.getBegin());
				vp.setEnd(t.getEnd());
				vp.setLevel(vocab1.get(token));
				vp.addToIndexes();
			}else if (vocab1.containsKey(t.getLemma().getValue())){
				VocabularyProfile vp = new VocabularyProfile(aJCas);
				vp.setName("Crowdsourcing");
				vp.setBegin(t.getBegin());
				vp.setEnd(t.getEnd());
				vp.setLevel(vocab1.get(t.getLemma().getValue()));
				vp.addToIndexes();
			}else if(isNumeric(t.getLemma().getValue())||isFloat(t.getLemma().getValue())) {
				VocabularyProfile vp = new VocabularyProfile(aJCas);
				vp.setName("Crowdsourcing");
				vp.setBegin(t.getBegin());
				vp.setEnd(t.getEnd());
				vp.setLevel("Rang1");
				vp.addToIndexes();
			}else {
				System.out.println(token);
			}
		}
	}
	//check whether a string is a number
	public static boolean isNumeric(String value) {
		 try {
		    int number = Integer.parseInt(value);
		    return true; 
		 }
		 catch(NumberFormatException e) {
		   return false;
		 }
		}
	public static boolean isFloat(String value) {
		 try {
		    float number = Float.parseFloat(value);
		    return true; 
		 }
		 catch(NumberFormatException e) {
		   return false;
		 }
		}

}
