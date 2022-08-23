

/* First created by JCasGen Sat Jun 04 02:58:00 CEST 2022 */
package de.fernunihagen.d2l2.coherence.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Aug 16 12:28:05 CEST 2022
 * XML source: C:/Users/ENVY/git/de.fernunihagen.d2l2.coherence/de.fernunihagen.d2l2.coherence/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class Transition extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Transition.class);
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
  protected Transition() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Transition(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Transition(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Transition(JCas jcas, int begin, int end) {
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
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.Transition");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Transition_Type)jcasType).casFeatCode_sentenceIndex);}
    
  /** setter for sentenceIndex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceIndex(int v) {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_sentenceIndex == null)
      jcasType.jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.Transition");
    jcasType.ll_cas.ll_setIntValue(addr, ((Transition_Type)jcasType).casFeatCode_sentenceIndex, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.Transition");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Transition_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.Transition");
    jcasType.ll_cas.ll_setStringValue(addr, ((Transition_Type)jcasType).casFeatCode_name, v);}    
  }

    