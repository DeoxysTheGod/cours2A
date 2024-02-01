<?php
    $link = mysqli_connect('localhost', 'root', '', 'blog_db');
    
    $result = mysqli_query($link, 'SELECT * FROM Post WHERE id='.$_GET['id'] );
    $post = mysqli_fetch_assoc($result);
?>

<!DOCTYPE html>
<html lang="fr">
 <head>
  <title>Exemple Blog Basic PHP</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
 </head>
 <body>

    <h1><?php echo $post['title']; ?></h1>

    <div class="date"> <?php echo $post['date']; ?> </div>
    <div class="body"> <?php echo $post['body']; ?> </div>

 </body>
</html>

<?php
    mysqli_close( $link );
?>