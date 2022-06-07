

/* First created by JCasGen Sat Jun 04 01:35:38 CEST 2022 */
package de.fernunihagen.d2l2.coherence.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Jun 05 21:44:22 CEST 2022
 * XML source: C:/Users/ENVY/git/de.fernunihagen.d2l2.coherence/de.fernunihagen.d2l2.coherence/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class CoreferenceEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CoreferenceEntity.class);
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
  protected CoreferenceEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public CoreferenceEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public CoreferenceEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public CoreferenceEntity(JCas jcas, int begin, int end) {
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
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public int getId() {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(int v) {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: beginPosition

  /** getter for beginPosition - gets 
   * @generated
   * @return value of the feature 
   */
  public int getBeginPosition() {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_beginPosition == null)
      jcasType.jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_beginPosition);}
    
  /** setter for beginPosition - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBeginPosition(int v) {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_beginPosition == null)
      jcasType.jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_beginPosition, v);}    
   
    
  //*--------------*
  //* Feature: endPosition

  /** getter for endPosition - gets 
   * @generated
   * @return value of the feature 
   */
  public int getEndPosition() {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_endPosition == null)
      jcasType.jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_endPosition);}
    
  /** setter for endPosition - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEndPosition(int v) {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_endPosition == null)
      jcasType.jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_endPosition, v);}    
   
    
  //*--------------*
  //* Feature: firstMention

  /** getter for firstMention - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFirstMention() {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_firstMention == null)
      jcasType.jcas.throwFeatMissing("firstMention", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_firstMention);}
    
  /** setter for firstMention - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFirstMention(String v) {
    if (CoreferenceEntity_Type.featOkTst && ((CoreferenceEntity_Type)jcasType).casFeat_firstMention == null)
      jcasType.jcas.throwFeatMissing("firstMention", "de.fernunihagen.d2l2.coherence.types.CoreferenceEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((CoreferenceEntity_Type)jcasType).casFeatCode_firstMention, v);}    
  }

    