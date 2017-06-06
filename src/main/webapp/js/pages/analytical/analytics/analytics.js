// Expand a single macro div.
/*function toggletablebody(divname, divtablename) {
 $("#" + divname).toggle();
 $("#" + divtablename).hide();

 }*/

//Show/Hide the data tables. 
function toggledatatable(divname) {
    $("#" + divname).toggle();
}

// Hide all of the tables when the page is loaded.
$(document).ready(function() {
    $("#detail-consistency-table").hide();
    $("#detail-poc-natinstsruments-table").hide();
    $("#detail-poc-obstacles-table").hide();
    $("#detail-poc-obstacles-rightgroups-table").hide();

});
