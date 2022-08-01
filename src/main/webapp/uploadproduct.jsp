<!doctype html>
<html>
<head>
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
    
    
<div class="container">
    <div class="title"><h1>EasyBuy</h1></div>
    
    <div class="tagline"><h2>Upload Your Product</h2></div>

    <br>
    <form action="UploadImage" method="post" enctype="multipart/form-data">
    
    <label for="pname" style="margin-top:10px;">Product name</label><br>
    <input required  id="pname"  class="input_field" type="text" name="pname" placeholder="Enter Product name"><br>

    <label for="price">Product Price</label><br>
    <input required  class="input_field" id="price" type="text" name="price" placeholder="Enter Price">
    <br>

    <label for="pdesc">Product description</label><br>
    <input  required  class="input_field" id="pdesc"  type="text" name="pdesc" placeholder="Enter Product Description">
  
    <br>
    
    <label for="p_img">Product Photo</label><br>
    <input required class="input_field" style="background-color:white;" id="p_img"  type="file" name="p_img" >
   
    <br>
    
    <label for="p_img_ext" style="display:none;">Product Photo Extension</label><br>
    <input  class="input_field" style="background-color:white; display:none;" value="" id="p_img_ext"  type="text" name="p_img_ext" >
   
    <br>
    <script>
    
    function checkFileExtension() {
        fileName = document.querySelector('#p_img').value;
        extension = fileName.split('.').pop();
        document.querySelector('#p_img_ext').value = extension;
 
    };
    
    </script>
    
    
 
   <br>
    
    <label for="form_submit"> </label>
        <input class="input_field" type="submit" onclick="checkFileExtension()" id="form_submit">
       
    </form>
</div>
</body>
</html>
