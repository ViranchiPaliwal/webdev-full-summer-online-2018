function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.register = register;
    this.login = login;
    this.updateProfile = updateProfile;
    this.url = '/api/user';
	this.registerUrl = '/api/register';
    this.loginUrl = '/api/login';
    this.profileUrl = '/api/profile';


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
                if(response.status==200){
                    return response.json();
                }
                else{
                    return null;
                }
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
            if(response.status==200){
                return response.json();
            }
            else{
                return null;
            }
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
		}).then(function(response){
			if(response.status==200){
				return response.json();
			}
			else{
				return null;
			}
		});
    }

    function success(response){}
    function error(response){}
    function login(newUser, callback) {
        return fetch(self.loginUrl,{
            method: 'post',
            body: JSON.stringify(newUser),
            headers:{
                'content-type':'application/json'
            }
        }).then(function(response){
            if(response.status==200){
                return response.json();
            }
            else{
                return null;
            }
        });
    }

    function updateProfile(updatedUser){
        return fetch(self.profileUrl,{
            method: 'put',
            body: JSON.stringify(updatedUser),
            headers:{
                'content-type':'application/json'
            }
        }).then(function(response){
            return response.json();
        });
	}
}
