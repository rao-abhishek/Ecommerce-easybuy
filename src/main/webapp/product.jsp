<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>EasyBuy</title>
<link rel="stylesheet" href="css/style.css" text="text/css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600;700&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous">
</script>
 <meta name="google-signin-client_id" content="438931416423-f9pfhe8pjal6o3ekmbnnmc6rvqemhi16.apps.googleusercontent.com">
 <script src="https://apis.google.com/js/platform.js" async defer></script>
        
</head>
    

<body>

<%  String s = (String) session.getAttribute("login");

String rld = (String) session.getAttribute("reload");

if(rld=="0")
{
	//String pid = request.getParameter("pid");
	//session.setAttribute("reload", "1");
	//String url = "product.jsp?pid="+pid;
	//System.out.println(rld);
    //response.setStatus(response.SC_MOVED_TEMPORARILY);
    //response.setIntHeader("Refresh",1);
	//response.setHeader("Location", url); 
}

else
{
	//System.out.println(rld);
}


%>

  <script>
  
        function onSignIn(googleUser) {
        	
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  //console.log('Password:'+ profile.getPassword());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  <% 
  //session.setAttribute("login","1");
  
	%>
 var http = new XMLHttpRequest();
  var url = 'AutoLogin';
  var params = 'uid=t'+profile.getId()+'&fname='+profile.getGivenName()+'&lname='+profile.getFamilyName()+'&email='+profile.getEmail();
  http.open('POST', url, true);
  
  /*document.getElementById('hemail').value = profile.getEmail();
  document.getElementById('hpwd').value = profile.getId();
  document.getElementById('hfname').value = profile.getGivenName();
  document.getElementById('hlname').value = profile.getFamilyName();
  document.getElementById('hurl').value = window.location.href;
  document.getElementById('hsubmit_form').click();*/

  //Send the proper header information along with the request
  http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  http.send(params);
  console.log('User Signned In (Google)');

	//location.reload();
  //location.reload();
  
}
           
          
                
             
                
                </script>
        
      <script>


      $(document).ready(function(){
    	  
    	  
    	  
    	  var product_data;
    	  
    	  $.post("AllProducts",{null:null},function(data,status)
    			  {
    		  console.log(data);
    		  console.log(status);
    		  
    		  
	 obj = JSON.parse(data);
    			 
    			 let in_html = ``;
    				let url ;
    				let img_url ;
    				let product_name ;
    				let price;
    			 
    			 
    			 for (var i = 0; i<obj.length; i++ )
    			 {
    				 
    				// console.log("pid test "+obj[i].pid);
    				url = obj[i].pid;
    				img_url = url+"."+obj[i].product_ext;
    				 product_name = obj[i].product_name;
    				 price = obj[i].product_price;
    				// console.log("pid img url test "+ img_url);
    				 
    			  in_html = in_html + `<a href="product.jsp?pid=\${url}"><div class="col-3">  
    		        <img src="product_img/\${img_url}">
    		           <div class="product-name">\${product_name}</div>
    		           <div class="product-price">&#8377; \${price}</div>
    		        </div> </a>`;
    			 
    			//  console.log(in_html);
    			 
    			 }
    			
    			 $('.feature_products').html(in_html);
    		  
    		//  $('.feature_products').html(data);
    		  
    			  })
    			  
    	var url_string = window.location.href;
    	var url = new URL(url_string); 
    	var pid = url.searchParams.get("pid");
    	console.log(pid);
    	
    	if(pid==null)
    		{
    		location.href="home.jsp";
    		}
    	
    	
    	
    	$.get("ShowProduct",{pid:pid},function(data,status){
    		
    		//document.querySelector("")
    		
    	 product_data = data;
    		
    		console.log(data);
    		console.log(status);
    		obj = JSON.parse(data);
    		console.log(obj[0].pid);
    		document.querySelector('#main_product_img').src="product_img/"+obj[0].pid+"."+obj[0].product_ext;
    		document.querySelector('#main_product_name').innerHTML=obj[0].product_name;
    		document.querySelector('#price').innerHTML="&#8377; "+obj[0].product_price;
    		document.querySelector('#main_product_desc').innerHTML=obj[0].product_description;
    		
    		
    		
    	})

      $('#form_submit').click(function(){
    	        
    	        var email = $("#email").val();
    	        var pwd = $("#pwd").val();
    	        
    	        $.post("UserLogin",{
    	            email:email,
    	            pwd:pwd
    	        },function(data,status){
    	            //console.log(data);
    	            //console.log(status);
    	           if(data=="1")
    	               {
    	                   $('#no_match').css('display', 'none'); 
    	                   location.reload();
    	                   //$('#form_submit').css('display','none');
    	               }
    	            
    	            else{
    	                $('#no_match').css('display', 'block'); 
    	               // $('#form_submit').css('display','block');
    	     
    	            }
    	            
    	        });
    	        
    	        
    	    });
    	       
    	
    	   $("#sign_out").click(function(){
         	  
       	    var auth2 = gapi.auth2.getAuthInstance();
       	    auth2.signOut().then(function () {
       	      console.log('User signed out.');
       	    });
       	   
       	  $.get("SignOut",{null:null},function(data,status)
       			  {
       		  console.log("User Signed Out");
       		   location.reload();
       			  })
       			  
       	   
          })
    	           
    	   });    

