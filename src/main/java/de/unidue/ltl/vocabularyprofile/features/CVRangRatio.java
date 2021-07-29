package de.unidue.ltl.vocabularyprofile.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.features.FeatureType;
import org.dkpro.tc.api.type.TextClassificationTarget;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.ltl.escrito.core.types.VocabularyProfile;

/*
 * Ratio of a particular POS to total number of tokens
 * Currently implemented: Noun ratio, verb ratio
 */

@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS" })
public class CVRangRatio
extends FeatureExtractorResource_ImplBase
implements FeatureExtractor
{
	public static final String FN_1Ratio = "1Ratio";
	public static final String FN_2Ratio = "2Ratio";
	public static final String FN_3Ratio = "3Ratio";
	public static final String FN_4Ratio = "4Ratio";
	public static final String FN_5Ratio = "5Ratio";
	public static final String FN_6Ratio = "6Ratio";
	public static final String FN_7Ratio = "7Ratio";
	public static final String FN_8Ratio = "8Ratio";
	public static final String FN_9Ratio = "9Ratio";
	public static final String FN_10Ratio = "10Ratio";
	public static final String FN_11Ratio = "11Ratio";
	public static final String FN_12Ratio = "12Ratio";
	public static final String NoneRatio = "NoneRatio";
	
	

	@Override
	public Set<Feature> extract(JCas jcas, TextClassificationTarget aTarget)
			throws TextClassificationException
	{
		
		int numberOfTokens = JCasUtil.select(jcas, POS.class).size();
		
		
		//Nouns, Verbs
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
		
		Collection<VocabularyProfile> vps = JCasUtil.select(jcas, VocabularyProfile.class);
		for (VocabularyProfile vp : vps){
			
			
			  if(vp.getLevel().equals("Rang1")) { numberOf1 += 1; }
			  if(vp.getLevel().equals("Rang2")) { numberOf2 += 1; }
			  if(vp.getLevel().equals("Rang3")) { numberOf3 += 1; }
			  if(vp.getLevel().equals("Rang4")) { numberOf4 += 1; }
			  if(vp.getLevel().equals("Rang5")) { numberOf5 += 1; }
			  if(vp.getLevel().equals("Rang6")) { numberOf6 += 1; }
			  if(vp.getLevel().equals("Rang7")) { numberOf7 += 1; }
			  if(vp.getLevel().equals("Rang8")) { numberOf8 += 1; }
			  if(vp.getLevel().equals("Rang9")) { numberOf9 += 1; }
			  if(vp.getLevel().equals("Rang10")) { numberOf10 += 1; }
			  if(vp.getLevel().equals("Rang11")) { numberOf11 += 1; }
			  if(vp.getLevel().equals("Rang12")) { numberOf12 += 1; }
			 
		}

		
		double Rang1 = (1.0*numberOf1)/numberOfTokens;
		double Rang2 = (1.0*numberOf2)/numberOfTokens;
		double Rang3 = (1.0*numberOf3)/numberOfTokens;
		double Rang4 = (1.0*numberOf4)/numberOfTokens;
		double Rang5 = (1.0*numberOf5)/numberOfTokens;
		double Rang6 = (1.0*numberOf6)/numberOfTokens;
		double Rang7 = (1.0*numberOf7)/numberOfTokens;
		double Rang8 = (1.0*numberOf8)/numberOfTokens;
		double Rang9 = (1.0*numberOf9)/numberOfTokens;
		double Rang10 = (1.0*numberOf10)/numberOfTokens;
		double Rang11 = (1.0*numberOf11)/numberOfTokens;
		double Rang12 = (1.0*numberOf12)/numberOfTokens;
		double none = (1.0*(numberOfTokens-numberOf1-numberOf2-numberOf3-numberOf4-numberOf5-numberOf6-numberOf7-numberOf8-numberOf9-numberOf10-numberOf11-numberOf12))/numberOfTokens;

		
		Set<Feature> features = new HashSet<Feature>();
		features.add(new Feature(FN_1Ratio, Rang1, FeatureType.NUMERIC));
		features.add(new Feature(FN_2Ratio, Rang2, FeatureType.NUMERIC));
		features.add(new Feature(FN_3Ratio, Rang3, FeatureType.NUMERIC));
		features.add(new Feature(FN_4Ratio, Rang4, FeatureType.NUMERIC));
		features.add(new Feature(FN_5Ratio, Rang5, FeatureType.NUMERIC));
		features.add(new Feature(FN_6Ratio, Rang6, FeatureType.NUMERIC));
		features.add(new Feature(FN_7Ratio, Rang7, FeatureType.NUMERIC));
		features.add(new Feature(FN_8Ratio, Rang8, FeatureType.NUMERIC));
		features.add(new Feature(FN_9Ratio, Rang9, FeatureType.NUMERIC));
		features.add(new Feature(FN_10Ratio, Rang10, FeatureType.NUMERIC));
		features.add(new Feature(FN_11Ratio, Rang11, FeatureType.NUMERIC));
		features.add(new Feature(FN_12Ratio, Rang12, FeatureType.NUMERIC));
		features.add(new Feature(NoneRatio, none, FeatureType.NUMERIC));
		return features;
	}

}
