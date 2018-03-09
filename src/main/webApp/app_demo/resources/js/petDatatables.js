var ajaxUrl = "ajax/profile/pets/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

// http://api.jquery.com/jQuery.ajax/#using-converters
$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            var json = JSON.parse(stringData);
            $(json).each(function () {
                this.dateTime = this.dateTime.replace('T', ' ').substr(0, 16);
            });
            return json;
        }
    }
});

$.datetimepicker.setLocale(localeCode);

//  http://xdsoft.net/jqplugins/datetimepicker/
var startDate = $('#startDate');
var endDate = $('#endDate');

$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "createdDate",
                "render": function (date, type, row) {
                    if (type === 'display') {
                        return formatDate(date);
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
            {
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderDeleteBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.exceed ? 'exceeded' : 'normal');
        }
    }));
});