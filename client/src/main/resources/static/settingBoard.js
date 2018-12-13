$(document).on('click', "div.emptyField", function() {
    $(this).addClass("shipOn");
    $(this).removeClass("emptyField");
});

$(document).on('click', "div.shipOn", function() {
    $(this).removeClass("shipOn");
    $(this).addClass("emptyField");
});



