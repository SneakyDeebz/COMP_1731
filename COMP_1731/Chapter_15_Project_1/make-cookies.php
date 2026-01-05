<?php

if(isset($_POST['theme']) && $_POST['theme'] != '0' && 
   isset($_POST['philosopher']) && $_POST['philosopher'] != '0') {
    
    
    $expiry = time() + 60*60*24; 
    setcookie("THEME", $_POST['theme'], $expiry, "/");
    
   
    setcookie("PHILOSOPHER", $_POST['philosopher'], 0, "/");
    
    
    session_start();
    $_SESSION['PHILOSOPHER'] = $_POST['philosopher'];
}


header("Location: ch15-proj1.php");
exit;
?>