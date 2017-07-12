var editor;
var table;


var myUrl = "../WXService/CommonServlet?param=workItemCheck&nowYMD=";
function initTable(){
	
 
	myUrl = myUrl+$('#s_ymd').val();
	console.info(myUrl);
	
	//var table = $('.tableExample').DataTable();
	 table = $('.tableExample').DataTable({
		 "language": {
			 "sProcessing":   "处理中...",
			    "sLengthMenu":   "显示 _MENU_ 项结果",
			    "sZeroRecords":  "没有匹配结果",
			    "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			    "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
			    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			    "sInfoPostFix":  "",
			    "sSearch":       "搜索:",
			    "sUrl":          "",
			    "sEmptyTable":     "表中数据为空",
			    "sLoadingRecords": "载入中...",
			    "sInfoThousands":  ",",
			    "oPaginate": {
			        "sFirst":    "首页",
			        "sPrevious": "上页",
			        "sNext":     "下页",
			        "sLast":     "末页"
			    },
			    "oAria": {
			        "sSortAscending":  ": 以升序排列此列",
			        "sSortDescending": ": 以降序排列此列"
			    }
        },
        "ajax": myUrl,
      
        "deferRender": true,
      
        "pagingType": "full_numbers",
        "scrollX": true,
        columnDefs: [ {
            orderable: false,
            className: 'select-checkbox',
            targets:   0
        } ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        select: true,
        "lengthMenu": [[20, 20, 100, -1], [10, 20, 100, "All"]],
       
        dom: 'B<"clear">lfrtip',
        buttons: [ ],
        
        
        initComplete: function () {
            this.api().columns().every( function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    } );
 
                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
        }
    });
	
	 
	
	    // DataTable
	    
	 
	    // Apply the search
	  
	
}


function initSearch(){
	$('#s_search').click(function(){
		table.clear();
		table.ajax.url( myUrl).load();
	});
}


$(document).ready(function() {
	
	initTable();
	
	initSearch();
	
});
