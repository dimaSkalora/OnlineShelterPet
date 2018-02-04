<%@ page import="com.online.shelter.pet.servlet.util.DateTimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://onlineShelterPet.com/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<html>
<head>
    <title>Pet List</title>
    <style>
        .normal{
            color: green;
        }
        .reduced{
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a> </h3>
    <h2>Pets</h2>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>TypePet</th>
            <th>NamePet</th>
            <th>Breed</th>
            <th>Sex</th>
            <th>Color</th>
            <th>Age</th>
            <th>Growth</th>
            <th>Weight</th>
            <th>NmePerson</th>
            <th>Phone</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pets}" var="pet">
            <jsp:useBean id="pet" scope="page" type="com.online.shelter.pet.servlet.model.PetWithDownplayWeight"/>
            <tr class="${pet.downplayWeight ? 'reduced' : 'normal'}">
                <td>
                        <%--<%=DateTimeUtil.toString(pet.getCreatedDate())%>--%>
                        ${fn:formatDate(pet.createdDate)}
                <%--    ${pet.createdDate}--%>
                </td>
                <td>${pet.typePet}</td>
                <td>${pet.namePet}</td>
                <td>${pet.breed}</td>
                <td>${pet.sex}</td>
                <td>${pet.color}</td>
                <td>${pet.age}</td>
                <td>${pet.growth}</td>
                <td>${pet.weight}</td>
                <td>${pet.namePerson}</td>
                <td>${pet.phone}</td>
                <td>${pet.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
</body>
</html>
