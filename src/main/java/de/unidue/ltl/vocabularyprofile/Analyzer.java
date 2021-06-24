package de.unidue.ltl.vocabularyprofile;

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
		
		//get number of tokens without punctuation
		for (Token token : tokens){
//			System.out.println(token.getCoveredText() +  " "+ token.getPos().getPosValue() + " "+ token.getLemma().getValue());
			if((!token.getLemma().getValue().equals("."))&&(!token.getLemma().getValue().equals(","))
				&&(!token.getLemma().getValue().equals("?"))&&(!token.getLemma().getValue().equals(":"))
				&&(!token.getLemma().getValue().equals("-"))&&(!token.getLemma().getValue().equals("_"))
				&&(!token.getLemma().getValue().equals("'"))&&(!token.getLemma().getValue().equals(";"))
				&&(!token.getLemma().getValue().equals("\""))&&(!token.getLemma().getValue().equals("!"))
				&&(!token.getLemma().getValue().equals("\\"))&&(!token.getLemma().getValue().equals("+"))
				&&(!token.getLemma().getValue().equals("*"))&&(!token.getLemma().getValue().equals("/"))) {
				
				numTokens +=1;
			}
			System.out.println(token.getCoveredText() +  " "+ token.getPos().getPosValue() + " "+ token.getLemma().getValue());
			
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
		System.out.println("Number of annotated tokens: "+vps.size());
		int countA1 = 0, countA2= 0, countB1= 0, countB2= 0, countC1=0, countC2 = 0;
		float percentA1 = 0, percentA2 = 0, percentB1=0, percentB2 = 0, percentC1=0,percentC2=0;
		
		for (VocabularyProfile vp : vps){
			
			
			  if(vp.getLevel().equals("A1")) { countA1 += 1; }
			  if(vp.getLevel().equals("A2")) { countA2 += 1; }
			  if(vp.getLevel().equals("B1")) { countB1 += 1; }
			  if(vp.getLevel().equals("B2")) { countB2 += 1; }
			  if(vp.getLevel().equals("C1")) { countC1 += 1; }
			  if(vp.getLevel().equals("C2")) { countC2 += 1; }
			 
									
			System.out.println(vp.getCoveredText() + " - " +vp.getName() + " ("+vp.getLevel()+")");
		}
		percentA1= ((float)countA1)/numTokens;
		percentA2= ((float)countA2)/numTokens;
		percentB1= ((float)countB1)/numTokens;
		percentB2= ((float)countB2)/numTokens;
		percentC1= ((float)countC1)/numTokens;
		percentC2= ((float)countC2)/numTokens;

		System.out.println("Number of A1 words:"+ countA1 +" with "+percentA1+" %");
		System.out.println("Number of A2 words:"+ countA2 +" with "+percentA2+" %");
		System.out.println("Number of B1 words:"+ countB1 +" with "+percentB1+" %");
		System.out.println("Number of B2 words:"+ countB2 +" with "+percentB2+" %");
		System.out.println("Number of C1 words:"+ countC1 +" with "+percentC1+" %");
		System.out.println("Number of C2 words:"+ countC2 +" with "+percentC2+" %");
		
		
	}
	
	
}
