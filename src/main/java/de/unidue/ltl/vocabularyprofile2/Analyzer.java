package de.unidue.ltl.vocabularyprofile2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.PennTree;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

import de.unidue.ltl.escrito.core.types.GrammarProfile;
import de.unidue.ltl.escrito.core.types.VocabularyProfile;
import de.unidue.ltl.vocabularyprofile.Vocabulary;

public class Analyzer extends JCasAnnotator_ImplBase {

	Map<String, String> typeBasedVocab;
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		DocumentMetaData meta = JCasUtil.selectSingle(aJCas, DocumentMetaData.class);
//		String id = meta.getDocumentId();
//		System.out.println("Printing essay: "+id);
		String content = meta.getDocumentTitle();
		System.out.println("Printing essay: "+content);

		Collection<Sentence> sentences = JCasUtil.select(aJCas, Sentence.class);
		for (Sentence sentence : sentences){
//			System.out.println("Sentence: X"+sentence.getCoveredText()+"X"+sentence.getCoveredText().length());
		}
		
		int numberOfTokens = JCasUtil.select(aJCas, POS.class).size();
		int numberOfPunc =0;
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		for (Token t: tokens) {
			if(t.getPos().getCoarseValue()!=null) {
				if(t.getPos().getCoarseValue().equals("PUNCT")) {
					numberOfPunc +=1;
				}
			}
		}
		int numberOfTokensWithoutPunct = numberOfTokens-numberOfPunc;

		Collection<Chunk> chunks = JCasUtil.select(aJCas, Chunk.class);
		for (Chunk chunk : chunks){
			System.out.println(chunk.getCoveredText() + " "+ chunk.getChunkValue());
		}
		Collection<PennTree> pennTrees = JCasUtil.select(aJCas, PennTree.class);
		if (pennTrees.isEmpty()){
			System.err.println("No Trees found!");
//			System.exit(-1);
			
		}
		for (PennTree penntree : pennTrees){
			System.out.println("TREE: "+penntree.toString());
		}
		Collection<Dependency> dependencies = JCasUtil.select(aJCas, Dependency.class);
		for (Dependency dep : dependencies){
			System.out.println(dep.getGovernor().getCoveredText() + " " + dep.getDependencyType().toString() + " " + dep.getDependent().getCoveredText());
		}		
//		for (GrammarAnomaly ga : JCasUtil.select(aJCas, GrammarAnomaly.class)){
//			System.out.println(ga.getCoveredText()+ ": "+ ga.getCategory()+ " - "+ga.getDescription());
//		}

		
		
		
		/*
		 * Collection<VocabularyProfile> vps = JCasUtil.select(aJCas,
		 * VocabularyProfile.class);
		 * System.out.println("Number of all tokens: "+numberOfTokens);
		 * System.out.println("Number of all tokens ohne punct: "
		 * +numberOfTokensWithoutPunct);
		 * System.out.println("Number of annotated tokens: "+vps.size()); int countRang1
		 * = 0, countRang2= 0, countRang3= 0, countRang4= 0, countRang5=0, countRang6
		 * =0; int countRang7 = 0, countRang8= 0, countRang9= 0, countRang10=
		 * 0,countRang11=0, countRang12 = 0; int countRang13 = 0, countRang14= 0,
		 * countRang15= 0, countRang16= 0, countRang17=0, countRang18 =0; int
		 * countRang19 = 0, countRang20= 0, countRang21= 0, countRang22=
		 * 0,countRang23=0, countRang24 = 0, countRang25 = 0;
		 * 
		 * for (VocabularyProfile vp : vps){ // System.out.println(vp.getCoveredText());
		 * 
		 * if(vp.getLevel().equals("Rang1")) { countRang1 += 1; }
		 * if(vp.getLevel().equals("Rang2")) { countRang2 += 1; }
		 * if(vp.getLevel().equals("Rang3")) { countRang3 += 1; }
		 * if(vp.getLevel().equals("Rang4")) { countRang4 += 1; }
		 * if(vp.getLevel().equals("Rang5")) { countRang5 += 1; }
		 * if(vp.getLevel().equals("Rang6")) { countRang6 += 1; }
		 * if(vp.getLevel().equals("Rang7")) { countRang7 += 1; }
		 * if(vp.getLevel().equals("Rang8")) { countRang8 += 1; }
		 * if(vp.getLevel().equals("Rang9")) { countRang9 += 1; }
		 * if(vp.getLevel().equals("Rang10")) { countRang10 += 1; }
		 * if(vp.getLevel().equals("Rang11")) { countRang11 += 1; }
		 * if(vp.getLevel().equals("Rang12")) { countRang12 += 1; }
		 * if(vp.getLevel().equals("Rang13")) { countRang13 += 1; }
		 * if(vp.getLevel().equals("Rang14")) { countRang14 += 1; }
		 * if(vp.getLevel().equals("Rang15")) { countRang15 += 1; }
		 * if(vp.getLevel().equals("Rang16")) { countRang16 += 1; }
		 * if(vp.getLevel().equals("Rang17")) { countRang17 += 1; }
		 * if(vp.getLevel().equals("Rang18")) { countRang18 += 1; }
		 * if(vp.getLevel().equals("Rang19")) { countRang19 += 1; }
		 * if(vp.getLevel().equals("Rang20")) { countRang20 += 1; }
		 * if(vp.getLevel().equals("Rang21")) { countRang21 += 1; }
		 * if(vp.getLevel().equals("Rang22")) { countRang22 += 1; }
		 * if(vp.getLevel().equals("Rang23")) { countRang23 += 1; }
		 * if(vp.getLevel().equals("Rang24")) { countRang24 += 1; }
		 * if(vp.getLevel().equals("Rang25")) { countRang25 += 1; }
		 * System.out.println(vp.getCoveredText() + " - " +vp.getName() +
		 * " ("+vp.getLevel()+")"); }
		 */
		int numberOf1 = 0;
		int numberOf2 = 0;
		int numberOf3 = 0;
		int numberOf4 = 0;
		int numberOf5 = 0;
		int numberOf6 = 0;
		int numberOf7 = 0;
		int numberOf8 = 0;
		int numberOf9 = 0;
		int numberOf10 = 0;
		int numberOf11 = 0;
		int numberOf12 = 0;
		int numberOf13 = 0;
		int numberOf14 = 0;
		int numberOf15 = 0;
		int numberOf16 = 0;
		int numberOf17 = 0;
		int numberOf18 = 0;
		int numberOf19 = 0;
		int numberOf20 = 0;
		int numberOf21 = 0;
		int numberOf22 = 0;
		int numberOf23 = 0;
		int numberOf24 = 0;
		int numberOf25 = 0;
		
		
		Collection<VocabularyProfile> vps = JCasUtil.select(aJCas, VocabularyProfile.class);
		typeBasedVocab = new HashMap<String,String>();
		
