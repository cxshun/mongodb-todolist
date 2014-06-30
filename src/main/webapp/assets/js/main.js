/**
 * Created by chenxiaoshun on 14-6-29.
 */
$(document).ready(function() {
    $("#content li").click(function(e){
        e.preventDefault();
        $(this).tab("show");
    })
});


function click2Finish(todoItemId) {
    $.get("/todoItem?action=finished", function(data) {
        if (data["result"] == true) {
           location.reload();
        }
    })
}

function click2Delete(todoItemId) {
    $.get("/todoItem?action=delete", function(data) {
        if (data["result"] == true) {
            location.reload();
        }
    })
}