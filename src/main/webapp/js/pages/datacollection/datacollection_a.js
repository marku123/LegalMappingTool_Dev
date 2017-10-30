/*International Instruments...........................................*/

$(document).ready(function() {

    // If we are dealing with pre entered international instruments then do not
    // show the refworld link and the upload files link.
    $("input[name^='intlinstrureflink']").each(function() {

        var element_id = $("input[name^='intlinstrureflink']").index(this);

        if ($(this).val() == "preentered") {
            $('.trhideclass' + element_id).hide();
            $('#tdhide1class' + element_id).hide();
            $('#tdhide1classtype' + element_id).hide();
        } else {
            $('#tdhide2class' + element_id).hide();
            $('#tdhide2classtype' + element_id).hide();

            // If we are dealing with an instrument that the user added then
            // remove the "notgeo" option.
            $('select[name*="intlinstruratified[' + element_id + ']"] option[value="notgeo"]').hide();
        }
    });

    // International Instrument File
    // Upload................................................

    // If a PDF version of a national instrument has been uploaded to the
    // server, show the link to it. If it hasn't been uploaded, show the "browse
    // for file" and "upload" buttons.
    $("input[name^='intlFileStorageName']").each(function() {

        var element_id = $("input[name^='intlFileStorageName']").index(this);

        if ($(this).val() == "") {
            $("a[name='intlFileURLLink[" + element_id + "]']").hide();
        } else {
            $("button[name='uploadButtonIntl[" + element_id + "]']").hide();
            $("input[name='intlinstrurefupload[" + element_id + "]']").hide();
            $("input[name='browseButtonIntl[" + element_id + "]']").hide();
            $("label[id='browseFileLabelIntl[" + element_id + "]']").hide();
        }
    });

    // When a file is uploaded, then display the name of the file.
    $(document).on('change', 'input[name^=browseButtonIntl]', function() {

        var element_id = $("input[name^=browseButtonIntl]").index(this);
        var instrumentName = "intlinstrurefupload[" + element_id + "]";
        var instrumentSelector = "input[name='" + instrumentName + "']";

        $(instrumentSelector).val($(this).val());

    });

    $(document).on('mousedown', 'button[name^=uploadButtonIntl]', function() {

        var element_id = $("button[name^=uploadButtonIntl]").index(this);

        $('p[id="uploadsuccessIntl[' + element_id + ']"]').text('Please wait. File upload in progress.');

    });

    // Upload the selected file using Ajax.
    $(document).on('click', 'button[name^=uploadButtonIntl]', function() {

        var countryName = $('input[name="countryNameForFileUpload"]').val();
        var element_id = $("button[name^=uploadButtonIntl]").index(this);

        var fileToUpload = new FormData();
        fileToUpload.append('Country', countryName);
        fileToUpload.append('file', $('input[name="browseButtonIntl[' + element_id + ']"]')[0].files[0]);

        $.ajax({
        url : 'UploadController',
        type : 'POST',
        async : false,
        cache : false,
        processData : false,
        contentType : false,
        enctype : 'multipart/form-data',
        data : fileToUpload,
        success : function(responseText) {
            uploadRespIntl(responseText, element_id);
        },
        error : function(responseText) {
            uploadRespErrorIntl(responseText, element_id);
        }
        });

    });

    // End International Instrument File
    // Upload................................................

});

// International Instrument File
// Upload................................................

