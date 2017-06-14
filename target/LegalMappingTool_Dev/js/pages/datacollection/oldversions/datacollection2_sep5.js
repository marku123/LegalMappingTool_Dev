//If a rights group has not been selected by the user, ensure the rest of the page is empty.         
$(document).ready(function() {

    var index = document.getElementById("personofconcernselect").selectedIndex;
    if (index == 0) {
        $("#allquestions").hide();
    }

});

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
    row = row + "<option value='bilateral'>Bilateral </option> ";
    row = row + "<option value='trilateral'>Trilateral </option> ";
    row = row + "<option value='multilateral'>Multilateral </option> ";
    row = row + "</select> ";
    row = row + "</td> ";
    row = row + "</tr>  ";
    row = row + "<tr> ";
    row = row + "<td>The instrument has been ratified:</td> ";
    row = row + "<td> ";
    row = row + "<input type='radio' name='intlinstruratified[" + arrayIndex + "]' value='ratified'>Ratified ";
    row = row + "<input type='radio' name='intlinstruratified[" + arrayIndex + "]' value='signed'>Signed ";
    row = row + "<input type='radio' name='intlinstruratified[" + arrayIndex + "]' value='notaparty'>Not a Party ";
    row = row + "</td> ";
    row = row + "</tr>  ";
    row = row + "<tr> ";
    row = row + "<td>Articles from the instrument related to the right:</td> ";
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

    row = row + newNationalTable(arrayIndex,rightsgroup);

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

/*
 * ******************* Contradictory Instruments Section********************
 * 
 */

// Add another row to the Contradictory Instruments table.
function contradictoryAddRow() {
    var table;
    var noRows;
    var arrayIndex;
    var row = '';

    // Test to see if there are any rows in the table. If not set all of the
    // indexes to 0.
    if (document.getElementById("contradictoryinstrumentsTable") == null) {
        noRows = 0;
        arrayIndex = 0;
    } else {
        table = document.getElementById("contradictoryinstrumentsTable");
        noRows = table.rows.length;
        arrayIndex = noRows - 1;
    }

    // If there are no rows then add a header to the table.
    if (noRows == 0) {
        row = row + "<table id='contradictoryinstrumentsTable' class='contradictoryinstruments'>";
        row = row + "<thead>";
        row = row + "<tr>";
        row = row + "<th>Contradictory Instruments or Instrument Provisions</th>";
        row = row + "<th>Instrument or Provision that Takes Precedence</th>";
        row = row + "<th>Source that Confirms Which Insturment Takes Precedence</th>";
        row = row + "</tr>";
        row = row + "</thead>";
        row = row + "<tbody id='contradictoryTableTbody'>";
    }

    // Create a new row for the table.
    row = row + "<tr>";
    row = row + "<td>";
    row = row + "<label>Instrument/Provision 1:</label>";
    row = row + "<input class='topprovision' name='instruprov1[" + arrayIndex + "]' type='text' value='' size='35'>";
    row = row + "<label>Instrument/Provision 2:</label>";
    row = row + "<input class='bottomprovision' name='instruprov2[" + arrayIndex + "]' type='text' value='' size='35'>";
    row = row + "</td>";
    row = row + "<td>";
    row = row + "<label>Name of the Instrument/Provision:</label> \n";
    row = row + "<input class='precprovision' name='instruprovprecedence[" + arrayIndex + "]' type='text' value='' size='35'>";
    row = row + "<label>Comments:</label> \n";
    row = row + "<textarea name='instruprovprecedencecomm["+ arrayIndex +"]'></textarea> \n ";
    row = row + "</td>";
    row = row + "<td>";
    row = row + "<input name='instruprovsource[" + arrayIndex + "]' type='text' value='' size='35'>";
    row = row + "</td>";
    row = row + "</tr>";

    // If there are no rows then close off the table and add the new table after
    // question 3. Otherwise just add a new row to the table.
    if (noRows == 0) {
        row = row + "</tbody>";
        row = row + "</table>";
        jQuery('#question3para').after(row);

    } else {

        jQuery('#contradictoryTableTbody').append(row);

    }
}

/*
 * **************String for New National Instruments Table**************
 * 
 */
