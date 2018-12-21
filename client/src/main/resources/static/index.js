$(document).ready(function() {
    setInterval("getAllRooms()",5000);
});

$("#registerPlayerButton").click(function() {
    registerPlayer();
    getAllRooms();
});

function registerPlayer() {
    $.post("player/add", {name: $("#playersNameInput").val()}, function (msg) {
        if (msg.response == "OK") {
            playerRegisteredFollowings();
        }
    })
};

function playerRegisteredFollowings() {
    var playersName = $( "#playersNameInput" ).val();
    $("#playerRegisteredInfo").append(playersName);
    $("#playerRegisteredInfo").css("visibility","visible");
    $("#playersNameInput").css("display", "none");
    $("#registerPlayerButton").css("display", "none");
    $("#addNewRoomButton").prop('disabled', false);
    $("table.table").css("display", "table");
};


$("#addNewRoomButton").click(function() {
    $.post( "game/add", {name: $("#playersNameInput" ).val()}, function( msg ) {
        if(msg.response=="OK") {
            // getAllRooms();
            window.location.href = "waitingRoom";
        }
    });
});

function getAllRooms() {
    console.log("Pobieramy pokoje");
    $.ajax({
        url: "game/getAll",
        dataType: 'json',
        context: document.body
    }).done(function( msg ) {
        addAllRooms(msg)
    });
};

function addAllRooms(allRooms) {
    $("table.table").children("tbody").empty();
    for (i = 0; i < allRooms.length; i++) {
        var playLabel = '<button class="btn btn-outline-secondary" type="button" onclick="play('+$.trim(allRooms[i].id)+')">Graj</button>';
        if(allRooms[i].gameStatus=="READY") playLabel = "<a>Zajęte</a>";
        var player2name = "-";
        if(allRooms[i].playerList[1]!=null) player2name = allRooms[i].playerList[1].name;
        $("table.table").children("tbody").append("<tr>\n" +
            "<th scope=\"row\">"+allRooms[i].id+"</th>\n" +
            "<td>"+allRooms[i].playerList[0].name+"</td>\n" +
            "<td>"+player2name+"</td>\n" +
            "<td>"+playLabel+"</td>\n" +
            "</tr>")
    }
}

function play(gameIdParam)
{
    $.post( "game/join", {gameId: gameIdParam}, function( msg ) {
        if(msg.response=="OK") {
            window.location.href = "setBoard";
        }
        else
        {
            console.log("Cos nie działa. Response: "+msg.response);
        }
    });
}