// When a file is successfully uploaded, display it on the page.
function uploadRespIntl(responseText, element_id) {

    var fileStorageName = responseText.match(/\d{2}\.\d{2}\.\d{2}.*/);
    var fileURL = responseText;
    var fileDisplayName = responseText.split(/\d{2}\.\d{2}\.\d{2}_/);

    fileStorageName = String(fileStorageName).replace(/[^\x00-\x7F]/g, "");
    fileURL = String(fileURL).replace(/[^\x00-\x7F]/g, "");

    $('p[id="uploadsuccessIntl[' + element_id + ']"]').text('The file has been successfully uploaded! To ensure these changes are saved click the "Save Changes" button.');

    $('a[name="intlFileURLLink[' + element_id + ']"]').attr('href', fileURL);
    $('a[name="intlFileURLLink[' + element_id + ']"]').text(fileDisplayName[1]);
    $('a[name="intlFileURLLink[' + element_id + ']"]').show(300);
    $('input[name="intlFileURL[' + element_id + ']"]').val(fileURL);
    $('input[name="intlFileStorageName[' + element_id + ']"]').val(fileStorageName);
    $('input[name="intlFileDisplayName[' + element_id + ']"]').val(fileDisplayName[1]);

}
// If the uploading of the file fails, display it on the page.
function uploadRespErrorIntl(responseText, element_id) {

    $('p[id="uploadsuccessIntl[' + element_id + ']"]').text('Uploading of file failed.');
}

// Expand all international instrument tables.
function expandAllIntl() {
    $(".intltablebody").show();
}

// Collapse all international instrument tables.
function collapseAllIntl() {
    $(".intltablebody").hide();
}

// Expand a single instrument table and ensure that when the instrument title is
// edited it is reflected in the table header.
function intltoggletablebody(table, input) {
    $("#" + table + " tbody").toggle();

    $("#" + input + "a").keyup(function() {

        $("#" + input).val($(this).val());
    });
}

