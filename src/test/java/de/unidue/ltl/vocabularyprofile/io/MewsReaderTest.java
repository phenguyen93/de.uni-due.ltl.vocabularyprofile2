package de.unidue.ltl.vocabularyprofile.io;


import static org.junit.Assert.assertEquals;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.dkpro.tc.ml.base.Experiment_ImplBase;
import org.junit.Test;

import de.unidue.ltl.vocabularyprofile.io.MewsReader;

public class MewsReaderTest {

	@Test
	public void Mewsreadertest() throws ResourceInitializationException {
		String trainData = "/Users/andrea/dkpro/datasets/MEWS_Essays/Essays_all/Deutschland/T1/AD";
		String scoreFile = "/Users/andrea/dkpro/datasets/MEWS_Essays/MEWSdocs/MEWS_FINAL_Deliverable_ScoreFile_120817.tsv";
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(
				MewsReader.class,
				MewsReader.PARAM_INPUT_FILE, trainData,
				MewsReader.PARAM_SCORE_FILE, scoreFile);
		int i = 0;
		for (JCas jcas : new JCasIterable(reader)) {
			System.out.println(jcas.getDocumentText().substring(0, 20)+"...");
			i++;
		}
		 assertEquals(392, i);
	}

} 
