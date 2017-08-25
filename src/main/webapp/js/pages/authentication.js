$(document).ready(function() {

    // If the user does not have permission to add/edit data then prevent them
    // from saving data when they click the "Save As" button.

    $("input[type=submit][name=savedata]").click(function(e) {

        if (($("input[type=hidden][name=authEditView]").val() === "false")) {

            $(".savebutton").attr("disabled", "disabled");
            e.preventDefault();

            document.getElementById('myAlertModal').style.display = "block";

        }

    });

    // If the user does not have permission to add/edit data then prevent them
    // from creating new judicial, administrative and traditional entities.

    $(".addrowbutton :input").click(function(e) {

        if (($("input[type=hidden][name=authEditView]").val() === "false")) {
            e.preventDefault();
            $(".addrowbutton :input").attr("disabled", "disabled");

            document.getElementById('myAlertModal').style.display = "block";

        }

    });

    // If the user does not have permission to add/edit data then prevent them
    // from uploading files in the National Instruments section of the Tool.
    $("input[name^='browseButton']").click(function(e) {

        if (($("input[type=hidden][name=authEditView]").val() === "false")) {

            $("#uploadbutton").attr("disabled", "disabled");

            e.preventDefault();

            document.getElementById('myAlertModal').style.display = "block";

        }

    });

    $("button[name^='uploadButton']").click(function(e) {

        if (($("input[type=hidden][name=authEditView]").val() === "false")) {

            $("#upLoadFileButton").attr("disabled", "disabled");

            $(".uploadfilesuccess").css("display", "none");
            e.preventDefault();

            document.getElementById('myAlertModal').style.display = "block";

        }

    });

    $(".addinstrument :input").click(function(e) {

        if (($("input[type=hidden][name=authEditView]").val() === "false")) {
            e.preventDefault();
            $(".addinstrument :input").attr("disabled", "disabled");

            document.getElementById('myAlertModal').style.display = "block";

        }

    });

    // If the user does not have permission to add/edit data then prevent them
    // from uploading files in the Obstacles section of the Tool.
    $("input[type=button][id=showHideUpload]").click(function(e) {

        if (($("input[type=hidden][name=authEditView]").val() === "false")) {
            $(".upLoadFileButton").attr("disabled", "disabled");

            document.getElementById('uploadButtonsSection').style.display = "none";
            $("#showHideUpload").val("Add File(s)");

            e.preventDefault();

            document.getElementById('myAlertModal').style.display = "block";

        }

    });

});

// Get the modal
var alertModal = document.getElementById('myAlertModal');

// Get the <span> element that closes the modal
var spanCloseModal = document.getElementsByClassName("closeModal")[0];

// When the user clicks on <span> (x), close the modal
spanCloseModal.onclick = function() {
    alertModal.style.display = "none";
};
