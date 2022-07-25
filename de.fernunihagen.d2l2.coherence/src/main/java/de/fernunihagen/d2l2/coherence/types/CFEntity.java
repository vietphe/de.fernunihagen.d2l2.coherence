

/* First created by JCasGen Fri Jun 10 16:49:17 CEST 2022 */
package de.fernunihagen.d2l2.coherence.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Jul 18 09:50:35 CEST 2022
 * XML source: C:/Users/ENVY/git/de.fernunihagen.d2l2.coherence/de.fernunihagen.d2l2.coherence/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class CFEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CFEntity.class);
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
  protected CFEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public CFEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public CFEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public CFEntity(JCas jcas, int begin, int end) {
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
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CFEntity_Type)jcasType).casFeatCode_sentenceIndex);}
    
  /** setter for sentenceIndex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceIndex(int v) {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((CFEntity_Type)jcasType).casFeatCode_sentenceIndex, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CFEntity_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((CFEntity_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: beginPosition

  /** getter for beginPosition - gets 
   * @generated
   * @return value of the feature 
   */
  public int getBeginPosition() {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_beginPosition == null)
      jcasType.jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CFEntity_Type)jcasType).casFeatCode_beginPosition);}
    
  /** setter for beginPosition - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBeginPosition(int v) {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_beginPosition == null)
      jcasType.jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((CFEntity_Type)jcasType).casFeatCode_beginPosition, v);}    
   
    
  //*--------------*
  //* Feature: endPosition

  /** getter for endPosition - gets 
   * @generated
   * @return value of the feature 
   */
  public int getEndPosition() {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_endPosition == null)
      jcasType.jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CFEntity_Type)jcasType).casFeatCode_endPosition);}
    
  /** setter for endPosition - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEndPosition(int v) {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_endPosition == null)
      jcasType.jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((CFEntity_Type)jcasType).casFeatCode_endPosition, v);}    
   
    
  //*--------------*
  //* Feature: dependencyType

  /** getter for dependencyType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDependencyType() {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_dependencyType == null)
      jcasType.jcas.throwFeatMissing("dependencyType", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CFEntity_Type)jcasType).casFeatCode_dependencyType);}
    
  /** setter for dependencyType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDependencyType(String v) {
    if (CFEntity_Type.featOkTst && ((CFEntity_Type)jcasType).casFeat_dependencyType == null)
      jcasType.jcas.throwFeatMissing("dependencyType", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((CFEntity_Type)jcasType).casFeatCode_dependencyType, v);}    
  }

    