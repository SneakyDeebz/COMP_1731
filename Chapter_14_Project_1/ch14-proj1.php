<?php
include 'includes/travel-config.inc.php';

try {
    
    $pdo = new PDO(DBCONNSTRING, DBUSER, DBPASS);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    
    $continentSQL = "SELECT ContinentCode, ContinentName FROM continents ORDER BY ContinentName";
    $continentStmt = $pdo->prepare($continentSQL);
    $continentStmt->execute();
    $continents = $continentStmt->fetchAll(PDO::FETCH_ASSOC);
    
    
    $countrySQL = "SELECT DISTINCT c.ISO, c.CountryName 
                  FROM countries c 
                  INNER JOIN imagedetails i ON c.ISO = i.CountryCodeISO 
                  ORDER BY c.CountryName";
    $countryStmt = $pdo->prepare($countrySQL);
    $countryStmt->execute();
    $countries = $countryStmt->fetchAll(PDO::FETCH_ASSOC);
    
   
    $sql = "SELECT i.ImageID, i.Title, i.Description, i.SourceURL, i.Exif, i.Colors, 
                  c.CountryName, ct.AsciiName as CityName 
           FROM imagedetails i
           LEFT JOIN countries c ON i.CountryCodeISO = c.ISO
           LEFT JOIN cities ct ON i.CityCode = ct.CityCode";
    
    
    $whereClause = [];
    $params = [];
    
    
    if (isset($_GET['continent']) && $_GET['continent'] != '0') {
        $whereClause[] = "c.Continent = ?";
        $params[] = $_GET['continent'];
    }
    
    
    if (isset($_GET['country']) && $_GET['country'] != '0') {
        $whereClause[] = "i.CountryCodeISO = ?";
        $params[] = $_GET['country'];
    }
    
    
    if (isset($_GET['title']) && !empty($_GET['title'])) {
        $whereClause[] = "i.Title LIKE ?";
        $params[] = '%' . $_GET['title'] . '%';
    }
    
    
    if (count($whereClause) > 0) {
        $sql .= " WHERE " . implode(" AND ", $whereClause);
    }
    
    
    $sql .= " ORDER BY i.Title";
    
    
    $stmt = $pdo->prepare($sql);
    $stmt->execute($params);
    $images = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
} catch (PDOException $e) {
    die("Database error: " . $e->getMessage());
}


function isSelected($value, $selected) {
    return $value == $selected ? 'selected' : '';
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Chapter 14</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/styles.css" />
</head>

<body>
    <header>
        <form action="ch14-proj1.php" method="get">
            <div class="form-inline">
                <select name="continent">
                    <option value="0">Select Continent</option>
                    <?php foreach ($continents as $continent) { ?>
                        <option value="<?= $continent['ContinentCode'] ?>" <?= isSelected($continent['ContinentCode'], $_GET['continent'] ?? '') ?>>
                            <?= $continent['ContinentName'] ?>
                        </option>
                    <?php } ?>
                </select>
                
                <select name="country">
                    <option value="0">Select Country</option>
                    <?php foreach ($countries as $country) { ?>
                        <option value="<?= $country['ISO'] ?>" <?= isSelected($country['ISO'], $_GET['country'] ?? '') ?>>
                            <?= $country['CountryName'] ?>
                        </option>
                    <?php } ?>
                </select>
                
                <input type="text" placeholder="Search title" name="title" value="<?= htmlspecialchars($_GET['title'] ?? '') ?>">
                <button type="submit" class="btn-primary">Filter</button>
                <button type="submit" class="btn-secondary" name="reset" value="true">Reset</button>
            </div>
        </form>
    </header>
    
    <main>
        <ul>
            <?php foreach ($images as $image) { ?>
                <li>
                    <a href="detail.php?id=<?= $image['ImageID'] ?>">
                        <img src="<?= htmlspecialchars($image['SourceURL']) ?>" alt="<?= htmlspecialchars($image['Title']) ?>">
                    </a>
                </li>
            <?php } ?>
        </ul>
    </main>
</body>
</html>