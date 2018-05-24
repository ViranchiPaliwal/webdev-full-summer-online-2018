function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.register = register;
	this.url = 'http://localhost:8080/api/user';
	this.registerUrl = 'http://localhost:8080/api/register';
	
	var self = this;
	function createUser(newUser, callback) {
		return fetch(self.url,{
			method: 'post',
			body: JSON.stringify(newUser),
			headers:{
				'content-type':'application/json'
			}
		});
	}
	function findAllUsers(callback) {
		return fetch(self.url)
		.then(function(response){
			return response.json();
		});
	}

	function findUserById(userId, callback){
		return fetch(self.url+'/'+userId)
		.then(function(response){
			return response.json();
		});
	}
	function updateUser(userId, updatedUser, callback){
		return fetch(self.url+'/'+userId,{
			method: 'put',
			body: JSON.stringify(updatedUser),
			headers:{
				'content-type':'application/json'
			}
		})
		.then(function(response){
			return response.json();
		});
	}
	function deleteUser(userId, callback) {
		return fetch(self.url+'/'+userId,{
			method: 'delete'
		});
	}
    function register(newUser, callback) {
    	return fetch(self.registerUrl,{
			method: 'post',
			body: JSON.stringify(newUser),
			headers:{
				'content-type':'application/json'
			}
		});
    }
}
