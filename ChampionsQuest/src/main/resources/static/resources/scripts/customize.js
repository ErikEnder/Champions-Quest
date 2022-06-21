// // Function allows you to change party member models
// function modelSelect(value) {
//     // Array contains links to different model images for easy swapping
//     const models = ["resources/images/animations/maleKnight.gif", "resources/images/animations/femaleKnight.gif", "resources/images/animations/modelWizard.gif", "resources/images/animations/modelHound.gif"];
//
//     // Based on the value from the radios, changes character model
//     switch(value) {
//         // First four cases check for Main Character/Avatar inputs
//         case "amale":
//             $("#avatar").attr("src", models[0])
//             break;
//         case "afemale":
//             $("#avatar").attr("src", models[1])
//             break;
//         case "awizard":
//             $("#avatar").attr("src", models[2])
//             break;
//         case "adog":
//             $("#avatar").attr("src", models[3])
//             break;
//
//         // Last four cases check for Side Character/Best Friend inputs
//         case "bmale":
//             $("#best-friend").attr("src", models[0])
//             break;
//         case "bfemale":
//             $("#best-friend").attr("src", models[1])
//             break;
//         case "bwizard":
//             $("#best-friend").attr("src", models[2])
//             break;
//         case "bdog":
//             $("#best-friend").attr("src", models[3])
//             break;
//
//         // Not sure how someone would break the switch since it uses static values, but returns this just in-case that somehow happens.
//         default:
//             console.log("I don't know how, but you broke it.");
//     }
// }
//
//
// function buyItem(value) {
//         $("#price" + value + "").text("SOLD!");
//         $("#item" + value + "").text("SOLD OUT!");
// }
//
// $(document).ready(function() {
//
//     $('input[name="avatar_model"]').on('click', function(e) {
//         modelSelect($('input[name="avatar_model"]:checked').val());
//     })
//
//     $('input[name="friend_model"]').on('click', function(e) {
//         modelSelect($('input[name="friend_model"]:checked').val());
//     })
//
//     $('.buy-button').on('click', function() {
//         buyItem($(this).attr("value"));
//     })
// });
