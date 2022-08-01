<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	   
	
	   
	   $('#check_email').click(function(){
		
		
		   var email = document.getElementById('email').value;
		   
		   document.querySelector("#email_msg").style.display = "none";
		   document.querySelector("#please_wait").style.display = "block";
		   
		   $.post("SendOtp",{email:email},function(data,status){
			   
			 
			   if(data=="1")
				   {
				   document.querySelector("#email_msg").style.display = "none";
				   document.querySelector(".email_div").style.display = "none";
				   document.querySelector(".otp_div").style.display = "block";
				   document.querySelector("#please_wait").style.display = "none";
				   document.getElementById("email").disabled = true;
				  
				   }
			   else
				   {
				   document.querySelector("#email_msg").style.display = "block";
				   document.querySelector("#please_wait").style.display = "none";

				   }
			  
			   
		   })
		   

		   
	   })
	   
	   
	      $('#check_otp').click(function(){
		
		
		   var otp = document.getElementById('otp').value;
		   
		   $.post("CheckOtp",{otp:otp},function(data,status){
			   
			   if(data=="1")
				   {
				   document.querySelector("#otp_msg").style.display = "none";
				   document.querySelector(".otp_div").style.display = "none";
				   document.querySelector(".pwd_div").style.display = "block";
				   document.getElementById("otp").disabled = true;
				  
				   }
			   else
				   {
				   document.querySelector("#otp_msg").style.display = "block";

				   }
			   
		   })
		   
	   })
	   
	   
	       $('#update_password').click(function(){
		
		
		   var pwd = document.getElementById('pwd2').value;
		   console.log(pwd)
		   if(pwd.length!=0)
			   {
		   $.post("UpdatePassword",{pwd:pwd},function(data,status){
			   
			   if(data=="1")
				   {
				   document.querySelector("#otp_msg").style.display = "none";
				   document.querySelector(".otp_div").style.display = "none";
				   document.querySelector(".pwd_div").style.display = "block";
				   document.getElementById("otp").disabled = true;
				   alert("Password Updated Successfully");
				   location.href="home.jsp";
				  
				   }
			   else
				   {
				   document.querySelector("#otp_msg").style.display = "block";

				   }
			   
		   })
			   }
		   else
			   {
		       $('#pwd_msg2').css('display','block');
		       $('#update_password').css('display','none');
			   }
		   
		   
	   })
	   
	   
	   
	   
	   
	   
	   
	 
    $('#pwd1, #pwd2').on('keyup', function () {
    	
    	 var pwd_regex = /(?=.*\d)(?=.*[A-Z])(?=.*[$@#?&.])(?=.*[a-z])/ ; // password validator regex
    	

 /*  if(!$('#pwd1').val().match(pwd_regex) || $('#pwd1').val().length<7)
   		{
	   
	    $('#pwd_msg3').css('display','block');
        $('#update_password').css('display','none');
	    
    	}
   else
	   {
	   $('#pwd_msg3').css('display','none');
	 
	   }*/
    	 
    	
    	
  if ($('#pwd1').val() == $('#pwd2').val() && $('#pwd1').val() && $('#pwd2').val() && ($('#pwd1').val().match(pwd_regex) && $('#pwd1').val().length>7)) 
  {
       $('#pwd_msg').css('display','none');
       $('#pwd_msg2').css('display','none');
	   $('#pwd_msg3').css('display','none');
   
       $('#update_password').css('display','block');
  } 
  else 
  {
       $('#pwd_msg').css('display','block');
       $('#pwd_msg2').css('display','none');
	    $('#pwd_msg3').css('display','block');
     
        $('#update_password').css('display','none');
        
        if( !$('#pwd1').val() && !$('#pwd2').val())
        	{
        	 $('#pwd_msg2').css('display','block');
        	 $('#pwd_msg').css('display','none');
        	
        	}
        
  }
});
       
      /*  $('#email').keyup(function(){
        
        var email = $(this).val();
        $.post("CheckEmail",{
            email:email
        },function(data,status){
            //console.log(data);
            //console.log(status);
           if(data=="1" && email!="")
               {
                   $('#email_msg').css('display', 'block'); 
               
                   $('#form_submit').css('display','none');
               }
            
            else{
                $('#email_msg').css('display', 'none'); 
     
                $('#form_submit').css('display','block');
            }
            
        });
        
        
    });*/
        

        
            
           
   });    

</script>
    
<div class="container">
    <div class="title"><h1>EasyBuy</h1></div>
    
    <div class="tagline"><h2>Update Password</h2></div>

    
    
    <label for="email" style="margin-top:10px;">Enter Email</label><br>
    <input id="email"  class="input_field" type="email" name="email" placeholder="Enter Email"><br>
    <div class="email_div">
    <button id="check_email"> Check Email</button>
    <p id="email_msg" style="color:red;display:none;">Email Doesn't exist</p>
    <p id="please_wait" style="display:none">Please Wait, The page will render itself.</p>
    <br>
    <br>
</div>
<div class="otp_div"  style="display:none;">
    <label for="otp">Enter OTP </label><br>
    <p>The OTP has been sent to your Email Id</p>
    <input class="input_field" id="otp" type="text" name="otp" placeholder="Enter OTP">
    <p id="otp_msg" style="color:red;display:none;">Incorrect OTP</p>
    <br>
    
    <button id="check_otp"> Check OTP </button>
    </div>

 <br>
 <br>
<div class="pwd_div" style="display:none;">
    <label for="pwd1">Set New Password</label><br>
    <input  class="input_field" id="pwd1"  type="password" name="pwd1" placeholder="Enter Password">
    <br>
 
    <label for="pwd2">Retype Password</label><br>
    <input  class="input_field"  id="pwd2" type="password" name="pwd2" placeholder="Retype Password">
     <p id="pwd_msg" style="color:red;display:none;"></p>
    <p id="pwd_msg2" style="color:red;display:none;">Please type your new password</p>
    <p id="pwd_msg3" style="color:red;display:display;">Password Must Satisfy the following conditions <br>-Both the Passwords should match<br> -Atleast One Capital Letter <br> -Atleast One Special Character <br> -Atleast One Digit Number <br> -Atleast One Small Letter <br> -Minimum Password length should be 7 </p>
   
    <br>
    
    
       
    <button id="update_password"> Submit </button>
    </div> 

      
</div>
</body>
</html>
