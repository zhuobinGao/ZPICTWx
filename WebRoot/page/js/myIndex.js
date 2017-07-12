
function initEditUser(){
	$('#upd_save').click(function(){
		if(houseID==""){
			alert("请选择一条数据!");
			return ;
		}
		
		if($('#upd_pwd1').val()!=$('#upd_pwd2').val()){
			alert("两次密码不一致！");
			return;
		}

		var mypwd = "";
		dwr.engine.setAsync(false);   
		EncrypDES.getenCord($('#upd_pwd1').val(),function(data){
			mypwd = data;
		});
		

		
		var param = {
				sqlID:"user_03",
				str0:$('#upd_name').val(),
				str1:mypwd,
				str2:$('#upd_ck').val(),
				str3:$('#upd_group').val(),
				str4:$('#upd_logid').val(),
				strCount:5
		}

		console.info(param);
		PreStatement.updateData(param,function(data){
			if("OK"==data){
				alert("已更新用户信息,请手工刷新!");
				$('#eidtModal').modal('hide');
			}else{
				alert("错误："+data);
			}
			
		});
		
		
		
		
		
	});
}


