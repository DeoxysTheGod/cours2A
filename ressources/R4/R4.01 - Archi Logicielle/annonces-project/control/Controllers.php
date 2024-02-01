<?php

namespace control;

use service\VerifyUserInformation;

include_once "service/AnnoncesChecking.php";
include_once "service/VerifyUserInformation.php";

class Controllers
{
	public function annoncesAction($login, $password, $data, $annonceCheck)
	{
		if ( $annonceCheck->authenticate($login, $password, $data) )
			$annonceCheck->getAllAnnonces($data);
	}

	public function postAction($id, $data, $annonceCheck)
	{
		$annonceCheck->getPost($id, $data);
	}

	public function addUser($login, $password, $name, $firstName, $data)
	{
		$areInformationsGood = (new VerifyUserInformation())->verifyUser($login, $password, $name, $firstName);
		if ( $areInformationsGood ) {
			$data->addUser($login, $password, $name, $firstName);
			return true;
		} else {
			return false;
		}
	}
}