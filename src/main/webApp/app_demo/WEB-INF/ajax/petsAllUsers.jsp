<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://onlineShelterPet.com/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script>
    function makeEditable() {
        form = $('#detailsForm');

        $(document).ajaxError(function (event, jqXHR, options, jsExc) {
            failNoty(jqXHR);
        });

        // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
        $.ajaxSetup({cache: false});


        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }

    // https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
    function extendsOpts(opts) {
        $.extend(true, opts,
            {
                "ajax": {
                    "url": ajaxUrl,
                    "dataSrc": ""
                },
                "paging": true,
                "info": true,
                "language": {
                    "search": i18n["common.search"]
                },
                "initComplete": makeEditable
            }
        );
        return opts;
    }


    function add() {
        $("#modalTitle").html(i18n["addTitle"]);
        $("#editRow").modal();
    }

    function updateTableByData(data) {
        datatableApi.clear().rows.add(data).draw();
    }

    function save() {
        $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: form.serialize()
        }).done(function () {
            $("#editRow").modal("hide");
            updateTable();
            successNoty("common.saved");
        });
    }


    var failedNote;

    function closeNoty() {
        if (failedNote) {
            failedNote.close();
            failedNote = undefined;
        }
    }

    function successNoty(key) {
        closeNoty();
        new Noty({
            text: "<span class='glyphicon glyphicon-ok'></span> &nbsp;" + i18n[key],
            type: 'success',
            layout: "bottomRight",
            timeout: 1000
        }).show();
    }

    function failNoty(jqXHR) {
        closeNoty();
        // https://stackoverflow.com/questions/48229776
        var errorInfo = JSON.parse(jqXHR.responseText);
        failedNote = new Noty({
            text: "<span class='glyphicon glyphicon-exclamation-sign'></span> &nbsp;" + errorInfo.typeMessage + "<br>" + errorInfo.details.join("<br>"),        type: "error",
            layout: "bottomRight"
        }).show();
    }
</script>
<script>
    var ajaxUrl = "ajax/profile/petsAllUsers/";
    var datatableApi;

    function updateTable() {
        $.ajax({
            type: "POST",
            url: ajaxUrl + "filter",
            data: $("#filter").serialize(),
        }).done(updateTableByData);
    }

    function clearFilter() {
        $("#filter")[0].reset();
        $.get(ajaxUrl, updateTableByData);
    }

    //  http://xdsoft.net/jqplugins/datetimepicker/
    var startDate = $('#startDate');
    var endDate = $('#endDate');

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').DataTable(extendsOpts({
            "columns": [
                {
                    "data": "createdDate",
                    "render": function (date, type, row) {
                        if (type === 'display') {
                            return date.replace('T', ' ').substr(0, 16);
                        }
                        return date;
                    }
                },
                {
                    "data": "typePet"
                },
                {
                    "data": "namePet"
                },
                {
                    "data": "breed"
                },
                {
                    "data": "sex"
                },
                {
                    "data": "color"
                },
                {
                    "data": "age"
                },
                {
                    "data": "growth"
                },
                {
                    "data": "weight"
                },
                {
                    "data": "namePerson"
                },
                {
                    "data": "phone"
                },
                {
                    "data": "email"
                },
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ],
        }));
    });
</script>
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
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>