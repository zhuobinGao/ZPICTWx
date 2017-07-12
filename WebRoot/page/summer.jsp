<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">


<title>物资管理平台</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style>
body.home {
 background-color: rgb(236,240,245); 
}
h2{
	font-size:20px;
}
.hero {
background:url(images/heroshot.jpg) no-repeat center center;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
padding: 25px 0 600px 0;
height: auto;
}
.panel-body{
color:#FFF;
}
.topM{
	margin-top:5px;
}

.bfontSize{
	font-size:18px;
}

canvas{
	width:100%;
}



</style>
</head>

<body class="home" style="zoom: 1;">

	<div class="container-fluid" style="margin-top:10px;">
		<div class="row">
		
			<div class="col-md-3">
				<div class="panel panel-default" style="background:rgb(66,139,202)">
					  <div class="panel-body" style="padding-left:0;padding-right:0">
					  		<div class="container-fluid">
					  			<div class="row">
						  				<div class="col-md-3">
						  					<span class="glyphicon glyphicon-inbox" aria-hidden="true" style="font-size:64px"></span>
						  				</div>
						  				<div class="col-md-9">
						  					<p style="font-size:26px; margin:0;" ><b>库存总量</b></p>
						  					<div style="border-top:solid 1px #FFF; padding-top:5px">
						  						<span id="sqlKuCun" class="bfontSize"></span>
						  					</div>	
						  				</div>
					  			</div>
					  		</div>
					  </div>
				</div>

			</div>
			
			<div class="col-md-3">
				<div class="panel panel-default" style="background:rgb(92,184,92)">
					  <div class="panel-body" style="padding-left:0;padding-right:0">
					  		<div class="container-fluid">
					  			<div class="row">
						  				<div class="col-md-3">
						  					<span class="glyphicon glyphicon-yen" aria-hidden="true" style="font-size:64px"></span>
						  				</div>
						  				<div class="col-md-9">
						  					<p style="font-size:26px; margin:0;" ><b>物资金额</b></p>
						  					<div style="border-top:solid 1px; padding-top:5px">
						  						<span id="sqlSum" class="bfontSize"></span>
						  					</div>	
						  				</div>
					  			</div>
					  		</div>
					  </div>
				</div>

			</div>
			
			
			
			
			<div class="col-md-3">
				<div class="panel panel-default" style="background:rgb(240,173,78)">
					  <div class="panel-body" style="padding-left:0;padding-right:0">
					  		<div class="container-fluid">
					  			<div class="row">
						  				<div class="col-md-3">
						  					<span class="glyphicon glyphicon-log-in" aria-hidden="true" style="font-size:64px"></span>
						  				</div>
						  				<div class="col-md-9">
						  					<p style="font-size:26px; margin:0;" ><b>本月进库</b></p>
						  					<div style="border-top:solid 1px; padding-top:5px">
						  						<span id="sqlRec" class="bfontSize"></span>
						  					</div>	
						  				</div>
					  			</div>
					  		</div>
					  </div>
				</div>

			</div>
			
			
			
			<div class="col-md-3">
				<div class="panel panel-default" style="background:rgb(217,83,79)">
					  <div class="panel-body" style="padding-left:0;padding-right:0">
					  		<div class="container-fluid">
					  			<div class="row">
						  				<div class="col-md-3">
						  					<span class="glyphicon glyphicon-log-out" aria-hidden="true" style="font-size:64px"></span>
						  				</div>
						  				<div class="col-md-9">
						  					<p style="font-size:26px; margin:0;" ><b>本月出库</b></p>
						  					<div style="border-top:solid 1px; padding-top:5px">
						  						<span id="sqlReq" class="bfontSize"></span>
						  					</div>	
						  				</div>
					  			</div>
					  		</div>
					  </div>
				</div>

			</div>
			
			
			
			
		</div>
    </div>

	
	<div class="container-fluid" style="color:#000;">
		
		<div class="row">
			<div class="col-md-6">
					  <div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title"><b>收料情况</b></h3>
						  </div>
						  <div class="panel-body">
						    	<!-- Nav tabs -->
								  <ul class="nav nav-tabs" id="myTab" role="tablist">
								    <li role="presentation" class="active"><a href="#home1" aria-controls="home1" role="tab" data-toggle="tab">数量</a></li>
								    <li role="presentation"><a  href="#profile1" aria-controls="profile1" role="tab" data-toggle="tab">金额</a></li>
								   </ul>
								
								  <!-- Tab panes -->
								  <div class="tab-content" style="color:#000">
									    <div role="tabpanel" class="tab-pane active" id="home1">
									    	<div class="btn-group" role="group" aria-label="..." style="margin-top:10px;">
											  <button type="button" class="btn btn-primary buttonCount" YType="2">最近一年</button>
											  <button type="button" class="btn btn-default buttonCount" YType="1">最近半年</button>
											  <button type="button" class="btn btn-default buttonCount" YType="0">最近三个月</button>
											  
											</div>
											<div id="count1" style="height:350px;width:100%;margin-top:10px;min-width:600px"></div>
									    </div>
									    <div role="tabpanel" class="tab-pane " id="profile1" style="width:100%;">
									    	<div class="btn-group" role="group" aria-label="..." style="margin-top:10px;">
											  <button type="button" class="btn btn-primary buttonMoney" YType="2">最近一年</button>
											  <button type="button" class="btn btn-default buttonMoney" YType="1">最近半年</button>
											  <button type="button" class="btn btn-default buttonMoney" YType="0">最近三个月</button>
											</div>
											
											<div class="row" style="width:100%;">
												<div class="col-md-12" style="width:100%;">
													
													<div id="money1" style="width:100%;height:350px;margin-top:10px;">
													
													</div>
												</div>
											</div>
											
											
									    </div>
								  </div>
						  </div>
					  </div>
			</div>
			
			<div class="col-md-6">
				<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title"><b>物资出库分类占比(%)</b></h3>
						  </div>
						  <div class="panel-body" >
						    	<!-- Nav tabs -->
								  <ul class="nav nav-tabs" id="myTab" role="tablist">
								    <li role="presentation" class="active"><a href="#home2" aria-controls="home2" role="tab" data-toggle="tab">数量</a></li>
								    <li role="presentation"><a href="#profile2" aria-controls="profile2" role="tab" data-toggle="tab">金额</a></li>
								   </ul>
								
								  <!-- Tab panes -->
								  <div class="tab-content" style="color:#000;" >
								    <div role="tabpanel" class="tab-pane active" id="home2">
								    	<div id="count2" style="height:395px; margin-top:30px;min-width:600px"></div>
								    </div>
								    <div role="tabpanel" class="tab-pane " id="profile2">
								    	<div id="money2" style="height:395px;margin-top:30px;min-width:600px"></div>
								    </div>
								  </div>
						  </div>
					  </div>
			</div>
		</div>
		
	</div>

	<div class="container-fluid" >
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title">快捷操作</h3>
						  </div>
						  <div class="panel-body" style="color:#000;margin-top:-20px;">
						  		<div class="row" style="margin:0">
						  			<div class="col-lg-3">
						  				<h2><b>进出库管理</b></h2>
						  				<p>
								  				<a href="page/inAndOut/inWarehouse.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
								  					物资进库
								  				</a>
								  				<a href="page/inAndOut/outWarehouse.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span>
								  					物资出库
								  				</a>
								  				<a href="page/inAndOut/searchIn.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								  					进库查询
								  				</a>
								  				<a href="page/inAndOut/searchOut.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								  					出库查询
								  				</a>
								  				<a href="page/inAndOut/inWarehouseEdit.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon  glyphicon-edit" aria-hidden="true"></span>
								  					进库信息调整/回退
								  				</a>
								  				<a href="page/inAndOut/outWarehouseEdit.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
								  					出库信息调整/回退
								  				</a>
						  				</p>
						  				
						  			</div>
						  			
						  			<div class="col-lg-3">
						  				<h2><b>仓存管理</b></h2>
						  				<p>
						  					<a href="page/store/monthSettlement.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
								  					数据月结
								  			</a>
								  			<a href="page/store/monthSettlementCheck.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-floppy-saved" aria-hidden="true"></span>
								  					数据审核
								  			</a>
								  			<a href="page/store/checkStore.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								  					库存查询
								  			</a>
								  			<a href="page/store/storeLine.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								  					库存预警设计
								  			</a>
								  			<a href="page/store/checkStoreLine.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								  					库存预警监控
								  			</a>
						  				</p>
						  			</div>
						  			
						  			<div class="col-lg-3">
						  				<h2><b>财务管理</b></h2>
						  				<p>
						  					<a href="page/settlement/monthStoreReport.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
								  					月末库存表
								  			</a>
								  			<a href="page/settlement/categoryReport.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-queen" aria-hidden="true"></span>
								  					类别结余明细
								  			</a>
								  			<a href="page/settlement/materialReport.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
								  					物资结余明细
								  			</a>
								  			<a href="page/settlement/monthReport.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-send" aria-hidden="true"></span>
								  					月度结存报总表
								  			</a>
								  			<a href="page/settlement/searchInHistory.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
								  					归档进库明细
								  			</a>
								  			<a href="page/settlement/searchOutHistory.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-grain" aria-hidden="true"></span>
								  					归档出库明细
								  			</a>
								  			<a href="page/settlement/searchOutFeeHistory.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span>
								  					费用项目
								  			</a>
						  				</p>
						  			</div>
						  			
						  			<div class="col-lg-3">
						  				<h2><b>基础信息</b></h2>
						  				<p>
						  					<a href="page/baseSet/material.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-glass" aria-hidden="true"></span>
								  					物资管理
								  			</a>
								  			<a href="page/baseSet/category.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-music" aria-hidden="true"></span>
								  					分类管理
								  			</a>
								  			<a href="page/baseSet/wareHouse.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
								  					仓库管理
								  			</a>
								  			<a href="page/baseSet/feeProject.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
								  					费用项目管理
								  			</a>
								  			<a href="page/baseSet/commonInfo.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span>
								  					公用信息管理
								  			</a>
								  			<a href="page/baseSet/leadPerson.jsp" class="btn btn-primary topM" role="button">
								  					<span class="glyphicon glyphicon-send" aria-hidden="true"></span>
								  					领用人信息管理
								  			</a>
						  				</p>
						  			</div>
						  		</div>
						  	
						  </div>
				</div>
			</div>
		</div>
	</div>


</body>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/echarts.min.js"></script>
<script src="js/summer.js?v=1.3192"></script>

<script type="text/javascript">

	function getStoreHouseID(){
		return "<%=session.getValue("cangKuID") %>";
	}
	
 



		
</script>

</html>
