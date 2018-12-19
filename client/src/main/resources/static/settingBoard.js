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
        url: "http://localhost:8082/board/validate",
        data : {jObject: fields},
        context: document.body
    }).done(function( msg ) {
        console.log(msg);
        if(msg=="OK"){
            alert("było ok");
            updateShips();
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
        url: "http://localhost:8080/fields/save",
        data : {jObject: jObject},
        context: document.body
    }).done(function( msg ) {
        if(msg=="OK"){
            //TODO: SWITCH TO PAGE GAMEPLAY vel BOARD
        }
    });
});


