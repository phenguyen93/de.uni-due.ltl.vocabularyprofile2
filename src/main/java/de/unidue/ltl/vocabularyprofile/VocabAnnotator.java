package de.unidue.ltl.vocabularyprofile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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

	
	Map<Vocabulary, String> vocab;
	
	 @Override
	  public void initialize(final UimaContext context) throws ResourceInitializationException {
	    super.initialize(context);
		vocab = new HashMap<Vocabulary, String>();
		String level= "";
		for (int i = 0; i < 6; i++) {
			
			  switch (i) { case 0: level = "C2"; break; case 1: level = "C1"; break; case
			  2: level = "B2"; break; case 3: level = "B1"; break; case 4: level = "A2";
			  break; case 5: level = "A1"; break;
			  
			  default: break; }
			 
			try
	        {
				
	            FileInputStream file = new FileInputStream(new File("D:\\BA\\EVPFinal.xlsx"));
	 
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
	                ArrayList<String> temp = new ArrayList<String>();
	                while (cellIterator.hasNext()){
	                    Cell cell = cellIterator.next();
	                    //Check the cell type and format accordingly
						
						  switch (cell.getCellType()) { 
						  case Cell.CELL_TYPE_NUMERIC:
	//						  System.out.print(cell.getNumericCellValue()); 
						  break; 
						  case	Cell.CELL_TYPE_STRING: 
							  temp.add(cell.getStringCellValue().toLowerCase());					 						  
						  break; 
						  }
						 
	                }
	               vocab.put(new Vocabulary(temp.get(0),temp.get(1)), level);
	               	}
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
		}
		//load extended Dictionary
		for (int j = 0; j < 5; j++) {			
			try{
				
	            FileInputStream file = new FileInputStream(new File("D:\\BA\\ExtendDictionary.xlsx"));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(j);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                ArrayList<String> temp = new ArrayList<String>();
	                while (cellIterator.hasNext()){
	                    Cell cell = cellIterator.next();
	                    //Check the cell type and format accordingly
						
						  switch (cell.getCellType()) { 
						  case Cell.CELL_TYPE_NUMERIC:
	//						  System.out.print(cell.getNumericCellValue()); 
						  break; 
						  case	Cell.CELL_TYPE_STRING: 
							  temp.add(cell.getStringCellValue().toLowerCase());					 						  
						  break; 
						  }
						 
	                }
	               vocab.put(new Vocabulary(temp.get(0),temp.get(1)), temp.get(2));
	               	}
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
		}
		
	}
	
	
	
	/**
	 *
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		for (Token t : tokens){
			String lemma = t.getLemma().getValue().toLowerCase();
//			String pOS =   t.getPos().getPosValue();
			String wordType = "";

			//change Tagset to compaire with types of words in the EVP-Wordlist
			if(t.getPos().getCoarseValue()!= null) {
				if(t.getPos().getCoarseValue().equals("NOUN")){
					wordType = "noun";
				}else if(t.getPos().getCoarseValue().equals("VERB")) {
					wordType = "verb";
				}else if(t.getPos().getCoarseValue().equals("ADJ")) {
					wordType = "adjective";
				}else if(t.getPos().getCoarseValue().equals("DET")) {
					wordType = "determiner";
				}else if(t.getPos().getCoarseValue().equals("ADV")) {
					wordType = "adverb";
				}else if(t.getPos().getCoarseValue().equals("ADP")) {
					wordType = "preposition";
				}else if(t.getPos().getCoarseValue().equals("PRON")) {
					wordType = "pronoun";
				}else if(t.getPos().getCoarseValue().equals("NUM")) {
					wordType = "NUM";	
				}else {
					wordType = "none";
				}
			}
			Vocabulary vocabulary = new Vocabulary(lemma,wordType);
				
			/*
			 * for (Vocabulary vo : sortByValue(vocab).keySet()) {
			 * if(vo.equals(vocabulary)){ VocabularyProfile vp = new
			 * VocabularyProfile(aJCas); vp.setName(t.getLemma().getValue());
			 * vp.setBegin(t.getBegin()); vp.setEnd(t.getEnd()); vp.setLevel(vocab.get(vo));
			 * vp.addToIndexes(); break; }
			 * 
			 * }
			 */
			 
			 
			
			
			  if (vocab.containsKey(vocabulary)){ 
				  VocabularyProfile vp = new VocabularyProfile(aJCas); 
				  vp.setName(wordType); 
				  vp.setBegin(t.getBegin());
				  vp.setEnd(t.getEnd()); 
				  vp.setLevel(vocab.get(vocabulary)); 
				  vp.addToIndexes(); 
			  }else if(wordType.equals("NUM")) {
				  VocabularyProfile vp = new VocabularyProfile(aJCas); 
				  vp.setName(wordType); 
				  vp.setBegin(t.getBegin());
				  vp.setEnd(t.getEnd()); 
				  vp.setLevel("A1"); 
				  vp.addToIndexes(); 
			  }else if(t.getPos().getCoarseValue() != null) {
				  if(!t.getPos().getCoarseValue().equals("PUNCT")) {
					  System.out.println(t.getCoveredText()+" ,"+lemma+"--"+t.getPos().getCoarseValue()); 
				  }
			  }
			 
		}
	}
	//sortiere Map in aufsteigende Reihe von Values A1-C2
	public static <Vocabulary, String extends Comparable<? super String>> Map<Vocabulary, String> sortByValue(Map<Vocabulary, String> map) {
        List<Entry<Vocabulary, String>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<Vocabulary, String> result = new LinkedHashMap<>();
        for (Entry<Vocabulary, String> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
	//superlative
	public static String getLemmaFromSuperlative(String word) {
		String lemma = "";
		if(word != null && word.length() >= 3) {
			if(word.substring(word.length() - 3 ).equals("ier")) {
				lemma = word.substring(0,word.length()-3)+"y";
			}
			if(word.substring(word.length() - 2 ).equals("er")) {
				lemma = word.substring(0,word.length()-2);
			}
		}
	
		return lemma;
	}

}
