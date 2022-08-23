
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
public class Cf_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Cf.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("webanno.custom.Cf");
 
  /** @generated */
  final Feature casFeat_order;
  /** @generated */
  final int     casFeatCode_order;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getOrder(int addr) {
        if (featOkTst && casFeat_order == null)
      jcas.throwFeatMissing("order", "webanno.custom.Cf");
    return ll_cas.ll_getIntValue(addr, casFeatCode_order);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOrder(int addr, int v) {
        if (featOkTst && casFeat_order == null)
      jcas.throwFeatMissing("order", "webanno.custom.Cf");
    ll_cas.ll_setIntValue(addr, casFeatCode_order, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Cf_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_order = jcas.getRequiredFeatureDE(casType, "order", "uima.cas.Integer", featOkTst);
    casFeatCode_order  = (null == casFeat_order) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_order).getCode();

  }
}



    