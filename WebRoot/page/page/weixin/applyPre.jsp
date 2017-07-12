<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	<div class="weui-cells__title">预付款申请</div>
	<div class="weui-cells weui-cells_form">
	
		
		
			<div class="weui-cell">
				<div class="weui-cell__hd">
	            	<label class="weui-label">微信号</label>
	        	</div>
	        	<div class="weui-cell__bd">
            		<input class="weui-input" name="name" type="text" placeholder="请在此输入姓名" value="123" disabled  />
        		</div>
	        	
			</div>
		
			<div class="weui-cell">
				
    			<div class="weui-cell__hd">
	            	<label class="weui-label">申请人</label>
	        	</div>
	        	
	        	 <div class="weui-cell__bd">
            		<input class="weui-input" name="name" type="text" placeholder="请在此输入姓名"  />
        		</div>
    			
    		</div>
		
		
    	
    	<div class="weui-cells__title">选择预警单位</div>
    	
    	<div class="weui-cell">
    		<div class="weui-cell__hd">
	            <label class="weui-label">预警单位</label>
	        </div>
	        <div class="weui-cell__bd">
                    <select class="weui-select" name="select2">
                    	<option value="-1"></option>
                        <option value="1">中国</option>
                        <option value="2">美国</option>
                        <option value="3">英国</option>
                    </select>
                </div>
    	</div>
    	
    	<div class="weui-cell">
	        <div class="weui-cell__hd">
	            <label class="weui-label">预警金额</label>
	        </div>
	        
	        <div class="weui-cell__bd">
                    <input class="weui-input" type="number" pattern="[0-9]*" placeholder="预警金额">
            </div>
	       
	        
    	</div>
    	
    	<div class="weui-cell weui-cell_vcode">
                <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" placeholder="请输入验证码">
                </div>
                <div class="weui-cell__ft">
                    <img class="weui-vcode-img" src="./images/vcode.jpg">
                </div>
            </div>
    	
    	
	</div>	

	<div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">提交申请</a>
    </div>



</body>
<script src="../../js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
	document.body.setAttribute('ontouchstart', '');

$(function(){
    
});
</script>

</html>