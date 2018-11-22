/**
 * MyWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcda5510.service;

public interface MyWebService extends java.rmi.Remote {
    public java.lang.String getTransaction(int ID) throws java.rmi.RemoteException;
    public java.lang.String createTransaction(int ID, java.lang.String nameOnCard, java.lang.String cardNumber, double unitPrice, int quantity, java.lang.String expDate) throws java.rmi.RemoteException;
    public java.lang.String updateTransaction(int ID, java.lang.String nameOnCard, java.lang.String cardNumber, double unitPrice, int quantity, java.lang.String expDate) throws java.rmi.RemoteException;
    public java.lang.String removeTransaction(int ID) throws java.rmi.RemoteException;
}
