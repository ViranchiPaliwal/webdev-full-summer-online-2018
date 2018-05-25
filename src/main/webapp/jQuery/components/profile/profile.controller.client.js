(function () {
    var $phone, $contact, $email, $role, $dob, $username, $isLegitAlert;
    var $logoutBtn, $updateBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
    	var userId = Number(window.location.href.split('=')[1]);
        $isLegitAlert = $('.wbdv-alert');
    	if(userId) {
            userService.findUserById(userId).then(showProfile);
            $updateBtn = $('#wbdv-update');
            $updateBtn.click(updateProfile);
            $logoutBtn = $('#wbdv-logout');
            $logoutBtn.click(logout);
        }
        else{
            unAuthorizedAccess();
        }
    }

    function showProfile(user){
        if(user==null){
            unAuthorizedAccess();
            return;
        }
        $('#wbdv-username').val(user.username);
        $('#wbdv-phone').val(user.phone);
        $('#wbdv-email').val(user.email);
        $('#wbdv-role').val(user.role);
        if(user.dateOfBirth){
        $('#wbdv-dob').val(user.dateOfBirth.split('T')[0]);
        }
    }

    function logout() {
    	window.location.href='../login/login.template.client.html';
    }

    function unAuthorizedAccess(){
        alert('Unauthorized access. Kindly click ok button to sign in.')
        logout();
    }

    function updateProfile(user){

        $username = $('#wbdv-username').val();
    	$contact = $('#wbdv-phone').val();
    	$email = $('#wbdv-email').val();
        $role = $('#wbdv-role').val();
        $dob = $('#wbdv-dob').val();

        var newUser = new User(
            $username,
            null, null, null,
            $role,
            $contact,
            $email,
            $dob);

        userService.updateProfile(newUser)
            .then(showProfile)
            .then(function(){
                alert('Profile updated successfully.');
            });
    }
})();
