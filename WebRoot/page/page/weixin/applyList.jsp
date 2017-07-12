<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="com.weixin.mesModel.HttpRequest" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <title>预付款预警</title>
        <!-- 引入 WeUI -->
        <link rel='stylesheet prefetch' href='https://res.wx.qq.com/open/libs/weui/1.1.0/weui.min.css'>

<style>
	body{
    background-color: #efeff4;
}
.weui-label{
    width: 5em;
}
.weui_select{
    padding-left: 0;
}
</style>

</head>
<body>
<%
	String code = request.getParameter("code");
	Map<String,String> map = HttpRequest.getOAuth(code);
	

 %>





	<div class="weui-tab" id="tab">
		<div class="weui-navbar"> 
			<div class="weui-navbar__item" id="t_apply">预警申请</div> 
			<div class="weui-navbar__item" id="t_list">预警列表</div> 
		 </div>
	
	
		<div class="weui-tab__panel">
			<div class="weui-tab__content">
				
					
					<div class="weui-cells weui-cells_form">
					
						
						
							<div class="weui-cell">
								<div class="weui-cell__hd">
					            	<label class="weui-label">微信号</label>
					        	</div>
					        	<div class="weui-cell__bd">
				            		<input class="weui-input" name="name"  type="text"  value="<%=map.get("nickname") %>" disabled  />
				        		</div>
					        	
							</div>
						
							<div class="weui-cell">
								
				    			<div class="weui-cell__hd">
					            	<label class="weui-label">申请人</label>
					        	</div>
					        	
					        	 <div class="weui-cell__bd">
				            		<input class="weui-input" name="name" type="text" id="wxname" placeholder="请在此输入申请人姓名"  />
				        		</div>
				    			
				    		</div>
						
						
				    	
				    	<div class="weui-cells__title">选择预警单位</div>
				    	
				    	<div class="weui-cell">
				    		<div class="weui-cell__hd">
					            <label class="weui-label">预警单位</label>
					        </div>
					        <div class="weui-cell__bd">
				                    <select class="weui-select" name="select2" id="yjdw">
				                    	
				                    </select>
				                </div>
				    	</div>
				    	
				    	<div class="weui-cell">
					        <div class="weui-cell__hd">
					            <label class="weui-label">预警金额</label>
					        </div>
					        
					        <div class="weui-cell__bd">
				                    <input class="weui-input" id="yjje" type="number" pattern="[0-9]*" placeholder="预警金额">
				            </div>
					       
					        
				    	</div>
				    	
				    	<div class="weui-cell weui-cell_vcode">
				                <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
				                <div class="weui-cell__bd">
				                    <input class="weui-input" type="text" id="yzm"  placeholder="请输入验证码">
				                </div>
				                <div class="weui-cell__ft">
				                    <img class="weui-vcode-img" id="img"  onclick="javascript:changeImg()">
				                </div>
				            </div>
				    	
				    	
					</div>	
				
					<div class="weui-btn-area">
				            <a class="weui-btn weui-btn_primary" href="javascript:" id="apply">提交申请</a>
				    </div>
			
			</div>
			
			
			<div class="weui-tab__content " > 
				
				
	
				<div class="weui-cells" id="apply_list">
					            <a class="weui-cell weui-cell_access" id="a0" >
					                <div class="weui-cell__bd">
					                    <p >无</p>
					                </div>
					                <div class="weui-cell__ft"></div>
					            </a>
					            <a class="weui-cell weui-cell_access" id="a1" >
					                <div class="weui-cell__bd">
					                    <p>无</p>
					                </div>
					                <div class="weui-cell__ft"></div>
					            </a>
					            
					            <a class="weui-cell weui-cell_access" id="a2" >
					                <div class="weui-cell__bd">
					                    <p>无</p>
					                </div>
					                <div class="weui-cell__ft"></div>
					            </a>
					            
					            <a class="weui-cell weui-cell_access" id="a3" >
					                <div class="weui-cell__bd">
					                    <p>无</p>
					                </div>
					                <div class="weui-cell__ft"></div>
					            </a>
					            
					            <a class="weui-cell weui-cell_access" id="a4" >
					                <div class="weui-cell__bd">
					                    <p>无</p>
					                </div>
					                <div class="weui-cell__ft"></div>
					            </a>
					   </div>
				
				
			</div>
		</div>
	</div>


	<input type="hidden" id="openid" value="<%=map.get("openid")%>">
    <input type="hidden" id="nickname" value="<%=map.get("nickname")%>">
  
   
   

