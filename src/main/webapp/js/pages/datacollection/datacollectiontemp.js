
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
                + "<td>The instrument is consistent with international standards:</td>  "
                + "<td>  "
                    + "<select class='consistentintlstandards' name='natlinstruconsistent["+ arrayIndex +"]'>  "
                        + "<option value=''></option>  "
                        + "<option value='yes' >Yes</option>  "
                        + "<option value='partially' >Partially</option>  "
                        + "<option value='no' >No</option>  "
                        + "<option value='unclear'>Unclear</option>  "
                    + "</select>  "
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
                                        
    
                
        + "</tbody>  "
            
    
            
            
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
    
        
    
    
        //Discrimination in the instrument.
        
        //Separator.
        + "<tbody id='discriminationseparator'>  "                                                        
            + "<tr>  "
                + "<td class='separatorstyling' colspan='2'>"
                    + "Groups the Instrument Discriminates Against "
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
                        + "<input type='checkbox' name='natinstrudiscrimination["+ arrayIndex +"]' value='other'>Other "
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
                    + "Links to Other Rights Groups "
                + "</td>  "
            + "</tr> "        
        + "</tbody>  "
        
        + "<tbody id='linksotherlegisrows'>  "
            //References to other legislation in other rights groups. 
            + "<tr>  "
                + "<td>The instrument references pieces of legislation related to other rights groups:</td>  "
                + "<td>  "
                    + "<div class='otherlegisradio'>"
                        + "<input type='radio' name='natlinstruotherlegis["+ arrayIndex +"]' value='yes' >Yes <br>  "
                        + "<input type='radio' name='natlinstruotherlegis["+ arrayIndex +"]' value='no'  >No  "
                    + "</div>"
                    + "<textarea class='otherlegistextarea' name='natlinstruotherlegistextarea["+ arrayIndex +"]' placeholder='Comments'></textarea>  "      
                + "</td>  "
            + "</tr>  "
            
            + "<tr>  "
            + "<td>The instrument should be read in conjunction with instruments related to the following rights groups:</td>  "
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
                    + "Articles in the Instrument Relevent to "+rightsGroup+" Rights "
                + "</td>  "
            + "</tr> "
        + "</tbody>  "
            
        + "<tbody id='articlesrows'>  "
            
            //Articles in the  instrument.
            + "<tr>  "
                + "<td colspan='2'>  "
                    + "<textarea class='natcomments' name='natlinstruarticles["+ arrayIndex +"]'></textarea>  "
                + "</td>  "
            + "</tr>  "
            
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