		for (VocabularyProfile vp : vps){
			typeBasedVocab.put(vp.getCoveredText().toLowerCase(), vp.getLevel());
			System.out.println(vp.getCoveredText() + " - " +vp.getName() + " ("+vp.getLevel()+")" );
		}
		System.out.println("----------------------------------------------- /n");
		for(String vo: typeBasedVocab.keySet()) {
			System.out.println(vo + " - "  + " ("+typeBasedVocab.get(vo)+")" );
		}
		for(String level : typeBasedVocab.values()) {			
			if(level.equals("Rang1")) {numberOf1 += 1; }
			if(level.equals("Rang2")) {numberOf2 += 1; }
			if(level.equals("Rang3")) {numberOf3 += 1; }
			if(level.equals("Rang4")) {numberOf4 += 1; }
			if(level.equals("Rang5")) {numberOf5 += 1; }
			if(level.equals("Rang6")) {numberOf6 += 1; }
			if(level.equals("Rang7")) {numberOf7 += 1; }
			if(level.equals("Rang8")) {numberOf8 += 1; }
			if(level.equals("Rang9")) {numberOf9 += 1; }
			if(level.equals("Rang10")) {numberOf10 += 1; }
			if(level.equals("Rang11")) {numberOf11 += 1; }
			if(level.equals("Rang12")) {numberOf12 += 1; }
			if(level.equals("Rang13")) {numberOf13 += 1; }
			if(level.equals("Rang14")) {numberOf14 += 1; }
			if(level.equals("Rang15")) {numberOf15 += 1; }
			if(level.equals("Rang16")) {numberOf16 += 1; }
			if(level.equals("Rang17")) {numberOf17 += 1; }
			if(level.equals("Rang18")) {numberOf18 += 1; }
			if(level.equals("Rang19")) {numberOf19 += 1; }
			if(level.equals("Rang20")) {numberOf20 += 1; }
			if(level.equals("Rang21")) {numberOf21 += 1; }
			if(level.equals("Rang22")) {numberOf22 += 1; }
			if(level.equals("Rang23")) {numberOf23 += 1; }
			if(level.equals("Rang24")) {numberOf24 += 1; }
			if(level.equals("Rang25")) {numberOf25 += 1; }
		}
		
