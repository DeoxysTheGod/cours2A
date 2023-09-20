<?php
$dbLink = mysqli_connect("mysql-uzaki.alwaysdata.net", "uzaki", "Uza1234*")
or die('Erreur de connexion au serveur : ' . mysqli_connect_error());

mysqli_select_db($dbLink , "uzaki_bd")
or die('Erreur dans la sélection de la base : ' . mysqli_error($dbLink)
);

$query = 'SELECT * FROM myuser';

if (!($dbResult = mysqli_query($dbLink, $query))) {
	echo 'Erreur de requête<br>';
	// Affiche le type d'erreur.
	echo 'Erreur : ' . mysqli_error($dbLink) . '<br>';
	// Affiche la requête envoyée.
	echo 'Requête : ' . $query . '<br>';
	exit();
}


while ($dbRow = mysqli_fetch_assoc($dbResult)) {
	echo $dbRow['id'] . ' - ';
	echo $dbRow['name'] . ' - ';
	echo $dbRow['sexe'] . ' - ';
	echo $dbRow['mail'] . ' - ';
	echo $dbRow['mdp'] . ' - ';
	echo $dbRow['pays'] . '<br>';
 }

