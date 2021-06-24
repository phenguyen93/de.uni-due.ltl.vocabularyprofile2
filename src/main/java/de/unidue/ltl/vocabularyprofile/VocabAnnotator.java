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
		/*
		 * for (String i : vocab.keySet()) { System.out.println("key: " + i + " value: "
		 * + vocab.get(i)); }
		 */
		
	}
	
	
	
	/**
	 *
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		for (Token t : tokens){
			String lemma = t.getLemma().getValue().toLowerCase();
			String pOS =   t.getPos().getPosValue();
			String wordType = "";

			//change Tagset to compaire with types of words in the EVP-Wordlist
			if(pOS.equals("NN")||pOS.equals("NNP")||pOS.equals("NNPS")||pOS.equals("NNS")||pOS.equals("NP")||pOS.equals("NPS")){
				wordType = "noun";
			}else if(pOS.equals("VBD")||pOS.equals("VBG")||pOS.equals("VBN")||pOS.equals("VBP")||pOS.equals("VBZ")||pOS.equals("VB")) {
				wordType = "verb";
			}else if(pOS.equals("JJ")||pOS.equals("JJR")||pOS.equals("JJS")) {
				wordType = "adjective";
			}else if(pOS.equals("MD")) {
				wordType = "modal verb";
			}else if(pOS.equals("DT")||pOS.equals("WDT")) {
				wordType = "determiner";
			}else if(pOS.equals("RB")||pOS.equals("RBR")||pOS.equals("RBS")||pOS.equals("WRB")||pOS.equals("EX")) {
				wordType = "adverb";
			}else if(pOS.equals("IN")) {
				wordType = "preposition";
			}else if(pOS.equals("PP")||pOS.equals("PP$")||pOS.equals("WP")||pOS.equals("WP$")||pOS.equals("PRP")) {
				wordType = "pronoun";	
			}else {
				wordType = "none";
			}
			Vocabulary vocabulary = new Vocabulary(lemma,wordType);
			
			for (Vocabulary vo : sortByValue(vocab).keySet()) {
				if(vo.equals(vocabulary)) {
					VocabularyProfile vp = new VocabularyProfile(aJCas);
					vp.setName(t.getLemma().getValue());
					vp.setBegin(t.getBegin());
					vp.setEnd(t.getEnd());
					vp.setLevel(vocab.get(vo));
					vp.addToIndexes();
					break;
				}
				
			}
			
			/*
			 * if (vocab.containsKey(lemma)){
			 * if(vocab.get(lemma).getWordType().equals(wordType)) { VocabularyProfile vp =
			 * new VocabularyProfile(aJCas); vp.setName("Cambridge");
			 * vp.setBegin(t.getBegin()); vp.setEnd(t.getEnd());
			 * vp.setLevel(vocab.get(lemma)); vp.addToIndexes(); }
			 * 
			 * }
			 */
			
			/*
			 * if (vocab.containsKey(vocabulary)){ VocabularyProfile vp = new
			 * VocabularyProfile(aJCas); vp.setName("Cambridge"); vp.setBegin(t.getBegin());
			 * vp.setEnd(t.getEnd()); vp.setLevel(); vp.addToIndexes(); }else {
			 * System.out.println(t.getLemma().getValue()); }
			 */
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

}
