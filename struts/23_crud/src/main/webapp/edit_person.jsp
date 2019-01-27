<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>
      <s:property value="#title"/>
    </title>
  </head>
  <body>
    <div class="titleDiv">
      <s:text name="application.title"/>
    </div>
    <h1>
      <s:property value="#title"/>
    </h1>
    <s:actionerror/>
    <s:actionmessage/>
    <s:form action="savePerson" method="post">
            <s:textfield key="person.firstName"/>
            <s:textfield key="person.lastName"/>
            <s:textfield key="person.email"/>
            <s:textfield key="person.phoneNumber"/>
      <s:select key="person.sport" list="sports"/>
      <s:radio key="person.gender" list="genders"/>
      <s:select name="person.country.countryId" list="countries" listKey="countryId" listValue="countryName" label="%{getText('person.country')}"/>
      <s:checkbox key="person.over21"/>
      <s:checkboxlist key="person.carModels" list="carModelsAvailable"/>
      <s:hidden name="person.personId" value="%{person.personId}"/>
      <s:submit value="%{getText('button.label.submit')}"/>
      <s:submit value="%{getText('button.label.cancel')}" action="index"/>
    </s:form>
  </body>
</html>