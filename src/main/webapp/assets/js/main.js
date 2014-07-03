/**
 * Created by chenxiaoshun on 14-6-29.
 */
$(document).ready(function() {
    $("#content a").click(function(e){
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

function click2Save() {
    var $form = $("#addForm");
    var title = $form.find("input[name='title']");
    var content = $form.find("input[name='content']");
    $.ajax("/todoItem?action=save", function(data) {
        if (data["result"] == true) {
            location.reload();
        }
    })
}