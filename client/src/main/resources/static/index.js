/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//$(document).on('click', "button-register-player",
$("#registerPlayerButton").click(function() {
    var playersName = $( "#playersNameInput" ).val();
    // alert("Rejestrujemy nowego gracza, wysyłamy info do serwera-klienta- dostajemy listę aktywnych pokoi");
    $("#playerRegisteredInfo").append(playersName);
    $("#playerRegisteredInfo").css("visibility","visible");
    $("#playersNameInput").css("display", "none");
    $("#registerPlayerButton").css("display", "none");
    $("#addNewRoomButton").prop('disabled', false);
    $("table.table").css("display", "table");
    $.ajax({
        url: "http://localhost:8082/get/rooms",
        dataType: 'json',
        context: document.body
      }).done(function( msg ) {
        for (i = 0; i < msg.length; i++) {
            var playLabel = "<a href='setBoard'>Graj</a>";
            if(msg[i].roomStatus=="OCCUPIED") playLabel = "<a>Zajęte</a>";


            $("table.table").children("tbody").append("<tr>\n" +
                "<th scope=\"row\">"+msg[i]._id+"</th>\n" +
                "<td>"+msg[i].name+"</td>\n" +
                "<td>"+msg[i].player1.name+"</td>\n" +
                "<td></td>\n" +
                "<td>"+playLabel+"</td>\n" +
                "</tr>")
            //alert("id: " + msg[i].id+"; name: " + msg[i].name+"; player:" + msg[i].player);
        }
            // alert( "Data get: id: " + msg.id+"; name: "+msg.name );
    });
});

$("#addNewRoomButton").click(function() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8082/add/room",
        data: 'player='+$( "#playersNameInput" ).val(),
        dataType: 'json',
        context: document.body
    }).done(function( msg ) {
        $("table.table").children("tbody").empty();
        for (i = 0; i < msg.length; i++) {
            $("table.table").children("tbody").append("<tr>\n" +
                "<th scope=\"row\">"+msg[i].id+"</th>\n" +
                "<td>"+msg[i].name+"</td>\n" +
                "<td>"+msg[i].player+"</td>\n" +
                "<td></td>\n" +
                "<td><a href='setBoard'>Graj</td>\n" +
                "</tr>")
            // alert("id: " + msg[i].id+"; name: " + msg[i].name+"; player:" + msg[i].player);
        }
    });
    // alert("Wysyłamy info do serwera-klienta, dostajemy z powrotem listę pokoi(?)");
});