<?php
    $link = mysqli_connect('localhost', 'root', '', 'blog_db');
    
    $query= 'SELECT login FROM Users WHERE login="'.$_POST['login'].'" and password="'.$_POST['password'].'"';
    $resultlogin = mysqli_query($link, $query );
    
    if( mysqli_num_rows( $resultlogin) ){
        mysqli_free_result( $resultlogin );
        $resultall = mysqli_query($link, 'SELECT id, title FROM Post');
    }
    else{
        header( "refresh:5;url=index.html" );
        echo 'Erreur de login et/ou de mot de passe (redirection automatique dans 5 sec.)';
        exit;
    }
?>

<!DOCTYPE html>
<html lang="fr">
 <head>
  <title>Exemple Blog Basic PHP</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
 </head>
 <body>
    <p> Hello <?php echo $_POST['login']; ?> </p>
    <h1>List of Posts</h1>
    <ul>
        <?php while ($row = mysqli_fetch_assoc($resultall)): ?>
        <li>
            <a href="post.php?id=<?php echo $row['id']; ?>">
            <?php echo $row['title']; ?>
            </a>
        </li>
        <?php endwhile ?>
    </ul>

 </body>
</html>

<?php
    mysqli_close( $link );
?>
