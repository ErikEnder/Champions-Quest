function justKidding() {
  let age = document.getElementById("age").value;
  let email = document.getElementById("email").value;
  const regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}', 'g')

  if (regex.test(email)) {
    if (age === "minor"){
      alert("Wait, what?  Why are you here?  Go away.");
    }
    else {
      alert("This doesn't actually contact me.  I can't believe you fell for that.")
    }
  }
  else {
    alert("That's no email...")
  }
}
