

/* First created by JCasGen Sun Jun 05 21:44:22 CEST 2022 */
package de.fernunihagen.d2l2.coherence.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Jun 05 21:44:22 CEST 2022
 * XML source: C:/Users/ENVY/git/de.fernunihagen.d2l2.coherence/de.fernunihagen.d2l2.coherence/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class ForwardLookingCenter extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ForwardLookingCenter.class);
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
  protected ForwardLookingCenter() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ForwardLookingCenter(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ForwardLookingCenter(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ForwardLookingCenter(JCas jcas, int begin, int end) {
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
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    return jcasType.ll_cas.ll_getIntValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_sentenceIndex);}
    
  /** setter for sentenceIndex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceIndex(int v) {
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    jcasType.ll_cas.ll_setIntValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_sentenceIndex, v);}    
   
    
  //*--------------*
  //* Feature: cf

  /** getter for cf - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getCf() {
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cf == null)
      jcasType.jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cf)));}
    
  /** setter for cf - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCf(StringArray v) {
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cf == null)
      jcasType.jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    jcasType.ll_cas.ll_setRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cf, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for cf - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getCf(int i) {
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cf == null)
      jcasType.jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cf), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cf), i);}

  /** indexed setter for cf - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setCf(int i, String v) { 
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cf == null)
      jcasType.jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cf), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cf), i, v);}
   
    
  //*--------------*
  //* Feature: cfWithCoref

  /** getter for cfWithCoref - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getCfWithCoref() {
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cfWithCoref == null)
      jcasType.jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cfWithCoref)));}
    
  /** setter for cfWithCoref - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCfWithCoref(StringArray v) {
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cfWithCoref == null)
      jcasType.jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    jcasType.ll_cas.ll_setRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cfWithCoref, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for cfWithCoref - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getCfWithCoref(int i) {
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cfWithCoref == null)
      jcasType.jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cfWithCoref), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cfWithCoref), i);}

  /** indexed setter for cfWithCoref - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setCfWithCoref(int i, String v) { 
    if (ForwardLookingCenter_Type.featOkTst && ((ForwardLookingCenter_Type)jcasType).casFeat_cfWithCoref == null)
      jcasType.jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cfWithCoref), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ForwardLookingCenter_Type)jcasType).casFeatCode_cfWithCoref), i, v);}
  }

    