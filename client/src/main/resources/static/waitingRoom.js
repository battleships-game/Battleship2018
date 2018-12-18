$(document).ready(function() {
    setInterval("checkForOtherPlayer()",5000);
});


function checkForOtherPlayer() {
  $.ajax({
   type: "GET",
   url: "http://localhost:8082/gamegame/wait",
   success: function(msg){
       console.log(msg)
       if(msg.response=="OK")
           goToGame();
   }
 });
}

function goToGame() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8082/player/getRoomId",
        success: function(msg){
            window.location.href = "http://localhost:8082/setBoard?r="+msg.id;
        }
    });

}