/**
 * Open popup, displays it and blur background.
 * @param popupId
 */
function openPopup(popupId){
    // retrieve popup html component
    const popupComponent = document.getElementById(popupId);
    if (popupComponent === undefined) {
        return;
    }

    // display popup
    popupComponent.classList.add('section-visible');
    popupComponent.classList.remove('section-hidden');

    // blur background
    const mainContent = document.querySelector('main');
    mainContent.classList.add('blurred');
}

/**
 * Close popup, hide it and unblur background.
 * @param event
 */
function closePopup(event){
    // retrieve popup html component
    const popupId = event.dataset.popupId;
    const popupComponent = document.getElementById(popupId);

    if (popupComponent === undefined) {
        return;
    }

    // hide popup
    popupComponent.classList.remove('section-visible');
    popupComponent.classList.add('section-hidden');

    // unblur background
    const mainContent = document.querySelector('main');
    mainContent.classList.remove('blurred');
}