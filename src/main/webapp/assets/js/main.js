/**
 * Created by chenxiaoshun on 14-6-29.
 */
$(document).ready(function() {
    click2Save();

    $("#todo-tab a").click(function() {
        e.preventDefault();
        $(this).tab("show");
    })

    if (days == 0) {
        $("#todo-tab a[id='today']").parent().addClass("active");
    } else if (days == 3) {
        $("#todo-tab a[id='3day']").parent().addClass("active");
    } else if (days == 7) {
        $("#todo-tab a[id='7day']").parent().addClass("active");
    }
});


function click2Finish(todoItemId, days, finished) {
    $.get("/todoItem?action=finished&days="+days+"&finished="+finished+"&todoItemId="+todoItemId, function(data) {
        location.reload();
    })
}

function click2Delete(todoItemId, days, finished) {
    $.get("/todoItem?action=deleted&days="+days+"&finished="+finished+"&todoItemId="+todoItemId, function(data) {
        location.reload();
    })
}

function click2Save() {
    var options = {
        success: showResponse
    };
    $("#createForm").submit(function() {
        $(this).ajaxSubmit(options);
        return false;
    })
}

function showResponse(responseData, updateTarget) {
    if (responseData.result == true) {
        $(updateTarget).html(responseData["message"]);
        return true;
    }
}

