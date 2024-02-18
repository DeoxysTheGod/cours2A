<?php

namespace control;

use service\VerifyPostCreation;
use service\VerifyUserInformation;

include_once "service/AnnoncesChecking.php";
include_once "service/UsersChecking.php";
include_once "service/VerifyUserInformation.php";
include_once "service/VerifyPostCreation.php";

class Controllers
{
	public function loginAction($login, $password, $data, $annonceCheck)
	{
		if ($annonceCheck->authenticate($login, $password, $data)) {
			$_SESSION['login'] = $login;
			$_SESSION['password'] = $password;
			header("Location: /annonces/index.php/annonces");
		}
		else {
			header( "refresh:5;url=/annonces/index.php" );
			echo 'Erreur de login et/ou de mot de passe ou vous êtes bloqué (redirection automatique dans 5 sec.)';
			exit;
		}
	}

	public function annoncesAction($login, $password, $data, $annonceCheck)
	{
		if ($annonceCheck->authenticate($login, $password, $data)) {
			$annonceCheck->getAllAnnonces($data);
		}
	}

	public function postAction($id, $data, $annonceCheck)
	{
		$annonceCheck->getPost($id, $data);
	}

	public function addUserAction($login, $password, $name, $firstName, $data, $admin = false)
	{
		if ((new VerifyUserInformation())->verifyUser($login, $password, $name, $firstName, $data)) {
			$data->addUser($login, $password, $name, $firstName, $admin);
			header("Location: /annonces/");
		} else {
			header( "refresh:5;url=/annonces/index.php/signin" );
			echo 'Erreur dans le formulaire d\'inscription (redirection automatique dans 5 sec.)';
			exit;
		}
	}

	public function createPostAction($title, $body, $data)
	{
		if ((new VerifyPostCreation())->verifyPost($title, $body)) {
			$data->addPost($title, $body);
			header("Location: /annonces/index.php/annonces");
		} else {
			header("Location: /annonces/index.php/addpost");
		}
	}

	public function deletePostAction($id, $data)
	{
		$result = $data->deletePost($id, $data->getUserIdFromLogin($_SESSION['login']));
		if ($result) {
			header("Location: /annonces/index.php/annonces");
		} else {
			header( "refresh:5;url=/annonces/index.php/annonces" );
			echo 'La suppresion a échoué (redirection automatique dans 5 sec.)';
			exit;
		}
	}

	public function manageUsersAction($data, $usersCheck)
	{
		$usersCheck->getAllUsers($data);
	}

	public function blockUserAction($id, $data)
	{
		if ($_SESSION['admin'])
			$data->blockUser($id);
		header("Location: /annonces/index.php/manageusers");
	}

	public function unblockUserAction($id, $data)
	{
		if ($_SESSION['admin'])
			$data->unblockUser($id);
		header("Location: /annonces/index.php/manageusers");
	}

	public function deleteUserAction($id, $data)
	{
		if ($_SESSION['admin'])
			$data->deleteUser($id);
		header("Location: /annonces/index.php/manageusers");
	}
}