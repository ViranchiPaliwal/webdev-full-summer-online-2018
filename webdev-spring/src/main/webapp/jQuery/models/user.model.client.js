function User(username, password, firstName, lastName, username, firstName, lastName, phone, email, role, dateOfBirth) {
	this.username = username;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phone = phone;
	this.email = email;
	this.role = role;
	this.dateOfBirth = dateOfBirth;

	this.setUsername = setUsername;
	this.getUsername = getUsername;
	this.setfirstName = setfirstName;
	this.getfirstName = getfirstName;
	this.setlastName = setlastName;
	this.getlastName = getlastName;
	this.setPhone = setPhone;
	this.getPhone = getPhone;
	this.setEmail = setEmail;
	this.getEmail = getEmail;
	this.setRole = setRole;
	this.getRole = getRole;

	function setUsername(username) {
		this.username = username;
	}

	function getUsername() {
		return this.username;
	}

	function setfirstName(firstName) {
		this.firstName = firstName;
	}

	function getfirstName() {
		return this.firstName;
	}

	function setlastName(lastName) {
		this.lastName = lastName;
	}

	function getlastName() {
		return this.lastName;
	}

	function setPhone(phone) {
		this.phone = phone;
	}

	function getPhone() {
		return this.phone;
	}

	function setEmail(email) {
		this.email = email;
	}

	function getEmail() {
		return this.email;
	}

	function setRole(role) {
		this.role = role;
	}

	function getRole() {
		return this.role;
	}
}
