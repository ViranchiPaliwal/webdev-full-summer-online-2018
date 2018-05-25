(function () {
    var $phone, $contact, $email, $role, $dob, $username;
    var $logoutBtn, $updateBtn;
    var userService = new UserServiceClient();
    $(main);

    /***
     * runs after complete html loading
     */
    function main() {
    	var userId = Number(window.location.href.split('=')[1]);
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

    /***
     * checks response status and updates ui fields
     * @param user response from server
     */
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

    /***
     * Logout user from the profile screen.
     */
    function logout() {
    	window.location.href='../login/login.template.client.html';
    }

    /***
     * give alert to user regarding unauthorized access
     */
    function unAuthorizedAccess(){
        alert('Unauthorized access. Kindly click ok button to sign in.')
        logout();
    }

    /***
     * updates user profile
     * @param takes user object as an input
     */
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
