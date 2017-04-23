/**
 * Created by christos_georgiadis on 18/04/2017.
 */

$(document).ready(function(){
    $('.selectpicker').selectpicker();

    $('#loginForm').on('submit', function(event){
        event.preventDefault();

        var search = {};
        search["username"] = $("#username").val();
        search["password"] = $("#password").val();

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/modalLogin",
            data : JSON.stringify(search),
            timeout : 100000,
            success : function() {
                window.location.href = "/user/games";
            },
            error : function() {
                $('#failureMessage').show();
            }
        });
    });

    $('#myModal').on('hidden.bs.modal', function () {
        $(this)
            .find("input")
            .val('')
            .end()
            .find('#failureMessage')
            .hide()
            .end();
    });

});

