<?php
$dbLink = mysqli_connect("mysql-uzaki.alwaysdata.net", "uzaki", "Uza1234*")
or die("Erreur de connexion à la base");
mysqli_select_db($dbLink, "uzaki_bd");

$email = $_POST['email'];
$action = $_POST['action'];
$mdp = $_POST['mdp'];
$id = $_POST['id'];
$gender = $_POST['gender'];
$pays = $_POST['pays'];

$message = 'Voici vos identifiants d\'inscription :' . PHP_EOL;
$message .= 'Email : ' . $email . PHP_EOL;
$message .= 'Id : ' . $id . PHP_EOL;
$message .= 'Mot de passe : ' . $mdp;

$query = 'INSERT INTO myuser (name, sexe, mail, mdp, pays)
VALUES (\''.$id.'\', \''.$gender.'\', \'' . $email . '\', \'' . $mdp . '\', \'' . $pays. '\')';

if ($action === 'send') {
	if (!($dbResult = mysqli_query($dbLink, $query))) {
		echo 'Erreur dans requête';
		exit();
	}
}

if ($action === 'mailer') {
	if (mail($email, 'Confirmation inscription', $message)) {
		echo '<p>Mail sended</p>';

	} else {
		echo '<p>Can\'t send message</p>';
	}
}