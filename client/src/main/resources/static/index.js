$("#registerPlayerButton").click(function() {
    var playersName = $( "#playersNameInput" ).val();
    $("#playerRegisteredInfo").append(playersName);
    $("#playerRegisteredInfo").css("visibility","visible");
    $("#playersNameInput").css("display", "none");
    $("#registerPlayerButton").css("display", "none");
    $("#addNewRoomButton").prop('disabled', false);
    $("table.table").css("display", "table");
    getAllRooms();
});

$("#addNewRoomButton").click(function() {
    $.post( "http://localhost:8082/room/add", {name: $("#playersNameInput" ).val()}, function( msg ) {
        if(msg=="OK") {
            getAllRooms();
        }
    });
});

function getAllRooms() {
    $.ajax({
        url: "http://localhost:8082/room/getAll",
        dataType: 'json',
        context: document.body
    }).done(function( msg ) {
        addAllRooms(msg)
    });
}

function addAllRooms(allRooms) {
    $("table.table").children("tbody").empty();
    for (i = 0; i < allRooms.length; i++) {
        var playLabel = "<a href='setBoard'>Graj</a>";
        if(allRooms[i].roomStatus=="OCCUPIED") playLabel = "<a>Zajęte</a>";
        var player2name = "-";
        if(allRooms[i].player2!=null) player2name = allRooms[i].player2.name;
        $("table.table").children("tbody").append("<tr>\n" +
            "<th scope=\"row\">"+allRooms[i]._id+"</th>\n" +
            "<td>"+allRooms[i].player1.name+"</td>\n" +
            "<td>"+player2name+"</td>\n" +
            "<td>"+playLabel+"</td>\n" +
            "</tr>")
    }
}