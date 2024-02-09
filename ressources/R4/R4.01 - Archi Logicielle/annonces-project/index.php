<?php

// charge et initialise les bibliothèques globales
include_once 'data/DataAccess.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/AnnoncesChecking.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewLogin.php';
include_once "gui/ViewSignin.php";
include_once 'gui/ViewAnnonces.php';
include_once 'gui/ViewCreatePost.php';
include_once 'gui/ViewPost.php';

use gui\{Layout, ViewCreatePost, ViewLogin, ViewAnnonces, ViewPost, ViewSignin};
use control\{Controllers, Presenter};
use data\DataAccess;
use service\AnnoncesChecking;

$data = null;
try {
	// construction du modèle
	$data = new DataAccess( new PDO('mysql:host=mysql-archilog.alwaysdata.net;dbname=archilog_annonce_db', 'archilog_annonce', 'Archilog1234*') );
} catch (PDOException $e) {
	print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
	die();
}

// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation AnnoncesChecking
$annoncesCheck = new AnnoncesChecking() ;

// intialisation du presenter avec accès aux données de AnnoncesCheking
$presenter = new Presenter($annoncesCheck);

// chemin de l'URL demandée au navigateur
// (p.ex. /annonces/index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// route la requête en interne
// i.e. lance le bon contrôleur en focntion de la requête effectuée
session_start();
if ( '/annonces/' == $uri || '/annonces/index.php' == $uri) {
	session_unset();

	$layout = new Layout("gui/layout.html" );
	$vueLogin = new ViewLogin( $layout );

	$vueLogin->display();
}
elseif ( '/annonces/index.php/login' == $uri
	&& isset($_POST['login']) && isset($_POST['password']) ){

	$controller->loginAction($_POST['login'], $_POST['password'], $data, $annoncesCheck);
}
elseif ( '/annonces/index.php/annonces' == $uri
&& isset($_SESSION['login']) && isset($_SESSION['password']) ){

	$controller->annoncesAction($_SESSION['login'], $_SESSION['password'], $data, $annoncesCheck);

	$layout = new Layout("gui/layout.html");
	$vueAnnonces= new ViewAnnonces( $layout, $_SESSION['login'], $presenter);

	$vueAnnonces->display();
}
elseif ( '/annonces/signin' == $uri || '/annonces/index.php/signin' == $uri) {

	$layout = new Layout("gui/layout.html" );
	$vueSignin = new ViewSignin( $layout );

	$vueSignin->display();
}
elseif ( '/annonces/index.php/register' == $uri
	&& isset($_POST['name']) && isset($_POST['firstname']) && isset($_POST['signin']) && isset($_POST['password']) ){

	$controller->addUserAction($_POST['signin'], $_POST['password'], $_POST['name'], $_POST['firstname'], $data);
}
elseif ( '/annonces/index.php/post' == $uri
	&& isset($_GET['id'])) {

	$controller->postAction($_GET['id'], $data, $annoncesCheck);

	$layout = new Layout("gui/layout.html" );
	$vuePost= new ViewPost( $layout, $presenter );

	$vuePost->display();
}
elseif ('/annonces/index.php/createpost' == $uri) {

	$layout = new Layout("gui/layout.html" );
	$vueCreatePost = new ViewCreatePost( $layout );

	$vueCreatePost->display();
}
elseif ('/annonces/index.php/addpost' == $uri
	&& isset($_POST['title']) && isset($_POST['body'])) {

	$controller->createPostAction($_POST['title'], $_POST['body'], $data);
}
else {
	header('Status: 404 Not Found');
	echo '<html><body><h1>My Page NotFound</h1></body></html>';
}
