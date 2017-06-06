$(document).ready(function() {
    $('#submitFeedback').click(function(event) {
        var name = $('#feedbackname').val();
        var feedbacktext = $('#feedbacktextarea').val();
        var feedbackcountry = $('#feedbackcountry').val();
        var feedbackfilename = $('#feedbackfilename').val();

        $.get('FeedbackController', {
        userName : name,
        feedbacktext : feedbacktext,
        feedbackcountry : feedbackcountry,
        feedbackfilename : feedbackfilename
        }, function(responseText) {
            responseToFeedback(responseText);
        });
    });
});

function responseToFeedback(responseText) {

    $(".feedback-modal-body").hide();
    $(".feedback-modal-body-response").show();
    $("#feedback-response-text").text(responseText);
}

// Get the modal
var modal = document.getElementById('myFeedbackModal');

// Get the button that opens the modal
var btn = document.getElementById("sidebartab");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("modalclose")[0];

// When the user clicks the Feedback button, open the modal
btn.onclick = function() {

    // Clear the feedback form.
    $("#feedbackname").val("");
    $("#feedbacktextarea").val("");

    // Make sure that the input and textarea is showing.
    $(".feedback-modal-body").show();
    $(".feedback-modal-body-response").hide();

    modal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};
