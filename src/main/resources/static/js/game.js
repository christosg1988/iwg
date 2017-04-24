/**
 * Created by christos_georgiadis on 24/04/2017.
 */

var game_id = 0;

$(document).ready(function(){
    $('#tryButton').click(function() {
        $.ajax({
            type : "POST",
            url : "/user/play",
            data : 'gameId=' + game_id,
            dataType: "json",
            timeout : 100000,
            success : function(data) {

                if(parseInt(data) <= 0){
                    var x = document.getElementById("snackbar")
                    x.className = "show";
                    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                }
                else{
                    $('#gamePlay').show(500);

                    if($('#gameMessage').is(":visible")){
                        hidePage();
                        setTimeout(showPage, 3000);
                    }
                    else {
                        setTimeout(showPage, 3000);
                    }

                    $("html, body").animate({ scrollTop: $(document).height() }, "slow");
                }
            },
            error : function() {

            }
        });
    });
});

function showPage() {
    document.getElementById("loader").style.display = "none";
    document.getElementById("gameMessage").style.display = "block";
}

function hidePage() {
    document.getElementById("loader").style.display = "block";
    document.getElementById("gameMessage").style.display = "none";
}

function getGameId(gameID){
    game_id = gameID;
}
