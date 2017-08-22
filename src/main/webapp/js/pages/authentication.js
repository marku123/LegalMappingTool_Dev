$(document).ready(function() {
    $("input[type=submit][name=savedata]").click(function(e) {

        if (($("input[type=hidden][name=authEditView]").val() === "false")) {

            $(".savebutton").attr("disabled", "disabled");
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
