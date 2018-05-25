(function (){
	var $newUsername, $newPassword;
	var $removeBtn, $editBtn, $createBtn, $updateBtn, $searchBtn;
	var $newFirstName, $newLastName, $userRole;
	var $userRowTemplate, $tbody;
	var userService = new UserServiceClient();

	$(main);

    /***
     * runs after complete html loading
     */
	function main() {
		$createBtn = $('#wbdv-create');
		$updateBtn = $('#wbdv-update');
		$searchBtn = $('#wbdv-search');
		$editBtn = $('#wbdv-edit');
		$tbody = $('.wbdv-tbody');
		$userRowTemplate = $('.wbdv-template');
		$createBtn.click(createUser);
		$updateBtn.click(updateUser);
		findAllUsers();
	}

    /***
	 * Create user functionality
     */
	function createUser() {
		$newUsername = $('#new-username').val();
		$newPassword = $('#new-password').val();
		$newFirstName = $('#new-firstname').val();
		$newLastName = $('#new-lastname').val() ;
		$userRole = $('#roleFld').val() ;
		var newUser = new User(
				$newUsername,
				$newPassword,
				$newFirstName,
				$newLastName,
				$userRole,
				null, null, null
		);
		userService.createUser(newUser).then(refreshForm).then(findAllUsers);
	}

    /***
	 * Finds all users from database and then call render to show on ui
     */
	function findAllUsers() {
		userService.findAllUsers().then(renderUsers);
	}

    /***
	 * Calls user Service to find user by id
     * @param userId id of user whose information required
     * @returns server response
     */
	function findUserById(userId) {
		return userService.findUserById(userId);
	}

    /***
	 * Fires this function when user clicks on delete
     * @param event contains information regarding element pressed in ui
     */
	function deleteUser(event) {
		$removeBtn = $(event.currentTarget);
		var userId = $removeBtn.parent().attr('userId');
		userService.deleteUser(userId).then(findAllUsers);
	}

    /***
	 * Updates existing user information via calling service with user id
	 * and object
     */
	function updateUser() {
		$newUsername = $('#new-username').val();
		$newPassword = $('#new-password').val();
		$newFirstName = $('#new-firstname').val();
		$newLastName = $('#new-lastname').val() ;
		$userRole = $('#roleFld').val() ;
		var updatedUser = new User(
				$newUsername,
				$newPassword,
				$newFirstName,
				$newLastName,
				$userRole,
				null, null, null
		);
		var userId = $updateBtn.attr('userId');
		userService.updateUser(userId, updatedUser)
		.then(refreshForm)
		.then(findAllUsers);
	}

    /***
	 * Clears all ui fields of admin edits
     */
	function refreshForm(){
		$('#new-username').val('');
		$('#new-password').val('');
		$('#new-firstname').val('');
		$('#new-lastname').val('');
		$('#roleFld').val('') ;
	}

    /***
	 * Fill up admin fields with user info when admi  click on edit
     * @param user
     */
	function renderUser(user) {
		$('#new-username').val(user.username);
		$('#new-password').val(user.password);
		$('#new-firstname').val(user.firstName);
		$('#new-lastname').val(user.lastName);
		$('#roleFld').val(user.role) ;
		$updateBtn.attr('userId',user.id);
	}

    /***
	 * For editing user fetch information about it from server
     */
	function editUser() {
		$editBtn = $(event.currentTarget);
		var userId = $editBtn.parent().attr('userId');
		findUserById(userId).then(renderUser);
	}

    /***
	 * For each user creats row in table for ui
     * @param users All users fetched from server
     */
	function renderUsers(users) {
		$tbody.empty();
		for(i=0; i<users.length;i++){
			var structure = $userRowTemplate.clone();
			var user = users[i];
			structure.find('.wbdv-username').html(user.username);
			structure.find('.wbdv-first-name').html(user.firstName);
			structure.find('.wbdv-last-name').html(user.lastName);
			structure.find('.wbdv-role').html(user.role);
			structure.find('.wbdv-edit').click(editUser);
			structure.find('.wbdv-actions-span').attr('userId',user.id);
			structure.find('.wbdv-remove').click(deleteUser);
			$tbody.append(structure);
		}
	}
})();
