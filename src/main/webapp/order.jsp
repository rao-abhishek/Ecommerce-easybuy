<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/d_register.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<script src="push.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600;700&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous">
</script>

</head>
<body style="  display: flex; justify-content: center; ">
    
    <script>

    

   $(document).ready(function(){
	
        Push.create("Hello World");
		/*  $('.order_list').on("click","#cancel_order",function(){
				  
			  $("body").css("cursor", "wait");
				  console.log("cancel clicked");
				  
				  oid2 = $('#cancel_order').val();
				  
				  console.log(oid2);
				  
				  $.post("CancelOrder",{oid:oid2},function(data,status){
					  
					  console.log(status);
					  location.reload();
					  
					  
				  })
				  
				  
			  })*/
			  
			  if (!("Notification" in window)) {
				    alert("This browser does not support desktop notification");
				  }
			  
			  Notification.requestPermission();
			  
			  console.log(Notification.permission);
		
			  if (Notification.permission === 'granted') {
				  
				  console.log("in-granted");
			        const notification = new Notification("Hi there!",{body: " Hey mate!! "});
			        console.log(notification);
			      }
	   
	   $.get("AllOrders",{null:null},function(data,status)
 			  {
 		  console.log(data);
 		  console.log(status);
 		  
 		  
	 obj = JSON.parse(data);
 			 
 			 let in_html = ``;
 				let url ;
 				let img_url ;
 				let product_name ;
 				let total_price =0;
 				let price;
 			 console.log(obj.length);
 			 
 			 for (var i = 0; i<obj.length; i++ )
 			 {
 				 
 				// console.log("pid test "+obj[i].pid);
 				url = obj[i].pid;
 				img_url = url+"."+obj[i].product_ext;
 				 product_name = obj[i].product_name;
 				 price = obj[i].product_price;
 				 total_price = parseInt(total_price) + parseInt(price);
 				 oid = obj[i].oid;
 				// console.log("pid img url test "+ img_url);
 				 
 			  in_html = in_html + ` <a href="product.jsp?pid=\${url}">
 			    <div class="product_item" >
 			    <img src = "product_img/\${img_url}"  style="width:30%;">
 			    <p style="float:right;margin:10px;"><b>\${product_name}</b> <br> 	&#8377; \${price}</p>
 			   </a>
 			    <a href="CancelOrder?oid=\${oid}"><button id="cancel_order" style="background-color:red" value="\${oid}"><b>Cancel / Return Order</b></button>
 			   </a>
 			    <br>
 			    </div>
 			    `;
 			 
 			//  console.log(in_html);
 			 
 			 }
 			
 			 $('.order_list').html(in_html);
 			 $('.total_price').html("Total Cost, &#x20B9; "+total_price);
			// $('.cancel_order_list').html(in_html);
 		//  $('.feature_products').html(data);
 		  
 			  })
	   
 			  
 	   $.get("AllCancelledOrders",{null:null},function(data,status)
 			  {
 		  console.log(data);
 		  console.log(status);
 		  
 		  
	 obj = JSON.parse(data);
 			 
 			 let in_html = ``;
 				let url ;
 				let img_url ;
 				let product_name ;
 				let price;
 			 console.log(obj.length);
 			 
 			 for (var i = 0; i<obj.length; i++ )
 			 {
 				 
 				// console.log("pid test "+obj[i].pid);
 				url = obj[i].pid;
 				img_url = url+"."+obj[i].product_ext;
 				 product_name = obj[i].product_name;
 				 price = obj[i].product_price;
 				// console.log("pid img url test "+ img_url);
 				 
 			  in_html = in_html + ` <a href="product.jsp?pid=\${url}">
 			    <div class="product_item" >
 			    <img src = "product_img/\${img_url}"  style="width:30%;">
 			    <p style="float:right;margin:10px;"><b>\${product_name}</b> <br> 	&#8377; \${price}</p>
 			  
 			   
 			    <br>
 			    </div>
 			    </a>`;
 			 
 			//  console.log(in_html);
 			 
 			 }
 			
 			// $('.order_list').html(in_html);
			 $('.cancel_order_list').html(in_html);
 		//  $('.feature_products').html(data);
 		  
 			  })
 			  

   });    

</script>
    
<div class="container"  style="background-color:lightblue; min-heigth:650px ; height: auto; float: left; margin: 30px;" >
    
         <a href="home.jsp"><i class="fa fa-home" aria-hidden="true"></i></a>
         
    <div class="title"><h1>EasyBuy</h1></div>
   
    <div class="tagline"><h2>Order History</h2></div>
    <div class="total_price"></div>

    
<div class="order_list" >
    

   
       

</div>


</div>

 <div class="container"  style="background-color:lightblue; min-heigth:650px ; height: auto; float: left; margin: 30px;" >
    
         <a href="home.jsp"><i class="fa fa-home" aria-hidden="true"></i></a>
         
    <div class="title"><h1>EasyBuy</h1></div>

    <div class="tagline"><h2>Cancelled Orders</h2></div>
   

    
  <div class="cancel_order_list">
    

   
       

</div>
    
</div>
</body>
</html>
