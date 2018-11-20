<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleMyWebServiceProxyid" scope="session" class="com.mcda5510.service.MyWebServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleMyWebServiceProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleMyWebServiceProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleMyWebServiceProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        com.mcda5510.service.MyWebService getMyWebService10mtemp = sampleMyWebServiceProxyid.getMyWebService();
if(getMyWebService10mtemp == null){
%>
<%=getMyWebService10mtemp %>
<%
}else{
        if(getMyWebService10mtemp!= null){
        String tempreturnp11 = getMyWebService10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String ID_1id=  request.getParameter("ID16");
        int ID_1idTemp  = Integer.parseInt(ID_1id);
        java.lang.String getTransaction13mtemp = sampleMyWebServiceProxyid.getTransaction(ID_1idTemp);
if(getTransaction13mtemp == null){
%>
<%=getTransaction13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getTransaction13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
case 18:
        gotMethod = true;
        String ID_2id=  request.getParameter("ID21");
        int ID_2idTemp  = Integer.parseInt(ID_2id);
        String nameOnCard_3id=  request.getParameter("nameOnCard23");
            java.lang.String nameOnCard_3idTemp = null;
        if(!nameOnCard_3id.equals("")){
         nameOnCard_3idTemp  = nameOnCard_3id;
        }
        String cardNumber_4id=  request.getParameter("cardNumber25");
            java.lang.String cardNumber_4idTemp = null;
        if(!cardNumber_4id.equals("")){
         cardNumber_4idTemp  = cardNumber_4id;
        }
        String unitPrice_5id=  request.getParameter("unitPrice27");
        double unitPrice_5idTemp  = Double.parseDouble(unitPrice_5id);
        String quantity_6id=  request.getParameter("quantity29");
        int quantity_6idTemp  = Integer.parseInt(quantity_6id);
        String expDate_7id=  request.getParameter("expDate31");
            java.lang.String expDate_7idTemp = null;
        if(!expDate_7id.equals("")){
         expDate_7idTemp  = expDate_7id;
        }
        java.lang.String createTransaction18mtemp = sampleMyWebServiceProxyid.createTransaction(ID_2idTemp,nameOnCard_3idTemp,cardNumber_4idTemp,unitPrice_5idTemp,quantity_6idTemp,expDate_7idTemp);
if(createTransaction18mtemp == null){
%>
<%=createTransaction18mtemp %>
<%
}else{
        String tempResultreturnp19 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createTransaction18mtemp));
        %>
        <%= tempResultreturnp19 %>
        <%
}
break;
case 33:
        gotMethod = true;
        String ID_8id=  request.getParameter("ID36");
        int ID_8idTemp  = Integer.parseInt(ID_8id);
        String nameOnCard_9id=  request.getParameter("nameOnCard38");
            java.lang.String nameOnCard_9idTemp = null;
        if(!nameOnCard_9id.equals("")){
         nameOnCard_9idTemp  = nameOnCard_9id;
        }
        java.lang.String updateTransaction33mtemp = sampleMyWebServiceProxyid.updateTransaction(ID_8idTemp,nameOnCard_9idTemp);
if(updateTransaction33mtemp == null){
%>
<%=updateTransaction33mtemp %>
<%
}else{
        String tempResultreturnp34 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateTransaction33mtemp));
        %>
        <%= tempResultreturnp34 %>
        <%
}
break;
case 40:
        gotMethod = true;
        String ID_10id=  request.getParameter("ID43");
        int ID_10idTemp  = Integer.parseInt(ID_10id);
        java.lang.String removeTransaction40mtemp = sampleMyWebServiceProxyid.removeTransaction(ID_10idTemp);
if(removeTransaction40mtemp == null){
%>
<%=removeTransaction40mtemp %>
<%
}else{
        String tempResultreturnp41 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(removeTransaction40mtemp));
        %>
        <%= tempResultreturnp41 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>