$(document).ready(function() {
    var space = 1;
    for (var r = 0; r < 10; r++) {
        var col = "";
        for (var c = 0; c < 10; c++) {
            col += "<td data-pos='" + space + "'><div class='emptyField rounded field' fieldID=" + space + "'></div></td>";
            space++;
        }
        $("#playersBoardTable").append("<tr>" + col + "</tr>");
    }
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
    var fields = new Array();
    $(".shipOn").each(function( index ) {
        var dataPos = $(this).attr("fieldID");
        console.log("datapos: "+dataPos);
        fields.push(dataPos);
    });
    var jObject={};
    jObject = JSON.stringify(fields);

    $.ajax({
        url: "http://localhost:8080/fields/validate",
        data : {jObject: jObject},
        context: document.body
    }).done(function( msg ) {
        if(msg=="OK"){
            alert("było ok");
            //TODO ENABLE BUTTON PLAY GAME BUTTON
        }
    });
});

$("#playGameButton").click(function() {
    var fields = new Array();
    $(".shipOn").each(function( index ) {
        var dataPos = $(this).attr("fieldID");
        fields.push(dataPos);
    });
    var jObject={};
    jObject = JSON.stringify(fields);
    $.ajax({
        url: "http://localhost:8080/fields/save",
        data : {jObject: jObject},
        context: document.body
    }).done(function( msg ) {
        if(msg=="OK"){
            //TODO SWITCH TO PAGE GAMEPLAY vel BOARD
        }
    });
});


