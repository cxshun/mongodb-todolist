/**
 * Created by chenxiaoshun on 14-6-29.
 */
$(document).ready(function() {
    $("#content a").click(function(e){
        e.preventDefault();
        $(this).tab("show");
    })

    click2Save();
});


function click2Finish(todoItemId, days, finished) {
    $.get("/todoItem?action=finished&days="+days+"&finished="+finished, function(data) {
        if (data["result"] == true) {
           location.reload();
        }
    })
}

function click2Delete(todoItemId, days, finished) {
    $.get("/todoItem?action=deleted&days="+days+"&finished="+finished, function(data) {
        if (data["result"] == true) {
            location.reload();
        }
    })
}

function click2Save() {
    var options = {
        success: showResponse
    };
    $("#createForm").submit(function() {
        alert("xxxx");
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

