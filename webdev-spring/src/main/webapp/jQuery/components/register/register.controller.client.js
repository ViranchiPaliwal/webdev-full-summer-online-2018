(function () {
	var $usernameFld, $passwordFld, $verifyPasswordFld;
	var $registerBtn;
	var userService = new UserServiceClient();
	$(main);

	function main() { 
		$registerBtn = $('#wbdv-register');
		$registerBtn.click(register);
	}
	function register() {
		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();

		var newUser = new User($usernameFld,
            				$passwordFld,
							null, null, null, null, null, null);
		userService.register(newUser);
		
	}
})();
