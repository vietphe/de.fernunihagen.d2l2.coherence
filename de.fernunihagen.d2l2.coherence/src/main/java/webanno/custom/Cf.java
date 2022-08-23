

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
public class Cf extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Cf.class);
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
  protected Cf() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Cf(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Cf(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Cf(JCas jcas, int begin, int end) {
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
  //* Feature: order

  /** getter for order - gets 
   * @generated
   * @return value of the feature 
   */
  public int getOrder() {
    if (Cf_Type.featOkTst && ((Cf_Type)jcasType).casFeat_order == null)
      jcasType.jcas.throwFeatMissing("order", "webanno.custom.Cf");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Cf_Type)jcasType).casFeatCode_order);}
    
  /** setter for order - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setOrder(int v) {
    if (Cf_Type.featOkTst && ((Cf_Type)jcasType).casFeat_order == null)
      jcasType.jcas.throwFeatMissing("order", "webanno.custom.Cf");
    jcasType.ll_cas.ll_setIntValue(addr, ((Cf_Type)jcasType).casFeatCode_order, v);}    
  }

    