// Add an empty International Instrument table for editing.
function intlInstruAddRow() {
    var tables = document.getElementsByClassName("interinstruments");
    var noOfTables = tables.length;
    var arrayIndex = noOfTables;
    var indexOfLastTable = arrayIndex - 1;
    var idOfLastTable = "intltable" + indexOfLastTable;
    var row = "";

    // If there are no instruments in the database then set everything to zero.
    if (indexOfLastTable < 0) {
        arrayIndex = 0;
        idOfLastTable = "intltable0";// I think I can remove this.
        row = row + "<div class='showhidediv'>";
        row = row + "<input class='expandbutton' type='button' name='show' value='Expand All' onclick='expandAllIntl()'> ";
        row = row + "<input class='collapsebutton' type='button' name='hide' value='Collapse All' onclick='collapseAllIntl()'>";
        row = row + "</div> ";

    } else {
        row = row + "<br>";
    }

    // Make sure the user has the permission to add data.
    if (($("input[type=hidden][name=authEditView]").val() === "true")) {

        // Create the empty instrument table.
        row = row + "<table id='intltable" + arrayIndex + "' class='interinstruments'>";
        row = row + "<thead  onclick=\"intltoggletablebody('intltable" + arrayIndex + "','intlinputtable" + arrayIndex + "')\">";
        row = row + "<tr> ";
        row = row + "<th class='tablehead' colspan='2'> ";
        row = row + "<input class='inputinstrumenthead' id='intlinputtable" + arrayIndex + "' type='text' value='' onfocus='this.blur()' readonly>";
        row = row + "<span class='tooltiptext'>Click to Expand/Collapse</span>";
        row = row + "</th> ";
        row = row + "</tr> ";
        row = row + "</thead> ";

        row = row + "<tbody class='intltablebody'>  ";
        row = row + "<tr> ";
        row = row + "<td class='col1' >Name of the instrument:</td> ";
        row = row + "<td class='col2'> ";
        row = row + "<input id='intlinputtable" + arrayIndex + "a' name='intlinstruname[" + arrayIndex + "]' type='text' value='' size='90'> ";
        row = row + "</td> ";
        row = row + "</tr> ";

        row = row + "<tr>  ";
        row = row + "<td>Link to the instrument in Refworld:</td>  ";
        row = row + "<td>  ";
        row = row + "<input name='intlinstrureflink[" + arrayIndex + "]' type='text' value='' size='65'> ";
        row = row + "<a class='refworldlink' href='' target='_blank'>Visit Link</a> ";
        row = row + "</td> ";
        row = row + "</tr>  ";

        row = row + "<tr>";
        row = row + "<td>Uploaded PDF version of the instrument (if not in Refworld):</td>";
        row = row + "<td>";
        row = row + "<a class='linkToUploadedFile' name='intlFileURLLink[" + arrayIndex + "]' href='' target='_blank'></a>";
        row = row + "<br><br><label class='browsefile' id='browseFileLabelIntl[" + arrayIndex + "]'> ";
        row = row + "<input id='uploadbuttonintl' name='browseButtonIntl[" + arrayIndex + "]' type='file' /> ";
        row = row + "Click to Browse for File ";
        row = row + "</label>";
        row = row + "<input class='inputfilenameIntl' type='text' name='intlinstrurefupload[" + arrayIndex + "]' />";
        row = row + "<button id='upLoadFileButtonIntl' name='uploadButtonIntl[" + arrayIndex + "]' type='button' value='" + arrayIndex + "'>Upload File</button>";
        row = row + "<p class='uploadfilesuccess' id='uploadsuccessIntl[" + arrayIndex + "]' ></p>";
        row = row + "<input type='hidden' name='intlFileStorageName[" + arrayIndex + "]' value='' />";
        row = row + "<input type='hidden' name='intlFileDisplayName[" + arrayIndex + "]' value='' />";
        row = row + "<input type='hidden' name='intlFileURL[" + arrayIndex + "]' value='' />";
        row = row + "</td> ";
        row = row + "</tr> ";

        row = row + "<tr>  ";
        row = row + "<td>Type of instrument:</td>  ";
        row = row + "<td>  ";
        row = row + "<select name='intlinstrutype[" + arrayIndex + "]'>  ";
        row = row + "<option value=''></option> ";
        row = row + "<option value='international'>International </option> ";
        row = row + "<option value='regional'>Regional </option> ";
        row = row + "<option value='bilateral'>Bilateral</option> ";
        row = row + "</select> ";
        row = row + "</td> ";
        row = row + "</tr>  ";

        row = row + "<tr> ";
        row = row + "<td>The instrument has been ratified:</td> ";
        row = row + "<td> ";
        row = row + "<select name='intlinstruratified[" + arrayIndex + "]'> ";
        row = row + "<option value=''></option> ";
        row = row + "<option value='ratified'>Ratified</option>  ";
        row = row + "<option value='signed'>Signed</option> ";
        row = row + "<option value='notaparty'>Not a Party</option> ";
        // row = row + "<option value='notgeo'>Not Geographically
        // Applicable</option> ";
        row = row + "</select>  ";
        row = row + "</td> ";
        row = row + "</tr>  ";

        row = row + "<tr> ";
        row = row + "<td>The instrument regulates or affects the following rights categories:</td> ";
        row = row + "<td> ";
        row = row + "<div class='otherlegischeckboxes1'> ";
        row = row + "<p class='otherlegisheadertop'>Civil/Political Rights Categories</p>";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='fair' >Access to Justice <br>";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='docu' >Documentation<br>";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='free' >Freedom of Movement <br>";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='lib'  >Liberty and Security of Person<br> ";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='nondis' >Non-Discrimination<br> ";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='poli' >Political Participation <br> ";
        row = row + "<p class='otherlegisheaders'>Economic Rights Categories</p>";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='housing' >Housing, Land and Property Rights<br>  ";
        row = row + "</div> ";
        row = row + "<div class='otherlegischeckboxes2'> ";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='work' >Right to Work and Rights at Work<br> ";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='soc'>Social Security<br>";
        row = row + "<p class='otherlegisheaders'>Socio-cultural Rights Categories</p>";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='edu'>Education<br>  ";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='fam'>Family Unity <br>";
        row = row + "<input type='checkbox' name='intlinstrrightschecked[" + arrayIndex + "]' value='heal'>Health <br>";
        row = row + "</div> ";
        row = row + "</td> ";
        row = row + "</tr> ";

        row = row + "</tbody>  ";
        row = row + "</table>  ";
        row = row + "<input type='hidden' name='intlinstrnameOrig[" + arrayIndex + "]' value=''> \n ";

        // If there are no instruments in the database, then add the empty
        // instrument after the hidden rights group input element.
        if (indexOfLastTable < 0) {
            jQuery('#question1para').after(row);
        } else {
            jQuery('#' + idOfLastTable).after(row);
        }

        // Expand the table once it has been added.
        $("#intltable" + arrayIndex + " tbody").toggle();
    }
}

