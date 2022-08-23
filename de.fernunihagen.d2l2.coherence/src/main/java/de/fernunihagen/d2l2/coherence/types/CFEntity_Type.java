
/* First created by JCasGen Fri Jun 10 16:49:17 CEST 2022 */
package de.fernunihagen.d2l2.coherence.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Aug 16 12:28:05 CEST 2022
 * @generated */
public class CFEntity_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CFEntity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.fernunihagen.d2l2.coherence.types.CFEntity");
 
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
      jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return ll_cas.ll_getIntValue(addr, casFeatCode_sentenceIndex);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceIndex(int addr, int v) {
        if (featOkTst && casFeat_sentenceIndex == null)
      jcas.throwFeatMissing("sentenceIndex", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    ll_cas.ll_setIntValue(addr, casFeatCode_sentenceIndex, v);}
    
  
 
  /** @generated */
  final Feature casFeat_name;
  /** @generated */
  final int     casFeatCode_name;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getName(int addr) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_name);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setName(int addr, String v) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_name, v);}
    
  
 
  /** @generated */
  final Feature casFeat_beginPosition;
  /** @generated */
  final int     casFeatCode_beginPosition;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBeginPosition(int addr) {
        if (featOkTst && casFeat_beginPosition == null)
      jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return ll_cas.ll_getIntValue(addr, casFeatCode_beginPosition);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBeginPosition(int addr, int v) {
        if (featOkTst && casFeat_beginPosition == null)
      jcas.throwFeatMissing("beginPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    ll_cas.ll_setIntValue(addr, casFeatCode_beginPosition, v);}
    
  
 
  /** @generated */
  final Feature casFeat_endPosition;
  /** @generated */
  final int     casFeatCode_endPosition;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEndPosition(int addr) {
        if (featOkTst && casFeat_endPosition == null)
      jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return ll_cas.ll_getIntValue(addr, casFeatCode_endPosition);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEndPosition(int addr, int v) {
        if (featOkTst && casFeat_endPosition == null)
      jcas.throwFeatMissing("endPosition", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    ll_cas.ll_setIntValue(addr, casFeatCode_endPosition, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dependencyType;
  /** @generated */
  final int     casFeatCode_dependencyType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDependencyType(int addr) {
        if (featOkTst && casFeat_dependencyType == null)
      jcas.throwFeatMissing("dependencyType", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dependencyType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDependencyType(int addr, String v) {
        if (featOkTst && casFeat_dependencyType == null)
      jcas.throwFeatMissing("dependencyType", "de.fernunihagen.d2l2.coherence.types.CFEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_dependencyType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public CFEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentenceIndex = jcas.getRequiredFeatureDE(casType, "sentenceIndex", "uima.cas.Integer", featOkTst);
    casFeatCode_sentenceIndex  = (null == casFeat_sentenceIndex) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceIndex).getCode();

 
    casFeat_name = jcas.getRequiredFeatureDE(casType, "name", "uima.cas.String", featOkTst);
    casFeatCode_name  = (null == casFeat_name) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_name).getCode();

 
    casFeat_beginPosition = jcas.getRequiredFeatureDE(casType, "beginPosition", "uima.cas.Integer", featOkTst);
    casFeatCode_beginPosition  = (null == casFeat_beginPosition) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_beginPosition).getCode();

 
    casFeat_endPosition = jcas.getRequiredFeatureDE(casType, "endPosition", "uima.cas.Integer", featOkTst);
    casFeatCode_endPosition  = (null == casFeat_endPosition) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_endPosition).getCode();

 
    casFeat_dependencyType = jcas.getRequiredFeatureDE(casType, "dependencyType", "uima.cas.String", featOkTst);
    casFeatCode_dependencyType  = (null == casFeat_dependencyType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dependencyType).getCode();

  }
}



    