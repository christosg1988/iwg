/**
 * Created by christos_georgiadis on 24/04/2017.
 */

var game_id = 0;

$(document).ready(function(){
    $('#tryButton').click(function() {
        $.ajax({
            type : "POST",
            url : "/user/try",
            data : 'gameId=' + game_id,
            dataType: "json",
            timeout : 100000,
            success : function(data) {
                var result = parseInt(data);

                if(result == 0){
                    var x = document.getElementById("snackbar")
                    x.className = "show";
                    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                }
                else{
                    $('#gamePlay').show(500);

                    if($('#gameMessage').is(":visible")){
                        hidePage();
                    }

                    if(result == 1){
                        setTimeout(showWinPage, 3000);
                    }
                    else {
                        setTimeout(showLosePage, 3000);
                    }

                    $("html, body").animate({ scrollTop: $(document).height() }, "slow");
                }
            },
            error : function() {

            }
        });
    });

    $('#playButton').click(function() {
        $.ajax({
            type : "POST",
            url : "/user/play",
            data : 'gameId=' + game_id,
            dataType: "json",
            timeout : 100000,
            success : function(data) {

                $('#gamePlay').show(500);

                if($('#gameMessage').is(":visible")){
                    hidePage();
                }

                setTimeout(function() {showGameResultAndUpdateBalance(data['flag'], data['balance'])}, 3000);

                $("html, body").animate({ scrollTop: $(document).height() }, "slow");
            },
            error : function() {

            }
        });
    });
});

function showWinPage() {
    document.getElementById("loader").style.display = "none";
    document.getElementById("gameMessage").style.display = "block";
    document.getElementById("gameResult").innerHTML = "You win!!!";

}

function showLosePage() {
    document.getElementById("loader").style.display = "none";
    document.getElementById("gameMessage").style.display = "block";
    document.getElementById("gameResult").innerHTML = "You lose..."

}

function showGameResultAndUpdateBalance(result, balance) {
    document.getElementById("loader").style.display = "none";
    document.getElementById("gameMessage").style.display = "block";

    if(result == 1){
        document.getElementById("gameResult").innerHTML = "You win!!!"
    }
    else{
        document.getElementById("gameResult").innerHTML = "You lose..."
    }

    document.getElementById("balance").innerHTML = balance;
}

function hidePage() {
    document.getElementById("loader").style.display = "block";
    document.getElementById("gameMessage").style.display = "none";
}

function getGameId(gameID){
    game_id = gameID;
}
