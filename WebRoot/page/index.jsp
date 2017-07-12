<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	//System.out.println("===>"+session);
	//测试仓库
	//session.putValue("cangKuID", "634EFECD796848CBB92219EC741D0545");
	//实际仓库
	//session.putValue("cangKuID", "795231CBF27F40FFA90EF987285D5886");
	//session.putValue("userID", "1");
	//session.putValue("userName", "测试人员");
	//session.putValue("cangKuName", "测试仓库");
 %>

<!DOCTYPE>
<html >
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">


<title>物资管理平台</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/jquery.mmenu.all.css" type="text/css" rel="stylesheet" />

<link rel="stylesheet" href="css/index.css">

<!--[if lt IE 9]>
<script src="js/html5.min.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

<style>
.modal-backdrop {
	z-index:0;
}
</style>

</head>

<body>

	 <nav id="my-menu" style="margin-top:52px;" >
           <ul>
              <li ><a href="#">固定资产管理</a>
              		<ul class="Vertical">
                    <li><a href="page/asset/fixedAsset.jsp" target="frame">固定资产信息</a></li>
                    <li><a href="page/asset/hardware.jsp" target="frame">硬件信息</a></li>
                    <li><a href="page/asset/repairRecored.jsp" target="frame">维修记录情况</a></li>
                    <li><a href="page/asset/hardcategory.jsp" target="frame">资产分类信息</a></li>
                    <li><a href="page/asset/commonMes.jsp" target="frame">状态列表</a></li>
                 </ul>
              </li>
              <li><a href="#">进/出库管理</a>
                 <ul class="Vertical">
                    <li><a href="page/inAndOut/inWarehouse.jsp" target="frame">物资进库</a></li>
                    <li><a href="page/inAndOut/outWarehouse.jsp" target="frame">物资出库</a></li>
                    <li><a href="page/inAndOut/searchIn.jsp" target="frame">进库查询</a></li>
                    <li><a href="page/inAndOut/searchOut.jsp" target="frame">出库查询</a></li>
                    <li><a href="page/inAndOut/inWarehouseEdit.jsp" target="frame">进库信息调整/回退</a></li>
                    <li><a href="page/inAndOut/outWarehouseEdit.jsp" target="frame">出库信息调整/回退</a></li>
                 </ul>
              </li>
              
             
              <li ><a href="#">仓存管理</a>
              	<ul class="Vertical">
                	<li><a href="page/store/monthSettlement.jsp" target="frame">数据月结</a></li>
                	<li><a href="page/store/monthSettlementCheck.jsp" target="frame">数据审核</a></li>
                	<li><a href="page/store/checkStore.jsp" target="frame">库存查询</a></li>
                    <li><a href="page/store/storeLine.jsp" target="frame">库存预警设计</a></li>
                    <li><a href="page/store/checkStoreLine.jsp" target="frame">库存预警监控</a></li>
                    <li><a href="page/store/tempStore.jsp" target="frame">代保管仓储</a></li>
                </ul>
              </li>
              
              
              <li ><a href="#">统计分析</a>
              	<ul class="Vertical">
                	<li><a href="#">领用部门数据分析</a></li>
                    <li><a href="#">机种机构数据数据分析</a></li>
                </ul>
              </li>
              
              
              <li ><a href="#">财务管理</a>
              	<ul class="Vertical">
                	<li class="Divider">报表类</li> 
                    <li><a href="page/settlement/monthStoreReport.jsp" target="frame">月末库存表</a></li>
                    <li><a href="page/settlement/categoryReport.jsp" target="frame">类别结余明细</a></li>
                    <li><a  href="page/settlement/materialReport.jsp" target="frame">物资结余明细</a></li>
                    <li><a href="page/settlement/monthReport.jsp" target="frame">月度结存报总表</a></li>
                    
                    <li class="Divider">归档类</li> 
                    <li><a href="page/settlement/searchInHistory.jsp" target="frame">归档进库明细</a></li>
                    <li><a href="page/settlement/searchOutHistory.jsp" target="frame">归档出库明细</a></li>
                    <li><a href="page/settlement/searchOutFeeHistory.jsp" target="frame">归档出库明细(费用项目)</a></li>
                </ul>
              </li>
              
              
              <li ><a href="#">基础信息</a>
              	<ul class="Vertical">
                    <li><a href="page/baseSet/material.jsp" target="frame">物资管理</a></li>
                    <li><a href="page/baseSet/category.jsp" target="frame">分类管理</a></li>
                    <li><a href="page/baseSet/wareHouse.jsp" target="frame">仓库管理</a></li>
                    <li><a href="page/baseSet/mechanism.jsp" target="frame">机种机构管理</a></li>
                    
                    <li><a href="page/baseSet/feeProject.jsp" target="frame">费用项目管理</a></li>
                    <li><a href="page/baseSet/commonInfo.jsp" target="frame">公用信息管理</a></li>
                    <li><a href="page/baseSet/leadPerson.jsp" target="frame">领用人信息管理</a></li>
                 </ul>
              
              </li>
              <li ><a href="#">权限管理</a>
              	<ul class="Vertical">
                	<li><a href="page/permission/user.jsp" target="frame">用户管理</a></li>
                    <li><a href="page/permission/permission.jsp" target="frame">权限管理</a></li>
                </ul>
              
              </li>
               
           </ul>
     </nav>


 	
     
   

	<div class="container-fluid" id="fluid" style="padding-left:0; padding-right:0;">
    
  	 <nav class="navbar navbar-inverse navbar-fixed-top" id="topNav">
      <div class="container-fluid">
        <div class="navbar-header" style="margin-top:15px; ">
          <a href="#my-memu" id="openMenu" style="font-size:20px; color:#FFF;">
          	<span class="glyphicon glyphicon-align-justify" aria-hidden="true">&nbsp;</span>
          </a>
        </div>
        <div class="navbar-header">
          <a class="navbar-brand" href="#my-memu" ><b>仓库云</b> <small>BY M31西叶星 STUDIO</small></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="" ><a href="a.html" target="frame"><b>主页</b></a></li>
            <li><a href="404.html" target="frame"><b>关于</b></a></li>
            <li><a href="a.html" target="frame"><b>联系方式</b></a></li>
          </ul>
          <ul class="nav navbar-nav" style="float:right;">
           <li ><a href="#"  data-toggle="modal" data-target="#myModal" >用户&nbsp;&nbsp;<span><%=session.getAttribute("userName") %></span></a></li>
           <li ><a href="../login/index.jsp" >安全退出</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
    
    
    
    
    
    	
  			<iframe class="embed-responsive-item" style="margin-top:50px;" name="frame" id="iframepage" scrolling="no" height="100%" width="100%" frameborder="0"    src="summer.jsp" onLoad="iFrameHeight()"  >
  			
  			</iframe>
		
		
		

	
    
    </div>
		
	
	<!--Add Modal -->
