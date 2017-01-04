$.ajaxSetup({
	contentType : 'application/json'
});

var presentUser = null;
var authenticatedUser = null;

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



function displayAllMessageFromUsername(username) {
	$.get('api/message?username=' + username, function(messages) {
		$('#messageBox').empty();
		for (var i = 0; i < messages.length; i++) {
			appendMessage(messages[i]);
		}
	});
}

 $.get('/api/user/auth', function(authenticated) {
	 authenticatedUser = authenticated;
 });

function appendMessage(message) {
	var bodyWithEmoticons = createEmoticons(message.body);
	if(message.senderId === authenticatedUser){
    var date = new Date(message.created);
	var messageBody = $('<small class="col-lg-10">' + bodyWithEmoticons + '</small>'), receiverOrSender = $('<h5 class="media-heading">'
			+ message.senderId + '</h5>'), messageTime = $('<small class="pull-right time"><i class="fa fa-clock-o"></i>'
			+ ' ' + date.toLocaleString() + '</small>');
	var picture = $('<div class="pull-left"></div>').append('<img class="media-object" alt="32x32" style="width: 32px; height: 32px;" src="http://www.iconsfind.com/wp-content/uploads/2016/10/20161014_58006be216aa3.png">');
	var div = $('<div class="media-body"></div>').append(messageTime, receiverOrSender, messageBody);
	var majorDiv = $('<div id="singleMsg" class="media msg "></div>').append(picture, div);
	
	$('#messageBox').append(majorDiv);
	
	majorDiv.on('click', function(){
		$(majorDiv).empty();
		$(majorDiv).remove();
		$.ajax({
		    url: 'api/message?id=' + message.id,
		    type: 'DELETE',
		});
	});
	
	$('#messageBox').scrollTop($('#messageBox')[0].scrollHeight);
	}
	
	
	else {
		var date = new Date(message.created);
		var messageBody = $('<small class="col-lg-10">' + bodyWithEmoticons + '</small>'), receiverOrSender = $('<h5 class="media-heading">'
				+ message.senderId + '</h5>'), messageTime = $('<small class="pull-right time"><i class="fa fa-clock-o"></i>'
				+ ' ' + date.toLocaleString() + '</small>');
		var picture = $('<div class="pull-left"></div>').append('<img class="media-object" alt="32x32" style="width: 32px; height: 32px;" src="http://www.iconsfind.com/wp-content/uploads/2015/08/20150831_55e46b12d72da.png">');
		var div = $('<div class="media-body"></div>').append(messageTime, receiverOrSender, messageBody);
		var majorDiv = $('<div id="singleMsg" class="media msg "></div>').append(picture, div);
		
		$('#messageBox').append(majorDiv);
		
		majorDiv.on('click', function(){
			$(majorDiv).empty();
			$(majorDiv).remove();
			$.ajax({
			    url: 'api/message?id=' + message.id,
			    type: 'DELETE',
			});
		});
		
		$('#messageBox').scrollTop($('#messageBox')[0].scrollHeight);
		}	
}


//$(function() {
//	$('#messageBox').on('click', '#singleMsg', function(){
//		var element = $(this);
//	    console.log('clicked');
//		$('#del-popup').show();
//		$('#yes-btn').click(function(){
//			$(element).empty();
//			$(element).remove();
//			$('#del-popup').hide();
//		});
//		$('#no-btn').click(function() {
//			$('#del-popup').hide();
//		});
//	});
//	})

function appendUsers(user) {
	var userName = $('<div class="media-body"></div>').append('<h5 class="media-heading">'
			+ user + '</h5> <small>send new message</small>');
	var picture = $('<div class="pull-left"></div>').append('<img class="media-object" alt="48x48" style="width: 48px; height: 48px;" src="http://www.iconsfind.com/wp-content/uploads/2015/08/20150831_55e46b12d72da.png">');
	var majorDiv = $('<div class="media conversation"></div>').append(picture, userName);
	$('#users').append(majorDiv);
	
	userName.click(function() {
//		 setInterval(function() {
			 displayAllMessageFromUsername(user);
//		 }, 3000);
		
		presentUser = user;
	});
};

function createEmoticons(input){
	return input.replace(/:D/g, '<img alt=":D" style="width: 15px; height: 15px;" src="http://www.freeiconspng.com/uploads/happy-emoticons-png-6.png" />')
		.replace(/:\)/g, '<img alt=":)" style="width: 15px; height: 15px;" src="http://www.freeiconspng.com/uploads/emoticons-png-20.png" />')
		.replace(/:\(/g, '<img alt=":(" style="width: 15px; height: 15px;" src="http://www.freeiconspng.com/uploads/sad-emoticons-png-8.png" />');
						 
};


$(function() {
	$('#send-btn').click(function() {
        var msgValue = $('textarea').val();
        if(msgValue.trim() !== ""){
            createMessage();
        }
        $('textarea').val('');
	});
})

$(function() {
$('textarea').keypress(function(e) {

	var msgValue = $('textarea').val();

	if (e.which == 13 && msgValue.trim() === "") {
		e.preventDefault();
	} else if (e.which == 13) {
		createMessage();
        $('textarea').val('');
	}
});
})


function createMessage() {
	var message = {
		receiverId : presentUser,
		body : $('textarea').val(),
		senderId : authenticatedUser
	};
	$.post('/api/message', JSON.stringify(message), function(data) {
		appendMessage(data);
	}, 'json');

}