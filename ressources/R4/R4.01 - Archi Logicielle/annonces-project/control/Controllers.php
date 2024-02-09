<?php

namespace control;

use service\VerifyPostCreation;
use service\VerifyUserInformation;

include_once "service/AnnoncesChecking.php";
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

	public function addUserAction($login, $password, $name, $firstName, $data)
	{
		if ((new VerifyUserInformation())->verifyUser($login, $password, $name, $firstName, $data)) {
			$data->addUser($login, $password, $name, $firstName);
			header("Location: /annonces/");
		} else {
			header("Location: /annonces/index.php/signin");
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
}