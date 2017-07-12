function initDate(){
	$('.form_date').datetimepicker({
        language:  'fr',
        format: "yyyy-mm-dd",
        autoclose: true,
        minView:2,
        todayBtn: true,
        minuteStep: 1,
        pickerPosition: "bottom-left"
  });
	
	$('.form_datetime').datetimepicker({
        language:  'fr',
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        minView:2,
        todayBtn: true,
        minuteStep: 1,
        pickerPosition: "bottom-left"
  });
}

function initMenu(){
	  $("#my-menu").mmenu(
		    	{
		         	"extensions": [     
     	             "effect-panels-zoom",
     	             "effect-listitems-slide",
		             "theme-dark" 
		         	],
		         	classNames: {
		                selected: "active"
		            },
		         	"searchfield": {
			            "resultsPanel": true,
			            "showTextItems": true
       			},
       			"navbars": [
			            {	
			               "position": "top",
			               "content": ["searchfield"
			               ]
			            }
       			]
    				}, {
			         "searchfield": {
			            "clear": true
			         }
    			}
		    );
		    var API = $("#my-menu").data( "mmenu" );
//		    API.open();
}



$(document).ready(function() {
			initDate();
			initMenu();
			
		  
      	
});