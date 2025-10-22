/**
 * Reload current page without tags after url.
 */
function reloadPage(){
    // reload page without tags after following current location path
    window.location = `${window.location.origin}${window.location.pathname}`;
}