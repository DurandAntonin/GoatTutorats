function reloadPage(event){
    event.preventDefault();
    window.location = `${window.location.origin}${window.location.pathname}`;
}