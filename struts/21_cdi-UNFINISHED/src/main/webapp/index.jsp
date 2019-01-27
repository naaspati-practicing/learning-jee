<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
  </head>
  <body>
    <h2>Numberguess Game - Struts 2 CDI Example</h2>
    <s:form>
      <s:textfield value="game.x" label="X     "/>
      <s:textfield value="game.y" label="Y     "/>
      <s:textfield value="game.sum" label="X+Y = "/>
      <s:submit/>
    </s:form>
    <s:actionerror/>
  </body>
</html>