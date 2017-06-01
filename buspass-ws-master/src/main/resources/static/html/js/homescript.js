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

    //TODO ajax call for reading categories
    loadAllCategories();

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

      $( function() {
                $("#dialog3").dialog({
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
                $("#dialog4").dialog({
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
       $("#dialog5").dialog({
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
          $("#dialog6").dialog({
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

    $('#centerTable').append('<tr><td><h5>Update Buyer Categories </h5></td><td>&nbsp;<button onclick="buyerCategories()">See More...</button></td></tr>');
    $('#centerTable').append('<tr><td><h5>Update Item Categories </h5></td><td>&nbsp;<button onclick="itemCategories()">See More...</button></td></tr>');
    $('#centerTable').append('<tr><td><h5>Update Action Events </h5></td><td>&nbsp;<button onclick="actionEvents()">See More...</button></td></tr>');
}

//******************** itemCATEGORIES ********************

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

        if(parseInt(maxDiscountValue) > 100){
                toastr.error("Max discount value can not be higher than 100%!");
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
                }
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
        loadAllCategories();
}

function loadAllCategories(){
    var subcat = new Array();
    var subsubcat = new Array();
    var getOver = false;
    $('#categories').html('<h4>Categories:<br></h4><br>');
    $.ajax({
            type: 'GET',
            url: 'itemCategory/getAll',
            dataType: 'json',
            success: function(data){
                if(data.length > 0){
                    console.log(data);
                    for(var i =0; i<data.length;i++){
                        getOver = false;

                        for(var r = 0; r<subcat.length;r++){ //Subcategory
                            if(subcat[r] == data[i].name)
                            getOver = true;
                        }

                        for(var e = 0; e<subsubcat.length;e++){ //SubSubcategory
                             if(subsubcat[e] == data[i].name)
                                getOver = true;
                        }

                        if(r == subcat.length && !getOver){
                            $('#categories').append('<h5><a href="#">*'+ data[i].name +'</a></h5>');
                            for(var j =i; j<data.length;j++){ //Do u have subcategory
                                if(data[j].superCategory == data[i].name){
                                    $('#categories').append('<h5>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">* '+ data[j].name +'</a></h5>');
                                    subcat.push(data[j].name);
                                    //treca podkat
                                    for(var z =j; z<data.length;z++){   //Do your subcategory have subcategory
                                         if(data[z].superCategory == data[j].name){
                                             $('#categories').append('<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">* '+ data[z].name +'</a></h5>');
                                             subsubcat.push(data[z].name);
                                         }
                                    }
                                }
                            }
                        }


                    }
                }
            }
    });
}

//******************** buyerCATEGORIES ********************

function buyerCategories(){
    $('#center').html('');
    $('#center').append('<br>');
    $('#center').append('<h5>Add new buyer category &nbsp; <input type="button" onclick="add2()" value="add"></h5>');
    $('#center').append('<h4>Preview of all buyer categories: </h4>');
    //TODO ajax call to list all categories
    loadAllBuyerCategories();
}

function add2(){
    $("#dialog3").dialog("open");
}

function addNewUserCategory(){
    var p = document.getElementsByName('dCode3');
    var codeValue = p[0].value;
    var i = document.getElementsByName('dName3');
    var nameValue = i[0].value;
    var j = document.getElementsByName('from3');
    var fromValue = j[0].value;
    var k = document.getElementsByName('to3');
    var toValue = k[0].value;
    var l = document.getElementsByName('coefficientC3');
    var coefficient = l[0].value;

   if(codeValue == "" || nameValue == "" || fromValue == "" || toValue =="" || coefficient == ""){
        toastr.error("Fields can not be empty!");
        return false;
   }

   if(parseInt(fromValue) > parseInt(toValue)){
        toastr.error("From value can not be higher than to value!");
        return false;
   }

   if(parseInt(coefficient) > 100){
           toastr.error("Coefficient can not be higher than 100%!");
           return false;
      }


   $.ajax({
        type: 'POST',
        url: 'buyerCategory/add',
        dataType: 'json',
        data: {code : codeValue, name : nameValue, from : fromValue, to : toValue, coefficient : coefficient},
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

    $("#dialog3").dialog('close');
    loadAllBuyerCategories();
}

function loadAllBuyerCategories(){
    $('#center').append('<table id="centerTable" border="1"><tr><th>&nbsp;&nbsp;Category code&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Category name&nbsp;&nbsp;</th><th>&nbsp;&nbsp;From value&nbsp;&nbsp;</th><th>&nbsp;&nbsp;To value&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Coefficient&nbsp;&nbsp;</th></tr></table>');
         $.ajax({
             type: 'GET',
             url: 'buyerCategory/getAll',
             dataType: 'json',
             success: function(data){
                 console.log(data);
                 if(data.length > 0){
                    for(var i =0; i<data.length; i++){
                        $('#centerTable').append('<tr><td>' + data[i].code + '&nbsp;</td><td>' + data[i].name + '&nbsp;</td><td>' + data[i].from + '$&nbsp;</td><td>' + data[i].to + ' $&nbsp;</td><td>' + data[i].coefficient + ' %&nbsp;</td><td><input type="button" onclick="editBuyerCategory(\''+ data[i].code +'\')" value="Edit Category"></td></tr>');
                    }
                 }
             },
        });
}

function editBuyerCategory(code){
    $("#dialog4").dialog("open");
    $.ajax({
        type: 'GET',
        url: 'buyerCategory/getOne',
        dataType: 'json',
        data: {code : code},
        success: function(data){
            $("#dCode4").val(code);
            $("#dCode4").prop('disabled', true);
            $("#dName4").val(data.name);
            $("#dName4").prop('disabled', true);
            $("#from4").val(data.from);
            $("#to4").val(data.to);
            $("#coefficientC4").val(data.coefficient);
            }
    });
}

function saveUserCategory(){
    var p = document.getElementsByName('dCode4');
    var codeValue = p[0].value;
    var i = document.getElementsByName('dName4');
    var nameValue = i[0].value;
    var j = document.getElementsByName('from4');
    var fromValue = j[0].value;
    var k = document.getElementsByName('to4');
    var toValue = k[0].value;
    var l = document.getElementsByName('coefficientC4');
    var coefficient = l[0].value;

   if(codeValue == "" || nameValue == "" || fromValue == "" || toValue =="" || coefficient == ""){
        toastr.error("Fields can not be empty!");
        return false;
   }

   if(parseInt(fromValue) > parseInt(toValue)){
        toastr.error("From value can not be higher than to value!");
        return false;
   }
    $.ajax({
        type: 'POST',
        url: 'buyerCategory/update',
        dataType: 'json',
        data: {code : codeValue, name : nameValue, from : fromValue, to : toValue, coefficient : coefficient},
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

        $("#dialog4").dialog('close');
        buyerCategories();

}

//******************** ACTION EVENTS ********************

function actionEvents(){
     $('#center').html('');
        $('#center').append('<br>');
        $('#center').append('<h5>Add new Action Event &nbsp; <input type="button" onclick="add3()" value="add"></h5>');
        $('#center').append('<h4>Preview of all Action Events: </h4>');
        //TODO ajax call to list all events
        loadEvents();
}

function add3(){
    $("#dialog5").dialog("open");
    $('#category5').html('');
          $.ajax({
                     type: 'GET',
                     url: 'itemCategory/getAll',
                     dataType: 'json',
                     success: function(data){
                         if(data.length > 0){
                            for(var i =0; i<data.length; i++){
                                $('#category5').append('<option value="'+ data[i].name +'">'+ data[i].name+'</option>');
                            }
                         }
                     },
                 });
}

function saveActionEvent(){
    var p = document.getElementsByName('dCode5');
    var codeValue = p[0].value;
    var i = document.getElementsByName('dName5');
    var nameValue = i[0].value;
    var j = document.getElementsByName('from5');
    var fromValue = j[0].value;
    var k = document.getElementsByName('to5');
    var toValue = k[0].value;
    var l = document.getElementsByName('coefficientC5');
    var coefficient = l[0].value;
    var i = document.getElementsByName('category5');
    var category = i[0];

    var categoriesList = getSelectValues(category);

    console.log(categoriesList);

    if(codeValue == "" || nameValue == "" || fromValue == "" || toValue =="" || coefficient == "" || category == null){
        toastr.error("Fields can not be empty!");
        return false;
    }

    if(parseInt(coefficient) > 100){
        toastr.error("Coefficient can not be higher than 100%!");
        return false;
    }

    var cat = JSON.stringify(categoriesList);

$.ajax({
            type: 'POST',
            url: 'actionEvent/add',
            dataType: 'json',
            data: {code : codeValue, name : nameValue, from : fromValue, to : toValue, coefficient : coefficient, category : cat},
            success: function(data){
                console.log(data);
            },
            complete: function(data){
            var response = data.responseText;
                console.log(response);
                if(response == "nill")
                    toastr.error("Bad request!");
                if(response == "codeErr")
                    toastr.error("Event with that code already exist!");
                if(response == "ok")
                    toastr.success("Event added!");
            }
        });

    $("#dialog5").dialog('close');
    actionEvents();

}

function loadEvents(){
    $('#center').append('<table id="centerTable" border="1"><tr><th>&nbsp;&nbsp;Event code&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Event name&nbsp;&nbsp;</th><th>&nbsp;&nbsp;From value&nbsp;&nbsp;</th><th>&nbsp;&nbsp;To value&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Coefficient&nbsp;&nbsp;</th><th>&nbsp;&nbsp;Categories effected&nbsp;&nbsp;</th></tr></table>');
             $.ajax({
                 type: 'GET',
                 url: 'actionEvent/getAll',
                 dataType: 'json',
                 success: function(data){
                     console.log(data);
                     if(data.length > 0){
                        for(var i =0; i<data.length; i++){
                            $('#centerTable').append('<tr><td>' + data[i].code + '&nbsp;</td><td>' + data[i].name + '&nbsp;</td><td>' + data[i].from + '&nbsp;</td><td>' + data[i].to + ' &nbsp;</td><td>' + data[i].discount + ' %&nbsp;</td><td>' + data[i].category + ' &nbsp;</td><td><input type="button" onclick="editEvent(\''+ data[i].code +'\')" value="Edit Category"></td></tr>');
                        }
                     }
                 },
            });
}

function editEvent(code){
$("#dialog6").dialog("open");
    $.ajax({
        type: 'GET',
        url: 'itemCategory/getAll',
        dataType: 'json',
        success: function(data){
            if(data.length > 0){
                for(var i =0; i<data.length; i++){
                    $('#category6').append('<option value="'+ data[i].name +'">'+ data[i].name+'</option>');
                }
            }
            $.ajax({
                type: 'GET',
                url: 'actionEvent/getOne',
                dataType: 'json',
                data: {code : code},
                success: function(data){
                                $("#dCode6").val(code);
                                $("#dCode6").prop('disabled', true);
                                $("#dName6").val(data.name);
                                //Dates and categories selectedc
                                console.log(data.from);

                                console.log(data.to);
                                $("#from6").val(data.from);
                                $("#to6").val(data.to);
                                $("#coefficientC6").val(data.discount);
                                var categories = data.category+ '';
                                var dataarray=categories.split(',');
                                $("#category6").val(dataarray);
                }
          });


        }
    });



}

function saveChangesEvent(){
     var p = document.getElementsByName('dCode6');
        var codeValue = p[0].value;
        var i = document.getElementsByName('dName6');
        var nameValue = i[0].value;
        var j = document.getElementsByName('from6');
        var fromValue = j[0].value;
        var k = document.getElementsByName('to6');
        var toValue = k[0].value;
        var l = document.getElementsByName('coefficientC6');
        var coefficient = l[0].value;
        var i = document.getElementsByName('category6');
        var category = i[0];


        var categoriesList = getSelectValues(category);

            console.log(categoriesList);

            if(codeValue == "" || nameValue == "" || fromValue == "" || toValue =="" || coefficient == "" || category == null){
                toastr.error("Fields can not be empty!");
                return false;
            }

            if(parseInt(coefficient) > 100){
                toastr.error("Coefficient can not be higher than 100%!");
                return false;
            }

            var cat = JSON.stringify(categoriesList);

        $.ajax({
                    type: 'POST',
                    url: 'actionEvent/edit',
                    dataType: 'json',
                    data: {code : codeValue, name : nameValue, from : fromValue, to : toValue, coefficient : coefficient, category : cat},
                    success: function(data){
                        console.log(data);
                    },
                    complete: function(data){
                    var response = data.responseText;
                        console.log(response);
                        if(response == "nill")
                            toastr.error("Bad request!");
                        if(response == "codeErr")
                            toastr.error("Event with that code already exist!");
                        if(response == "ok")
                            toastr.success("Event added!");
                    }
                });

            $("#dialog6").dialog('close');
            actionEvents();
}

function getSelectValues(select) {
  var result = [];
  var options = select && select.options;
  var opt;

  for (var i=0, iLen=options.length; i<iLen; i++) {
    opt = options[i];

    if (opt.selected) {
      result.push(opt.value || opt.text);
    }
  }
  return result;
}
