(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
    	$loginBtn =$('#wbdv-login');
    	$loginBtn.click(login);		
    }
    function login() { 
    	$usernameFld = $('#usernameFld').val();
    	$passwordFld = $('#passwordFld').val();

        var newUser = new User($usernameFld,
            $passwordFld,
            null, null, null, null, null, null);

        userService.login(newUser)
            .then(showStatus);
    }

    function showStatus(user){
        if(user==null){
            alert('user not found');
        }
        else{
            alert('Success');
        }
    }
})();
