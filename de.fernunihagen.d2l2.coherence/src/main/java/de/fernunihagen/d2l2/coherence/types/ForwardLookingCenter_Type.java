
/* First created by JCasGen Sun Jun 05 21:44:22 CEST 2022 */
package de.fernunihagen.d2l2.coherence.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sun Jun 05 21:44:22 CEST 2022
 * @generated */
public class ForwardLookingCenter_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ForwardLookingCenter.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
 
  /** @generated */
  final Feature casFeat_sentenceIndex;
  /** @generated */
  final int     casFeatCode_sentenceIndex;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentenceIndex(int addr) {
        if (featOkTst && casFeat_sentenceIndex == null)
      jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    return ll_cas.ll_getIntValue(addr, casFeatCode_sentenceIndex);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceIndex(int addr, int v) {
        if (featOkTst && casFeat_sentenceIndex == null)
      jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    ll_cas.ll_setIntValue(addr, casFeatCode_sentenceIndex, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cf;
  /** @generated */
  final int     casFeatCode_cf;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCf(int addr) {
        if (featOkTst && casFeat_cf == null)
      jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    return ll_cas.ll_getRefValue(addr, casFeatCode_cf);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCf(int addr, int v) {
        if (featOkTst && casFeat_cf == null)
      jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    ll_cas.ll_setRefValue(addr, casFeatCode_cf, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getCf(int addr, int i) {
        if (featOkTst && casFeat_cf == null)
      jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cf), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_cf), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cf), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setCf(int addr, int i, String v) {
        if (featOkTst && casFeat_cf == null)
      jcas.throwFeatMissing("cf", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cf), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_cf), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cf), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_cfWithCoref;
  /** @generated */
  final int     casFeatCode_cfWithCoref;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCfWithCoref(int addr) {
        if (featOkTst && casFeat_cfWithCoref == null)
      jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    return ll_cas.ll_getRefValue(addr, casFeatCode_cfWithCoref);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCfWithCoref(int addr, int v) {
        if (featOkTst && casFeat_cfWithCoref == null)
      jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    ll_cas.ll_setRefValue(addr, casFeatCode_cfWithCoref, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getCfWithCoref(int addr, int i) {
        if (featOkTst && casFeat_cfWithCoref == null)
      jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cfWithCoref), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_cfWithCoref), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cfWithCoref), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setCfWithCoref(int addr, int i, String v) {
        if (featOkTst && casFeat_cfWithCoref == null)
      jcas.throwFeatMissing("cfWithCoref", "de.fernunihagen.d2l2.coherence.types.ForwardLookingCenter");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cfWithCoref), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_cfWithCoref), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_cfWithCoref), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ForwardLookingCenter_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentenceIndex = jcas.getRequiredFeatureDE(casType, "sentenceIndex", "uima.cas.Integer", featOkTst);
    casFeatCode_sentenceIndex  = (null == casFeat_sentenceIndex) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceIndex).getCode();

 
    casFeat_cf = jcas.getRequiredFeatureDE(casType, "cf", "uima.cas.StringArray", featOkTst);
    casFeatCode_cf  = (null == casFeat_cf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cf).getCode();

 
    casFeat_cfWithCoref = jcas.getRequiredFeatureDE(casType, "cfWithCoref", "uima.cas.StringArray", featOkTst);
    casFeatCode_cfWithCoref  = (null == casFeat_cfWithCoref) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cfWithCoref).getCode();

  }
}



    