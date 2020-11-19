$(function() {

    $('body').on('click', '.footer', function () {

        console.log('Clicked');

        $.ajax({
            type: 'POST',
            url: '/ajax/main',
            data: {},

            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
            },

            success: function (responseData) {
                console.log('success');

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('error');
            }

        }).always(function (responseData) {
            console.log('always');
        });


    });

});