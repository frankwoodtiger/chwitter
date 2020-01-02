<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="showEditChweet" required="true" type="java.lang.Boolean" %>

<c:if test="${showEditChweet}">
    <div class="row">
        <div class="chweet w-100 p-2 mb-2 mx-auto border border-dark">
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
</c:if>