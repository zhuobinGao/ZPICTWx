<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    <title>翻箱作业检测</title>
    
   
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>page/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>page/css/jquery.mmenu.all.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/buttons.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/select.dataTables.min.css">
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<%=basePath %>page/js/html5shiv.min.js"></script>
      <script src="<%=basePath %>page/js/respond.min.js"></script>
    <![endif]-->
	
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap-datetimepicker.min.css" media="screen">
	<link rel="stylesheet" href="<%=basePath%>css/jquery-ui.min.css" media="screen">
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mySkin.css?v=1.0">
	
	<style type="text/css">
	

	
.mm-slideout{
	    transition: -webkit-transform .4s ease;
    transition: transform .4s ease;
    transition: transform .4s ease,-webkit-transform .4s ease;
    z-index:inherit;
}
tfoot input {
        width: 100%;
        padding: 3px;
        box-sizing: border-box;
    }
    .ui-front {
    z-index: 2000;
}
#project-label {
    display: block;
    font-weight: bold;
    margin-bottom: 1em;
  }
  #project-icon {
    float: left;
    height: 32px;
    width: 32px;
  }
  #project-description {
    margin: 0;
    padding: 0;
  }
	</style>
	
  </head>
  
  <body>
   
 
 
 
 
    
    <div class="container-fluid" >
    	<ol class="breadcrumb" style="margin-top:20px;">
		  <li><a >首页</a></li>
		  
		  <li class="active">翻箱检测信息</li>
		</ol>
    	
    	
    	
    	
    	<hr>
    	
    	
    	<div class="row">
    		<div class="col-sm-12">
    		
    			<table id="example"  class="display nowrap tableExample" cellspacing="0" width="100%" style="margin-top:10px;">
                        <thead>
                                <tr>
                                	<th>选择</th>
                                	
                                	<th>箱号</th>
                                	<th>原位置</th>
                                	<th>目标位置</th>
                                    <th>作业时间</th>
                                    
                                    
                                    <th>lift机械</th>
                                    <th>put机械</th>
                                   
                                    <th>驾驶人员</th>
                                    
                                    
                                </tr>
                        </thead>
                        
                        <tfoot>
                        	<tr>
                        			<th>选择</th>
                                	
                                	<th>箱号</th>
                                	<th>原位置</th>
                                	<th>目标位置</th>
                                    <th>作业时间</th>
                                    <th>lift机械</th>
                                    
                                    <th>put机械</th>
                                    <th>驾驶人员</th>
                                </tr>
                        
                        </tfoot>
                        
                        <tbody>
                            	<tr>
                            		<td></td>
                            		
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		<td>1</td>
                            		<td>5</td>
                            		
                            		<td>1</td>
                            		<td>1</td>
                            		
                            		
                            		
                            	</tr>
                        </tbody>
        
                </table>
                
    		</div>
    		
    		 	
    	</div>

    </div>
    
    
    
   
     
   
   
 
  
  
  <input type="hidden" id="s_ymd" value="<%=request.getParameter("ymd")%>">
  <input type="hidden" id="u_cgs" value="">
  </body>
  
  <script src="<%=basePath %>page/js/jquery-1.12.1.min.js"></script>
   <script src="<%=basePath %>page/js/bootstrap.min.js"></script>
   <script src="<%=basePath %>js/jquery-ui.min.js"></script>
   <script src="<%=basePath %>page/js/jquery.mmenu.all.min.js"></script>
   
   <script src="<%=basePath %>js/jquery.dataTables.min.js"></script>
   
   <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
   <script src="<%=basePath %>js/bootstrap-datetimepicker.fr.js"></script>
  
   <script src="<%=basePath %>js/exportJS/dataTables.buttons.min.js"></script>
   <script src="<%=basePath %>js/exportJS/buttons.flash.min.js"></script>
   <script src="<%=basePath %>js/exportJS/jszip.min.js"></script>
   <script src="<%=basePath %>js/exportJS/pdfmake.min.js"></script>
   <script src="<%=basePath %>js/exportJS/vfs_fonts.js"></script>
   <script src="<%=basePath %>js/exportJS/buttons.html5.min.js"></script>
   <script src="<%=basePath %>js/exportJS/buttons.print.min.js"></script>
   
   <script src="<%=basePath %>js/dataTables.select.min.js"></script>
  
   
  
  
 
   <script src="<%=basePath %>/page/page/asset/js/workitemCheck.js"></script>
  
  
</html>
