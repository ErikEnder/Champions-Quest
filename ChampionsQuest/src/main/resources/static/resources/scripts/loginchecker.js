$(document).ready(function() {
    if (window.location.href.indexOf("?error") > -1) {
        $(".failed-ver").text("Invalid e-mail and/or password.");
    }

    if (window.location.href.indexOf("registerfail") > -1) {
        $(".failed-ver").text("An account with that e-mail already exists.");
    }
})