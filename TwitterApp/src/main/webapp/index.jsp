<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<style>
body{

	background : url("Backgroung.jpg") ;
	background-size: cover;
	font-family:sans-serif;
}
.box{
	position:absolute;
	top:50%;
	Left:50%;
	width:400px;
	padding:40px;
	box-sizing:border-box;
	transform: translate(-50%,-50%);
	background: rgba(0, 0 ,0 ,.6);
	box-shadow:0 15px 25px rgba(0,0,0,0.7);
	border-radius:20px;
	color:white;
}
</style>
</head>
<body>
<div class="box">
<h2>Twitter Application</h2>
<form method="post" action="GetInfo">
	<input class="btn btn-sm btn-primary" type="submit" value="GetTweets"></input><br/>
</form>

</div>
</body>
</html>
