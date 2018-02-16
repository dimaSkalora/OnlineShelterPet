<%@ taglib prefix="fn" uri="http://onlineShelterPet.com/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><spring:message code="pet.title"/></h3>

    <form method="post" action="pets/filter">
        <dl>
            <dt><spring:message code="pet.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="pet.filter"/></button>
    </form>
    <hr/>
    <a href="pets/create"><spring:message code="pet.add"/></a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="pet.createdDate"/></th>
            <th><spring:message code="pet.typePet"/></th>
            <th><spring:message code="pet.namePet"/></th>
            <th><spring:message code="pet.breed"/></th>
            <th><spring:message code="pet.sex"/></th>
            <th><spring:message code="pet.color"/></th>
            <th><spring:message code="pet.age"/></th>
            <th><spring:message code="pet.growth"/></th>
            <th><spring:message code="pet.weight"/></th>
            <th><spring:message code="pet.namePerson"/></th>
            <th><spring:message code="pet.phone"/></th>
            <th><spring:message code="pet.email"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pets}" var="pet">
            <jsp:useBean id="pet" scope="page" type="com.online.shelter.pet.spring_mvc.to.PetWithDownplayWeight"/>
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
                <td><a href="pets/update?id=${pet.id}"><spring:message code="common.update"/></a></td>
                <td><a href="pets/delete?id=${pet.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
