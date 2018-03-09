<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                "paging": false,
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

    function updateRow(id) {
        $("#modalTitle").html(i18n["editTitle"]);
        $.get(ajaxUrl + id, function (data) {
            $.each(data, function (key, value) {
                form.find("input[name='" + key + "']").val(value);
            });
            $('#editRow').modal();
        });
    }

    function deleteRow(id) {
        $.ajax({
            url: ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            updateTable();
            successNoty("common.deleted");
        });
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

    function renderEditBtn(data, type, row) {
        if (type === "display") {
            return "<a onclick='updateRow(" + row.id + ");'>" +
                "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
        }
    }

    function renderDeleteBtn(data, type, row) {
        if (type === "display") {
            return "<a onclick='deleteRow(" + row.id + ");'>" +
                "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
        }
    }
</script>
<script>
    var ajaxUrl = "ajax/admin/users/";
    var datatableApi;

    function updateTable() {
        $.get(ajaxUrl, updateTableByData);
    }

    function enable(chkbox, id) {
        var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
        $.ajax({
            url: ajaxUrl + id,
            type: "POST",
            data: "enabled=" + enabled
        }).done(function () {
            chkbox.closest("tr").toggleClass("disabled");
            successNoty(enabled ? "common.enabled" : "common.disabled");
        }).fail(function () {
            $(chkbox).prop("checked", !enabled);
        });
    }

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').DataTable(extendsOpts({
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return "<a href='mailto:" + data + "'>" + data + "</a>";
                        }
                        return data;
                    }
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                        }
                        return data;
                    }
                },
                {
                    "data": "registered",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return date.substring(0, 10);
                        }
                        return date;
                    }
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.enabled) {
                    $(row).addClass("disabled");
                }
            }
        }));
    });
</script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="user.title"/></h3>
        <br/>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a>
        <table class="table table-striped display" id="datatable">
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
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="user.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="user.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="user.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="user.email"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message code="user.password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="user.password"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="save()" class="btn btn-primary">
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
<jsp:include page="fragments/i18n.jsp"/>
<script type="text/javascript">
    i18n["addTitle"] = '<spring:message code="user.add"/>';
    i18n["editTitle"] = '<spring:message code="user.edit"/>';
</script>
</html>