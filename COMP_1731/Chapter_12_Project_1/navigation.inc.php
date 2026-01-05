<aside class="col-md-2">
    <div class="panel panel-info">
        <div class="panel-heading">Continents</div>
        <ul class="list-group">
            <?php
            foreach ($continents as $continent) {
                echo "<li class='list-group-item'><a href='#'>$continent</a></li>";
            }
            ?>
        </ul>
    </div>
    <!-- end continents panel -->

    <div class="panel panel-info">
        <div class="panel-heading">Popular</div>
        <ul class="list-group">
            <?php
            foreach ($countries as $iso => $country) {
                echo "<li class='list-group-item'>" . generateLink("photos.php?iso=$iso", $country) . "</li>";
            }
            ?>
        </ul>
    </div>
    <!-- end continents panel -->
</aside>