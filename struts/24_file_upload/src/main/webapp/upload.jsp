<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>File upload</title>
  </head>
  <body>
    <s:form action="upload" method="post" enctype="multipart/form-data">
      <s:file name="upload"/>
      <s:file name="upload"/>
      <s:file name="upload"/>
      <s:submit/>
    </s:form>
    <s:iterator value="upload" var="u">
      <s:property value="u"/>
    </s:iterator><br/>
    <s:iterator value="uploadFileName" var="u">
      <s:property value="u"/>
    </s:iterator><br/>
    <s:iterator value="uploadContentType" var="u">
      <s:property value="u"/>
    </s:iterator><br/>
  </body>
</html>