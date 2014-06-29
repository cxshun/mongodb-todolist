/**
 * Created by chenxiaoshun on 14-6-29.
 */
$(document).ready(function() {
    $("#content li").click(function(e){
        e.preventDefault();
        $(this).tab("show");
    })
})