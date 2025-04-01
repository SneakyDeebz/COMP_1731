<?php
function generateLink($url, $label, $class = '') {
    return "<a href='$url'" . ($class ? " class='$class'" : '') . ">$label</a>";
}

function outputStars($goldStars) {
    $output = '';
    // Output gold stars
    for ($i = 0; $i < $goldStars; $i++) {
        $output .= "<img src='images/star-gold.svg' width='16' />";
    }
    // Output white stars to complete 5 stars
    for ($i = $goldStars; $i < 5; $i++) {
        $output .= "<img src='images/star-white.svg' width='16' />";
    }
    return $output;
}

function outputPostRow($post) {
    ?>
    <div class="row">
        <div class="col-md-4"> 
            <?php echo generateLink("post.php?id={$post['postId']}", "<img src='images/{$post['thumb']}' alt='{$post['title']}' class='img-responsive'/>"); ?>
        </div>
        <div class="col-md-8"> 
            <h2><?php echo $post['title']; ?></h2>
            <div class="details">
                Posted by <?php echo generateLink("user.php?id={$post['userId']}", $post['userName']); ?>
                <span class="pull-right"><?php echo $post['date']; ?></span>
                <p class="ratings">
                    <?php 
                    echo outputStars($post['reviewsRating']);
                    echo " {$post['reviewsNum']} Reviews";
                    ?>
                </p>
            </div>
            <p class="excerpt">
                <?php echo $post['excerpt']; ?>
            </p>
            <p class="pull-right">
                <?php echo generateLink("post.php?id={$post['postId']}", "Read more", "btn btn-primary btn-sm"); ?>
            </p>
        </div>
    </div>
    <hr/>
    <?php
}
?>