/* ............................................................... */
/* National Instruments........................................... */
/* ............................................................... */

$(document).ready(function() {

    // National Instrument File
    // Upload................................................

    // If a PDF version of a national instrument has been uploaded to the
    // server, show the link to it. If it hasn't been uploaded, show the "browse
    // for file" and "upload" buttons.
    $("input[name^='natFileStorageName']").each(function() {

        var element_id_nat = $("input[name^='natFileStorageName']").index(this);

        if ($(this).val() == "") {
            $("a[name='natFileURLLink[" + element_id_nat + "]']").hide();
        } else {
            $("button[name='uploadButtonNat[" + element_id_nat + "]']").hide();
            $("input[name='natlinstrurefupload[" + element_id_nat + "]']").hide();
            $("input[name='browseButtonNat[" + element_id_nat + "]']").hide();
            $("label[id='browseFileLabel[" + element_id_nat + "]']").hide();
        }
    });

    // When a file is uploaded, then display the name of the file.
    $(document).on('change', 'input[name^=browseButtonNat]', function() {

        var element_id_nat = $("input[name^=browseButtonNat]").index(this);
        var instrumentName = "natlinstrurefupload[" + element_id_nat + "]";
        var instrumentSelector = "input[name='" + instrumentName + "']";

        $(instrumentSelector).val($(this).val());

    });

    $(document).on('mousedown', 'button[name^=uploadButtonNat]', function() {

        var element_id_nat = $("button[name^=uploadButtonNat]").index(this);

        $('p[id="uploadsuccess[' + element_id_nat + ']"]').text('Please wait. File upload in progress.');

    });

    // Upload the selected file using Ajax.
    $(document).on('click', 'button[name^=uploadButtonNat]', function() {

        var countryName = $('input[name="countryNameForFileUpload"]').val();
        // console.log('online country name: ' + countryName);
        var element_id_nat = $("button[name^=uploadButtonNat]").index(this);

        var fileToUpload = new FormData();
        fileToUpload.append('Country', countryName);
        fileToUpload.append('file', $('input[name="browseButtonNat[' + element_id_nat + ']"]')[0].files[0]);

        $.ajax({
        url : 'UploadController',
        type : 'POST',
        async : false,
        cache : false,
        processData : false,
        contentType : false,
        enctype : 'multipart/form-data',
        data : fileToUpload,
        success : function(responseText) {
            uploadResp(responseText, element_id_nat);
        },
        error : function(responseText) {
            uploadRespError(responseText, element_id_nat);
        }
        });

    });

    // End National Instrument File
    // Upload................................................

});

// National Instrument File
// Upload................................................

// When a file is successfully uploaded, display it on the page.
function uploadResp(responseText, element_id_nat) {

    var fileStorageName = responseText.match(/\d{2}\.\d{2}\.\d{2}.*/);
    var fileURL = responseText;
    var fileDisplayName = responseText.split(/\d{2}\.\d{2}\.\d{2}_/);

    fileStorageName = String(fileStorageName).replace(/[^\x00-\x7F]/g, "");
    fileURL = String(fileURL).replace(/[^\x00-\x7F]/g, "");

    $('p[id="uploadsuccess[' + element_id_nat + ']"]').text('The file has been successfully uploaded! To ensure these changes are saved click the "Save Changes" button.');

    $('a[name="natFileURLLink[' + element_id_nat + ']"]').attr('href', fileURL);
    $('a[name="natFileURLLink[' + element_id_nat + ']"]').text(fileDisplayName[1]);
    $('a[name="natFileURLLink[' + element_id_nat + ']"]').show(300);
    // $('a[name="natFileURLLink[' + element_id + ']"]').addClass("spacer");
    $('input[name="natFileURL[' + element_id_nat + ']"]').val(fileURL);
    $('input[name="natFileStorageName[' + element_id_nat + ']"]').val(fileStorageName);
    $('input[name="natFileDisplayName[' + element_id_nat + ']"]').val(fileDisplayName[1]);

}
// If the uploading of the file fails, display it on the page.
function uploadRespError(responseText, element_id_nat) {

    $('p[id="uploadsuccess[' + element_id_nat + ']"]').text('Uploading of file failed.');
}

