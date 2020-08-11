<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.recipe.vo.Postal" %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    { "status" : "success",
      "data" : [
<%
   List<Postal> postals = (ArrayList<Postal>)request.getAttribute("postals");

   for(int i = 0; i < postals.size(); i++) { %> 
      {
         "zipCode" :  "<%=postals.get(i).getZipcode()%>",
         "city" : "<%=postals.get(i).getCity()%>",
         "doro" : "<%=postals.get(i).getDoro()%>",
         "building" : "<%=postals.get(i).getBuilding()%>",
         "buildingno" : "<%=postals.get(i).getBuildingno()%>"
      } <% if(i != postals.size()-1) %>, 
   <%}%> ]
   }