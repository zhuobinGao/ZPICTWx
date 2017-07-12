<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
        <title>预付款预警申请</title>
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
		String data = request.getParameter("data");
		System.out.println(data);
		String str[] = data.split(",");
		System.out.println(str.toString()+"|"+str.length);
		String status = "0".equals(str[5])?"未开通":"已启用";
		
		String mind_id = str[6];
	 %>


	<div class="weui-cells__title">预付款详情</div>
	<div class="weui-cells weui-cells_form">
	
		
		
			<div class="weui-cell">
				<div class="weui-cell__hd">
	            	<label class="weui-label">微信号</label>
	        	</div>
	        	<div class="weui-cell__bd">
            		<input class="weui-input" name="name" type="text" placeholder="请在此输入姓名" value="<%=str[1] %>" disabled  />
        		</div>
	        	
			</div>
		
		
		
    	
    	<div class="weui-cells__title">预警信息</div>
    	
    	<div class="weui-cell">
    		<div class="weui-cell__hd">
	            <label class="weui-label">预警单位</label>
	        </div>
	        <div class="weui-cell__bd">
                    <input class="weui-input" name="name" type="text" placeholder="请在此输入姓名" value="<%=str[0] %>" disabled  />
                </div>
    	</div>
    	
    	<div class="weui-cell">
	        <div class="weui-cell__hd">
	            <label class="weui-label">预警金额</label>
	        </div>
	        
	        <div class="weui-cell__bd">
                    <input class="weui-input" type="number" pattern="[0-9]*" placeholder="预警金额" value="<%=str[2] %>" disabled >
            </div>
	       
	        
    	</div>
    	
    	
	        
	        
                    
            
    	
    	
    	
    	<%
    		if("已启用".equals(status)){
	    		out.println("<div class='weui-cell'> <div class='weui-cell__hd'><label class='weui-label'>帐户金额</label></div>");
	    		out.println("<div class='weui-cell__bd'><input class='weui-input' type='number' pattern='[0-9]*' placeholder='帐户金额' value='"+str[3]+"' disabled></div>");
	    		out.println("</div>");
    		}
    	 %>
    	
    	
    	
    	
    	<div class="weui-cell">
	        <div class="weui-cell__hd">
	            <label class="weui-label">预警功能</label>
	        </div>
	        
	        <div class="weui-cell__bd">
                    <input class="weui-input" type="text"  placeholder="预警功能" value="<%=status %>" disabled>
            </div>  
    	</div>
    	
    	
    	
	</div>	

	<div class="weui-btn-area">
			<a class="weui-btn weui-btn_default" href="javascript:" id="goback">返回</a>
            <a class="weui-btn weui-btn_warn" href="javascript:" id="showTooltips">取消预警</a>
    </div>

	<input type="hidden" id="yjid" type="text" value="<%=mind_id %>">

</body>
<script src="../../js/jquery-1.12.0.min.js"></script>
<script src="../../js/weui.min.js"></script>
<script type="text/javascript">
	document.body.setAttribute('ontouchstart', '');

$(function(){
    
    $('#showTooltips').click(function(){
    	 weui.confirm('确认取消预警功能吗', function(){ 
    	 			var jsonparam = { "mindID":$('#yjid').val() };
    	 			
    	 			$.post('<%=basePath %>/servlet/MoneyControlerServlet?type=cancelForm',jsonparam,function(data){
		        		if(data=="OK"){
		        			window.location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23382b585e7bf49c&redirect_uri=http://ictwx.zjport.com/WXService/page/page/weixin/applyList.jsp&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		        			return;
		        		}
		        		weui.alert(data);
        		    });		
    	 		}
    	);
    });
    
    $('#goback').click(function(){
    	window.location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23382b585e7bf49c&redirect_uri=http://ictwx.zjport.com/WXService/page/page/weixin/applyList.jsp&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    });
    
   
    
});
</script>

</html>