$(function() {

    // This function sets the highlighting of the menu when the user scrolls
    // through section D of the data collection page.

    $(window).scroll(function() {

        var targetOffsetIntro = $("#D1").offset().top;
        var targetOffsetA = $("#D2").offset().top;
        var targetOffsetB = $("#D3").offset().top;
        var targetOffsetC = $("#D4").offset().top;
        var targetOffsetD = $("#D5").offset().top;

        if ($(this).scrollTop() > (targetOffsetIntro - 200)) {
            $("#menu-country-datacollection-5_1").addClass("visited-submenu");
            $("#menu-country-datacollection-5_1_a").addClass("visited-submenu");

            $("#menu-country-datacollection-5_2").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_2_a").removeClass("visited-submenu");

            $("#menu-country-datacollection-5_3").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_3_a").removeClass("visited-submenu");

            $("#menu-country-datacollection-5_4").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_4_a").removeClass("visited-submenu");

            $("#menu-country-datacollection-5_5").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_5_a").removeClass("visited-submenu");

        }

        if ($(this).scrollTop() > (targetOffsetA - 200)) {
            $("#menu-country-datacollection-5_2").addClass("visited-submenu");
            $("#menu-country-datacollection-5_2_a").addClass("visited-submenu");

            $("#menu-country-datacollection-5_1").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_3").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_4").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_5").removeClass("visited-submenu");

            $("#menu-country-datacollection-5_1_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_3_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_4_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_5_a").removeClass("visited-submenu");
        }

        if ($(this).scrollTop() > (targetOffsetB - 200)) {
            $("#menu-country-datacollection-5_3").addClass("visited-submenu");
            $("#menu-country-datacollection-5_3_a").addClass("visited-submenu");

            $("#menu-country-datacollection-5_2").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_1").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_4").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_5").removeClass("visited-submenu");

            $("#menu-country-datacollection-5_2_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_1_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_4_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_5_a").removeClass("visited-submenu");

        }

        if ($(this).scrollTop() > (targetOffsetC - 200)) {
            $("#menu-country-datacollection-5_4").addClass("visited-submenu");
            $("#menu-country-datacollection-5_4_a").addClass("visited-submenu");

            $("#menu-country-datacollection-5_1").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_2").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_3").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_5").removeClass("visited-submenu");

            $("#menu-country-datacollection-5_1_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_2_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_3_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_5_a").removeClass("visited-submenu");

        }

        if ($(this).scrollTop() > (targetOffsetD - 500)) {
            $("#menu-country-datacollection-5_5").addClass("visited-submenu");
            $("#menu-country-datacollection-5_5_a").addClass("visited-submenu");

            $("#menu-country-datacollection-5_1").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_2").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_3").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_4").removeClass("visited-submenu");

            $("#menu-country-datacollection-5_1_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_2_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_3_a").removeClass("visited-submenu");
            $("#menu-country-datacollection-5_4_a").removeClass("visited-submenu");

        }
    });

});