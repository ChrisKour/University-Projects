
/**
 * CalculatorCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package calculator;

    /**
     *  CalculatorCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CalculatorCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CalculatorCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CalculatorCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for elegxosArithmitikou method
            * override this method for handling normal response from elegxosArithmitikou operation
            */
           public void receiveResultelegxosArithmitikou(
                    calculator.CalculatorStub.ElegxosArithmitikouResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from elegxosArithmitikou operation
           */
            public void receiveErrorelegxosArithmitikou(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for elegxosPrakshs method
            * override this method for handling normal response from elegxosPrakshs operation
            */
           public void receiveResultelegxosPrakshs(
                    calculator.CalculatorStub.ElegxosPrakshsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from elegxosPrakshs operation
           */
            public void receiveErrorelegxosPrakshs(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for calculation method
            * override this method for handling normal response from calculation operation
            */
           public void receiveResultcalculation(
                    calculator.CalculatorStub.CalculationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from calculation operation
           */
            public void receiveErrorcalculation(java.lang.Exception e) {
            }
                


    }
    