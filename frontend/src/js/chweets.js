import * as AjaxUtils from "./ajaxUtils.js";

let bindChweetContextMenu = function () {
    $(document).on("click", ".chweet-menu-arrow", function() {
        // Since toggleClass depends on either the class's presence or the value of the state argument,
        // this will work assuming only fa-chevron-down is on the html by default.
        $(this).toggleClass("fa-chevron-down fa-chevron-up");
        $(this).siblings(".chweet-menu").toggleClass("hide");
    });
};

let bindNewChweetOnEnter = function () {
    $(document).on("keypress", ".new-chweet-msg", function(e) {
        if(e.which == 13) {
            let chweetInput = $(this);
            AjaxUtils.postWithCsrf("/newChweet",
                { message: chweetInput.val() },
                "POST",
                function(data) {
                    // only needed to wrap by $ if we need to do animation like hide or fade in
                    let newChweetDiv = $($.parseHTML(data));
                    newChweetDiv.hide()
                    chweetInput.closest(".row").after(newChweetDiv);
                    newChweetDiv.fadeIn("slow");
                    chweetInput.val("");
                });
        }
    });
};

let bindDeleteChweet = function () {
    $(document).on("click", ".chweet .delete", function() {
        let chweetRow = $(this).closest(".row");
        let chweetId = $(this).closest(".chweet").find("input[name=chweetId]").val();
        AjaxUtils.postWithCsrf(
            "/chweets/" + chweetId,
            "",
            "DELETE",
            function (data) {
                chweetRow.fadeOut("slow", function() {
                    $(this).remove();
                });
            });
    });
};

export function bindAll() {
    $(function () {
        bindNewChweetOnEnter();
        bindChweetContextMenu();
        bindDeleteChweet();
    });
}