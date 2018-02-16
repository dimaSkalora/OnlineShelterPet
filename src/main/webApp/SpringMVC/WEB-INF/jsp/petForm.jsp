<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <jsp:useBean id="pet" type="com.online.shelter.pet.spring_mvc.model.Pet" scope="request"/>
    <h3><spring:message code="${pet.isNew() ? 'pet.add' : 'pet.edit'}"/></h3>
    <hr>
    <form method="post" action="pets">
        <input type="hidden" name="id" value="${pet.id}">
        <dl>
            <dt><spring:message code="pet.createdDate"/>:</dt>
            <dd><input type="datetime-local" value="${pet.createdDate}" name = "createDate" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.typePet"/>:</dt>
            <dd><input type="text" value="${pet.typePet}" size="50" name = "typePet" required></dd>
           <%-- <dd><select name="typePet" required>
                <option>Cat</option>
                <option>Dog</option>
                <option>Others</option>
            </select></dd>--%>
        </dl>
        <dl>
            <dt><spring:message code="pet.namePet"/>:</dt>
            <dd><input type="text" value="${pet.namePet}"size="20" name="namePet" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.breed"/>:</dt>
            <dd><input type="text" value="${pet.breed}"size="15" name="breed" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.sex"/>:</dt>
            <dd><input type="text" value="${pet.sex}"size="1" name="sex" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.color"/>:</dt>
            <dd><input type="text" value="${pet.color}"size="15" name="color" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.age"/>:</dt>
            <dd><input type="step" value="${pet.age}" name="age" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.growth"/>:</dt>
            <dd><input type="number" value="${pet.growth}" name="growth" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.weight"/>:</dt>
            <dd><input type="step" value="${pet.weight}" name="weight" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.namePerson"/>:</dt>
            <dd><input type="text" value="${pet.namePerson}"  size="15" name="namePerson" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.phone"/>:</dt>
            <dd><input type="tel" value="${pet.phone}"  size="20" name="phone" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="pet.email"/>:</dt>
            <dd><input type="email" value="${pet.email}"  size="20" name="email" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
