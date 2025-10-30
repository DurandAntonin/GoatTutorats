document.addEventListener("DOMContentLoaded", function() {
    init();
});


let buttons;
let apprenticeInformationSection;
let apprenticeRemarkTable;
let apprenticeMissionTable;

/**
 * Initialize components.
 */
function init(){
    // retrieve all buttons to select current displayed section
    buttons = document.querySelectorAll(".apprentice-section-selector button");

    // retrieve all apprentice information section
    apprenticeInformationSection = document.querySelectorAll('.sections .section');

    apprenticeRemarkTable = document.getElementById('table-remarks');
    apprenticeMissionTable = document.getElementById('table-missions');

    // hide all sections
    for (const section of apprenticeInformationSection) {
        section.classList.add('section-hidden');
    }

    // add onclick change displayed section event on each button
    for (const button of buttons) {
        button.addEventListener('click', function(event) {
            changeCurrentSectionDisplayed(event);
        });
        button.classList.add('section-not-clicked');
    }

    // click first button
    if (buttons.length > 0) {
        buttons[0].click();
    }
}

/**
 * Change current section displayed, hive others and highlight section name currently displayed.
 * Each section displays apprentice information such as (general information, mission information, etc.)
 * @param event
 */
function changeCurrentSectionDisplayed(event){
    // update background color for not selected button
    for (const button of buttons) {
        button.classList.remove('section-clicked');
        button.classList.add('section-not-clicked');
    }

    // update background for clicked button
    event.target.classList.remove('section-not-clicked');
    event.target.classList.add('section-clicked');

    // retrieve section id to display
    const sectionToDisplay = event.target.dataset.section;

    // hide other sections and display section associated to the button
    for (const section of apprenticeInformationSection) {
        if (section.id === sectionToDisplay) {
            section.classList.remove('section-hidden');
            section.classList.add('section-visible');
        }
        else{
            section.classList.add('section-hidden');
            section.classList.remove('section-visible');
        }
    }
}

/**
 * Add new mission entry to table.
 */
function addNewMissionEntry(){

    // create a new row
    const row = apprenticeMissionTable.getElementsByTagName("tbody")[0].insertRow(-1);
    row.classList.add('apprentice-mission');

    // retrieve number of missions already present
    const numberOfRemarks = document.querySelectorAll('.apprentice-mission');

    // create new inputs for this remark
    const missionTargetJob = createHtmlElement(
        'input',
        ['row-length-95'],
        [
            {name: 'name', value: `missions[${numberOfRemarks.length-1}].targetJob`},
            {name: 'required', value: true},
        ]
    );
    missionTargetJob.type = 'text';

    const missionKeywords = createHtmlElement(
        'textarea',
        ['row-length-95', 'textarea-row3'],
        [
            {name: 'name', value: `missions[${numberOfRemarks.length-1}].keywords`},
            {name: 'required', value: true},
            {name: 'row', value: 5},
        ]
    );

    const missionComments = createHtmlElement(
        'textarea',
        ['row-length-95', 'textarea-row3'],
        [
            {name: 'name', value: `missions[${numberOfRemarks.length-1}].comments`},
            {name: 'required', value: true},
            {name: 'row', value: 5},
        ]
    );

    // create cells and save row
    row.insertCell(0).appendChild(missionTargetJob);
    row.insertCell(1).appendChild(missionKeywords);
    row.insertCell(2).appendChild(missionComments);
}

/**
 * Add new remark entry to table.
 */
function addNewRemarkEntry(){

    // create a new row
    const row = apprenticeRemarkTable.getElementsByTagName("tbody")[0].insertRow(-1);
    row.classList.add('apprentice-note');

    // retrieve number of remarks already present
    const numberOfRemarks = document.querySelectorAll('.apprentice-note');

    // create new inputs for this remark
    const noteAuthor = createHtmlElement(
        'input',
        ['row-length-95'],
        [
            {name: 'name', value: `notes[${numberOfRemarks.length-1}].author`},
            {name: 'required', value: true},
        ]
    );
    noteAuthor.type = 'text';

    const noteComments = createHtmlElement(
        'textarea',
        ['row-length-95', 'textarea-row3'],
        [
            {name: 'name', value: `notes[${numberOfRemarks.length-1}].comments`},
            {name: 'required', value: true},
        ]
    );

    // create cells and save row
    row.insertCell(0).appendChild(noteAuthor);
    row.insertCell(1).appendChild(noteComments);
}

/**
 * Create html element based on provided configuration.
 * @param tagName Html tag name.
 * @param classList List of classes.
 * @param attributes List of attributes.
 * @returns {HTMLAnchorElement | HTMLElement | HTMLAreaElement | HTMLAudioElement | HTMLBaseElement | HTMLQuoteElement | HTMLBodyElement | HTMLBRElement | HTMLButtonElement | HTMLCanvasElement | HTMLTableCaptionElement | HTMLTableColElement | HTMLDataElement | HTMLDataListElement | HTMLModElement | HTMLDetailsElement | HTMLDialogElement | HTMLDivElement | HTMLDListElement | HTMLEmbedElement | HTMLFieldSetElement | HTMLFormElement | HTMLHeadingElement | HTMLHeadElement | HTMLHRElement | HTMLHtmlElement | HTMLIFrameElement | HTMLImageElement | HTMLInputElement | HTMLLabelElement | HTMLLegendElement | HTMLLIElement | HTMLLinkElement | HTMLMapElement | HTMLMenuElement | HTMLMetaElement | HTMLMeterElement | HTMLObjectElement | HTMLOListElement | HTMLOptGroupElement | HTMLOptionElement | HTMLOutputElement | HTMLParagraphElement | HTMLPictureElement | HTMLPreElement | HTMLProgressElement | HTMLScriptElement | HTMLSelectElement | HTMLSlotElement | HTMLSourceElement | HTMLSpanElement | HTMLStyleElement | HTMLTableElement | HTMLTableSectionElement | HTMLTableCellElement | HTMLTemplateElement | HTMLTextAreaElement | HTMLTimeElement | HTMLTitleElement | HTMLTableRowElement | HTMLTrackElement | HTMLUListElement | HTMLVideoElement}
 */
function createHtmlElement(tagName, classList, attributes) {
    const element = document.createElement(tagName);

    for (const classElem of classList) {
        element.classList.add(classElem);
    }

    for (const attribute of attributes) {
        element.setAttribute(attribute.name, attribute.value);
    }

    return element;
}

/**
 * Initializes Select2 on all elements with the class 'target-job-select'.
 * <p>
 * This enhances standard <select> elements with a searchable dropdown, a placeholder,
 * scrollable options, and allows clearing the selection.
 * </p>
 *
 * Behavior:
 * - width: 100% (fills the container)
 * - placeholder: "-- Sélectionner un métier --"
 * - allowClear: true (user can clear selection)
 * - maximumSelectionLength: 1 (single selection only)
 *
 * Requirements:
 * - jQuery must be loaded before this script
 * - Select2 library (JS + CSS) must be included
 *
 * Example HTML:
 * &lt;select class="target-job-select"&gt;
 *   &lt;option value=""&gt;&lt;/option&gt;
 *   &lt;option&gt;Consultant&lt;/option&gt;
 * &lt;/select&gt;
 *
 * Dependencies:
 * - jQuery
 * - Select2
 */
$(document).ready(function() {
    $(".target-job-select").select2({
        width: '100%',
        placeholder: "-- Sélectionner un métier --",
        allowClear: true,
        maximumSelectionLength: 1
    });
});
