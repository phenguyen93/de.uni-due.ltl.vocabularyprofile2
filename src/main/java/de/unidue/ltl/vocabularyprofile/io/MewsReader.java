package de.unidue.ltl.vocabularyprofile.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.dkpro.tc.api.type.TextClassificationOutcome;
import org.dkpro.tc.api.type.TextClassificationTarget;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import de.unidue.ltl.escrito.core.types.LearnerAnswer;
import de.unidue.ltl.escrito.io.util.Utils;

/*
 * read essays (raw text) from a folder
 * reads additionally scores from a tsv file
 * 
 * 4 prompts, 2 Länder, 2 Zeitpunkte
 * 
 */

public class MewsReader extends JCasCollectionReader_ImplBase {

	public static final String PARAM_INPUT_FILE = "InputFile";
	@ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
	protected String inputFileString;
	protected URL inputFileURL;

	public static final String PARAM_SCORE_FILE = "ScoreFile";
	@ConfigurationParameter(name = PARAM_SCORE_FILE, mandatory = true)
	protected String scoreFileString;

	public static final String PARAM_LANGUAGE = "Language";
	@ConfigurationParameter(name = PARAM_LANGUAGE, mandatory = false, defaultValue = "en")
	protected String language;

	public static final String PARAM_ENCODING = "Encoding";
	@ConfigurationParameter(name = PARAM_ENCODING, mandatory = false, defaultValue = "UTF-8")
	private String encoding;

	public static final String PARAM_SEPARATOR = "Separator";
	@ConfigurationParameter(name = PARAM_SEPARATOR, mandatory = false, defaultValue = "\t")
	private String separator;

	protected int currentIndex;

	protected Queue<MewsItem> items;

	Map<Integer, Integer> scorePerId_AD = new HashMap<Integer, Integer>();
	Map<Integer, Integer> scorePerId_CH = new HashMap<Integer, Integer>();
	Map<Integer, Integer> scorePerId_TE = new HashMap<Integer, Integer>();
	Map<Integer, Integer> scorePerId_VO = new HashMap<Integer, Integer>();

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		items = new LinkedList<MewsItem>();
		try {
			readScoreFile();
			inputFileURL = ResourceUtils.resolveLocation(inputFileString, this, aContext);
			File inputFile = new File(inputFileURL.getFile());
			File[] fileArray = inputFile.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.indexOf(".txt") != -1;
				}
			});
			for (File f : fileArray) {
				String fileName = f.getName();
				String id = fileName.substring(0, fileName.length() - 4);
				int studentId = Integer.parseInt(id.substring(0, id.indexOf("_")));
				// System.out.println(id);
				String text = cleanString(String.join(" ", FileUtils.readLines(f)));
				// System.out.println("text: "+text);
				if (text.startsWith("missing data") || text.equals("")) {
					continue;
				}
				// System.out.println(text);
				int score = -1;
				// System.out.println(studentId);
				try {
					if (id.endsWith("AD")) {
						score = scorePerId_AD.get(studentId);
					} else if (id.endsWith("TE")) {
						score = scorePerId_TE.get(studentId);
					} else if (id.endsWith("VO")) {
						score = scorePerId_VO.get(studentId);
					} else if (id.endsWith("CH")) {
						score = scorePerId_CH.get(studentId);
					}

					MewsItem item = new MewsItem(id, text, score);
					items.add(item);
				} catch (NullPointerException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
		currentIndex = 0;
	}

	// HOTFIX for Issue 445 in DKPro Core
	private static String cleanString(String textForCas) {
		textForCas = textForCas.replaceAll("[^a-zA-Z0-9\\-\\.,:;\\(\\)\\? ]", "");
		textForCas = textForCas.replace("…", "...");
		textForCas = textForCas.replace("´", "'");
		return textForCas.replace("’", "'").trim();
	}

	private void readScoreFile() throws IOException {
		List<String> lines = FileUtils.readLines(new File(scoreFileString));
		for (String line : lines) {
			if (line.startsWith("ID") || line.contains("Human rater 1 score") || line.equals("")) {
				// System.out.println(line);
				continue;
			}
			System.out.println(line);
			String[] parts = line.split("\t");
			int id = Integer.parseInt(parts[0]);
			int id2 = id;
			if (parts[3].equals("SWITZERLAND")) {
				try {
					id = Integer.parseInt(parts[1]);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				try {
					id2 = Integer.parseInt(parts[2]);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			int independent1 = -1;
			int independent2 = -1;
			int integrated1 = -1;
			int integrated2 = -1;

			try {
				independent1 = Integer.parseInt(parts[8]);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			try {
				independent2 = Integer.parseInt(parts[16]);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			try {
				integrated1 = Integer.parseInt(parts[12]);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			try {
				integrated2 = Integer.parseInt(parts[20]);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			if (parts[4].equals("TV_Ads")) {
				scorePerId_AD.put(id, independent1);
			} else if (parts[4].equals("Teachers")) {
				scorePerId_TE.put(id, independent1);
			} else {
				System.err.println("Unknown task " + parts[4]);
			}

			if (parts[6].equals("TV_Ads")) {
				scorePerId_AD.put(id2, independent2);
			} else if (parts[6].equals("Teachers")) {
				scorePerId_TE.put(id2, independent2);
			} else {
				System.err.println("Unknown task " + parts[6]);
			}

			if (parts[5].equals("Chevalier")) {
				scorePerId_CH.put(id, integrated1);
			} else if (parts[5].equals("Voting_Machines")) {
				scorePerId_VO.put(id, integrated1);
			} else {
				System.err.println("Unknown task " + parts[5]);
			}

			if (parts[7].equals("Chevalier")) {
				scorePerId_CH.put(id2, integrated2);
			} else if (parts[7].equals("Voting_Machines")) {
				scorePerId_VO.put(id2, integrated2);
			} else {
				System.err.println("Unknown task " + parts[7]);
			}

			// System.out.println(parts.length+ " "+id+" "+independent1+" "
			// +integrated1+" "+independent2+" "+integrated2);
			if (id == 174071) {
				break;
			}
		}
	}

	public boolean hasNext() throws IOException, CollectionException {
		return !items.isEmpty();
	}

	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(currentIndex, currentIndex, Progress.ENTITIES) };
	}

	@Override
	public void getNext(JCas jcas) throws IOException, CollectionException {
		MewsItem item = items.poll();
		getLogger().debug(item);
		try {
			jcas.setDocumentLanguage(language);
			jcas.setDocumentText(item.getText());
			DocumentMetaData dmd = DocumentMetaData.create(jcas);
			dmd.setDocumentId(item.getId());
			dmd.setDocumentTitle(item.getText());
			dmd.setDocumentUri(inputFileURL.toURI().toString());
			dmd.setCollectionId(item.getId());
		}

		catch (URISyntaxException e) {
			throw new CollectionException(e);
		}

		LearnerAnswer learnerAnswer = new LearnerAnswer(jcas, 0, jcas.getDocumentText().length());
		learnerAnswer.setPromptId("-1");
		learnerAnswer.addToIndexes();

		TextClassificationTarget unit = new TextClassificationTarget(jcas, 0, jcas.getDocumentText().length());
		// will add the token content as a suffix to the ID of this unit
		unit.setSuffix(item.getId());
		unit.addToIndexes();
		TextClassificationOutcome outcome = new TextClassificationOutcome(jcas, 0, jcas.getDocumentText().length());
		// TODO
		outcome.setOutcome(String.valueOf(item.getScore()));
		outcome.addToIndexes();
		currentIndex++;
	}

}
