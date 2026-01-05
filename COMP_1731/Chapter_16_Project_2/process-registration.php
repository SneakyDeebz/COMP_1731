<?php
include 'config.inc.php';
include 'db-functions.php';


if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    $firstname = isset($_POST['first']) ? sanitizeInput($_POST['first']) : '';
    $lastname = isset($_POST['last']) ? sanitizeInput($_POST['last']) : '';
    $city = isset($_POST['city']) ? sanitizeInput($_POST['city']) : '';
    $country = isset($_POST['country']) ? sanitizeInput($_POST['country']) : '';
    $email = isset($_POST['email']) ? sanitizeInput($_POST['email']) : '';
    $password = isset($_POST['password']) ? $_POST['password'] : '';
    
    
    if (empty($firstname) || empty($lastname) || empty($city) || empty($country) || empty($email) || empty($password)) {
        die("All fields are required");
    }
    
    try {
        
        $pdo = getConnection();
        
        
        $stmt = $pdo->prepare("SELECT email FROM users WHERE email = ?");
        $stmt->execute([$email]);
        if ($stmt->fetch()) {
            die("Email already exists. Please choose another email or login if you already have an account.");
        }
        
        
        $bcrypt_hash = password_hash($password, PASSWORD_BCRYPT, ['cost' => 12]);
        
        
        $salt = bin2hex(random_bytes(16)); // 32 character string
        
        
        $sha256_hash = hash('sha256', $password . $salt);
        
        
        $stmt = $pdo->prepare("INSERT INTO users (email, firstname, lastname, city, country, password, salt, password_sha256) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        
        $stmt->execute([
            $email,
            $firstname,
            $lastname,
            $city,
            $country,
            $bcrypt_hash,
            $salt,
            $sha256_hash
        ]);
        
        
        redirectTo("login-form-bcrypt.php");
        
    } catch (PDOException $e) {
        die("Database error: " . $e->getMessage());
    }
} else {
    
    redirectTo("registration-form.php");
}
?>