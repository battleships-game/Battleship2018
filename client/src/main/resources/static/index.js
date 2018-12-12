$("#registerPlayerButton").click(function() {
    var playersName = $( "#playersNameInput" ).val();
    $("#playerRegisteredInfo").append(playersName);
    $("#playerRegisteredInfo").css("visibility","visible");
    $("#playersNameInput").css("display", "none");
    $("#registerPlayerButton").css("display", "none");
    $("#addNewRoomButton").prop('disabled', false);
    $("table.table").css("display", "table");
    $.ajax({
        url: "http://localhost:8082/room/getAll",
        dataType: 'json',
        context: document.body
      }).done(function( msg ) {
        for (i = 0; i < msg.length; i++) {
            var playLabel = "<a href='setBoard'>Graj</a>";
            if(msg[i].roomStatus=="OCCUPIED") playLabel = "<a>Zajęte</a>";
            console.log(msg[i]._id+" "+msg[i].player1.name)

            $("table.table").children("tbody").append("<tr>\n" +
                "<th scope=\"row\">"+msg[i]._id+"</th>\n" +
                "<td>"+msg[i].name+"</td>\n" +
                "<td>"+msg[i].player1.name+"</td>\n" +
                "<td></td>\n" +
                "<td>"+playLabel+"</td>\n" +
                "</tr>")
        }
    });
});

$("#addNewRoomButton").click(function() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8082/room/add",
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
        }
    });
});