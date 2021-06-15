package de.unidue.ltl.vocabularyprofile2;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.PennTree;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

import de.unidue.ltl.escrito.core.types.GrammarProfile;
import de.unidue.ltl.escrito.core.types.VocabularyProfile;

public class Analyzer extends JCasAnnotator_ImplBase {

	
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
		
		int numTokens = 0;
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		
		
		/*
		 * System.out.println("-----------------------");
		 * System.out.println(tokens.size());
		 * System.out.println("------------------------");
		 */
		//get number of tokens without punctuation
		for (Token token : tokens){
			if((!token.getLemma().getValue().equals("."))&&(!token.getLemma().getValue().equals(","))
				&&(!token.getLemma().getValue().equals("?"))&&(!token.getLemma().getValue().equals(":"))
				&&(!token.getLemma().getValue().equals("-"))&&(!token.getLemma().getValue().equals("_"))
				&&(!token.getLemma().getValue().equals("'"))&&(!token.getLemma().getValue().equals(";"))
				&&(!token.getLemma().getValue().equals("\""))&&(!token.getLemma().getValue().equals("!"))
				&&(!token.getLemma().getValue().equals("\\"))&&(!token.getLemma().getValue().equals("+"))
				&&(!token.getLemma().getValue().equals("*"))&&(!token.getLemma().getValue().equals("/"))) {
				
				numTokens +=1;
			}
//			System.out.println(token.getCoveredText() +  " "+ token.getPos().getPosValue() + " "+ token.getLemma().getValue());
			
		}
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

		
		
		
		  Collection<VocabularyProfile> vps = JCasUtil.select(aJCas, VocabularyProfile.class); 
		  System.out.println("Number of all tokens: "+numTokens);
		  System.out.println("Number of annotated tokens: "+vps.size()); int countRang1 = 0, countRang2= 0, countRang3= 0, countRang4= 0, countRang5=0, countRang6 =
		  0; int countRang7 = 0, countRang8= 0, countRang9= 0, countRang10= 0,
		  countRang11=0, countRang12 = 0;
		  
		  for (VocabularyProfile vp : vps){ // System.out.println(vp.getCoveredText());
		  
		  if(vp.getLevel().equals("Rang1")) { countRang1 += 1; }
		  if(vp.getLevel().equals("Rang2")) { countRang2 += 1; }
		  if(vp.getLevel().equals("Rang3")) { countRang3 += 1; }
		  if(vp.getLevel().equals("Rang4")) { countRang4 += 1; }
		  if(vp.getLevel().equals("Rang5")) { countRang5 += 1; }
		  if(vp.getLevel().equals("Rang6")) { countRang6 += 1; }
		  if(vp.getLevel().equals("Rang7")) { countRang7 += 1; }
		  if(vp.getLevel().equals("Rang8")) { countRang8 += 1; }
		  if(vp.getLevel().equals("Rang9")) { countRang9 += 1; }
		  if(vp.getLevel().equals("Rang10")) { countRang10 += 1; }
		  if(vp.getLevel().equals("Rang11")) { countRang11 += 1; }
		  if(vp.getLevel().equals("Rang12")) { countRang12 += 1; }
		  System.out.println(vp.getCoveredText() + " - " +vp.getName() +  " ("+vp.getLevel()+")");
		  }
		   
		  
		  System.out.println("Number of Rang1 words:"+ countRang1+" with "+((float)countRang1)/tokens.size()+" %");
		  System.out.println("Number of Rang2 words:"+ countRang2+" with "+((float)countRang2)/tokens.size()+" %");
		  System.out.println("Number of Rang3 words:"+ countRang3+" with "+((float)countRang3)/tokens.size()+" %");
		  System.out.println("Number of Rang4 words:"+ countRang4+" with "+((float)countRang4)/tokens.size()+" %");
		  System.out.println("Number of Rang5 words:"+ countRang5+" with "+((float)countRang5)/tokens.size()+" %");
		  System.out.println("Number of Rang6 words:"+ countRang6+" with "+((float)countRang6)/tokens.size()+" %");
		  System.out.println("Number of Rang7 words:"+ countRang7+" with "+((float)countRang7)/tokens.size()+" %");
		  System.out.println("Number of Rang8 words:"+ countRang8+" with "+((float)countRang8)/tokens.size()+" %");
		  System.out.println("Number of Rang9 words:"+ countRang9+" with "+((float)countRang9)/tokens.size()+" %");
		  System.out.println("Number of Rang10 words:"+ countRang10+" with "+((float)countRang10)*100/tokens.size()+" %");
		  System.out.println("Number of Rang11 words:"+ countRang11+" with "+((float)countRang11)*100/tokens.size()+" %");
		  System.out.println("Number of Rang12 words:"+ countRang12+" with "+((float)countRang12)*100/tokens.size()+" %");
		 
	}
	
	
}
