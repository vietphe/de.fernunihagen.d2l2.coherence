

/* First created by JCasGen Fri Aug 19 18:15:33 CEST 2022 */
package webanno.custom;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Aug 22 12:47:14 CEST 2022
 * XML source: C:/Users/ENVY/git/de.fernunihagen.d2l2.coherence/de.fernunihagen.d2l2.coherence/data/TypeSystemManuell.xml
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
  //* Feature: Continue

  /** getter for Continue - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getContinue() {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_Continue == null)
      jcasType.jcas.throwFeatMissing("Continue", "webanno.custom.Transition");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_Continue);}
    
  /** setter for Continue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContinue(boolean v) {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_Continue == null)
      jcasType.jcas.throwFeatMissing("Continue", "webanno.custom.Transition");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_Continue, v);}    
   
    
  //*--------------*
  //* Feature: Retain

  /** getter for Retain - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getRetain() {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_Retain == null)
      jcasType.jcas.throwFeatMissing("Retain", "webanno.custom.Transition");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_Retain);}
    
  /** setter for Retain - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRetain(boolean v) {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_Retain == null)
      jcasType.jcas.throwFeatMissing("Retain", "webanno.custom.Transition");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_Retain, v);}    
   
    
  //*--------------*
  //* Feature: RoughShift

  /** getter for RoughShift - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getRoughShift() {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_RoughShift == null)
      jcasType.jcas.throwFeatMissing("RoughShift", "webanno.custom.Transition");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_RoughShift);}
    
  /** setter for RoughShift - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRoughShift(boolean v) {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_RoughShift == null)
      jcasType.jcas.throwFeatMissing("RoughShift", "webanno.custom.Transition");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_RoughShift, v);}    
   
    
  //*--------------*
  //* Feature: SmoothShift

  /** getter for SmoothShift - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getSmoothShift() {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_SmoothShift == null)
      jcasType.jcas.throwFeatMissing("SmoothShift", "webanno.custom.Transition");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_SmoothShift);}
    
  /** setter for SmoothShift - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSmoothShift(boolean v) {
    if (Transition_Type.featOkTst && ((Transition_Type)jcasType).casFeat_SmoothShift == null)
      jcasType.jcas.throwFeatMissing("SmoothShift", "webanno.custom.Transition");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Transition_Type)jcasType).casFeatCode_SmoothShift, v);}    
  }

    