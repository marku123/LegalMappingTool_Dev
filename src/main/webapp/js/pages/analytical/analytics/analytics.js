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

$(document).ready(function() {

    // Remove any references from national instruments to POCs that are not in
    // the country.
    var POCs = $("input[name='countrypocs']").val();

    if (!(POCs.length === 0)) {
        if (!POCs.includes('Asylum Seekers')) {
            $('.trhideclassAsy').hide();
        }
        if (!POCs.includes('Internally Displaced Persons')) {
            $('.trhideclassIDP').hide();
        }
        if (!POCs.includes('Refugees')) {
            $('.trhideclassRef').hide();
        }
        if (!POCs.includes('Returnees')) {
            $('.trhideclassRet').hide();
        }
        if (!POCs.includes('Stateless Persons')) {
            $('.trhideclassState').hide();
        }
    }

});