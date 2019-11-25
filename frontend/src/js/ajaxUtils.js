
export function postWithCsrf(url, data, method, successCallback) {
    let csrfFieldName = $("meta[name='_csrf_header']").attr("content");
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = {};
    csrfHeader[csrfFieldName] = csrfToken;
    $.ajax({
        url: url,
        headers: csrfHeader,
        method: method,
        data: data,
        success: successCallback
    });
}