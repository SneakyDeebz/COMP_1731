<?php
include 'includes/travel-config.inc.php';


if (!isset($_GET['id']) || empty($_GET['id'])) {
    header("Location: ch14-proj1.php");
    exit;
}

try {
    
    $pdo = new PDO(DBCONNSTRING, DBUSER, DBPASS);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    
    $sql = "SELECT i.ImageID, i.Title, i.Description, i.SourceURL, i.Exif, i.Colors, 
              CONCAT(u.FirstName, ' ', u.LastName) AS UserName,
              c.CountryName, ct.AsciiName as CityName 
       FROM imagedetails i
       LEFT JOIN users u ON i.UserID = u.UserID
       LEFT JOIN countries c ON i.CountryCodeISO = c.ISO
       LEFT JOIN cities ct ON i.CityCode = ct.CityCode
       WHERE i.ImageID = ?";

    
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$_GET['id']]);
    $image = $stmt->fetch(PDO::FETCH_ASSOC);
    
    
    if (!$image) {
        header("Location: ch14-proj1.php");
        exit;
    }
    
    
    $exifData = json_decode($image['Exif'], true);
    $colorsData = json_decode($image['Colors'], true);
    
} catch (PDOException $e) {
    die("Database error: " . $e->getMessage());
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title><?= htmlspecialchars($image['Title']) ?> - Chapter 14</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/styles.css" />
</head>

<body>
    <main class="detail">
        <div>
            <img src="<?= htmlspecialchars($image['SourceURL']) ?>" alt="<?= htmlspecialchars($image['Title']) ?>">
        </div>
        <div>
            <h1><?= htmlspecialchars($image['Title']) ?></h1>
            <h3><?= htmlspecialchars($image['CityName'] ?? '') ?>, <?= htmlspecialchars($image['CountryName']) ?></h3>
            <p><?= htmlspecialchars($image['Description']) ?></p>
            
            <div class="box">
                <h3>Creator</h3>
                <p><?= htmlspecialchars($image['UserName']) ?></p>
            </div>
            
            <div class="box">
                <h3>Camera</h3>
                <?php if (isset($exifData) && is_array($exifData)): ?>
                    <p>
                        <?= isset($exifData['model']) ? htmlspecialchars($exifData['model']) : 'Unknown' ?><br>
                        ISO <?= isset($exifData['iso']) ? htmlspecialchars($exifData['iso']) : 'N/A' ?> | 
                        f/<?= isset($exifData['aperture']) ? htmlspecialchars($exifData['aperture']) : 'N/A' ?> | 
                        <?= isset($exifData['exposure']) ? htmlspecialchars($exifData['exposure']) : 'N/A' ?> sec
                    </p>
                <?php else: ?>
                    <p>No camera information available</p>
                <?php endif; ?>
            </div>
            
            <div class="box">
                <h3>Colors</h3>
                <?php if (isset($colorsData) && is_array($colorsData)): ?>
                    <div style="display: flex;">
                        <?php foreach ($colorsData as $color): ?>
                            <div style="width: 30px; height: 30px; background-color: <?= $color ?>; margin-right: 5px;"></div>
                        <?php endforeach; ?>
                    </div>
                <?php else: ?>
                    <p>No color information available</p>
                <?php endif; ?>
            </div>
        </div>
    </main>
</body>
</html>