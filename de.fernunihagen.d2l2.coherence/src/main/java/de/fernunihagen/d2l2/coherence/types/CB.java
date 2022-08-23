

/* First created by JCasGen Tue Aug 16 12:28:05 CEST 2022 */
package de.fernunihagen.d2l2.coherence.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** backward-looking center
 * Updated by JCasGen Tue Aug 16 12:28:05 CEST 2022
 * XML source: C:/Users/ENVY/git/de.fernunihagen.d2l2.coherence/de.fernunihagen.d2l2.coherence/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class CB extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CB.class);
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
  protected CB() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public CB(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public CB(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public CB(JCas jcas, int begin, int end) {
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
  //* Feature: sentenceIndex

  /** getter for sentenceIndex - gets 
   * @generated
   * @return value of the feature 
   */
  public int getSentenceIndex() {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.CB");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CB_Type)jcasType).casFeatCode_sentenceIndex);}
    
  /** setter for sentenceIndex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceIndex(int v) {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.CB");
    jcasType.ll_cas.ll_setIntValue(addr, ((CB_Type)jcasType).casFeatCode_sentenceIndex, v);}    
   
    
  //*--------------*
  //* Feature: beginPosition

  /** getter for beginPosition - gets 
   * @generated
   * @return value of the feature 
   */
  public int getBeginPosition() {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_beginPosition == null)
      jcasType.jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CB");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CB_Type)jcasType).casFeatCode_beginPosition);}
    
  /** setter for beginPosition - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBeginPosition(int v) {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_beginPosition == null)
      jcasType.jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CB");
    jcasType.ll_cas.ll_setIntValue(addr, ((CB_Type)jcasType).casFeatCode_beginPosition, v);}    
   
    
  //*--------------*
  //* Feature: endPosition

  /** getter for endPosition - gets 
   * @generated
   * @return value of the feature 
   */
  public int getEndPosition() {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_endPosition == null)
      jcasType.jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CB");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CB_Type)jcasType).casFeatCode_endPosition);}
    
  /** setter for endPosition - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEndPosition(int v) {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_endPosition == null)
      jcasType.jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CB");
    jcasType.ll_cas.ll_setIntValue(addr, ((CB_Type)jcasType).casFeatCode_endPosition, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CB");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CB_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CB");
    jcasType.ll_cas.ll_setStringValue(addr, ((CB_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: dependencyType

  /** getter for dependencyType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDependencyType() {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_dependencyType == null)
      jcasType.jcas.throwFeatMissing("dependencyType", "de.fernunihagen.d2l2.coherence.types.CB");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CB_Type)jcasType).casFeatCode_dependencyType);}
    
  /** setter for dependencyType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDependencyType(String v) {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_dependencyType == null)
      jcasType.jcas.throwFeatMissing("dependencyType", "de.fernunihagen.d2l2.coherence.types.CB");
    jcasType.ll_cas.ll_setStringValue(addr, ((CB_Type)jcasType).casFeatCode_dependencyType, v);}    
   
    
  //*--------------*
  //* Feature: corefId

  /** getter for corefId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCorefId() {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_corefId == null)
      jcasType.jcas.throwFeatMissing("corefId", "de.fernunihagen.d2l2.coherence.types.CB");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CB_Type)jcasType).casFeatCode_corefId);}
    
  /** setter for corefId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCorefId(String v) {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_corefId == null)
      jcasType.jcas.throwFeatMissing("corefId", "de.fernunihagen.d2l2.coherence.types.CB");
    jcasType.ll_cas.ll_setStringValue(addr, ((CB_Type)jcasType).casFeatCode_corefId, v);}    
   
    
  //*--------------*
  //* Feature: firstMention

  /** getter for firstMention - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFirstMention() {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_firstMention == null)
      jcasType.jcas.throwFeatMissing("firstMention", "de.fernunihagen.d2l2.coherence.types.CB");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CB_Type)jcasType).casFeatCode_firstMention);}
    
  /** setter for firstMention - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFirstMention(String v) {
    if (CB_Type.featOkTst && ((CB_Type)jcasType).casFeat_firstMention == null)
      jcasType.jcas.throwFeatMissing("firstMention", "de.fernunihagen.d2l2.coherence.types.CB");
    jcasType.ll_cas.ll_setStringValue(addr, ((CB_Type)jcasType).casFeatCode_firstMention, v);}    
  }

    