var count1Chart;
var count2Chart;
var money1Chart;
var money2Chart;

function createChartCount1(){
	count1Chart = echarts.init(document.getElementById('count1'));
	option = { 
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['今年进库数量','上年进库数量']
		    },
		    toolbox: {
		        show: true,
		        feature: {
		            dataZoom: {
		                yAxisIndex: 'none'
		            },
		            dataView: {readOnly: false},
		            magicType: {type: ['line', 'bar']},
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    
//		    itemStyle: {
//			    normal: {
//			        // 设置扇形的颜色
//			        color: 'rgba(92,184,92,1)'
//			       
//			    	}
//		    },
		    xAxis:  {
		      
		        boundaryGap: false,
		        data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		    },
		    yAxis: {
		        type: 'value',
		    }
		    
	};
	count1Chart.resize();
	count1Chart.setOption(option);
	
}


function createChartMoney1(){
	money1Chart = echarts.init(document.getElementById('money1'));
	option = { 
		    tooltip: {
		        trigger: 'axis'
		    },
		    toolbox: {
		        show: true,
		        feature: {
		            dataZoom: {
		                yAxisIndex: 'none'
		            },
		            dataView: {readOnly: false},
		            magicType: {type: ['line', 'bar']},
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    
		   
		    xAxis:  {
		      
		        boundaryGap: false,
		        data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		    },
		    yAxis: {
		        type: 'value',
		    }
		    
		    
	};
	
	money1Chart.setOption(option);
	money1Chart.resize();

//	$('#money1').children().children().attr("width",$('#count1').css('width'));
	
	console.info("money12");
}


function createChartCount2(){
	console.info("===111==");
	count2Chart = echarts.init(document.getElementById('count2'));
	option = {
		    
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        feature: {
		            dataView: {readOnly: false},
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    legend: {
		        data: ['展现','点击','访问','咨询','订单']
		    },
		    calculable: true,
		    series: [
		        {
		          
		            type:'funnel',
		            left: '10%',
		            top: 60,
		            //x2: 80,
		            bottom: 60,
		            width: '80%',
		            // height: {totalHeight} - y - y2,
		            min: 0,
		            max: 100,
		            minSize: '0%',
		            maxSize: '100%',
		            sort: 'descending',
		            gap: 2,
		            label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                },
		                emphasis: {
		                    textStyle: {
		                        fontSize: 20
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    length: 10,
		                    lineStyle: {
		                        width: 1,
		                        type: 'solid'
		                    }
		                }
		            },
		            itemStyle: {
		                normal: {
		                    borderColor: '#fff',
		                    borderWidth: 1
		                }
		            },
		            data: [
		                {value: 60, name: '访问'},
		                {value: 10, name: '咨询'},
		                {value: 20, name: '订单'},
		                {value: 80, name: '点击'},
		                {value: 100, name: '展现'}
		            ]
		        }
		    ]
		};



	count2Chart.setOption(option);
	
}

function createChartMoney2(){
	money2Chart = echarts.init(document.getElementById('money2'));
	option = {
		    
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        feature: {
		            dataView: {readOnly: false},
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    legend: {
		        data: ['展现','点击','访问','咨询','订单']
		    },
		    calculable: true,
		    series: [
		        {
		          
		            type:'funnel',
		            left: '10%',
		            top: 60,
		            //x2: 80,
		            bottom: 60,
		            width: '80%',
		            // height: {totalHeight} - y - y2,
		            min: 0,
		            max: 100,
		            minSize: '0%',
		            maxSize: '100%',
		            sort: 'descending',
		            gap: 2,
		            label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                },
		                emphasis: {
		                    textStyle: {
		                        fontSize: 20
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    length: 10,
		                    lineStyle: {
		                        width: 1,
		                        type: 'solid'
		                    }
		                }
		            },
		            itemStyle: {
		                normal: {
		                    borderColor: '#fff',
		                    borderWidth: 1
		                }
		            },
		            data: [
		                {value: 2000, name: '访问'},
		                {value: 10, name: '咨询'},
		                {value: 20, name: '订单'},
		                {value: 80, name: '点击'},
		                {value: 100, name: '展现'}
		            ]
		        }
		    ]
		};



	money2Chart.setOption(option);
	
}


function getSummer(){
	var url = "../servlet/AjaxServlet?dataType=bmwzSummer&houseID="+getStoreHouseID();
	$.get(url, function(strData){
	   var data = jQuery.parseJSON(strData);
       $('#sqlKuCun').text(data.sqlKuCun);
       $('#sqlSum').text(data.sqlSum);
       $('#sqlRec').text(data.sqlRec);
       $('#sqlReq').text(data.sqlReq);
	});
}


function getCountChart(myChart,url){
	myChart.showLoading();
	$.get(url, function(strData){
	   myChart.hideLoading();
	   var dbData = jQuery.parseJSON(strData);
	   var option = {
			   legend: {
			        data:['今年进库数量','上年进库数量']
			    },
			   xAxis:  {
				      
			        boundaryGap: false,
			        data: dbData.category
			    },
			    yAxis: {
			        type: 'value',
			    },
			    series: [
			             {
			                 name:'今年进库数量',
			                 type:'line',
			                 data:dbData.data1,
			                 markPoint: {
			                     data: [
			                         {type: 'max', name: '最大值'},
			                         {type: 'min', name: '最小值'}
			                     ]
			                 },
			                 markLine: {
			                     data: [
			                         {type: 'average', name: '平均值'}
			                     ]
			                 }
			             },
			             {
			                 name:'上年进库数量',
			                 type:'line',
			                 data:dbData.data2,
			                 markPoint: {
			                     data: [
			                         {type: 'max', name: '最大值'},
			                         {type: 'min', name: '最小值'}
			                     ]
			                 },
			                 markLine: {
			                     data: [
			                         {type: 'average', name: '平均值'}
			                     ]
			                 }
			             }
			             
			             
			       ]

	   };
	   myChart.setOption(option);
	});
}

function getMoneyChart(myChart,url){
	myChart.showLoading();
	$.get(url, function(strData){
	   myChart.hideLoading();
	   var dbData = jQuery.parseJSON(strData);
	   var option = {
			   legend: {
			        data:['今年进库金额','上年进库金额']
			    },
			   xAxis:  {
				      
			        boundaryGap: false,
			        data: dbData.category
			    },
			    yAxis: {
			        type: 'value',
			    },
			    series: [
			             {
			                 name:'今年进库金额',
			                 type:'line',
			                 data:dbData.data1,
			                 markPoint: {
			                     data: [
			                         {type: 'max', name: '最大值'},
			                         {type: 'min', name: '最小值'}
			                     ]
			                 },
			                 markLine: {
			                     data: [
			                         {type: 'average', name: '平均值'}
			                     ]
			                 }
			             },
			             {
			                 name:'上年进库金额',
			                 type:'line',
			                 data:dbData.data2,
			                 markPoint: {
			                     data: [
			                         {type: 'max', name: '最大值'},
			                         {type: 'min', name: '最小值'}
			                     ]
			                 },
			                 markLine: {
			                     data: [
			                         {type: 'average', name: '平均值'}
			                     ]
			                 }
			             }
			             
			             
			       ]

	   };
	   myChart.setOption(option);
	});
}


function getCountMoneyChart2(myChart,url){
	myChart.showLoading();
	$.get(url, function(strData){
	   myChart.hideLoading();
	   var dbData = jQuery.parseJSON(strData);
	   console.info(dbData)
	   var option = {
			   legend: {
			        data: dbData.category
			    },
			    calculable: true,
			    series: [
			        {
			          
			            type:'funnel',
			            left: '10%',
			            top: 60,
			            //x2: 80,
			            bottom: 60,
			            width: '80%',
			            // height: {totalHeight} - y - y2,
			            min: 0,
			            max: 100,
			            minSize: '0%',
			            maxSize: '100%',
			            sort: 'descending',
			            gap: 2,
			            label: {
			                normal: {
			                    show: true,
			                    position: 'inside'
			                },
			                emphasis: {
			                    textStyle: {
			                        fontSize: 20
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    length: 10,
			                    lineStyle: {
			                        width: 1,
			                        type: 'solid'
			                    }
			                }
			            },
			            itemStyle: {
			                normal: {
			                    borderColor: '#fff',
			                    borderWidth: 1
			                }
			            },
			            data:dbData.data
			        }
			    ]   

	   };
	   myChart.setOption(option);
	});
}


function initCountButton(){
	$(".buttonCount").click(function(){
		
		$('.buttonCount').attr("class","btn btn-default buttonCount");
		
		$(this).attr("class","btn btn-primary buttonCount");
		var YType = $(this).attr("YType");
		var url = "../servlet/AjaxServlet?dataType=bmwzCountChart&houseID="+getStoreHouseID()+"&YType="+YType;
		getCountChart(count1Chart,url);
	});
}

function initMoneyButton(){
	$(".buttonMoney").click(function(){
		
		$('.buttonMoney').attr("class","btn btn-default buttonMoney");
		
		$(this).attr("class","btn btn-primary buttonMoney");
		var YType = $(this).attr("YType")
		
		
		var url ="../servlet/AjaxServlet?dataType=bmwzMoneyChart&houseID="+getStoreHouseID()+"&YType="+YType;
		getMoneyChart(money1Chart,url);
		
	});
}

function initMoney1Click(){
	
	$('*[href="#profile1"]').click(function(){
		$('#money1').css("width",$("#count1").css("width"));
		money1Chart.resize();
	});
	
	$('*[href="#profile2"]').click(function(){
		$('#money2').css("width",$("#count1").css("width"));
		
		
		money2Chart.resize();
	});
	
	$('*[href="#home2"]').click(function(){
		$('#count2').css("width",$("#count1").css("width"));
		count2Chart.resize();
	});
	
}



$(document).ready(function() {
	
	//initMyTab();
	createChartCount1();
	createChartCount2();
	createChartMoney1();
	createChartMoney2();
	getSummer();
	var url = "../servlet/AjaxServlet?dataType=bmwzCountChart&houseID="+getStoreHouseID()+"&YType=2";
	getCountChart(count1Chart,url);
	url ="../servlet/AjaxServlet?dataType=bmwzMoneyChart&houseID="+getStoreHouseID()+"&YType=2";
	getMoneyChart(money1Chart,url);
	
	url ="../servlet/AjaxServlet?dataType=bmwzCountMoneyChart&houseID="+getStoreHouseID()+"&YType=1";
	getCountMoneyChart2(count2Chart,url);
	url ="../servlet/AjaxServlet?dataType=bmwzCountMoneyChart&houseID="+getStoreHouseID()+"&YType=2";
	getCountMoneyChart2(money2Chart,url);
	
	initCountButton();
	initMoney1Click();
	initMoneyButton();
});