/* ******************* National Instruments Section****************** */

// Expand all national instrument tables.
function expandAllNatl() {
    $('#question2>table>tbody').show();
}

// Collapse all national instrument tables.
function collapseAllNatl() {
    $('#question2>table>tbody').hide();
}

// Expand a single instrument table and ensure that when the instrument title is
// edited it is reflected in the table header.
function natltoggletablebody(table, input) {
    // Toggle the body of the table.
    $("#" + table + " tbody").toggle();

    // If the name of the instrument is altered, make sure it is altered in the
    // header.
    $("#" + input + "a").keyup(function() {
        $("#" + input).val($(this).val());
    });

}

// Add an empty National Instrument table for editing.
function natlInstruAddRow() {
    var tables = document.getElementsByClassName("natinstruments");
    var noOfTables = tables.length;
    var arrayIndex = noOfTables;
    var indexOfLastTable = arrayIndex - 1;
    var idOfLastTable = "natltable" + indexOfLastTable;
    var row = "";

    // If there are no instruments in the database then set everything to zero.
    if (indexOfLastTable < 0) {
        arrayIndex = 0;
        idOfLastTable = "natltable0";// I don't think I need this.
        row = row + "<div class='showhidediv'>";
        row = row + "<input class='expandbutton' type='button' name='show' value='Expand All' onclick='expandAllNatl()'> ";
        row = row + "<input class='collapsebutton' type='button' name='hide' value='Collapse All' onclick='collapseAllNatl()'>";
        row = row + "</div> ";

    } else {
        row = row + "<br>";
    }

    if (($("input[type=hidden][name=authEditView]").val() === "true")) {

        row = row + newNationalTable(arrayIndex);
        // If there are no instruments in the database, then add the empty
        // instrument after the hidden rights group input element.
        if (indexOfLastTable < 0) {
            jQuery('#question2para').after(row);
        } else {
            jQuery('#' + idOfLastTable).after(row);
        }

        // Expand the table once it has been added.
        $("#natltable" + arrayIndex + " tbody").toggle();
    }

}

/*
 * **************String for New National Instruments Table**************
 * 
 */