<div class="modal fade bs-example-modal-sm" id="myModal" style="z-index:9999;margin-top:200px;" tabindex="1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><b>修改密码1</b></h3>
        
      </div>
      <div class="modal-body">
      
        	<div class="form-group form-group-sm form-horizontal" >
                   	<label for="vessel" class="control-label">用户密码:</label>
                    <input type="password" class="form-control" id="mypwd1" placeholder="用户密码" size="40"  >
		    </div>
		    
		    <div class="form-group form-group-sm form-horizontal" >
                   	<label for="vessel" class="control-label">确认密码:</label>
                    <input type="password" class="form-control" id="mypwd2" placeholder="确认密码" size="40"  >
		    </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="upd_save">保 存</button>
      </div>
    </div>
  </div>
</div>
<!--Add Modal End-->


		




</body>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.mmenu.all.min.js" type="text/javascript"></script>
<script src='<%=basePath %>dwr/engine.js'></script> 
<script src='<%=basePath %>dwr/util.js'></script> 

<script src='<%=basePath %>dwr/interface/UserAndPermission.js'></script>
<script src='<%=basePath %>dwr/interface/PreStatement.js'></script>
<script src='<%=basePath %>dwr/interface/EncrypDES.js'></script>

<script src="js/index.js" ></script>
<script type="text/javascript">
 var i=0;
 
 
 var userid = "<%=session.getAttribute("userID") %>";
 
 function iFrameHeight() {
		i++;
        var ifm= document.getElementById("iframepage");
        var subWeb = document.frames ? document.frames["iframepage"].document :	ifm.contentDocument;
            if(ifm != null && subWeb != null) {
				ifm.height = subWeb.body.scrollHeight;
            }

    } 
    
    window.setInterval("iFrameHeight()",1000); 

</script>

</html>
