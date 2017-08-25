$(function() {

    // Datepicker.
    $("#constdateeffect").datepicker({
    changeMonth : true,
    changeYear : true,
    dateFormat : "dd/mm/yy",
    yearRange : "-300:+0"
    });

    $("#constdateeffectamend").datepicker({
    changeMonth : true,
    changeYear : true,
    dateFormat : "dd/mm/yy",
    yearRange : "-300:+0"
    });

    // When a radio button is clicked, show/hide additional questions if there
    // is/is not a constitution.
    $('input:radio[name="constitutionyesno"]').change(function() {
        if ($(this).val() == 'yes') {
            $("#constyes").show(500);
        } else {
            $("#constyes").hide(500);
        }
    });

    $('input:radio[name="constamendyesno"]').change(function() {

        if ($(this).val() == 'yes') {
            $("#constamenddate").show(500);
        } else {
            $("#constamenddate").hide(500);
        }
    });

    // When a radio button is clicked, show/hide the description of the plural
    // legal system.
    $('input:radio[name="commoncivilplural"]').change(function() {
        if ($(this).val() == 'plural') {
            $("#commoncivilpluraltext").show(500);
        } else {
            $("#commoncivilpluraltext").hide(500);
        }
    });

});

// When a page loads, show/hide additional questions if there is/is not a
// constitution.
$('input:radio[name="constitutionyesno"]').ready(function() {

    if ($('input[name="constitutionyesno"]:checked').val() == 'yes') {
        $("#constyes").show(500);
    } else {
        $("#constyes").hide(500);
    }
});

// When a page loads, show/hide the constitution amendment date if the
// constitution was/was not amended.
$('input:radio[name="constamendyesno"]').ready(function() {

    if ($('input[name="constamendyesno"]:checked').val() == 'yes') {
        $("#constamenddate").show(500);
    } else {
        $("#constamenddate").hide(500);
    }
});

// When a page loads, show/hide the description of the plural legal system.
$('input:radio[name="commoncivilplural"]').ready(function() {

    if ($('input[name="commoncivilplural"]:checked').val() == 'plural') {
        $("#commoncivilpluraltext").show(500);
    } else {
        $("#commoncivilpluraltext").hide(500);
    }
});

// Add another row to the Judicial Entities table.
function judicialAddRow() {
    var table = document.getElementById("judicialTable");
    var noRows = table.rows.length;
    var arrayIndex = noRows - 1;
    var row;

    if (($("input[type=hidden][name=authEditView]").val() === "true")) {

        row = "<tr>";
        row = row + "<td>";
        row = row + "<input type='text' maxlength='50' name='entitycourt[" + arrayIndex + "]' value=''>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='refugeesaccesscourt[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='refugeesaccesscourt[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='refugeesaccesscourt[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='IDPsaccesscourt[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='IDPsaccesscourt[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='IDPsaccesscourt[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='returneesaccesscourt[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='returneesaccesscourt[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='returneesaccesscourt[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='statelessaccesscourt[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='statelessaccesscourt[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='statelessaccesscourt[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='asylumaccesscourt[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='asylumaccesscourt[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='asylumaccesscourt[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "</tr>";

        jQuery('#judicialTableAddRow').append(row);

    }
}

// Add another row to the Administrative Entities table.
function adminAddRow() {
    var table = document.getElementById("adminTable");
    var noRows = table.rows.length;
    var arrayIndex = noRows - 1;
    var row;
    if (($("input[type=hidden][name=authEditView]").val() === "true")) {

        row = "<tr>";
        row = row + "<td>";
        row = row + "<input type='text'  maxlength='50' name='adminentityname[" + arrayIndex + "]' value=''>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='refugeesaccessadmin[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='refugeesaccessadmin[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='refugeesaccessadmin[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='IDPsaccessadmin[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='IDPsaccessadmin[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='IDPsaccessadmin[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='returneesaccessadmin[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='returneesaccessadmin[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='returneesaccessadmin[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='statelessaccessadmin[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='statelessaccessadmin[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='statelessaccessadmin[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='asylumaccessadmin[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='asylumaccessadmin[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='asylumaccessadmin[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "</tr>";

        jQuery('#administrativeTableAddRow').append(row);

    }
}

// Add another row to the Traditional Mechanisms table.
function tradAddRow() {
    var table = document.getElementById("tradTable");
    var noRows = table.rows.length;
    var arrayIndex = noRows - 1;
    var row;

    if (($("input[type=hidden][name=authEditView]").val() === "true")) {

        row = "<tr>";
        row = row + "<td>";
        row = row + "<input type='text'  maxlength='50' name='tradmechname[" + arrayIndex + "]' value=''>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='refugeesaccesstrad[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='refugeesaccesstrad[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='refugeesaccesstrad[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='IDPsaccesstrad[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='IDPsaccesstrad[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='IDPsaccesstrad[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='returneesaccesstrad[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='returneesaccesstrad[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='returneesaccesstrad[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='statelessaccesstrad[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='statelessaccesstrad[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='statelessaccesstrad[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "<td>";
        row = row + "<input type='radio' name='asylumaccesstrad[" + arrayIndex + "]' value='yes'>Yes <br>";
        row = row + "<input type='radio' name='asylumaccesstrad[" + arrayIndex + "]' value='no'>No <br>";
        row = row + "<input type='radio' name='asylumaccesstrad[" + arrayIndex + "]' value='unclear'>Unclear <br>";
        row = row + "</td>";
        row = row + "</tr>";

        jQuery('#traditionalTableAddRow').append(row);

    }

}