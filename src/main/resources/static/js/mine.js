$(document).ready(function () {
    $('.editBtn').on('click', function (event) {
        event.preventDefault();
        $.get($(this).attr('href'), function (user) {
            $('#id1').val(user.id);
            $('#firstname1').val(user.firstName);
            $('#lastname1').val(user.lastName);
            $('#age1').val(user.age);
            $('#email1').val(user.email);
        });
        $('#editUser').modal();
    });

    $('.deleteBtn').on('click', function (event) {
        event.preventDefault();
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