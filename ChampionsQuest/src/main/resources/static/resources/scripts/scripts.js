/**
 * Function controlling Contact form functionality
 */
function justKidding() {
  let age = document.getElementById("age").value;
  let email = document.getElementById("email").value;
  const regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}', 'g')

  // Tests if the e-mail is formatted properly (does not test for validity)
  if (regex.test(email)) {
    // If user is below 18
    if (age === "minor"){
      alert("Wait, what?  Why are you here?  Go away.");
    }
    // If user is above 18
    else {
      alert("This doesn't actually contact me.  I can't believe you fell for that.")
    }
  }
  // If e-mail is not properly formatted
  else {
    alert("That's no email...")
  }
}
