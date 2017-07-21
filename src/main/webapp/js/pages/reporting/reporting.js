function genPDF() {
    alert('start report');

    // Create a date stamp for the report and put it on the footer and on the
    // title of the report.
    var date = getTodaysDate();
    $('.dateColumn').text(date);
    $('.reportTitleDate').text(date);

    // Generate the report in A4 format.
    xepOnline.Formatter.Format('content-onecolumn', {
    render : 'download',
    pageWidth : '216mm',
    pageHeight : '279mm'
    });
    // The page width and page height are for A4.
    alert('report generation completed');

}

function getTodaysDate() {

    var rawDate = new Date();
    var day = rawDate.getDate();
    var month = rawDate.getMonth();
    var year = rawDate.getFullYear();
    var finaldate;

    if (rawDate.getDate() < 10) {
        day = "0" + day;
    }

    if (rawDate.getMonth() < 10) {
        month = "0" + month;
    }
    finaldate = day + "/" + month + "/" + year;

    return finaldate;

}