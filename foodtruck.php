<?php
  
  $fn =  $_POST['fname'];
  $ln = $_POST['lname'];
  $email = $_POST['email'];
  
  echo "I received: <br/>";
  print_r($fn);
  echo "<br\>\n";
  print_r($ln);
  echo "<br\>\n";
  print_r($email);
  echo "<br\>\n";
  
  
  echo 'trying to connect to database <br>';
  // Create connection
  $servername = 'localhost';
  $username = 'hci2021';
  $password = 'hci2021';
  $dbname = 'hci2021';
  $conn = new mysqli($servername, $username, $password, $dbname);

  // Check connection
  if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
  } 
    //go through each record and update the submitted data in the database 
    for($i = 0; $i < count ($fn); $i++){
        $query = "UPDATE customers set fname = '" . $fn[$i] . "', lname= '" . $ln[$i] . "' where email= '" . $email[$i] . "'";
        echo $query . "<br/>";
        $conn->query($query); //now update the record 
    }
  


  //query database
  $result = $conn->query(" SELECT fname, lname, email, cid FROM customers ");

  //display the result
  if ($result->num_rows > 0) {
	  
      // output data of each row
	echo "<form method=post>\n";
	while($row = $result->fetch_assoc()){
	  echo " First name: <input type=text name=fname[] value=" . $row['fname'] . ">,\n";
	  echo " Last name: <input type=text name=lname[] value=" . $row['lname'] . ">,\n";
	  echo " <input type=hidden name=email[] value=". $row['email'] . ">\n";
	  echo "  Email:" . $row['email'] . ",\n";
	  echo "  CID:" .  $row['cid'] . "<br/>\n";
       }
          echo "<input type = submit>";
        echo "</form>";
	
  } else {
    echo "0 results";
  }

  
  echo "Connected successfully";
  $conn->close(); // close connection
  
?>