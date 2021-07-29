package de.unidue.ltl.vocabularyprofile.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
public class CVRangRatio2
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
	public static final String FN_13Ratio = "13Ratio";
	public static final String FN_14Ratio = "14Ratio";
	public static final String FN_15Ratio = "15Ratio";
	public static final String FN_16Ratio = "16Ratio";
	public static final String FN_17Ratio = "17Ratio";
	public static final String FN_18Ratio = "18Ratio";
	public static final String FN_19Ratio = "19Ratio";
	public static final String FN_20Ratio = "20Ratio";
	public static final String FN_21Ratio = "21Ratio";
	public static final String FN_22Ratio = "22Ratio";
	public static final String FN_23Ratio = "23Ratio";
	public static final String FN_24Ratio = "24Ratio";
	public static final String FN_25Ratio = "25Ratio";
	public static final String FN_NoneRatio = "NoneRatio";
	
	

	@Override
	public Set<Feature> extract(JCas jcas, TextClassificationTarget aTarget)
			throws TextClassificationException
	{
		
		int numberOfTokens = JCasUtil.select(jcas, POS.class).size();
		int numberOfPunc =0;
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		for (Token t: tokens) {
			if(t.getPos().getCoarseValue()!=null) {
				if(t.getPos().getCoarseValue().equals("PUNCT")) {
					numberOfPunc +=1;
				}
			}
			
		}
		int numberOfTokensWithoutPunct = numberOfTokens-numberOfPunc;
		
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
			  if(vp.getLevel().equals("Rang13")) { numberOf13 += 1; }
			  if(vp.getLevel().equals("Rang14")) { numberOf14 += 1; }
			  if(vp.getLevel().equals("Rang15")) { numberOf15 += 1; }
			  if(vp.getLevel().equals("Rang16")) { numberOf16 += 1; }
			  if(vp.getLevel().equals("Rang17")) { numberOf17 += 1; }
			  if(vp.getLevel().equals("Rang18")) { numberOf18 += 1; }
			  if(vp.getLevel().equals("Rang19")) { numberOf19 += 1; }
			  if(vp.getLevel().equals("Rang20")) { numberOf20 += 1; }
			  if(vp.getLevel().equals("Rang21")) { numberOf21 += 1; }
			  if(vp.getLevel().equals("Rang22")) { numberOf22 += 1; }
			  if(vp.getLevel().equals("Rang23")) { numberOf23 += 1; }
			  if(vp.getLevel().equals("Rang24")) { numberOf24 += 1; }
			  if(vp.getLevel().equals("Rang25")) { numberOf25 += 1; }
			 
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
		features.add(new Feature(FN_13Ratio, Rang13, FeatureType.NUMERIC));
		features.add(new Feature(FN_14Ratio, Rang14, FeatureType.NUMERIC));
		features.add(new Feature(FN_15Ratio, Rang15, FeatureType.NUMERIC));
		features.add(new Feature(FN_16Ratio, Rang16, FeatureType.NUMERIC));
		features.add(new Feature(FN_17Ratio, Rang17, FeatureType.NUMERIC));
		features.add(new Feature(FN_18Ratio, Rang18, FeatureType.NUMERIC));
		features.add(new Feature(FN_19Ratio, Rang19, FeatureType.NUMERIC));
		features.add(new Feature(FN_20Ratio, Rang20, FeatureType.NUMERIC));
		features.add(new Feature(FN_21Ratio, Rang21, FeatureType.NUMERIC));
		features.add(new Feature(FN_22Ratio, Rang22, FeatureType.NUMERIC));
		features.add(new Feature(FN_23Ratio, Rang23, FeatureType.NUMERIC));
		features.add(new Feature(FN_24Ratio, Rang24, FeatureType.NUMERIC));
		features.add(new Feature(FN_25Ratio, Rang25, FeatureType.NUMERIC));
		features.add(new Feature(FN_NoneRatio, None, FeatureType.NUMERIC));
		return features;
	}

}
