$(document).ready(function() {

    var todaysDate = getTodaysDate();

    $(".reportTitleDate").text(todaysDate);
});

// Make sure that the menu is not fixed when the user scrolls to the right.
$(document).scroll(function(e) {
    $('#left-index').css({
        'left' : 1 - $(document).scrollLeft()
    });
});
function getTodaysDate() {

    var rawDate = new Date();
    var day = rawDate.getDate();
    var month = rawDate.getMonth() + 1;
    var year = rawDate.getFullYear();
    var finaldate;

    if (rawDate.getDate() < 10) {
        day = "0" + day;
    }

    if (rawDate.getMonth() < 10) {
        month = "0" + month;
    }
    finaldate = day + "/" + month + "/" + year;

    return finaldate;

}