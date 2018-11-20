/**
 * MyWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcda5510.service;

public class MyWebServiceServiceLocator extends org.apache.axis.client.Service implements com.mcda5510.service.MyWebServiceService {

    public MyWebServiceServiceLocator() {
    }


    public MyWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MyWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MyWebService
    private java.lang.String MyWebService_address = "http://localhost:8080/Assignment3beta/services/MyWebService";

    public java.lang.String getMyWebServiceAddress() {
        return MyWebService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MyWebServiceWSDDServiceName = "MyWebService";

    public java.lang.String getMyWebServiceWSDDServiceName() {
        return MyWebServiceWSDDServiceName;
    }

    public void setMyWebServiceWSDDServiceName(java.lang.String name) {
        MyWebServiceWSDDServiceName = name;
    }

    public com.mcda5510.service.MyWebService getMyWebService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MyWebService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMyWebService(endpoint);
    }

    public com.mcda5510.service.MyWebService getMyWebService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.mcda5510.service.MyWebServiceSoapBindingStub _stub = new com.mcda5510.service.MyWebServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getMyWebServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMyWebServiceEndpointAddress(java.lang.String address) {
        MyWebService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.mcda5510.service.MyWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.mcda5510.service.MyWebServiceSoapBindingStub _stub = new com.mcda5510.service.MyWebServiceSoapBindingStub(new java.net.URL(MyWebService_address), this);
                _stub.setPortName(getMyWebServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MyWebService".equals(inputPortName)) {
            return getMyWebService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.mcda5510.com", "MyWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.mcda5510.com", "MyWebService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MyWebService".equals(portName)) {
            setMyWebServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
