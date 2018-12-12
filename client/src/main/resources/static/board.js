$(document).ready(function() {
    var space = 1;
    for (var r = 0; r < 10; r++) {
        var col = "";
        for (var c = 0; c < 10; c++) {
            col += "<td data-pos='" + space + "'><div class='emptyField rounded' dataX='" + c + "' dataY='" + r + "'></div></td>";
            space++;
        }
        $("#playersBoardTable").append("<tr>" + col + "</tr>");
    }
    var space2 = 1;
    for (var r = 0; r < 10; r++) {
        var col2 = "";
        for (var c = 0; c < 10; c++) {
            col2 += "<td data-pos='" + space2 + "'><div class='enemyField rounded' dataX='" + c + "' dataY='" + r + "'></div></td>";
            space2++;
        }
        $("#enemyBoardTable").append("<tr>" + col2 + "</tr>");
    }
});

$(document).on('click', "div.enemyField", function() {
    var dataX = $(this).attr("dataX");
    var dataY = $(this).attr("dataY");
    alert("Wysyłamy to pole do serwera-klienta: " + "x= " + dataX + ", y= " + dataY);
});

