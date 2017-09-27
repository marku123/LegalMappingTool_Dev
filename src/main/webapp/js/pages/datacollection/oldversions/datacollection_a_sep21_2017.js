$(document).ready(function() {

    // If a rights group has not been selected by the user, ensure the rest of
    // the page is empty.
    /*
     * var index =
     * document.getElementById("personofconcernselect").selectedIndex; if (index ==
     * 0) { $("#allquestions").hide(); }
     */

    // If a PDF version of a national instrument has been uploaded to the
    // server, show the link to it. If it hasn't been uploaded, show the "browse
    // for file" and "upload" buttons.
    $("input[name^='natFileStorageName']").each(function() {

        var element_id = $("input[name^='natFileStorageName']").index(this);

        if ($(this).val() == "") {
            $("a[name='natFileURLLink[" + element_id + "]']").hide();
        } else {
            $("button[name='uploadButton[" + element_id + "]']").hide();
            $("input[name='natlinstrurefupload[" + element_id + "]']").hide();
            $("input[name='browseButton[" + element_id + "]']").hide();
            $("label[id='browseFileLabel[" + element_id + "]']").hide();
        }
    });

    // When a file is uploaded, then display the name of the file.
    $(document).on('change', 'input[name^=browseButton]', function() {

        var element_id = $("input[name^=browseButton]").index(this);
        var instrumentName = "natlinstrurefupload[" + element_id + "]";
        var instrumentSelector = "input[name='" + instrumentName + "']";

        $(instrumentSelector).val($(this).val());

    });

    $(document).on('mousedown', 'button[name^=uploadButton]', function() {

        var element_id = $("button[name^=uploadButton]").index(this);

        $('p[id="uploadsuccess[' + element_id + ']"]').text('Please wait. File upload in progress.');

    });

    // Upload the selected file using Ajax.
    $(document).on('click', 'button[name^=uploadButton]', function() {
        var countryName = $('input[name="countryNameForFileUpload"]').val();
        console.log('online country name: ' + countryName);
        var element_id = $("button[name^=uploadButton]").index(this);

        var fileToUpload = new FormData();
        fileToUpload.append('Country', countryName);
        fileToUpload.append('file', $('input[name="browseButton[' + element_id + ']"]')[0].files[0]);

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
            uploadResp(responseText, element_id);
        },
        error : function(responseText) {
            uploadRespError(responseText, element_id);
        }
        });

    });

});

// When I file is successfully uploaded, display it on the page.
function uploadResp(responseText, element_id) {

    var fileStorageName = responseText.match(/\d{2}\.\d{2}\.\d{2}.*/);
    var fileURL = responseText;
    var fileDisplayName = responseText.split(/\d{2}\.\d{2}\.\d{2}_/);

    fileStorageName = String(fileStorageName).replace(/[^\x00-\x7F]/g, "");
    fileURL = String(fileURL).replace(/[^\x00-\x7F]/g, "");

    $('p[id="uploadsuccess[' + element_id + ']"]').text('The file has been successfully uploaded! To ensure these changes are saved click the "Save Changes" button.');

    $('a[name="natFileURLLink[' + element_id + ']"]').attr('href', fileURL);
    $('a[name="natFileURLLink[' + element_id + ']"]').text(fileDisplayName[1]);
    $('a[name="natFileURLLink[' + element_id + ']"]').show(300);
    // $('a[name="natFileURLLink[' + element_id + ']"]').addClass("spacer");
    $('input[name="natFileURL[' + element_id + ']"]').val(fileURL);
    $('input[name="natFileStorageName[' + element_id + ']"]').val(fileStorageName);
    $('input[name="natFileDisplayName[' + element_id + ']"]').val(fileDisplayName[1]);

}
// If the uploading of the file fails, display it on the page.
function uploadRespError(responseText, element_id) {

    $('p[id="uploadsuccess[' + element_id + ']"]').text('Uploading of file failed.');
}
/*
 * ******************* International Instruments Section********************
 * 
 */

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
        row = row + "<option value='notgeo'>Not Geographically Applicable</option> ";
        row = row + "</select>  ";
        row = row + "</td> ";
        row = row + "</tr>  ";
        row = row + "<tr> ";
        row = row + "<td>Articles from the instrument related to the rights:</td> ";
        row = row + "<td> ";
        row = row + "<textarea name='intlinstruarticles[" + arrayIndex + "]'></textarea> ";
        row = row + "</td> ";
        row = row + "</tr> ";
        row = row + "<tr> ";
        row = row + "<td>Reservations related to the right have been taken:</td>  ";
        row = row + "<td> ";
        row = row + "<input type='radio' name='intlinstrures[" + arrayIndex + "]' value='yes'>Yes ";
        row = row + "<input type='radio' name='intlinstrures[" + arrayIndex + "]' value='no'>No ";
        row = row + "</td> ";
        row = row + "</tr> ";
        row = row + "<tr> ";
        row = row + "<td>Nature and scope of the reservations (if any):</td>  ";
        row = row + "<td> ";
        row = row + "<textarea name='intlinstruresnature[" + arrayIndex + "]'></textarea> ";
        row = row + "</td> ";
        row = row + "</tr> ";
        row = row + "</tbody>  ";
        row = row + "</table>  ";

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

