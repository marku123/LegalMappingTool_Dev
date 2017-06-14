// Change the highlighting of the menu as the user scrolls through the page.
$(function() {

    // This function sets the highlighting of the menu when the user scrolls
    // through the Narrative analysis page.

    $(window).scroll(function() {

        var targetOffsetB = $("#B").offset().top;
        var targetOffsetD = $("#D").offset().top;

        if ($(this).scrollTop() > (targetOffsetB - 200)) {
            $("#menu-country-analytical-6").addClass("visited-submenu");

            $("#menu-country-analytical-8").removeClass("visited-submenu");

        }

        if ($(this).scrollTop() > (targetOffsetD - 285)) {
            $("#menu-country-analytical-6").removeClass("visited-submenu");

            $("#menu-country-analytical-8").addClass("visited-submenu");

        }

    });

});