</body>
<script src="../../js/jquery-1.12.0.min.js"></script>
<script src="../../js/weui.min.js"></script>
<script type="text/javascript">
	
	function createCode(){  
	    code = "";  
	    var codeLength = 4;//验证码的长度  
	    //所有候选组成验证码的字符，可以用中文  
	    var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');  
	    for(var i=0;i<codeLength;i++)  
	    {  
	        var charIndex = Math.floor(Math.random()*60);  
	        code +=selectChar[charIndex];  
	    }  
	    return code;  
	}  
	
	var verCode;
	function changeImg(){
	 	verCode = createCode();
	 	$('#yzm').val("");
        var img = document.getElementById("img");  
        img.src = "<%=basePath %>/authImage?date="+verCode;
    }  
   
	document.body.setAttribute('ontouchstart', '');

	
	
	function vaildate(){
		if( $('#openid').val()=="" || $('#nickname').val()=="" ){
			weui.alert('未锁定微信ID,请重新操作');
			return false;
		}
		if( $('#openid').val()=="null" || $('#nickname').val()=="null" ){
			weui.alert('未锁定微信ID,请重新操作');
			return false;
		}
		if( $('#wxname').val()==""  ){
			weui.alert('申请人姓名不能为空');
			return false;
		}
		if( $('#yjdw').val()=="-1"  ){
			weui.alert('预警单位不能为空');
			return false;
		}
		if( $('#yjje').val()==""  ){
			weui.alert('预警金额不能为空');
			return false;
		}
		//yzm
		if( $('#yzm').val().toUpperCase() != verCode.toUpperCase()  ){
			weui.alert('验证码不正确');
			return false;
		}
		return true;
	}
	
	function t_applyClick(){
		$('#t_apply').click(function(){
			$('#yjdw').val("-1");
			$('#yzm').val("");
			changeImg();
		});
	}
	
	function postMes(){
		$('#apply').click(function(){
			if(!vaildate()){
				return;
			}
			//$("#t_list").trigger("click");
			var loading = weui.loading('loading', {
    			className: 'custom-classname'
			});
			
			var jsonparam = {
				"wxid": $('#openid').val(),
				"wxname":$('#nickname').val(), 
				"applyUser":$('#wxname').val(), 
				"customerid":$('#yjdw').val(),
				"mindMoney": $('#yjje').val() 
				};
			
			$.post('<%=basePath %>/servlet/MoneyControlerServlet?type=applyForm',jsonparam,function(data){
	        		loading.hide();
	        		if(data=="OK"){
	        			$("#t_list").trigger("click");
	        			return;
	        		}
	        		changeImg();
	        		weui.alert('申请失败:'+data);
        	});
		});
	}

	function getYJList(){
		var jsonparam = {"wxid": $('#openid').val()	};
		
		$.post('<%=basePath %>/servlet/MoneyControlerServlet?type=applyList',jsonparam,function(data){
	        console.info(data);
	        var listdata =  eval('(' + data + ')').data;
	      
	        for(var i=0; i<listdata.length&&i<5;i++){
	        	
	        	
	        	$("#a"+i).attr("href","applyCancel.jsp?data="+listdata[i]);
	        	
	        	$("#a"+i+" p").text(listdata[i][0]);
	        	
	        	if(listdata[i][4]!=""){
	        		$("#a"+i+" p").attr("style","color:#"+listdata[i][4]);
	        	}
	        }
	        
	        if(listdata.length>0){
	        	$("#t_list").trigger("click");
	        }
        
        });
	}

	function init(){
		weui.tab('#tab',{defaultIndex: 0 });		
		verCode = createCode();
	    var img = document.getElementById("img");  
        img.src = "<%=basePath %>/authImage?date="+verCode;
        $.post('<%=basePath %>/servlet/MoneyControlerServlet?type=getCustomer',function(data){
        	$('#yjdw').append(data);
        });
        
        if($('#openid').val()!=""){
        	getYJList();
        }
        
	}

	$(function(){
		if($('#openid').val()=="" || $('#openid').val()=="null"){
			window.location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23382b585e7bf49c&redirect_uri=http://ictwx.zjport.com/WXService/page/page/weixin/applyList.jsp&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		}
		
	
		init();
	    postMes();
	    t_applyClick();
	});
	
	
</script>



</html>