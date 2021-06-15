

/* First created by JCasGen Thu May 20 14:40:01 CEST 2021 */
package de.unidue.ltl.escrito.core.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri May 21 18:45:18 CEST 2021
 * XML source: /Users/andrea/Documents/Programmierung/workspace_ASAS/de.uni-due.ltl.vocabularyprofile/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class VocabularyProfile extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(VocabularyProfile.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected VocabularyProfile() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public VocabularyProfile(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public VocabularyProfile(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public VocabularyProfile(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (VocabularyProfile_Type.featOkTst && ((VocabularyProfile_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.unidue.ltl.escrito.core.types.VocabularyProfile");
    return jcasType.ll_cas.ll_getStringValue(addr, ((VocabularyProfile_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (VocabularyProfile_Type.featOkTst && ((VocabularyProfile_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.unidue.ltl.escrito.core.types.VocabularyProfile");
    jcasType.ll_cas.ll_setStringValue(addr, ((VocabularyProfile_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: level

  /** getter for level - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLevel() {
    if (VocabularyProfile_Type.featOkTst && ((VocabularyProfile_Type)jcasType).casFeat_level == null)
      jcasType.jcas.throwFeatMissing("level", "de.unidue.ltl.escrito.core.types.VocabularyProfile");
    return jcasType.ll_cas.ll_getStringValue(addr, ((VocabularyProfile_Type)jcasType).casFeatCode_level);}
    
  /** setter for level - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLevel(String v) {
    if (VocabularyProfile_Type.featOkTst && ((VocabularyProfile_Type)jcasType).casFeat_level == null)
      jcasType.jcas.throwFeatMissing("level", "de.unidue.ltl.escrito.core.types.VocabularyProfile");
    jcasType.ll_cas.ll_setStringValue(addr, ((VocabularyProfile_Type)jcasType).casFeatCode_level, v);}    
  }

    