function newNationalTable(arrayIndex) {

    var row = "";

    row = row + "<br>" + "<table id='natltable" + arrayIndex + "' class='natinstruments'> " + "<thead  onclick=\"natltoggletablebody('natltable" + arrayIndex + "','natlinputtable" + arrayIndex + "')\">  " + "<tr>" + "<th class='tablehead' colspan='2'>  " + "<input class='inputinstrumenthead' id='natlinputtable" + arrayIndex + "' type='text' value='' size='65' onfocus='this.blur()' readonly> " + "<span class='tooltiptext'>Click to Expand/Collapse</span>  " + "</th>  " + "</tr> " + "</thead>  "

    + "<tbody>"

    // Name of the instrument.
    + "<tr>  <td class='col1' >Name of the national instrument:</td> " + "<td class='col2'> " + "<input id='natlinputtable" + arrayIndex + "a' name='natlinstruname[" + arrayIndex + "]' type='text' value='' size='65'>  " + "</td> </tr>  "

    // Link to Refworld.
    + "<tr> <td>Link to the instrument in Refworld:</td>  " + "<td> <input name='natlinstrureflink[" + arrayIndex + "]' type='text' value='' size='65'>  " + "<a class='refworldlink' href='' target='_blank'>Visit Link</a>  " + "</td> " + "</tr>  "

    // Upload instrument.
    + "<tr>" + "<td>Uploaded PDF version of the instrument (if not in Refworld):</td> " + "<td> <a class='linkToUploadedFile' name='natFileURLLink[" + arrayIndex + "]' href='' target='_blank'></a>" + "<br><br><label class='browsefile' id='browseFileLabel[" + arrayIndex + "]'> " + "<input id='uploadbutton' name='browseButtonNat[" + arrayIndex + "]' type='file' /> " + "Click to Browse for File " + "</label>" + "<input class='inputfilename' type='text' name='natlinstrurefupload[" + arrayIndex + "]' />" + "<button id='upLoadFileButton' name='uploadButtonNat[" + arrayIndex + "]' type='button' value='" + arrayIndex + "'>Upload File</button>" + "<p class='uploadfilesuccess' id='uploadsuccess[" + arrayIndex + "]' ></p>" + "<input type='hidden' name='natFileStorageName[" + arrayIndex + "]' value='' />" + "<input type='hidden' name='natFileDisplayName[" + arrayIndex + "]' value='' />" + "<input type='hidden' name='natFileURL[" + arrayIndex + "]' value='' />" + "</td> " + "</tr>"

    // Applicable in all or parts of the country.
    + "<tr>  " + "<td>The instrument is applicable in all or parts of the country:</td>" + "<td>" + "<div id='instruconsistentcommdiv'> " + "<select class='consistentintlstandards' name='natlinstruapplicable[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='federal' >All</option>  " + "<option value='state'>Part(s)</option>" + "</select>" + "</div> " + "<label id='instruconsistentlabel' class='instruconsistentlabel'>Explanation as to where it is applicable (if 'Part(s)' is selected): </label> " + "<textarea class='instruconsistentcomm' name='natlinstruapplicablecomm[" + arrayIndex + "]'></textarea> " + "</td> " + "</tr> "

    // Rights categories.

    + "<tr> \n " + "<td>The instrument regulates or affects the following rights categories:</td> " + "<td>  " + "<div class='otherlegischeckboxes1'>" + "<p class='otherlegisheadertop'>Civil/Political Rights Categories</p>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='fair'>Access to Justice <br>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='docu'  >Documentation<br>  " + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='free' >Freedom of Movement <br>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='lib'  >Liberty and Security of Person<br>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='nondis'  >Non-Discrimination<br> " + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='poli' >Political Participation <br>" + "<p class='otherlegisheaders'>Economic Rights Categories</p>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='housing' >Housing, Land and Property Rights<br>" + "</div> \n" + "<div class='otherlegischeckboxes2'>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='work'>Right to Work and Rights at Work<br> " + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='soc' >Social Security<br>" + "<p class='otherlegisheaders'>Socio-cultural Rights Categories</p>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='edu' >Education<br> " + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='fam' >Family Unity <br>" + "<input type='checkbox' name='natinstrrightscategories[" + arrayIndex + "]' value='heal' >Health <br>" + "</div> " + "</td>" + "</tr>"

    // Comments on the instrument.
    + "<tr><td>Comments on the instrument:</td> " + "<td> <textarea class='natcomments' name='natlinstrucomments[" + arrayIndex + "]'></textarea>  " + "</td>" + "</tr><br>  "

    + "</tbody> "

    + "</table>  ";

    return row;
}

/*
 * **************String for Constitution Table**************
 * 
 */
$(function() {
    // Select all for the populations of concern.
    $("input[name='populationgroupscheckedselectall']").change(function() {
        if ($(this).is(':checked')) {
            $("input[name^='populationgroupschecked[]']").prop('checked', true);
        } else {
            $("input[name^='populationgroupschecked[]']").prop('checked', false);
        }
    });

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
