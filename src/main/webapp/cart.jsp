<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    
    
    %>

<html>
<head>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/d_register.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600;700&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous">
</script>

</head>
<body>
    

    <script>


   $(document).ready(function(){
	   
	   
		 let in_html = ``;
			let url ;
			let img_url ;
			let product_name ;
			let price;
			let total_price =  0;
			var oids= new Array();
	   
	   $.get("CartProducts",{null:null},function(data,status)
 			  {
 		  console.log(data);
 		  console.log(status);
 		  
 		  
	 obj = JSON.parse(data);
 			 
 
 			 console.log(obj.length);
 			 
 			 for (var i = 0; i<obj.length; i++ )
 			 {
 				 
 				// console.log("pid test "+obj[i].pid);
 				url = obj[i].pid;
 				img_url = url+"."+obj[i].product_ext;
 				 product_name = obj[i].product_name;
 				 price = obj[i].product_price;
 				 total_price = parseInt(total_price) + parseInt(price);
 				 oids[i] = obj[i].oid;
 				 
 				 
 				// console.log("pid img url test "+ img_url);
 					
 				 
 			  in_html = in_html + ` <a href="product.jsp?pid=\${url}">
 			    <div class="product_item" >
 			    <img src = "product_img/\${img_url}"  style="width:30%;">
 			    <p style="float:right;margin:10px;"><b>\${product_name}</b> <br> &#x20B9; \${price}</p>
 			   
 			    <br>
 			    </div>
 			    </a>`;
 			 
 			//  console.log(in_html);
 			 
 			 }
 			
 			 $('.order_list').html(in_html);
 			 $('.buy_all').html("Buy All ,&#x20B9;"+total_price);
 			 
 			 console.log(oids);
 		  
 		//  $('.feature_products').html(data);
 		  
 			  })
 			  
 			   console.log(oids);
 		
 			  $('.buy_all').click(function(){
 				  
 				 console.log(oids);
 				  
 				  $.post("BuyFromCart", {oids:oids},function(data, status)
 						  {
 					  
 					  document.body.style.cursor='wait';
 					  location.href = "order.jsp";
 						  })
 				  
 				  
 				  
 				  
 			  })
 		 
	   
	

   });    

</script>
    
    
    
<div class="container"  style="background-color:lightblue; min-heigth:650px ; height: auto" >
    
    
      <a href="home.jsp"><i class="fa fa-home" aria-hidden="true"></i></a>
    <div class="title"><h1>EasyBuy</h1></div>
    
   
    <div class="tagline"><h2>Cart</h2></div>
    
    <button class="buy_all"> Buy All </button>


    
  <div class="order_list">
    

   
       

</div>
    
</div>
</body>
</html>
