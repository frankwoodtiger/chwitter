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
            function (data) {
                $(".followers").html(data);
            });
    });
}

export function bindAll() {
    $(function () {
        bindSearchPotentialFollowers();
        bindFollow();
    });
}