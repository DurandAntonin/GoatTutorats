window.onload = init

let buttons;
let apprenticeInformationSection;

function init(){
    // retrieve all buttons to select current displayed section
    buttons = document.querySelectorAll(".apprentice-section-selector button");

    // retrieve all apprentice information section
    apprenticeInformationSection = document.querySelectorAll('.sections .section');

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