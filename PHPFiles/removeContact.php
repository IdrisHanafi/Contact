<?php
    $id = $_POST["id"];
    
    include_once 'DB_Connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();

    $result = mysql_query("DELETE FROM user_info WHERE id='$id'");
?>