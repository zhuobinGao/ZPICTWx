// JavaScript Document
function initMenu(){
	$("#my-menu").mmenu({
        // options
		extensions: ["pagedim-black","effect-menu-slide", "effect-listitems-slide"],
		counters: true,
		
		
		offCanvas: {
           position  : "left",
           zposition : "back"
        },
		 navbar	: {
			title: "菜单页"
		},
		 navbars: {
           content: [ "searchfield" ]
        },
		searchfield: {
           resultsPanel: true,
			placeholder: "菜单检索"
        }
	   
		 
     }, {
        // configuration
		searchfield: {
           clear: true
        },
		 
        classNames: {
           vertical: "expand"
        }
     });

	  var API = $("#my-menu").data( "mmenu" );
     console.info(1);
	  API.close();
	  console.info(2);
     $("#openMenu").click(function() {
        API.open();
     });
}

function initEditPwd(){
	
	$('#upd_save').click(function(){
		
		var uid = userid;
		
		if($('#mypwd1').val()!=$('#mypwd2').val()){
			alert("两次密码不一致！");
			return;
		}

		var mypwd = "";
		dwr.engine.setAsync(false);   
		EncrypDES.getenCord($('#mypwd1').val(),function(data){
			mypwd = data;
		});
		

		
		var param = {
				sqlID:"user_05",
				str0:mypwd,
				str1:userid,
				strCount:2
		}

		console.info(param);
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已修改用户密码");
				$('#myModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
		
		
		
	});
}


$(document).ready(function() {
      
	initMenu();
	initEditPwd();
	
/*
	  $("#my-menu .Vertical a").click(function() {
         API.close();
      });
*/


		










   });