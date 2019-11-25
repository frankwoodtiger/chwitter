import * as AjaxUtils from "./ajaxUtils.js";

let bindTweetContextMenu = function () {
    $(document).on("click", ".tweet-menu-arrow", function() {
        // Since toggleClass depends on either the class's presence or the value of the state argument,
        // this will work assuming only fa-chevron-down is on the html by default.
        $(this).toggleClass("fa-chevron-down fa-chevron-up");
        $(this).siblings(".tweet-menu").toggleClass("hide");
    });
};

let bindNewTweetOnEnter = function () {
    $(document).on("keypress", ".new-tweet-msg", function(e) {
        if(e.which == 13) {
            let tweetInput = $(this);
            AjaxUtils.postWithCsrf("/newTweet",
                { message: tweetInput.val() },
                "POST",
                function(data) {
                    // only needed to wrap by $ if we need to do animation like hide or fade in
                    let newTweetDiv = $($.parseHTML(data));
                    newTweetDiv.hide()
                    tweetInput.closest(".row").after(newTweetDiv);
                    newTweetDiv.fadeIn("slow");
                    tweetInput.val("");
                });
        }
    });
};

let bindDeleteTweet = function () {
    $(document).on("click", ".tweet .delete", function() {
        let tweetRow = $(this).closest(".row");
        let tweetId = $(this).closest(".tweet").find("input[name=tweetId]").val();
        AjaxUtils.postWithCsrf(
            "/tweets/" + tweetId,
            "",
            "DELETE",
            function (data) {
                tweetRow.fadeOut("slow", function() {
                    $(this).remove();
                });
            });
    });
};

export function bindAll() {
    $(function () {
        bindNewTweetOnEnter();
        bindTweetContextMenu();
        bindDeleteTweet();
    });
}