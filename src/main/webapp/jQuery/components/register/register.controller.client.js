(function () {
	var $usernameFld, $passwordFld, $verifyPasswordFld, $unsuccessAlert;
	var $registerBtn;
	var userService = new UserServiceClient();
	$(main);

	function main() { 
		$registerBtn = $('#wbdv-register');
		$registerBtn.click(register);
        $unsuccessAlert = $('.wbdv-unsuccess');
	}
	function register() {
		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();
        $verifyPasswordFld = $('#verifyPasswordFld').val();
        $unsuccessAlert.hide();

        if($passwordFld!=$verifyPasswordFld){
            $unsuccessAlert.html('Registration unsuccessful. Entered passwords does not match');
            $unsuccessAlert.show();
			return;
		}
		var newUser = new User($usernameFld,
				$passwordFld,
				null, null, null, null, null, null);

		userService.register(newUser).then(showStatus);

	}
	function showStatus(response){
		if(response){
            window.location.href='../profile/profile.template.client.html?userId='+response.id;
        }
		else{
            $unsuccessAlert.html('Registration unsuccessful. User with these credentials already exist.');
            $unsuccessAlert.show();
        }
	}
	function success(response){
		console.log('done');
	}
	function error(response){
		console.log('errrorr');
	}
})();
