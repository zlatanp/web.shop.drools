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

   $( function() {
          $("#dialog2").dialog({
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
    $('#center').append('<h5>Add new item category &nbsp; <input type="button" onclick="add()" value="add"></h5>');
    $('#center').append('<h4>Preview of all item categories: </h4>');
    //TODO ajax call to list all categories
    //$('#center').append('<div id="dialog" title="Modify artist"><p>Name</p><input name="name" type="text" id="Dname"><br><p>Surname</p><input name="surname" type="text" id="Dsurname"><br><p>Hit</p><input name="hit" type="text" id="Dhit"><br><br><button id="dialogModify">Save</button></div>');
    $('#center').append('<table id="centerTable" border="1"><tr><th>&nbsp;&nbsp;Category code&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Category name&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Supercategory&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Max discount&nbsp;&nbsp;</th></tr></table>');
     $.ajax({
                     type: 'GET',
                     url: 'itemCategory/getAll',
                     dataType: 'json',
                     success: function(data){
                     console.log(data);
                         if(data.length > 0){
                            for(var i =0; i<data.length; i++){
                                $('#centerTable').append('<tr><td>' + data[i].code + '&nbsp;</td><td>' + data[i].name + '&nbsp;</td><td>' + data[i].superCategory + '&nbsp;</td><td>' + data[i].maxDiscount + ' %&nbsp;</td><td><input type="button" onclick="editCategory(\''+ data[i].code +'\')" value="Edit Category"></td></tr>');
                            }
                         }
                     },
                 });
}

function add(){
     $("#dialog").dialog("open");
     $('#dSupCategory').html('<option value="null">No Super Category</option>');
      $.ajax({
                 type: 'GET',
                 url: 'itemCategory/getAll',
                 dataType: 'json',
                 success: function(data){
                     if(data.length > 0){
                        for(var i =0; i<data.length; i++){
                            $('#dSupCategory').append('<option value="'+ data[i].name +'">'+ data[i].name+'</option>');
                        }
                     }
                 },
             });
}

function addNewCategory(){
    var i = document.getElementsByName('dCode');
    var codeValue = i[0].value;
    var j = document.getElementsByName('dName');
    var nameValue = j[0].value;
    var k = document.getElementsByName('dMaxDiscount');
    var maxDiscountValue = k[0].value;
    var l = document.getElementsByName('dSupCategory');
    var supCategory = l[0].value;

    if(codeValue == "" || nameValue == "" || maxDiscountValue =="" || supCategory == ""){
        toastr.error("Fields can not be empty!");
        return false;
        }

    $.ajax({
            type: 'POST',
            url: 'itemCategory/add',
            dataType: 'json',
            data: {code : codeValue, name : nameValue, maxdiscount : maxDiscountValue, supercategory : supCategory},
            success: function(data){
                console.log(data);
            },
            complete: function(data){
            var response = data.responseText;
                console.log(response);
                if(response == "nill")
                    toastr.error("Bad request!");
                if(response == "codeErr")
                    toastr.error("Category with that code already exist!");
                if(response == "ok")
                    toastr.success("Category added!");
            }
        });

    $("#dialog").dialog('close');
    itemCategories();
}

function editCategory(code){
    $("#dialog2").dialog("open");
    $('#dSupCategory2').html('<option value="null">No Super Category</option>');
    $.ajax({
        type: 'GET',
        url: 'itemCategory/getAll',
        dataType: 'json',
        success: function(data){
            if(data.length > 0){
                for(var i =0; i<data.length; i++){
                    $('#dSupCategory2').append('<option value="'+ data[i].name +'">'+ data[i].name+'</option>');
                }
            }
            $.ajax({
                type: 'GET',
                url: 'itemCategory/getOne',
                dataType: 'json',
                data: {code : code},
                success: function(data){
                    $("#dCode2").val(code);
                    $("#dCode2").prop('disabled', true);
                    $("#dName2").val(data.name);
                    $("#dMaxDiscount2").val(data.maxDiscount);
                    $("#dSupCategory2").val(data.superCategory);
                },
          });


        }
    });
}

function updateCategory(){
    var i = document.getElementsByName('dCode2');
    var codeValue = i[0].value;
    var j = document.getElementsByName('dName2');
    var nameValue = j[0].value;
    var k = document.getElementsByName('dMaxDiscount2');
    var maxDiscountValue = k[0].value;
    var l = document.getElementsByName('dSupCategory2');
    var supCategory = l[0].value;

    if(codeValue == "" || nameValue == "" || maxDiscountValue =="" || supCategory == ""){
        toastr.error("Fields can not be empty!");
        return false;
    }

    $.ajax({
                type: 'POST',
                url: 'itemCategory/update',
                dataType: 'json',
                data: {code : codeValue, name : nameValue, maxdiscount : maxDiscountValue, supercategory : supCategory},
                success: function(data){
                    console.log(data);
                },
                complete: function(data){
                var response = data.responseText;
                    console.log(response);
                    if(response == "nill")
                        toastr.error("Bad request!");
                    if(response == "ok")
                        toastr.success("Category updated!");
                }
            });

        $("#dialog2").dialog('close');
        itemCategories();
}