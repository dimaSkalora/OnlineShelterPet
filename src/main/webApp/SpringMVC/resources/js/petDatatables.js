var ajaxUrl = "ajax/profile/pets/";
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

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "createdDate"
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
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});