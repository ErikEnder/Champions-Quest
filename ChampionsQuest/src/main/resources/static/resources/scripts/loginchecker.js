$(document).ready(function() {
    if (window.location.href.indexOf("?error") > -1) {
        $(".failed-ver").text("Invalid e-mail and/or password.");
    }

    if (window.location.href.indexOf("registerfailed") > -1) {
        $(".failed-ver").text("An account with that e-mail already exists.");
    }

    if (window.location.href.indexOf("invalidinfo") > -1) {
        $(".failed-ver").text("Custom input too long.");
    }

    if (window.location.href.indexOf("charnameerror") > -1) {
        $(".failed-ver").text("Character name too long.");
    }
})