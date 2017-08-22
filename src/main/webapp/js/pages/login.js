$(document).ready(function() {
    $(".signout").hide();

    $("#topnav ul").hide();

    if ($("input[type=hidden][name=loginattempted]").val() === "true") {
        $(".tryagainmessage").show();
    } else {
        $(".tryagainmessage").hide();
    }

});