		int numberOfNone =numberOfTokensWithoutPunct-(numberOf1+numberOf2+numberOf3+numberOf4+numberOf5+numberOf6+numberOf7
				+numberOf8+numberOf9+numberOf10+numberOf11+numberOf12+numberOf13+numberOf14
				+numberOf15+numberOf16+numberOf17+numberOf18+numberOf19+numberOf20+numberOf21
				+numberOf22+numberOf23+numberOf24+numberOf25);
				
		
		double Rang1 = (1.0*numberOf1)/numberOfTokensWithoutPunct;
		double Rang2 = (1.0*numberOf2)/numberOfTokensWithoutPunct;
		double Rang3 = (1.0*numberOf3)/numberOfTokensWithoutPunct;
		double Rang4 = (1.0*numberOf4)/numberOfTokensWithoutPunct;
		double Rang5 = (1.0*numberOf5)/numberOfTokensWithoutPunct;
		double Rang6 = (1.0*numberOf6)/numberOfTokensWithoutPunct;
		double Rang7 = (1.0*numberOf7)/numberOfTokensWithoutPunct;
		double Rang8 = (1.0*numberOf8)/numberOfTokensWithoutPunct;
		double Rang9 = (1.0*numberOf9)/numberOfTokensWithoutPunct;
		double Rang10 = (1.0*numberOf10)/numberOfTokensWithoutPunct;
		double Rang11 = (1.0*numberOf11)/numberOfTokensWithoutPunct;
		double Rang12 = (1.0*numberOf12)/numberOfTokensWithoutPunct;
		double Rang13 = (1.0*numberOf13)/numberOfTokensWithoutPunct;
		double Rang14 = (1.0*numberOf14)/numberOfTokensWithoutPunct;
		double Rang15 = (1.0*numberOf15)/numberOfTokensWithoutPunct;
		double Rang16 = (1.0*numberOf16)/numberOfTokensWithoutPunct;
		double Rang17 = (1.0*numberOf17)/numberOfTokensWithoutPunct;
		double Rang18 = (1.0*numberOf18)/numberOfTokensWithoutPunct;
		double Rang19 = (1.0*numberOf19)/numberOfTokensWithoutPunct;
		double Rang20 = (1.0*numberOf20)/numberOfTokensWithoutPunct;
		double Rang21 = (1.0*numberOf21)/numberOfTokensWithoutPunct;
		double Rang22 = (1.0*numberOf22)/numberOfTokensWithoutPunct;
		double Rang23 = (1.0*numberOf23)/numberOfTokensWithoutPunct;
		double Rang24 = (1.0*numberOf24)/numberOfTokensWithoutPunct;
		double Rang25 = (1.0*numberOf25)/numberOfTokensWithoutPunct;
		double None = (1.0*numberOfNone)/numberOfTokensWithoutPunct;
		   
		  
		  System.out.println("Number of Rang1 words:"+ numberOf1+" with "+Rang1);
		  System.out.println("Number of Rang2 words:"+ numberOf2+" with "+Rang2);
		  System.out.println("Number of Rang3 words:"+ numberOf3+" with "+Rang3);
		  System.out.println("Number of Rang4 words:"+ numberOf4+" with "+Rang4);
		  System.out.println("Number of Rang5 words:"+ numberOf5+" with "+Rang5);
		  System.out.println("Number of Rang6 words:"+ numberOf6+" with "+Rang6);
		  System.out.println("Number of Rang7 words:"+ numberOf7+" with "+Rang7);
		  System.out.println("Number of Rang8 words:"+ numberOf8+" with "+Rang8);
		  System.out.println("Number of Rang9 words:"+ numberOf9+" with "+Rang9);
		  System.out.println("Number of Rang10 words:"+ numberOf10+" with "+Rang10);
		  System.out.println("Number of Rang11 words:"+ numberOf11+" with "+Rang11);
		  System.out.println("Number of Rang12 words:"+ numberOf12+" with "+Rang12);
		  System.out.println("Number of Rang13 words:"+ numberOf13+" with "+Rang13);
		  System.out.println("Number of Rang14 words:"+ numberOf14+" with "+Rang14);
		  System.out.println("Number of Rang15 words:"+ numberOf15+" with "+Rang15);
		  System.out.println("Number of Rang16 words:"+ numberOf16+" with "+Rang16);
		  System.out.println("Number of Rang17 words:"+ numberOf17+" with "+Rang17);
		  System.out.println("Number of Rang18 words:"+ numberOf18+" with "+Rang18);
		  System.out.println("Number of Rang19 words:"+ numberOf19+" with "+Rang19);
		  System.out.println("Number of Rang20 words:"+ numberOf20+" with "+Rang20);
		  System.out.println("Number of Rang21 words:"+ numberOf21+" with "+Rang21);
		  System.out.println("Number of Rang22 words:"+ numberOf22+" with "+Rang22);
		  System.out.println("Number of Rang23 words:"+ numberOf23+" with "+Rang23);
		  System.out.println("Number of Rang24 words:"+ numberOf24+" with "+Rang24);
		  System.out.println("Number of Rang25 words:"+ numberOf25+" with "+Rang25);
	}
	
	
}
