
/* First created by JCasGen Fri Aug 19 18:15:33 CEST 2022 */
package webanno.custom;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Aug 22 12:47:14 CEST 2022
 * @generated */
public class Transition_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Transition.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("webanno.custom.Transition");
 
  /** @generated */
  final Feature casFeat_Continue;
  /** @generated */
  final int     casFeatCode_Continue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getContinue(int addr) {
        if (featOkTst && casFeat_Continue == null)
      jcas.throwFeatMissing("Continue", "webanno.custom.Transition");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_Continue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContinue(int addr, boolean v) {
        if (featOkTst && casFeat_Continue == null)
      jcas.throwFeatMissing("Continue", "webanno.custom.Transition");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_Continue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Retain;
  /** @generated */
  final int     casFeatCode_Retain;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getRetain(int addr) {
        if (featOkTst && casFeat_Retain == null)
      jcas.throwFeatMissing("Retain", "webanno.custom.Transition");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_Retain);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRetain(int addr, boolean v) {
        if (featOkTst && casFeat_Retain == null)
      jcas.throwFeatMissing("Retain", "webanno.custom.Transition");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_Retain, v);}
    
  
 
  /** @generated */
  final Feature casFeat_RoughShift;
  /** @generated */
  final int     casFeatCode_RoughShift;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getRoughShift(int addr) {
        if (featOkTst && casFeat_RoughShift == null)
      jcas.throwFeatMissing("RoughShift", "webanno.custom.Transition");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_RoughShift);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRoughShift(int addr, boolean v) {
        if (featOkTst && casFeat_RoughShift == null)
      jcas.throwFeatMissing("RoughShift", "webanno.custom.Transition");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_RoughShift, v);}
    
  
 
  /** @generated */
  final Feature casFeat_SmoothShift;
  /** @generated */
  final int     casFeatCode_SmoothShift;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getSmoothShift(int addr) {
        if (featOkTst && casFeat_SmoothShift == null)
      jcas.throwFeatMissing("SmoothShift", "webanno.custom.Transition");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_SmoothShift);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSmoothShift(int addr, boolean v) {
        if (featOkTst && casFeat_SmoothShift == null)
      jcas.throwFeatMissing("SmoothShift", "webanno.custom.Transition");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_SmoothShift, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Transition_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Continue = jcas.getRequiredFeatureDE(casType, "Continue", "uima.cas.Boolean", featOkTst);
    casFeatCode_Continue  = (null == casFeat_Continue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Continue).getCode();

 
    casFeat_Retain = jcas.getRequiredFeatureDE(casType, "Retain", "uima.cas.Boolean", featOkTst);
    casFeatCode_Retain  = (null == casFeat_Retain) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Retain).getCode();

 
    casFeat_RoughShift = jcas.getRequiredFeatureDE(casType, "RoughShift", "uima.cas.Boolean", featOkTst);
    casFeatCode_RoughShift  = (null == casFeat_RoughShift) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_RoughShift).getCode();

 
    casFeat_SmoothShift = jcas.getRequiredFeatureDE(casType, "SmoothShift", "uima.cas.Boolean", featOkTst);
    casFeatCode_SmoothShift  = (null == casFeat_SmoothShift) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SmoothShift).getCode();

  }
}



    