function newNationalTable(arrayIndex,rightsGroup){


    var row = ""; 
     

    row = row + "<br>"
    + "<table id='natltable"+ arrayIndex +"' class='natinstruments'> "
        + "<thead  onclick=\"natltoggletablebody('natltable"+ arrayIndex +"','natlinputtable"+ arrayIndex +"')\">  "
            + "<tr>  "
                + "<th class='tablehead' colspan='2'>  "
                + "<input class='inputinstrumenthead' id='natlinputtable"+ arrayIndex +"' type='text' value='' size='65' onfocus='this.blur()' readonly>  "
                + "<span class='tooltiptext'>Click to Expand/Collapse</span>  "
                + "</th>  "
            + "</tr> "
        + "</thead>  "
        //Separator for the overview of the national instrument section.
        + "<tbody id='overviewseparator'>  "
            + "<tr>  "
                + "<td class='separatorstyling' colspan='2'>  "
                    + "Overview of the Instrument "
                + "</td>  "
            + "</tr> "
        + "</tbody>  "
        + "<tbody id='overviewrows'>  "
        
            //Name of the instrument.
            + "<tr>  "
                + "<td class='col1' >Name of the national instrument:</td>  "
                + "<td class='col2'> "
                    + "<input id='natlinputtable"+ arrayIndex +"a' name='natlinstruname["+ arrayIndex +"]' type='text' value='' size='65'>  "
                + "</td>  "
            + "</tr>  "
            
            //Link to Refworld.
            + "<tr>  "
                + "<td>Link to the instrument in Refworld:</td>  "
                + "<td> "
                    + "<input name='natlinstrureflink["+ arrayIndex +"]' type='text' value='' size='65'>  "
                    + "<a class='refworldlink' href='' target='_blank'>Visit Link</a>  "
                + "</td>  "
            + "</tr>  "
        
            //Instrument is federal, state or local.
            + "<tr>  "
                + "<td>The instrument is applicable at the federal, state or local levels:</td>  "
                + "<td>  "
                    + "<input type='radio' name='natlinstrufederal["+ arrayIndex +"]' value='federal'>Federal  "
                    + "<input type='radio' name='natlinstrufederal["+ arrayIndex +"]' value='state' >State  "
                    + "<input type='radio' name='natlinstrufederal["+ arrayIndex +"]' value='local' >Local  "                          
                + "</td>  "
            + "</tr> "
            
            //Consistency with international standards.
            + "<tr>  "
                + "<td>The content of the instrument is consistent with international standards:</td>  "
                + "<td>  "
                    + "<div id='instruconsistentcommdiv'> \n"
                    + "<select class='consistentintlstandards' name='natlinstruconsistent["+ arrayIndex +"]'>  "
                        + "<option value=''></option>  "
                        + "<option value='yes' >Yes</option>  "
                        + "<option value='partially' >Partially</option>  "
                        + "<option value='no' >No</option>  "
                        + "<option value='unclear'>Unclear</option>  "
                    + "</select>  "
                    + "</div> \n"
                    + "<label id='instruconsistentlabel' class='instruconsistentlabel'>Explanation as to why there is a lack of clarity (if 'Unclear' is selected): </label>  \n"                                  
                    + "<textarea class='instruconsistentcomm' name='natlinstruconsistentcomm["+ arrayIndex +"]'></textarea> \n "
                + "</td>  "
            + "</tr> "                        
        
        + "</tbody>  "
            
        //Beginning of the Supports/Restricts section.                        

        //Separator for the Support/Restricts section.                          
        + "<tbody id='supportrestrictssepartor'>  "                           
            + "<tr>  "
                + "<td class='separatorstyling' colspan='2'>"
                    + "Formal Support and/or Restriction of the "+rightsGroup+" Rights of Nationals and Populations of Concern "
                + "</td>  "
            + "</tr> "
        + "</tbody>  "
            
        + "<tbody id='supportrestrictsrows'>  "
            
            //The instrument formally SUPPORTS the rights of NATIONALS.
            + "<tr>  "
                + "<td class='col1'>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>nationals</strong>:</td>  "
                + "<td class='col2'>  "
                
                    + "<div id='supportrestrictdiv'> "                                    
                    + "<label class='supportrestrict'>Supports</label>  "                                 
                    + "<select class='supportrestrict' name='natlinstrusupportnatls["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially'>Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "<label class='supportrestrict'>Restricts</label>  "                                    
                    + "<select class='supportrestrict' name='natlinstrurestrictnatls["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear'>Unclear</option>  "
                    + "</select>  "
                    + "</div> "                                   
    
                    + "<textarea class='supportrestrict' name='natlinstrusupportnatlscom["+ arrayIndex +"]' placeholder='Comments'></textarea>  "     
                + "</td>  "
            + "</tr> "
                                        
                
            //The instrument formally SUPPORTS the rights of IDPs.
            + "<tr>  "
                + "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>internally displaced persons</strong>:</td>  "
                + "<td>  "
                    + "<div id='supportrestrictdiv'> "                                    
                    + "<label class='supportrestrict'>Supports</label>  "                                 
                    + "<select class='supportrestrict' name='natlinstrusupportidps["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially'>Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "<label class='supportrestrict'>Restricts</label>  "                                    
                    + "<select class='supportrestrict' name='natlinstrurestrictidps["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "</div> "                                   
    
                    + "<textarea class='supportrestrict' name='natlinstrusupportidpscom["+ arrayIndex +"]' placeholder='Comments'></textarea>  "      
                + "</td>  "
            + "</tr> "
                    
            
            //The instrument formally SUPPORTS the rights of Refugees.
            + "<tr>  "
                + "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>refugees</strong>:</td>  "
                + "<td>  "
                    + "<div id='supportrestrictdiv'> "                                    
                    + "<label class='supportrestrict'>Supports</label>  "                                 
                    + "<select class='supportrestrict' name='natlinstrusupportrefug["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "<label class='supportrestrict'>Restricts</label>  "                                    
                    + "<select class='supportrestrict' name='natlinstrurestrictrefug["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "</div> "                                   
    
                    + "<textarea class='supportrestrict' name='natlinstrusupportrefugcom["+ arrayIndex +"]' placeholder='Comments'></textarea>  "        
                + "</td>  "
            + "</tr> "
                
                
            //The instrument formally SUPPORTS the rights of Asylum Seekers.
            + "<tr>  "
                + "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>asylum seekers</strong>:</td>  "
                + "<td>  "
                    + "<div id='supportrestrictdiv'> "                                    
                    + "<label class='supportrestrict'>Supports</label>  "                                 
                    + "<select class='supportrestrict' name='natlinstrusupportasyl["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "<label class='supportrestrict'>Restricts</label>  "                                    
                    + "<select class='supportrestrict' name='natlinstrurestrictasyl["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "</div> "                                   
    
                    + "<textarea class='supportrestrict' name='natlinstrusupportasylcom["+ arrayIndex +"]' placeholder='Comments'></textarea>  "     
                + "</td>  "
            + "</tr> "
                
                
            //The instrument formally SUPPORTS the rights of Returnees.
            + "<tr>  "
                + "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>returnees</strong>:</td>  "
                + "<td>  "
                    + "<div id='supportrestrictdiv'> "                                    
                    + "<label class='supportrestrict'>Supports</label>  "                                 
                    + "<select class='supportrestrict' name='natlinstrusupportreturn["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "<label class='supportrestrict'>Restricts</label>  "                                    
                    + "<select class='supportrestrict' name='natlinstrurestrictreturn["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "</div> "                                   
    
                    + "<textarea class='supportrestrict' name='natlinstrusupportreturncom["+ arrayIndex +"]' placeholder='Comments'></textarea>  "       
                + "</td>  "
            + "</tr> "
                                
            
            //The instrument formally SUPPORTS the rights of Stateless Persons.
            + "<tr>  "
                + "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>stateless persons</strong>:</td>  "
                + "<td>  "
                    + "<div id='supportrestrictdiv'> "                                    
                    + "<label class='supportrestrict'>Supports</label>  "                                 
                    + "<select class='supportrestrict' name='natlinstrusupportstateless["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "<label class='supportrestrict'>Restricts</label>  "                                    
                    + "<select class='supportrestrict' name='natlinstrurestrictstateless["+ arrayIndex +"]'>  "
                            + "<option value=''></option>  "
                            + "<option value='yes' >Yes</option>  "
                            + "<option value='partially' >Partially</option>  "
                            + "<option value='no' >No</option>  "
                            + "<option value='unclear' >Unclear</option>  "
                    + "</select>  "
                    + "</div> "                                   
    
                    + "<textarea class='supportrestrict' name='natlinstrusupportcomstateless["+ arrayIndex +"]' placeholder='Comments'></textarea>  "        
                + "</td>  "
            + "</tr> "
    
        
            + "</tbody>  "

    
        //Discrimination in the instrument.
        
        //Separator.
        + "<tbody id='discriminationseparator'>  "                                                        
            + "<tr>  "
                + "<td class='separatorstyling' colspan='2'>"
                    + "Grounds on Which the Instrument Discriminates"
                + "</td>  "
            + "</tr> "        
        + "</tbody>  "
        
        + "<tbody id='discriminationrows'>  "
            + "<tr>  "
                + "<td>In the context of <strong>"+rightsGroup+"</strong> rights, the instrument discriminates based on the following social identifiers:</td>  "
                + "<td>  "
                    + "<div class='discriminationcheckboxes'> "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='sex'>Sex <br>"
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='gender' >Gender "
                    + "</div> "
                    + "<div class='discriminationcheckboxes'> "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='age'>Age<br> "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='national'>National Origin "
                    + "</div> "
                    + "<div class='discriminationcheckboxes'> "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='ethnicity'>Ethnicity<br>  "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='religion'>Religion "
                    + "</div> "
                    + "<div class='discriminationcheckboxes'> "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='residence'>Residence Status<br> "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='disability'>Disability"
                    + "</div> "
                    + "<div class='discriminationcheckboxes'> "
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='other'>Other: "
                        + "<input type='text' name='natinstrudiscriminationother["+ arrayIndex +"]' value=''> "
                    + "</div> "
                + "</td>  "
            + "</tr>  "               
        + "</tbody>  "                            
        
        //Reference to Other Rights Groups Legislation.
        
        //Separator.
        + "<tbody id='linksotherlegisseparator'>  "                                                       
            + "<tr>  "
                + "<td class='separatorstyling' colspan='2'>"
                    + "Links to Other Rights"
                + "</td>  "
            + "</tr> "        
        + "</tbody>  "
        
        + "<tbody id='linksotherlegisrows'>  "
            //References to other legislation in other rights groups. 
            + "<tr>  "
                + "<td>The instrument references pieces of legislation related to other rights:</td>  "
                + "<td>  "
                    + "<div class='otherlegisradio'>"
                        + "<input type='radio' name='natlinstruotherlegis["+ arrayIndex +"]' value='yes' >Yes <br>  "
                        + "<input type='radio' name='natlinstruotherlegis["+ arrayIndex +"]' value='no'  >No  "
                    + "</div>"
                    + "<textarea class='otherlegistextarea' name='natlinstruotherlegistextarea["+ arrayIndex +"]' placeholder='Comments'></textarea>  "      
                + "</td>  "
            + "</tr>  "
            
            + "<tr>  "
            + "<td>The instrument should be read in conjunction with instruments related to the following rights:</td>  "
                + "<td>  "
                    + "<div class='otherlegischeckboxes1'> "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='a2j' >Access to Justice <br>"
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='civpoli'  >Civil and Political <br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='docu' >Documentation <br>"
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='education'  >Education <br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='employment' >Employment <br> "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='family'  >Family Unity and Reunification <br>"
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='freeasso' >Freedom of Association and Assembly<br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='freerelig'  >Freedom of Expression, Opinions, <br>&emsp;&nbsp;&nbsp;Thoughts and Religion<br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='freemove' >Freedom of Movement "
                    + "</div> "
                    + "<div class='otherlegischeckboxes2'> "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='housing' >Housing, Land and Property <br> "                                        
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='lifephysical'  >Life and Physical Integrity<br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='nationality'  >Nationality and Residence <br> "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='nondiscrim' >Non-Discrimination <br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='nonrefoulement' >Non-Refoulement and Protection<br>&emsp;&nbsp;&nbsp;Against Forced Return <br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='polipart'  >Political Participation <br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='publicrelief' >Public Relief <br>  "
                        + "<input type='checkbox' name='natinstruotherlegischecked["+ arrayIndex +"]' value='socioeco' >Socio-Economic  "                                                                              
                    + "</div> "
                + "</td>  "
            + "</tr>  "
            
        + "</tbody>  "        
    
        //Articles section.
        //Separator for the Articles section.
    
        + "<tbody id='articlesseparator'>  "                                                      
            + "<tr>  "
                + "<td class='separatorstyling' colspan='2'>"
                    + "Articles in the Instrument Relevant to "+rightsGroup+" Rights "
                + "</td>  "
            + "</tr> "
        + "</tbody>  "
            
        + "<tbody id='articlesrows'>  "
            
            //Articles in the  instrument.
            + "<tr>  "
                + "<td>Full text of articles from the instrument (in English or French) that are relevant to "+rightsGroup+" rights:</td> \n "
            
                + "<td> \n "
                    + "<textarea class='natcomments' name='natlinstruarticles["+ arrayIndex +"]'></textarea>  "
                + "</td>  "
            + "</tr>  "
            

            //Comments on the Articles in the  instrument.
            + "<tr> \n "
                + "<td>Comments on the articles:</td> \n "
            
                + "<td> \n "
                    + "<textarea class='natcomments' name='natlinstruarticlescomm["+ arrayIndex +"]'></textarea> \n "
                + "</td> \n "
            + "</tr> \n "
            
            
        + "</tbody>  "    
    
        
        
        
        //Other Comments section.
        //Separator for the Other Comments national instrument section.
    
        + "<tbody id='othercommentsseparator'>  "                                                     
            + "<tr>  "
                + "<td class='separatorstyling' colspan='2'>"
                    + "Additional Comments on the Instrument "
                + "</td>  "
            + "</tr> "
        + "</tbody>  "
            
        + "<tbody id='othercommentsrows'>  "
            
            //Comments on the instrument.
            + "<tr>  "
                + "<td colspan='2'>  "
                    + "<textarea class='natcomments' name='natlinstrucomments["+ arrayIndex +"]' placeholder='Additional Comments'></textarea>  "
                + "</td>  "
            + "</tr>  "
            
        + "</tbody>  "        
    
    
    + "</table>  ";   
    
    return row;
}
