import * as AjaxUtils from "./ajaxUtils.js";
import {constructCsrfHeader} from "./ajaxUtils";

let bindChweetContextMenu = function () {
    $(document).on("click", ".chweet-menu-arrow", function() {
        // Since toggleClass depends on either the class's presence or the value of the state argument,
        // this will work assuming only fa-chevron-down is on the html by default.
        $(this).toggleClass("fa-chevron-down fa-chevron-up");
        $(this).siblings(".chweet-menu").toggleClass("hide");
    });
};

let validateNewTweetInput = function () {
    let $chweetInput = $(".new-chweet-msg");
    if ($chweetInput.val() !== "") {
        $chweetInput.closest(".chweet-msg")
            .addClass("border-dark")
            .removeClass("border-danger");
        return true;
    }
    $chweetInput.closest(".chweet-msg")
        .addClass("border-danger")
        .removeClass("border-dark");
    return false
};

let newTweetSuccessHandler = function ($chweetInput, data) {
    // only needed to wrap by $ if we need to do animation like hide or fade in
    let $newChweetDiv = $($.parseHTML(data));
    $newChweetDiv.hide()
    $chweetInput.closest(".row").after($newChweetDiv);
    $newChweetDiv.fadeIn("slow");
    $chweetInput.val("");
};

let bindNewChweetOnEnter = function () {
    $(document).on("keypress", ".new-chweet-msg", function(e) {
        if (e.which === 13) {
            let $chweetInput = $(this);
            if (validateNewTweetInput()) {
                AjaxUtils.postWithCsrf("/newChweet",
                    { message: $chweetInput.val() },
                    "POST",
                    function(data) {
                        newTweetSuccessHandler($chweetInput, data)
                    });
            }
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
}

let bindLinkImageToChweet = function () {
    $(document).on("click", ".chweet .upload-image .fa-camera-retro", function() {
        $(this).closest(".chweet").find("input[type=file]").trigger("click");
    });

    $(document).on("change", ".chweet .upload-image input[type=file]", function() {
        if (this.value !== "") {
            let fileData = $(".chweet .upload-image input[type=file]").prop('files')[0];
            let fileReader = new FileReader();
            let $chweet =  $(this).closest(".chweet");
            fileReader.addEventListener("load", function () {
                $chweet.find(".preview-image").removeClass("hide")
                    .append($("<img/>").attr("src", fileReader.result));
                $chweet.find(".fa-camera-retro").addClass("hide");
                $chweet.find(".file-name").removeClass("hide").text(fileData.name);
                $chweet.find(".confirm-image").removeClass("hide");
                $chweet.find(".clear-image").removeClass("hide");
            }, false);
            // https://developer.mozilla.org/en-US/docs/Web/API/FileReader/readAsDataURL
            fileReader.readAsDataURL(fileData);
        }
    });

    $(document).on('click', ".chweet .upload-image .clear-image", function () {
        resetImageControl($(this));
    });

    $(document).on('click', ".chweet .upload-image .confirm-image", function () {
        if (validateNewTweetInput()) {
            let formData = new FormData();
            let fileData = $('.chweet .upload-image input[type=file]').prop('files')[0];
            formData.append("image", fileData); // must match @RequestParam name on the controller in this case, image

            let $chweetInput = $(".new-chweet-msg");
            let tweetMsg = $chweetInput.val();
            formData.append("message", tweetMsg);

            $.ajax({
                url: '/newChweet', // point to server-side controller method
                dataType: 'text', // what to expect back from the server
                cache: false,
                contentType: false,
                headers: AjaxUtils.constructCsrfHeader(),
                processData: false,
                data: formData,
                type: "POST",
                success: function (data) {
                    newTweetSuccessHandler($chweetInput, data)
                    resetImageControl($chweetInput);
                }
            });
        }
    });

    let resetImageControl = function ($currentDomElement) {
        let $chweet =  $currentDomElement.closest(".chweet");
        $chweet.find("input[type=file]").val("");
        $chweet.find(".preview-image").addClass("hide").empty();
        $chweet.find(".fa-camera-retro").removeClass("hide");
        $chweet.find(".file-name").addClass("hide").val("");
        $chweet.find(".confirm-image").addClass("hide");
        $chweet.find(".clear-image").addClass("hide");
    };
};

export function bindAll() {
    $(function () {
        bindNewChweetOnEnter();
        bindChweetContextMenu();
        bindDeleteChweet();
        bindLinkImageToChweet();
    });
}