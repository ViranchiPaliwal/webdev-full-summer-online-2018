(function (){
	var $newUsername, $newPassword;
    var $removeBtn, $editBtn, $createBtn, $updateBtn, $searchBtn;
    var $newFirstName, $newLastName;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $newUsername = $('#new-username');
        $newPassword = $('#new-password');
        $newFirstName = $('#new-firstname');
        $newLastName = $('#new-lastname');
        $createBtn = $('#wbdv-create');
        $updateBtn = $('#wbdv-update');
        $searchBtn = $('#wbdv-search');
        $removeBtn = $('#wbdv-remove');
        $editBtn = $('#wbdv-edit');
    }
    function createUser() {  }
    function findAllUsers() {  }
    function findUserById() {  }
    function deleteUser() {  }
    function selectUser() {  }
    function updateUser() {  }
    function renderUser(user) {  }
    function renderUsers(users) {  }
});