</script>


<div class="hidden_form" style="display:none;">
<form action="AutoLogin" method="post">
    <input type="text" name="lname" id="hfname">
    <input type="text" name="fname" id="hlname">
    <input type="text" name="url" id="hurl">
    <input type="text" name="email" id="hemail">
        <input type="password" name="id" id="hpwd" >
        <input type="submit" id="hsubmit_form">
        </form>
</div>

<div class="header">
<div class="container">
	<div class="navbar">
		<div class="logo">
	       <a href="home.jsp"><img src="img/logo.png" width="125px"></a>
	        <p> <%
	     
	        if(s=="1")
	        {
	           out.print("Hi "+session.getAttribute("fname")+" "); 
	           out.print(session.getAttribute("lname"));
	        }
	        
	        else
	        {
	        	//out.print(s);
	        }
	        
	       
	        
	        
	        %>  </p>
		</div>
        <nav>
            <ul>
                <li><a href="home.jsp">Home</a></li>

                 
                <%  
                if(s=="1")
                	{
                %>
                	
                <li><span id="sign_out" ><button style="width:auto;">Sign Out</button></span></li>
                
            
                
                <% 
                	}
                
                else
                { %>
                	 <li><a href="#" onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Account</a></li>
                  <%
                }
                %>
<div id="id01" class="modal">
  
  <div class="modal-content animate">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
      <img src="img/logo.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
       
      <label for="email"><b>Username</b></label>
      <input type="text" placeholder="Enter Email" id="email" name="email" required>

      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" id="pwd" name="pwd" required>
      <p style="color:red;display:none;" id="no_match">Username or Password doesn't match</p>
      <button type="submit" id="form_submit">Login</button>
        <div class="g-signin2" data-onsuccess="onSignIn" style="width:200px;margin-right:auto;margin-left:auto;'"></div>
      <label  >
      <a class="register" href="register.html">Register Now</a>
        <br>
        <a class="register" href="forgotpassword.jsp">Forgot Password</a>
       
        <input type="checkbox" checked="checked" style="display:none;" name="remember">
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
    
      <span class="psw" style="display:none;">Forgot <a href="#">password?</a></span>
    </div>
  </div>
</div>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
             
                
                <% if(s=="1") {%>
        <li><a href="cart.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li> 
        <%} else{
        
        	// 
        	
        %>
     <li><a><i class="fa fa-shopping-cart" onclick="document.getElementById('id01').style.display='block'" aria-hidden="true"></i></a></li> 
        <% }%>
                 
            </ul>
        
        </nav>
	</div>

 <!--    <div class="row">

    
    <div class="col-2">
        <img src="img/pngwing.com.png" alt="shopping.jpeg">
    </div>
        <div class="col-2">
        <h1> <span class="title">EasyBuy</span> anything with just a click!</h1>
        <p> Lorem Ipsum is simply dummy text of the printing and typesetting industry. <br> Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,<br> when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
        <a href="#" class="btn">Explore Now &#8594;</a>
    </div>
    </div>-->
