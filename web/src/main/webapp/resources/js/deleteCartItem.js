function deleteCartItem(url) {
    $.ajax({
        type: "DELETE",
        url: url
    })
}