/**
 * MyWebServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcda5510.service;

public interface MyWebServiceService extends javax.xml.rpc.Service {
    public java.lang.String getMyWebServiceAddress();

    public com.mcda5510.service.MyWebService getMyWebService() throws javax.xml.rpc.ServiceException;

    public com.mcda5510.service.MyWebService getMyWebService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
