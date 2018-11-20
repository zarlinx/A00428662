package com.mcda5510.service;

public class MyWebServiceProxy implements com.mcda5510.service.MyWebService {
  private String _endpoint = null;
  private com.mcda5510.service.MyWebService myWebService = null;
  
  public MyWebServiceProxy() {
    _initMyWebServiceProxy();
  }
  
  public MyWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMyWebServiceProxy();
  }
  
  private void _initMyWebServiceProxy() {
    try {
      myWebService = (new com.mcda5510.service.MyWebServiceServiceLocator()).getMyWebService();
      if (myWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)myWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)myWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (myWebService != null)
      ((javax.xml.rpc.Stub)myWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mcda5510.service.MyWebService getMyWebService() {
    if (myWebService == null)
      _initMyWebServiceProxy();
    return myWebService;
  }
  
  public java.lang.String getTransaction(int ID) throws java.rmi.RemoteException{
    if (myWebService == null)
      _initMyWebServiceProxy();
    return myWebService.getTransaction(ID);
  }
  
  public java.lang.String createTransaction(int ID, java.lang.String nameOnCard, java.lang.String cardNumber, double unitPrice, int quantity, java.lang.String expDate) throws java.rmi.RemoteException{
    if (myWebService == null)
      _initMyWebServiceProxy();
    return myWebService.createTransaction(ID, nameOnCard, cardNumber, unitPrice, quantity, expDate);
  }
  
  public java.lang.String updateTransaction(int ID, java.lang.String nameOnCard) throws java.rmi.RemoteException{
    if (myWebService == null)
      _initMyWebServiceProxy();
    return myWebService.updateTransaction(ID, nameOnCard);
  }
  
  public java.lang.String removeTransaction(int ID) throws java.rmi.RemoteException{
    if (myWebService == null)
      _initMyWebServiceProxy();
    return myWebService.removeTransaction(ID);
  }
  
  
}