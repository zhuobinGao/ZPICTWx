// JavaScript Document
$(document).ready(function() {
      $("#my-menu").mmenu({
         // options
		extensions: ["pagedim-black","effect-menu-slide", "effect-listitems-slide"],
		counters: true,
		
		
		offCanvas: {
            position  : "left",
            zposition : "back"
         },
		 navbar	: {
			title: "菜单页"
		},
		 navbars: {
            content: [ "searchfield" ]
         },
		searchfield: {
            resultsPanel: true,
			placeholder: "菜单检索"
         }
	   
		 
      }, {
         // configuration
		searchfield: {
            clear: true
         },
		 
         classNames: {
            vertical: "expand"
         }
      });

	  var API = $("#my-menu").data( "mmenu" );
      console.info(1);
	  API.close();
	  console.info(2);
      $("#openMenu").click(function() {
         API.open();
      });

/*
	  $("#my-menu .Vertical a").click(function() {
         API.close();
      });
*/


		










   });