<?php
	$email = $_POST['email'];
	$action = $_POST['action'];
	$mdp = $_POST['mdp'];

	$message = 'Voici vos identifiants d\'inscription :' . PHP_EOL;
	$message .= 'Email : ' . $email . PHP_EOL;
	$message .= 'Mot de passe : ' . PHP_EOL . $mdp;

	if ($action === 'mailer') {
		if (mail($email, 'jsp', $message))
		{
			echo '<p>Mail sended</p>';
		} else
		{
			echo '<p>Can\'t send message</p>';
		}
	}