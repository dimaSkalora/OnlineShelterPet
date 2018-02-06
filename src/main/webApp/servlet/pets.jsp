<%@ page import="com.online.shelter.pet.servlet.util.DateTimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://onlineShelterPet.com/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<html>
<head>
    <title>Pet List</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a> </h3>
    <h2>Pets</h2>
    <form method="post" action="pets?action=filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt>From Time:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr/>
    <a href="pets?action=create">Add Pet</a>
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
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pets}" var="pet">
            <jsp:useBean id="pet" scope="page" type="com.online.shelter.pet.servlet.to.PetWithDownplayWeight"/>
            <tr class="${pet.downplayWeight ? 'reduced' : 'normal'}">
                <td>
                        <%--${pet.dateTime.toLocalDate()} ${pet.dateTime.toLocalTime()}--%>
                        <%--<%=DateTimeUtil.toString(pet.getCreatedDate())%>--%>
                                <%--${fn:replace(pet.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(pet.createdDate)}
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
                <td><a href="pets?action=update&id=${pet.id}">Update</a></td>
                <td><a href="pets?action=delete&id=${pet.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
</body>
</html>
