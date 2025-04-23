<?php
function getConnection() {
    try {
       
        $pdo = new PDO('sqlite:./database/security.db');
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return $pdo;
    } catch (PDOException $e) {
        die("Database connection failed: " . $e->getMessage());
    }
}


function sanitizeInput($input) {
    return htmlspecialchars(trim($input));
}


function isUserLoggedIn() {
    if (session_status() === PHP_SESSION_NONE) {
        session_start();
    }
    return isset($_SESSION['user']);
}


function redirectTo($page) {
    header("Location: $page");
    exit();
}
?>