import * as AjaxUtils from "./ajaxUtils.js";

let bindSearchPotentialFollowers = function () {
    let followerSearchHandler = function () {
        let keyword = $(".who-to-follow input[type=text]").val();
        if (keyword !== "") {
            AjaxUtils.ajaxWithCsrf("/user/users", { keyword: keyword }, "GET",
                function (potentialUserDivs) {
                    $(".potential-followers").html(potentialUserDivs);
                });
        }
    };
    $(document).on("click", ".who-to-follow .pointer", followerSearchHandler);

    $(document).on("keypress", ".who-to-follow-text", function (e) {
        if (e.which === 13) {
            followerSearchHandler();
        }
    });
}

let bindFollow = function () {
    $(document).on("click", "button.follow-user", function () {
        let userId = $(this).data("userId");
        AjaxUtils.ajaxWithCsrf("/user/" + userId+ "/follow", {},"GET",
            data => {
                $(".followers").html(data);
                // perfect place to use arrow function here, 'this' is in the context of the parent caller such that
                // 'this' is button.follow-user
                $(this).parent().fadeOut("slow", function() { $(this).remove() });
            });
    });
}

let bindResendRegistrationConfirmation = function () {
    let toggleRegistrationConfirmation = function () {
        $(".confirm-registration-form").toggleClass("hide");
        $("#login-msg").remove();
        $("a#confirm-registration").closest("td").find(".fa-caret-up").toggleClass("hide");
        $("a#confirm-registration").closest("td").find(".fa-caret-down").toggleClass("hide");
    };
    $(document).on("click", "a#confirm-registration", function (e) {
        e.preventDefault();
        toggleRegistrationConfirmation();
    });
    $(document).on("click", "input#confirm-registration-submit", function () {
        AjaxUtils.ajaxWithCsrf("/user/refreshRegistrationToken", {
            username: $("#refresh-username").val()
        },
        "POST",
        data => {
            toggleRegistrationConfirmation();
            $("#confirm-registration-msg").addClass("hide");
        },
        data => {
            $("#confirm-registration-msg")
                .addClass("alert alert-warning")
                .removeClass("hide")
                .text(data.responseJSON.message);
        });
    });
}

export function bindAll() {
    $(function () {
        bindSearchPotentialFollowers();
        bindFollow();
        bindResendRegistrationConfirmation();
    });
}