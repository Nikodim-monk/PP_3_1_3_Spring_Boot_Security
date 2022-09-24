/**
 *
 * */
$(document).ready(function () {
    $("body").on('click', '.editBtn', function (event) {
        event.preventDefault ? event.preventDefault() : (event.returnValue = false);
        let href =$(this).attr('href');
        $.get(href, function (user) {
            $('#id1').val(user.id);
            $('#firstname1').val(user.firstName);
            $('#lastname1').val(user.lastName);
            $('#age1').val(user.age);
            $('#email1').val(user.email);
            console.log(user.id,href)
        });
        $('#editUser').modal();

    });

    $('body').on('click', '.deleteBtn', function (event) {
        event.preventDefault ? event.preventDefault() : (event.returnValue = false);
        $.get($(this).attr('href'), function (user) {
            $('#id2').val(user.id);
            $('#firstname2').val(user.firstName);
            $('#lastname2').val(user.lastName);
            $('#age2').val(user.age);
            $('#email2').val(user.email);
        });
        $('#deleteUser').modal();
    });
});