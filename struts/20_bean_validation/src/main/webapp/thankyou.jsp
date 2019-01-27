<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Struts 2 Bean Validation - Update Successful!</title>
  </head>
  <body>
    <h1>Updated Information</h1>
    <pre>
      <s:property value="personBean"/></pre>
    <p><a href="<s:url action='index'/>">Return to home page</a></p>
  </body>
</html>