package de.unidue.ltl.vocabularyprofile;

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
import de.unidue.ltl.escrito.core.types.VocabularyProfile;



public class VocabAnnotator extends JCasAnnotator_ImplBase{

	
	Map<String, String> vocab;
	
	 @Override
	  public void initialize(final UimaContext context) throws ResourceInitializationException {
	    super.initialize(context);
		vocab = new HashMap<String, String>();
		String level= "";
		for (int i = 0; i < 6; i++) {
			switch (i) {
			case 0:
				level = "C2";
				break;
			case 1:
				level = "C1";
				break;
			case 2:
				level = "B2";
				break;
			case 3:
				level = "B1";
				break;
			case 4:
				level = "A2";
				break;
			case 5:
				level = "A1";
				break;

			default:
				break;
			}
			
			try
	        {
				
	            FileInputStream file = new FileInputStream(new File("D:\\BA\\EVP.xlsx"));
	 
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
	                        	vocab.put(cell.getStringCellValue().toLowerCase(), level);
	                        	

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
			String lemma = t.getLemma().getValue().toLowerCase();
			if (vocab.containsKey(lemma)){
				VocabularyProfile vp = new VocabularyProfile(aJCas);
				vp.setName("Cambridge");
				vp.setBegin(t.getBegin());
				vp.setEnd(t.getEnd());
				vp.setLevel(vocab.get(lemma));
				vp.addToIndexes();
			}else {
				System.out.println(t.getLemma().getValue());
			}
		}
	}

}
