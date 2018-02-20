<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://onlineShelterPet.com/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/petDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

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
                        <a class="btn btn-primary" type="button" onclick="updateTable()">
                            <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a>
        <table class="table table-striped display" id="datatable">
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
                    <td><a><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                    <td><a onclick="deleteRow(${pet.id})">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"><spring:message code="pet.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="createdDate" class="control-label col-xs-3"><spring:message
                                code="pet.createdDate"/></label>

                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="createdDate" name="createdDate"
                                   placeholder="<spring:message code="pet.createdDate"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="typePet" class="control-label col-xs-3"><spring:message
                                code="pet.typePet"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="typePet" name="typePet"
                                   placeholder="<spring:message code="pet.typePet"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="namePet" class="control-label col-xs-3"><spring:message
                                code="pet.namePet"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="namePet" name="namePet"
                                    placeholder="<spring:message code="pet.namePet"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="breed" class="control-label col-xs-3"><spring:message
                                code="pet.breed"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="breed" name="breed"
                                   placeholder="<spring:message code="pet.breed"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sex" class="control-label col-xs-3"><spring:message
                                code="pet.sex"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="sex" name="sex"
                                   placeholder="<spring:message code="pet.sex"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="color" class="control-label col-xs-3"><spring:message
                                code="pet.color"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="color" name="color"
                                   placeholder="<spring:message code="pet.color"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="age" class="control-label col-xs-3"><spring:message
                                code="pet.age"/></label>

                        <div class="col-xs-9">
                            <input type="step" class="form-control" id="age" name="age"
                                   placeholder="<spring:message code="pet.age"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="growth" class="control-label col-xs-3"><spring:message
                                code="pet.growth"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="growth" name="growth"
                                   placeholder="<spring:message code="pet.growth"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="weight" class="control-label col-xs-3"><spring:message
                                code="pet.weight"/></label>

                        <div class="col-xs-9">
                            <input type="step" class="form-control" id="weight" name="weight"
                                   placeholder="<spring:message code="pet.weight"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="namePerson" class="control-label col-xs-3"><spring:message
                                code="pet.namePerson"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="namePerson" name="namePerson"
                                   placeholder="<spring:message code="pet.namePerson"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phone" class="control-label col-xs-3"><spring:message
                                code="pet.phone"/></label>

                        <div class="col-xs-9">
                            <input type="tel" class="form-control" id="phone" name="phone"
                                   placeholder="<spring:message code="pet.phone"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message
                                code="pet.email"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="email" name="email"
                                   placeholder="<spring:message code="pet.email"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>