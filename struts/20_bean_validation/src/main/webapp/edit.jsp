<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Struts 2 Bean Validation - Welcome</title>
  </head>
  <body>
    <h1>Update Information</h1>
    <p>Use the form below to edit your information.</p>
    <s:form action="save" method="post">
      <s:textfield key="personBean.firstName"/>
      <s:textfield key="personBean.lastName"/>
      <s:textfield key="personBean.email"/>
      <s:textfield key="personBean.phoneNumber"/>
      <s:select key="personBean.sport" list="sports"/>
      <s:radio key="personBean.gender" list="genders"/>
      <s:select key="personBean.residency" list="states" listKey="stateAbbr" listValue="stateName"/>
      <s:checkbox key="personBean.over21"/>
      <s:checkboxlist key="personBean.carModels" list="carModelsAvailable"/>
      <s:submit key="submit"/>
    </s:form>
  </body>
</html>