<?php
 
/*
 * Following code will create a new contact row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
// check for required fields
if (isset($_POST['firstName']) && isset($_POST['lastName']) && isset($_POST['phone']) && isset($_POST['email'])) {
 
    $firstName = $_POST['firstName'];
    $lastName = $_POST['lastName'];
    $phone = $_POST['phone'];
    $email = $_POST['email'];
    $imageName = $_POST['imageName'];
    $image = $_POST['image'];
    
    $decodedImage = base64_decode("$image");
    file_put_contents("pictures/" . $imageName . ".JPG", $decodedImage);
 
    // include db connect class
    include_once 'DB_Connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO user_info (first_name, last_name, phone, email, imageName) VALUES ('$firstName', '$lastName', '$phone', '$email', '$imageName')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully added.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["failed"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["failed"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>