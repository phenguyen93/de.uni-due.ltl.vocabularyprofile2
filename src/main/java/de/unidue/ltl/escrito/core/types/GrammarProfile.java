

/* First created by JCasGen Tue May 18 16:55:04 CEST 2021 */
package de.unidue.ltl.escrito.core.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue May 18 17:56:59 CEST 2021
 * XML source: /Users/andrea/Documents/Programmierung/workspace_ASAS/de.uni-due.ltl.grammarprofile/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class GrammarProfile extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GrammarProfile.class);
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
  protected GrammarProfile() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GrammarProfile(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GrammarProfile(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GrammarProfile(JCas jcas, int begin, int end) {
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
    if (GrammarProfile_Type.featOkTst && ((GrammarProfile_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.unidue.ltl.escrito.core.types.GrammarProfile");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GrammarProfile_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (GrammarProfile_Type.featOkTst && ((GrammarProfile_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.unidue.ltl.escrito.core.types.GrammarProfile");
    jcasType.ll_cas.ll_setStringValue(addr, ((GrammarProfile_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: level

  /** getter for level - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLevel() {
    if (GrammarProfile_Type.featOkTst && ((GrammarProfile_Type)jcasType).casFeat_level == null)
      jcasType.jcas.throwFeatMissing("level", "de.unidue.ltl.escrito.core.types.GrammarProfile");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GrammarProfile_Type)jcasType).casFeatCode_level);}
    
  /** setter for level - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLevel(String v) {
    if (GrammarProfile_Type.featOkTst && ((GrammarProfile_Type)jcasType).casFeat_level == null)
      jcasType.jcas.throwFeatMissing("level", "de.unidue.ltl.escrito.core.types.GrammarProfile");
    jcasType.ll_cas.ll_setStringValue(addr, ((GrammarProfile_Type)jcasType).casFeatCode_level, v);}    
  }

    