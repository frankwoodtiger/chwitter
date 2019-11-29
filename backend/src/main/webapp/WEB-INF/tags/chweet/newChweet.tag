<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
    <div class="chweet col-xs-10 col-md-5 p-2 m-2 mx-auto border border-dark">
        <div class="chweet-msg m-1 border border-dark">
            <input type="text" name="new-chweet-msg" class="new-chweet-msg border-0 p-2"  placeholder="What's up" />
        </div>
        <div class="p-2 upload-image">
            <i class="pointer fas fa-camera-retro fa-lg" title="upload image"></i>
            <input class="hide" type="file" name="image" />
            <div class="hide p-2 file-name"></div>
            <div class="hide p-2 preview-image">

            </div>
            <button type="button" class="hide confirm-image btn btn-success">Confirm</button>
            <button type="button" class="hide clear-image btn btn-danger">Clear</button>
        </div>
    </div>
</div>