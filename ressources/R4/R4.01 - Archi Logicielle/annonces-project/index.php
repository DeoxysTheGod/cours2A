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
include_once 'gui/ViewPost.php';

use gui\{Layout, ViewLogin, ViewAnnonces, ViewPost, ViewSignin};
use control\{Controllers, Presenter};
use data\DataAccess;
use service\AnnoncesChecking;

$data = null;
try {
	// construction du modèle
	$data = new DataAccess( new PDO('mysql:host=mysql-archilog.alwaysdata.net;dbname=archilog_annonce_db', 'archilog_annonce', 'Deoxysmaster2004*') );
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
if ( '/annonces/' == $uri || '/annonces/index.php' == $uri) {

	$layout = new Layout("gui/layout.html" );
	$vueLogin = new ViewLogin( $layout );

	$vueLogin->display();
}
elseif ( '/annonces/signin' == $uri || '/annonces/index.php/signin' == $uri) {

	$layout = new Layout("gui/layout.html" );
	$vueSignin = new ViewSignin( $layout );

	$vueSignin->display();
}
elseif ( '/annonces/index.php/annonces' == $uri
	&& isset($_POST['login']) && isset($_POST['password']) ){

	$controller->annoncesAction($_POST['login'], $_POST['password'], $data, $annoncesCheck);

	$layout = new Layout("gui/layout.html");
	$vueAnnonces= new ViewAnnonces( $layout, $_POST['login'], $presenter);

	$vueAnnonces->display();
}
elseif ( '/annonces/index.php/register' == $uri
	&& isset($_POST['name']) && isset($_POST['firstname']) && isset($_POST['signin']) && isset($_POST['password']) ){

	$result = $controller->addUser($_POST['signin'], $_POST['password'], $_POST['name'], $_POST['firstname'], $data);

	$layout = new Layout("gui/layout.html");
	if ($result) {
		$vueResult = new ViewLogin($layout);
	} else {
		$vueResult = new ViewSignin($layout);
	}

	$vueResult->display();
}
elseif ( '/annonces/index.php/post' == $uri
	&& isset($_GET['id'])) {

	$controller->postAction($_GET['id'], $data, $annoncesCheck);

	$layout = new Layout("gui/layout.html" );
	$vuePost= new ViewPost( $layout, $presenter );

	$vuePost->display();
}
else {
	header('Status: 404 Not Found');
	echo '<html><body><h1>My Page NotFound</h1></body></html>';
}
