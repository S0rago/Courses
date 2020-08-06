function showTask(e) {
    var span = e.nextSibling.nextSibling;
    span.hidden = !span.hidden;
};

function openForm() {
    $("#add-btn").hide();
    $("#save-form").show();
};

function closeForm() {
    if (saveTask()) {
        $("#save-form").hide();
        $("#add-btn").show();
    }
};

function saveTask() {
    var data = $('#save-form form').serialize();
    if (data.indexOf('=&') > -1) {
        alert("Заполните оба поля");
        return false;
    }
    $.ajax({
        method: "POST",
        url: '/tasks/',
        data: data,
        success: function(response) {
            var task = {};
            task.id = response;
            var dataArray = $('#save-form form').serializeArray();
            for(i in dataArray) {
                task[dataArray[i]['name']] = dataArray[i]['value'];
                }
            $("#save-form form").load('/tasks/');
            }
        });
    return false;
};

function deleteTask(e) {
    var url = '/tasks/' + $(e).children("a").data("id");
    $.ajax({
        method: "POST",
        url: url,
        success: function(response) {
            $("#save-form form").load('/tasks/');
        }
        });
    return false;
}