
export function constructCsrfHeader() {
    let csrfFieldName = $("meta[name='_csrf_header']").attr("content");
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = {};
    csrfHeader[csrfFieldName] = csrfToken;
    return csrfHeader;
}

export function postWithCsrf(url, data, method, successCallback) {
    $.ajax({
        url: url,
        headers: constructCsrfHeader(),
        method: method,
        data: data,
        success: successCallback
    });
}