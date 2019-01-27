<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Basic Struts 2 Application - Welcome</title>
  </head>
  <body>
    <h1>Welcome To Struts 2!</h1>
    <p><a href="<s:url action='hello'/>">Hello World</a></p>
    <s:url action="hello" var="helloLink">
      <s:param name="userName">Bruce Phillips</s:param>
    </s:url>
    <p><a href="${helloLink}">Hello Bruce Phillips</a></p>
    <p>Get your own personal hello by filling out and submitting this form.</p>
    <s:form action="hello">
      <s:textfield value="game.x" label="%{'Your Name'}"/>
      <s:submit value="%{'Submit'}"/>
    </s:form>
  </body>
</html>