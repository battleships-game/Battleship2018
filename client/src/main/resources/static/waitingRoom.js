$(document).ready(function() {
    setInterval("checkForOtherPlayer()",5000);
});


function checkForOtherPlayer() {
  $.ajax({
   type: "GET",
   url: "http://localhost:8082/game/checkForGame",
   success: function(msg){
       console.log(msg)
       if(msg.response=="READY")
           window.location.href = "http://localhost:8082/setBoard";
   }
 });
}