function addToCompareList(phoneId, url) {
    $.post({
        url: url,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify({
            phoneId: phoneId
        }),
        dataType: 'json'
    })
}