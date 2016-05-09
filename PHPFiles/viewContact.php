<?php
 
// array for JSON response
$response = array();
 
// include db connect class
include_once 'DB_Connect.php';
 
// connecting to db
$db = new DB_CONNECT();

	$userResults = Array();
    // get users from user_info
    $result = mysql_query("SELECT * FROM user_info");
    
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
			while($results = mysql_fetch_row($result)) {
                $string=implode(" ",$results);
                array_push($userResults, $string);	
			}
 
            /**foreach($userResults as $value) {
                echo($value);
            }*/
            
			echo json_encode($userResults);
        } else {
            // error
            $response["failed"] = 0;
            $response["message"] = "No contact found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // error
        $response["failed"] = 0;
        $response["message"] = "No contact found";
 
        // echo no users JSON
        echo json_encode($response);
    }
?>