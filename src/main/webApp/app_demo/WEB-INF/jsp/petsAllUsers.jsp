<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://onlineShelterPet.com/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<script>
    $(document).ready(function() {
        $('#tableBootStapUserAllPets').DataTable();
    } );
</script>
<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="pet.title"/></h3>

        <div class="row">
            <div class="col-sm-7">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form class="form-horizontal" id="filter">
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="startDate"><spring:message
                                        code="pet.startDate"/>:</label>

                                <div class="col-sm-4">
                                    <input class="form-control" type="date" name="startDate" id="startDate">
                                </div>

                                <label class="control-label col-sm-3" for="startTime"><spring:message
                                        code="pet.startTime"/>:</label>

                                <div class="col-sm-3">
                                    <input class="form-control" type="time" name="startTime" id="startTime">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="endDate"><spring:message
                                        code="pet.endDate"/>:</label>

                                <div class="col-sm-4">
                                    <input class="form-control" type="date" name="endDate" id="endDate">
                                </div>

                                <label class="control-label col-sm-3" for="endTime"><spring:message
                                        code="pet.endTime"/>:</label>

                                <div class="col-sm-3">
                                    <input class="form-control" type="time" name="endTime" id="endTime">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer text-right">
                        <a class="btn btn-danger" type="button" onclick="clearFilter()">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </a>
                        <a class="btn btn-primary" type="button" onclick="updateTable()">
                            <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
       <%-- <table class="table table-striped display" id="dataTable">--%>
        <table id="tableBootStapUserAllPets" class="table table-striped table-bordered" cellspacing="0" width="100%">
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
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${petsAllUsers}" var="pet">
                <jsp:useBean id="pet" scope="page" type="app_demo.spring_mvc.model.Pet"/>
                <tr>
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<a href="myPets">myPets</a><br>
<a href="users">users</a><br>
<a href="pets/p">pets/p</a><br>
<a href="pets/create">pets/create</a><br>
<a href="ajax/profile/pets">/ajax/profile/pets</a><br>
<a href="rest/profile/pets">rest/profile/pets</a><br>
<a href="ajax/admin/users">ajax/admin/users</a><br>
<a href="ajax/admin/users/100000">ajax/admin/users/100000</a><br>
<a href="rest/admin/users">rest/admin/users</a><br>
<a href="rest/admin/users/100000">rest/admin/users/100000</a><br>
<a href="rest/profile">/rest/profile</a><br>


<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>