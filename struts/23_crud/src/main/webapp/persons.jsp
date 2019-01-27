<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Struts 2 CRUD Example - Welcome</title>
  </head>
  <body>
    <div class="titleDiv">
      <s:text name="application.title"/>
    </div>
    <h1>
      <s:text name="label.persons"/>
    </h1>
    <s:url var="url" action="inputPerson"/><a href="${url}">Add new Person</a><br/><br/>
    <table class="borderAll">
      <tr>
        <th>
          <s:text name="person.firstName"/>
        </th>
        <th>
          <s:text name="person.lastName"/>
        </th>
        <th>
          <s:text name="person.email"/>
        </th>
        <th>
          <s:text name="person.phoneNumber"/>
        </th>
        <th>
          <s:text name="person.sport"/>
        </th>
        <th>
          <s:text name="person.gender"/>
        </th>
        <th>
          <s:text name="person.country"/>
        </th>
        <th>
          <s:text name="person.over21"/>
        </th>
        <th>
          <s:text name="person.carModels"/>
        </th>
      </tr>
      <s:iterator value="persons" status="status">
        <tr class="<s:if test='#status.even'>even</s:if><s:else>odd</s:else>">
          <td class="nowrap">
            <s:property value="firstName"/>
          </td>
          <td class="nowrap">
            <s:property value="lastName"/>
          </td>
          <td class="nowrap">
            <s:property value="email"/>
          </td>
          <td class="nowrap">
            <s:property value="phoneNumber"/>
          </td>
          <td class="nowrap">
            <s:property value="sport"/>
          </td>
          <td class="nowrap">
            <s:property value="gender"/>
          </td>
          <td class="nowrap">
            <s:property value="country.countryName"/>
          </td>
          <td class="nowrap">
            <s:if test="over21"><span style="color:green;font-size:large;">&#x2714;</span></s:if>
            <s:else><span style="color:red;font-size:large;">&#x2717;</span></s:else>
          </td>
          <td class="nowrap">
            <s:property value="carModels"/>
          </td>
          <td class="nowrap">
                        <s:url action="inputPerson" var="url">
                          <s:param name="person.personId" value="personId"/>
                        </s:url><a href="${url}">Edit</a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deletePerson" var="url">
                          <s:param name="person.personId" value="personId"/>
                        </s:url><a href="${url}">Delete</a>
          </td>
        </tr>
      </s:iterator>
    </table>
  </body>
</html>