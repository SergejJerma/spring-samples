<html>
<head>
<title>Welcome</title>
</head>
<body>
Welcome ${name}. You are now authenticated.
<a href="/list-todos">Click here</a> to start maintaining your todo's.

  <form>
   <p>Choose period: 
   <input type="date" name="calendar" value="2012-06-01"
   max="2012-06-04" min="2012-05-29">
   <input type="submit" value="Submit"></p>
  </form>
</body>
</html>