package de.unidue.ltl.vocabularyprofile2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

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

public class Toefl11Reader extends JCasCollectionReader_ImplBase
{

	protected static final String LANGUAGE = "en";

	public static final String PARAM_INPUT_PATH = "InputDir";
	@ConfigurationParameter(name = PARAM_INPUT_PATH, mandatory = true)
	protected String inputDirString;
	protected URL inputDirURL;

	public static final String PARAM_SCORE_FILE = "ScoreFile";
	@ConfigurationParameter(name = PARAM_SCORE_FILE, mandatory = true)
	protected String scoreFile;
	protected URL scoreFileURL;


	public static final String PARAM_ENCODING = "Encoding";
	@ConfigurationParameter(name = PARAM_ENCODING, mandatory = false, defaultValue = "UTF-8")
	protected String encoding;

	public static final String PARAM_SEPARATOR = "Separator";
	@ConfigurationParameter(name = PARAM_SEPARATOR, mandatory = false, defaultValue = ",")
	protected String separator;

	protected int currentIndex;    

	protected Queue<GenericItem> items;



	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException
	{
		items = new LinkedList<GenericItem>();

		try {
			scoreFileURL = ResourceUtils.resolveLocation(scoreFile, this, aContext);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							scoreFileURL.openStream(),
							encoding
							)
					);
			String nextLine;
			int student = 0;
			while ((nextLine = reader.readLine()) != null) {
				System.out.println(nextLine);
				// skip the header
				if (nextLine.startsWith("FileName,")) {
					nextLine = reader.readLine();
				}

				String[] nextItem = nextLine.split(separator);
				String file = nextItem[0];

				String studentId = String.valueOf(student);
				student++;
				String promptId   = nextItem[1];

				String fileName = inputDirString+file;
				String text = readEssay(fileName);
				//	System.out.println("text: "+text);


				String level = nextItem[3];
				int grade = 0;
				if (level.equals("high")){
					grade = 2;
				} else if (level.equals("medium")){
					grade = 1;
				}

				// HOTFIX for Issue 445 in DKPro Core
				text = text.replace("’", "'");

				GenericItem newItem = new GenericItem(studentId, promptId, text, grade);
				System.out.println(newItem.toString());
				if (!(text.trim().equals(""))){
					items.add(newItem);
				}
			}
			System.out.println("Read "+items.size()+" items.");
		}
		catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
		currentIndex = 0;
	}

	@Override
	public boolean hasNext()
			throws IOException 
	{
		return !items.isEmpty();
	}

	@Override
	public void getNext(JCas jcas)
			throws IOException, CollectionException
	{
		GenericItem item = items.poll();
		String itemId = item.getQuestionId()+"_"+item.getStudentId(); 

		jcas.setDocumentLanguage(LANGUAGE);
		jcas.setDocumentText(item.getText());

		DocumentMetaData dmd = DocumentMetaData.create(jcas);
		dmd.setDocumentId(itemId); 
		dmd.setDocumentTitle(item.getText());
		dmd.setCollectionId(itemId);



		TextClassificationTarget unit = new TextClassificationTarget(jcas, 0, jcas.getDocumentText().length());
		// will add the token content as a suffix to the ID of this unit 
		unit.setSuffix(itemId);
		unit.addToIndexes();

		TextClassificationOutcome outcome = new TextClassificationOutcome(jcas, 0, jcas.getDocumentText().length());
		outcome.setOutcome(Integer.toString(item.getGrade()));
		outcome.addToIndexes();

		currentIndex++;
	}



	@Override
	public Progress[] getProgress()
	{
		return new Progress[] { new ProgressImpl(currentIndex, currentIndex, Progress.ENTITIES) };
	}


	private String readEssay(String path) throws IOException, UnsupportedEncodingException {
		String text = "";
		try {
			BufferedReader readerEssay = new BufferedReader(
					new FileReader(path)
					);
			String essayLine = readerEssay.readLine();
			while (essayLine != null){
				text += essayLine+"\n";
				essayLine = readerEssay.readLine();
			}
			if(text.contains(",,")){
				text = text.replace(",,", "„");
			}
			if(text.contains("''")){
				text = text.replace("''", "“");
			}
			text = text.trim();
			readerEssay.close();
			//	System.out.println("read Essay: "+text);
		} catch (IOException e){
			System.err.println("Could not read file "+path+"\n"+e.getMessage());
		}
		return text;
	}


}
