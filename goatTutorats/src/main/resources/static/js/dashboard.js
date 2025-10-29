document.addEventListener("DOMContentLoaded", function() {
    init();
});

let selectYear;
let btnSelectYear;

/**
 * Initialise components.
 */
function init() {
    // retrieve button to select academic year
    btnSelectYear = document.getElementById("btn-select-year");

    // retrieve year selection input
    selectYear = document.getElementById("select-year");

    // add event to click button when select is updated
    selectYear.addEventListener("change", function() {
        btnSelectYear.click();
    })
}