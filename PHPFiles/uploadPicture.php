<?php
    $id = $_POST["id"];
    $imageName = $_POST["imageName"];
    $image = $_POST["image"];

    include_once 'DB_Connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();

    $result = mysql_query("UPDATE user_info SET imageName='$imageName' WHERE id='$id'");

    $decodedImage = base64_decode("$image");
    file_put_contents("pictures/" . $imageName . ".JPG", $decodedImage);
?>