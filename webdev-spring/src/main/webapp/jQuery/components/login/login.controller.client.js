(function () {
    var $usernameFld, $passwordFld, $unsuccessAlert;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
    	$loginBtn =$('#wbdv-login');
    	$loginBtn.click(login);
        $unsuccessAlert = $('.wbdv-unsuccess');
    }
    function login() { 
    	$usernameFld = $('#usernameFld').val();
    	$passwordFld = $('#passwordFld').val();
        $unsuccessAlert.hide();

        var newUser = new User($usernameFld,
            $passwordFld,
            null, null, null, null, null, null);

        userService.login(newUser)
            .then(showStatus);
    }

    function showStatus(user){
        if(user==null){
            $unsuccessAlert.html('Sign in unsuccessful. Entered credentials not found.');
            $unsuccessAlert.show();
        }
        else{
            window.location.href='../profile/profile.template.client.html?userId='+user.id;
        }
    }
})();
