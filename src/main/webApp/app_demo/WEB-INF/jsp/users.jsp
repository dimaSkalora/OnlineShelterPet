<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<script>
    $(document).ready(function() {
        $('#tableBootStapUsers').DataTable();
    } );
</script>
<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="user.title"/></h3>
        <br/>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a>
        <table class="table table-striped display" id="tableBootStapUsers">
            <thead>
            <tr>
                <th><spring:message code="user.name"/></th>
                <th><spring:message code="user.email"/></th>
                <th><spring:message code="user.roles"/></th>
                <th><spring:message code="user.active"/></th>
                <th><spring:message code="user.registered"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="app_demo.spring_mvc.model.User"/>
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                    <td>${user.roles}</td>
                    <td><input type="checkbox"
                               <c:if test="${user.enabled}">checked</c:if> onclick="enable($(this), ${user.id})"/>
                    </td>
                    <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>