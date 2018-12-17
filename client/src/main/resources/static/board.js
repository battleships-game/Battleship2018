$(document).on('click', "div.enemyField", function() {
    var fieldID = $(this).attr("fieldid");
    alert("Wysyłamy to pole do serwera-klienta: " + ". Nr pola: "+fieldID);
});