/*
 * ******************* National Instruments Section********************
 * 
 */

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
function natlInstruAddRow(rightsgroup) {
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

        row = row + newNationalTable(arrayIndex, rightsgroup);
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
function newNationalTable(arrayIndex, rightsGroup) {

    var row = "";

    row = row + "<br>" + "<table id='natltable" + arrayIndex + "' class='natinstruments'> " + "<thead  onclick=\"natltoggletablebody('natltable" + arrayIndex + "','natlinputtable" + arrayIndex + "')\">  " + "<tr>  " + "<th class='tablehead' colspan='2'>  " + "<input class='inputinstrumenthead' id='natlinputtable" + arrayIndex + "' type='text' value='' size='65' onfocus='this.blur()' readonly>  " + "<span class='tooltiptext'>Click to Expand/Collapse</span>  " + "</th>  " + "</tr> " + "</thead>  "
    // Separator for the overview of the national instrument section.
    + "<tbody id='overviewseparator'>  " + "<tr>  " + "<td class='separatorstyling' colspan='2'>  " + "Overview of the Instrument " + "</td>  " + "</tr> " + "</tbody>  " + "<tbody id='overviewrows'>  "

    // Name of the instrument.
    + "<tr>  " + "<td class='col1' >Name of the national instrument:</td>  " + "<td class='col2'> " + "<input id='natlinputtable" + arrayIndex + "a' name='natlinstruname[" + arrayIndex + "]' type='text' value='' size='65'>  " + "</td>  " + "</tr>  "

    // Link to Refworld.
    + "<tr>  " + "<td>Link to the instrument in Refworld:</td>  " + "<td> " + "<input name='natlinstrureflink[" + arrayIndex + "]' type='text' value='' size='65'>  " + "<a class='refworldlink' href='' target='_blank'>Visit Link</a>  " + "</td>  " + "</tr>  "

    // Upload instrument.
    + "<tr> " + "<td>Uploaded PDF version of the instrument (if not in Refworld):</td> " + "<td>" + "<a class='linkToUploadedFile' name='natFileURLLink[" + arrayIndex + "]' href='' target='_blank'></a>" + "<br><br><label class='browsefile' id='browseFileLabel[" + arrayIndex + "]'> " + "<input id='uploadbutton' name='browseButton[" + arrayIndex + "]' type='file' /> " + "Click to Browse for File " + "</label>" + "<input class='inputfilename' type='text' name='natlinstrurefupload[" + arrayIndex + "]' />" + "<button id='upLoadFileButton' name='uploadButton[" + arrayIndex + "]' type='button' value=''>Upload File</button>" + "<p class='uploadfilesuccess' id='uploadsuccess[" + arrayIndex + "]' ></p>" + "<input type='hidden' name='natFileStorageName[" + arrayIndex + "]' value='' />" + "<input type='hidden' name='natFileDisplayName[" + arrayIndex + "]' value='' />" + "<input type='hidden' name='natFileURL[" + arrayIndex + "]' value='' />" + "</td> " + "</tr> "

    // Instrument is federal, state or local.
    + "<tr>  " + "<td>The instrument is applicable at the federal, state or local levels:</td>  " + "<td>  " + "<input type='radio' name='natlinstrufederal[" + arrayIndex + "]' value='federal'>Federal  " + "<input type='radio' name='natlinstrufederal[" + arrayIndex + "]' value='state' >State  " + "<input type='radio' name='natlinstrufederal[" + arrayIndex + "]' value='local' >Local  " + "</td>  " + "</tr> "

    // Consistency with international standards.
    + "<tr>  " + "<td>The content of the instrument is consistent with international standards:</td>  " + "<td>  " + "<div id='instruconsistentcommdiv'> \n" + "<select class='consistentintlstandards' name='natlinstruconsistent[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear'>Unclear</option>  " + "</select>  " + "</div> \n" + "<label id='instruconsistentlabel' class='instruconsistentlabel'>Explanation as to why there is a lack of clarity (if 'Unclear' is selected): </label>  \n" + "<textarea class='instruconsistentcomm' name='natlinstruconsistentcomm[" + arrayIndex + "]'></textarea> \n " + "</td>  " + "</tr> "

    // Consistency with with 1951 Refugee convention.
    + "<tr>  " + "<td>The content of the instrument is consistent with the 1951 Refugee Conventions:</td>  " + "<td>  " + "<div id='instruconsistent1951div'> \n" + "<select class='consistentintlstandards1951' name='natlinstruconsistent1951[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear'>Unclear</option>  " + "</select>  " + "</div> \n" + "<label id='instruconsistent1951label' class='instruconsistent1951label'>Explanation as to why there is a lack of clarity (if 'Unclear' is selected): </label>  \n" + "<textarea class='instruconsistentcomm1951' name='natlinstruconsistentcomm1951[" + arrayIndex + "]'></textarea> \n " + "</td>  " + "</tr> "

    + "</tbody>  "

    // Beginning of the Supports/Restricts section.

    // Separator for the Support/Restricts section.
    + "<tbody id='supportrestrictssepartor'>  " + "<tr>  " + "<td class='separatorstyling' colspan='2'>" + "Formal Support and/or Restriction of the " + rightsGroup + " Rights of Nationals and Populations of Concern " + "</td>  " + "</tr> " + "</tbody>  "

    + "<tbody id='supportrestrictsrows'>  "

    // The instrument formally SUPPORTS the rights of NATIONALS.
    + "<tr>  " + "<td class='col1'>The instrument formally supports and/or restricts the " + rightsGroup + " rights of <strong>nationals</strong>:</td>  " + "<td class='col2'>  "

    + "<div id='supportrestrictdiv'> " + "<label class='supportrestrict'>Supports</label>  " + "<select class='supportrestrict' name='natlinstrusupportnatls[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially'>Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "<label class='supportrestrict'>Restricts</label>  " + "<select class='supportrestrict' name='natlinstrurestrictnatls[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear'>Unclear</option>  " + "</select>  " + "</div> "

    + "<textarea class='supportrestrict' name='natlinstrusupportnatlscom[" + arrayIndex + "]' placeholder='Comments'></textarea>  " + "</td>  " + "</tr> "

    // The instrument formally SUPPORTS the rights of IDPs.
    + "<tr>  " + "<td>The instrument formally supports and/or restricts the " + rightsGroup + " rights of <strong>internally displaced persons</strong>:</td>  " + "<td>  " + "<div id='supportrestrictdiv'> " + "<label class='supportrestrict'>Supports</label>  " + "<select class='supportrestrict' name='natlinstrusupportidps[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially'>Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "<label class='supportrestrict'>Restricts</label>  " + "<select class='supportrestrict' name='natlinstrurestrictidps[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "</div> "

    + "<textarea class='supportrestrict' name='natlinstrusupportidpscom[" + arrayIndex + "]' placeholder='Comments'></textarea>  " + "</td>  " + "</tr> "

    // The instrument formally SUPPORTS the rights of Refugees.
    + "<tr>  " + "<td>The instrument formally supports and/or restricts the " + rightsGroup + " rights of <strong>refugees</strong>:</td>  " + "<td>  " + "<div id='supportrestrictdiv'> " + "<label class='supportrestrict'>Supports</label>  " + "<select class='supportrestrict' name='natlinstrusupportrefug[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "<label class='supportrestrict'>Restricts</label>  " + "<select class='supportrestrict' name='natlinstrurestrictrefug[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "</div> "

    + "<textarea class='supportrestrict' name='natlinstrusupportrefugcom[" + arrayIndex + "]' placeholder='Comments'></textarea>  " + "</td>  " + "</tr> "

    // The instrument formally SUPPORTS the rights of Asylum Seekers.
    + "<tr>  " + "<td>The instrument formally supports and/or restricts the " + rightsGroup + " rights of <strong>asylum seekers</strong>:</td>  " + "<td>  " + "<div id='supportrestrictdiv'> " + "<label class='supportrestrict'>Supports</label>  " + "<select class='supportrestrict' name='natlinstrusupportasyl[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "<label class='supportrestrict'>Restricts</label>  " + "<select class='supportrestrict' name='natlinstrurestrictasyl[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "</div> "

    + "<textarea class='supportrestrict' name='natlinstrusupportasylcom[" + arrayIndex + "]' placeholder='Comments'></textarea>  " + "</td>  " + "</tr> "

    // The instrument formally SUPPORTS the rights of Returnees.
    + "<tr>  " + "<td>The instrument formally supports and/or restricts the " + rightsGroup + " rights of <strong>returnees</strong>:</td>  " + "<td>  " + "<div id='supportrestrictdiv'> " + "<label class='supportrestrict'>Supports</label>  " + "<select class='supportrestrict' name='natlinstrusupportreturn[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "<label class='supportrestrict'>Restricts</label>  " + "<select class='supportrestrict' name='natlinstrurestrictreturn[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "</div> "

    + "<textarea class='supportrestrict' name='natlinstrusupportreturncom[" + arrayIndex + "]' placeholder='Comments'></textarea>  " + "</td>  " + "</tr> "

    // The instrument formally SUPPORTS the rights of Stateless Persons.
    + "<tr>  " + "<td>The instrument formally supports and/or restricts the " + rightsGroup + " rights of <strong>stateless persons</strong>:</td>  " + "<td>  " + "<div id='supportrestrictdiv'> " + "<label class='supportrestrict'>Supports</label>  " + "<select class='supportrestrict' name='natlinstrusupportstateless[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "<label class='supportrestrict'>Restricts</label>  " + "<select class='supportrestrict' name='natlinstrurestrictstateless[" + arrayIndex + "]'>  " + "<option value=''></option>  " + "<option value='yes' >Yes</option>  " + "<option value='partially' >Partially</option>  " + "<option value='no' >No</option>  " + "<option value='unclear' >Unclear</option>  " + "</select>  " + "</div> "

    + "<textarea class='supportrestrict' name='natlinstrusupportcomstateless[" + arrayIndex + "]' placeholder='Comments'></textarea>  " + "</td>  " + "</tr> "

    + "</tbody>  "

    // Discrimination in the instrument.

    // Separator.
    + "<tbody id='discriminationseparator'>  " + "<tr>  " + "<td class='separatorstyling' colspan='2'>" + "Grounds on Which the Instrument Discriminates" + "</td>  " + "</tr> " + "</tbody>  "

    + "<tbody id='discriminationrows'>  " + "<tr>  " + "<td>In the context of <strong>" + rightsGroup + "</strong> rights, the instrument discriminates based on the following social identifiers:</td>  " + "<td>  " + "<div class='discriminationcheckboxes'> " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='sex'>Sex <br>" + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='gender' >Gender " + "</div> " + "<div class='discriminationcheckboxes'> " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='age'>Age<br> " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='national'>National Origin " + "</div> " + "<div class='discriminationcheckboxes'> " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='ethnicity'>Ethnicity<br>  " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='religion'>Religion " + "</div> " + "<div class='discriminationcheckboxes'> " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='residence'>Residence Status<br> " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='disability'>Disability" + "</div> " + "<div class='discriminationcheckboxes'> " + "<input type='checkbox' name='natinstrudiscrimination[" + arrayIndex + "]' value='other'>Other: " + "<input type='text' name='natinstrudiscriminationother[" + arrayIndex + "]' value=''> " + "</div> " + "</td>  " + "</tr>  " + "</tbody>  "

    // Reference to Other Rights Groups Legislation.

    // Separator.
    + "<tbody id='linksotherlegisseparator'>  " + "<tr>  " + "<td class='separatorstyling' colspan='2'>" + "Links to Other Rights Categories" + "</td>  " + "</tr> " + "</tbody>  "

    + "<tbody id='linksotherlegisrows'>  "
    // References to other legislation in other rights groups.
    + "<tr>  " + "<td>The instrument references pieces of legislation related to other rights categories:</td>  " + "<td>  " + "<div class='otherlegisradio'>" + "<input type='radio' name='natlinstruotherlegis[" + arrayIndex + "]' value='yes' >Yes <br>  " + "<input type='radio' name='natlinstruotherlegis[" + arrayIndex + "]' value='no'  >No  " + "</div>" + "<textarea class='otherlegistextarea' name='natlinstruotherlegistextarea[" + arrayIndex + "]' placeholder='Comments'></textarea>  " + "</td>  " + "</tr>  "

    + "<tr>  " + "<td>The instrument should be read in conjunction with instruments related to the following rights categories:</td>  " + "<td>  " + "<div class='otherlegischeckboxes1'> " + "<p class='otherlegisheadertop'>Civil/Political Rights Categories</p>" + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='fair'>Access to Justice<br>" + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='docu'>Documentation<br>" + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='free'>Freedom of Movement<br>" + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='lib'>Liberty and Security of Person<br>  " + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='nondis'>Non-Discrimination<br>  " + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='poli'>Political Participation<br>  " + "<p class='otherlegisheaders'>Economic Rights Categories</p>" + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='housing'>Housing, Land and Property Rights <br> " + "</div> " + "<div class='otherlegischeckboxes2'> " + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='work'>Right to Work and Rights at Work<br>" + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='soc'>Social Security<br>" + "<p class='otherlegisheaders'>Legal Rights Categories</p>" + "<p class='otherlegisheaders'>Socio-cultural Rights Categories</p>" + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='edu'>Education<br>  " + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='fam'>Family Unity<br>  " + "<input type='checkbox' name='natinstruotherlegischecked[" + arrayIndex + "]' value='heal'>Health<br>" + "</div> " + "</td>  " + "</tr>  " + "</tbody>  "

    // Articles section.
    // Separator for the Articles section.

    + "<tbody id='articlesseparator'>  " + "<tr>  " + "<td class='separatorstyling' colspan='2'>" + "Articles in the Instrument Relevant to " + rightsGroup + " Rights " + "</td>  " + "</tr> " + "</tbody>  "

    + "<tbody id='articlesrows'>  "

    // Articles in the instrument.
    + "<tr>  " + "<td>Full text of articles from the instrument (in English or French) that are relevant to " + rightsGroup + " rights:</td> \n "

    + "<td> \n " + "<textarea class='natcomments' name='natlinstruarticles[" + arrayIndex + "]'></textarea>  " + "</td>  " + "</tr>  "

    // Comments on the Articles in the instrument.
    + "<tr> \n " + "<td>Comments on the articles:</td> \n "

    + "<td> \n " + "<textarea class='natcomments' name='natlinstruarticlescomm[" + arrayIndex + "]'></textarea> \n " + "</td> \n " + "</tr> \n "

    + "</tbody>  "

    // Other Comments section.
    // Separator for the Other Comments national instrument section.

    + "<tbody id='othercommentsseparator'>  " + "<tr>  " + "<td class='separatorstyling' colspan='2'>" + "Additional Comments on the Instrument " + "</td>  " + "</tr> " + "</tbody>  "

    + "<tbody id='othercommentsrows'>  "

    // Comments on the instrument.
    + "<tr>  " + "<td colspan='2'>  " + "<textarea class='natcomments' name='natlinstrucomments[" + arrayIndex + "]' placeholder='Additional Comments'></textarea>  " + "</td>  " + "</tr>  "

    + "</tbody>  "

    + "</table>  ";

    return row;
}
