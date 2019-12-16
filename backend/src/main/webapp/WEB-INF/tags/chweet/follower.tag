<%@attribute name="follower" required="true" type="com.chi.chwitter.mapper.UserDTO" %>

<div class="d-flex p-2 m-2 align-items-center">
    <div><a href="/${follower.username}/chweets">${follower.username}</a></div>
</div>