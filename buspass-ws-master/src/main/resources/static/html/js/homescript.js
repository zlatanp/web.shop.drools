var x = document.cookie;
var parts = x.split("username@");
var second = parts[1];

var username;
var type;
var pom = second.split("#");
type = pom[0];
username = pom[1];


$(document).ready(function(){
   $('#usernamePlaceholder').html('<span class="glyphicon glyphicon-user"></span> ' + username);
   if(type === "manager")
        managerOnload();

   $( function() {
       $("#dialog").dialog({
            autoOpen: false,
            show: {
                effect: "blind",
                duration: 500
                },
            hide: {
                effect: "explode",
                duration: 500
                }
       });

   });
});

function logout(){
    document.cookie = "username" + '@; Max-Age=0';
    window.location.href="index.html"
}

function profile(){
    $('#center').html('');

    $.ajax({
        type: 'GET',
        url: 'user/info',
        dataType: 'json',
        data: {username : username},
        success: function(data){
            $('#center').append('<h3>Name: ' + data.name +  '</h3><br>');
            $('#center').append('<h3>Surname: ' + data.surname +  '</h3><br>');
            $('#center').append('<h6>Username: ' + data.username +  '</h6><br>');
            $('#center').append('<h6>Password: ' + data.password +  '</h6><br>');
            $('#center').append('<h6>Type: ' + data.type +  '</h6><br>');
            $('#center').append('<h6>Buyer Profile: ' + data.buyerProfile +  '</h6><br>');
            $('#center').append('<h6>ID: ' + data.id +  '</h6><br>');
            $('#center').append('<h6>Date of registration: ' + String(new Date(data.dateOfRegistration).subtractdHours(2)) +  '</h6><br>');
        },
    });
}

Date.prototype.subtractdHours= function(h){
    this.setHours(this.getHours()-h);
    return this;
}

//Manager things

function managerOnload(){
    $('#center').html('');
    $('#center').html('<table id="centerTable"></table>');

    $('#centerTable').append('<tr><td><h5>Update Buyer Categories </h5></td><td>&nbsp;<button>Update</button></td></tr>');
    $('#centerTable').append('<tr><td><h5>Update Item Categories </h5></td><td>&nbsp;<button onclick="itemCategories()">Update</button></td></tr>');
    $('#centerTable').append('<tr><td><h5>Update Action Events </h5></td><td>&nbsp;<button>Update</button></td></tr>');
}

function itemCategories(){
    $('#center').html('');
    $('#center').append('<br>');
    $('#center').append('<h5>Add new item category &nbsp; <input type="button" onclick="aa()" id="addNewCategory" value="add"></h5>');
    $('#center').append('<h4>Preview of all item categories: </h4>');
    //TODO ajax call to list all categories
    //$('#center').append('<div id="dialog" title="Modify artist"><p>Name</p><input name="name" type="text" id="Dname"><br><p>Surname</p><input name="surname" type="text" id="Dsurname"><br><p>Hit</p><input name="hit" type="text" id="Dhit"><br><br><button id="dialogModify">Save</button></div>');
}

function aa(){
     $("#dialog").dialog("open");
}