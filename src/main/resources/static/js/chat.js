$.ajaxSetup({
	contentType : 'application/json'
});

var presentUser;

$.get('/api/message', function(message) {
	for (var i = 0; i < message.length; i++) {
		appendMessage(message[i]);
	}
});

$.get('/api/user', function(allUsers) {
	for (var i = 0; i < allUsers.length; i++) {
		appendUsers(allUsers[i]);
	}
});

setInterval(function() {
	$.get('api/message/all');
}, 3000);

function displayAllMessageFromUsername(username) {
	$.get('api/message?username=' + username, function(messages) {
		$('#messages').empty();
		for (var i = 0; i < messages.length; i++) {
			appendMessage(messages[i]);
		}
	});
}

// function(){
// $('#checkbox').click(function(){
// $(document).keypress(function(e) {
// if(e.which == 13) {
// alert('You pressed enter!');
// }
// });
// });

$.get('/api/user/auth', function(authenticatedUser) {
	$('#header').append('You are logged in as ' + authenticatedUser);
});

function appendMessage(message) {
	$('#messages').append(
			'<li class="list-group-item">' + message.body + '</li>')
}

function appendUsers(user) {
	var currentUser = $('<li class="list-group-item list-group-item-warning">'
			+ user + '</li>');
	$('#users').append(currentUser);
	currentUser.click(function() {
		displayAllMessageFromUsername(user);
		$('#title').empty();
		$('#title').append(user);
		presentUser = user;
	});
};

$(function() {
	$('#sendBtn').click(function() {
		createMessage();
	});
})

function createMessage() {
	var message = {
		senderId : $('#senderId').val(),
		receiverId : presentUser,
		body : $('#body').val()
	};
	$.post('/api/message', JSON.stringify(message), function(data) {
		appendMessage(data);
	}, 'json');

}