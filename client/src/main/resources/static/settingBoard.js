$(document).ready(function() {
    $.ajax({
        url: "game/getGame",
        dataType: 'json',
        context: document.body
    }).done(function( msg ) {
        $('#player1Name').text(msg.playerList[0].name);
        $('#player2Name').text(msg.playerList[1].name);
    });
});

$(document).on('click', "div.emptyField", function() {
        $(this).addClass("shipOn");
        $(this).removeClass("emptyField");
});

$(document).on('click', "div.shipOn", function() {
    $(this).removeClass("shipOn");
    $(this).addClass("emptyField");
});

$(document).on('click', "button.submitBoard", function() {
    var fields;
    $(".shipOn").each(function( index ) {
        var dataPos = $(this).attr("fieldID");
        console.log("datapos: "+dataPos);
        if(fields==null)
            fields = dataPos;
        else
            fields = fields+","+dataPos;
    });

    $.ajax({
        url: "board/validate",
        data : {jObject: fields},
        context: document.body
    }).done(function( msg ) {
        console.log(msg);
        if(msg=="OK"){
            alert("Statek zostanie dodany do planszy");
            updateShips();
        }
        else
        {
            alert("W tym przypadku statek nie może zostać utworzony");
        }
    });
});

function updateShips () {
    $(".shipOn").each(function( index ) {
        $(this).removeClass("shipOn");
        $(this).addClass("shipFixed");
    });
}



$("#playGameButton").click(function() {
    var fields = new Array();
    $(".shipOn").each(function( index ) {
        var dataPos = $(this).attr("fieldid");
        fields.push(dataPos);
    });
    var jObject={};
    jObject = JSON.stringify(fields);
    $.ajax({
        url: "fields/save",
        data : {jObject: jObject},
        context: document.body
    }).done(function( msg ) {
        if(msg=="OK"){
            //TODO: SWITCH TO PAGE GAMEPLAY vel BOARD
        }
    });
});