</div>
</div>

<!---Featured categories----->
<div class="categories">

    <div class="row ">

<div class="products_details">

    <a href="#"><div class="col-3">  
        <img id="main_product_img" src="">
           <div class="product-name"></div>
           <div class="product-price"></div>
        </div> </a>  
    

</div>

        <h1 >
        <span id="main_product_name">
        
        </span>
      
        <br>
        <span id="price" style="color:green;">
   
        </span>
     
        </h1>
        
        <div class="product_description" id="main_product_desc" style="width:30%;min-width:200px;">
        <span id="main_product_desc">
        </span>
        </div>
<div class="cart_buy_button">
<% if(s=="1") {%>
        <button id="buy_now" style="width:auto;">Buy Now</button>
        <%} else{
        
        %>
        <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Buy Now</button>
        <% }%>
        
        <script>
         $('#buy_now').click(function(){
        	 document.body.style.cursor='wait';
        	 $.post("BuyProduct",{
        		 pid:obj[0].pid,
        		 uid:obj[0].uid,
        		 price:obj[0].product_price
        		 
        		 },function(data,status){
        		 
        		 console.log(status);
        		 document.body.style.cursor='default';
        		 alert("Order Placed Successfully");
        		 
        		location.href = "order.jsp";
        		 
        	 })
        	 
        	 
        	 
        	 
         })
         
              
        
        
        </script>
        <%  if(s=="1") { %>
        <Button id="add_to_cart" style="background-color:red;width:auto;'">Add to Cart</Button>
        <% } else { %>
          <Button onclick="document.getElementById('id01').style.display='block'"  style="background-color:red;width:auto;'">Add to Cart</Button>
          <% } %>
          
          <script>  $('#add_to_cart').click(function(){
         	 
         	 $.post("AddToCart",{
         		 pid:obj[0].pid,
         		 uid:obj[0].uid,
         		 price:obj[0].product_price
         		 
         		 },function(data,status){
         		 
         		 console.log(status);
         		 alert("Added To Cart Successfully");
         		 
         		 location.reload();
         		 
         	 })
         	 
         	 
         	 
         	 
          })
          </script>
          
          
        </div>
</div>

</div>
<!----------- Featured Products -->
<div class="all-products">
<div class="row ">
        <h1>
        You May Like it
        </h1>
<div class="feature_products">

    <a href="#"><div class="col-3">  
        <img src="img/product1.jpg">
           <div class="product-name">Sneaker #1</div>
           <div class="product-price">$200</div>
        </div> </a>  

    
   

    <a href="#"><div class="col-3">  
        <img src="img/product2.jpg">
              <div class="product-name">Sneaker #2</div>
               <div class="product-price">$200</div>
        </div> </a>  
    
     <a href="#"><div class="col-3">  
        <img src="img/product4.jpg">
           <div class="product-name">Sneaker #1</div>
           <div class="product-price">$200</div>
        </div> </a>  


 
   <a href="#"> <div class="col-3">  
        <img src="img/product3.jpg">
              <div class="product-name">Sneaker #3</div>
               <div class="product-price">$200</div>
       </div>  </a> 

</div>
</div>

 </div>
 <!--    
 new on trends 
<div class="new_on_trend"> 
 <div class="row ">
        <h1>
        New on Trend
        </h1>
<div class="feature_products">
    <a href="#"><div class="col-3">  
        <img src="img/product4.jpg">
              <div class="product-name">Sneaker #2</div>
               <div class="product-price">$200</div>
        </div> </a>  
    
      <a href="#"><div class="col-3">  
        <img src="img/product5.jpg">
           <div class="product-name">Sneaker #1</div>
           <div class="product-price">$200</div>
        </div> </a>  
    
     <a href="#"><div class="col-3">  
        <img src="img/product6.jpg">
           <div class="product-name">Sneaker #1</div>
           <div class="product-price">$200</div>
        </div> </a>  


 
   <a href="#"> <div class="col-3">  
        <img src="img/product3.jpg">
              <div class="product-name">Sneaker #3</div>
               <div class="product-price">$200</div>
       </div>  </a> 
      
     

</div>
</div>

    </div>

-->
</body>
</html>