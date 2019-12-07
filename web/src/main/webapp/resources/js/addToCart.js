function addToCart(phoneId, url) {
    $.post({
        url: url,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify({
            phoneId: phoneId,
            quantity: $('#' + phoneId).val()
        }),
        dataType: 'json',
        success: function (response) {
            if(response['errorMessage'] === null) {
                $('#error' + phoneId).css("display", "none");
            }
            else {
                let errorMessage = $('#error' + phoneId);
                errorMessage.text(response['errorMessage']);
                errorMessage.show();
            }
        }
    })
}