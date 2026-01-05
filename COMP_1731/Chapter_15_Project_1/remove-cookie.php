<?php

session_start();


if(isset($_SESSION['PHILOSOPHER'])) {
    unset($_SESSION['PHILOSOPHER']);
}


if(isset($_COOKIE['THEME'])) {
    setcookie("THEME", "", time() - 3600, "/"); 
}

if(isset($_COOKIE['PHILOSOPHER'])) {
    setcookie("PHILOSOPHER", "", time() - 3600, "/"); 
}


session_destroy();


header("Location: ch15-proj1.php");
exit;
?>