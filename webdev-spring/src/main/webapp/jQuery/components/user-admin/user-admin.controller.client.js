(function (){
	var $newUsername, $newPassword;
    var $removeBtn, $editBtn, $createBtn, $updateBtn, $searchBtn;
    var $newFirstName, $newLastName, $userRole;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();

    $(main);

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
        userService.createUser(newUser).then(findAllUsers);
    }
    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }
    function findUserById(userId) {
        return userService.findUserById(userId);
    }
    function deleteUser(event) {
        $removeBtn = $(event.currentTarget);
        var userId = $removeBtn.parent().attr('userId');
        userService.deleteUser(userId).then(findAllUsers);
    }
    function selectUser() {  }
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

    function refreshForm(){
        $('#new-username').val('');
        $('#new-password').val('');
        $('#new-firstname').val('');
        $('#new-lastname').val('');
        $('#roleFld').val('') ;
    }
    function renderUser(user) {
        $('#new-username').val(user.username);
        $('#new-password').val(user.password);
        $('#new-firstname').val(user.firstName);
        $('#new-lastname').val(user.lastName);
        $('#roleFld').val(user.role) ;
        $updateBtn.attr('userId',user.id);
    }
    function editUser() {
        $editBtn = $(event.currentTarget);
        var userId = $editBtn.parent().attr('userId');
        findUserById(userId).then(renderUser);
    }
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
