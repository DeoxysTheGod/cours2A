<?php

namespace service;

class VerifyUserInformation {
	public function verifyUser($login, $password, $name, $firstName, $data): bool
	{
		if ( $login == null || $password == null || $name == null || $firstName == null )
			return false;
		if ($data->isUserAlreadyExist($login))
			return false;
		if ( strlen($login) < 3 || strlen($password) < 6 || strlen($name) < 3 || strlen($firstName) < 3 )
			return false;
		if ( strlen($login) > 20 || strlen($password) > 40 || strlen($name) > 20 || strlen($firstName) > 20 )
			return false;
		// On peut changer le regex du mot de passe pour demander quelquechose de plus complexe
		if ( !preg_match("/^[a-zA-Z0-9]+$/", $login) || !preg_match("/^[a-zA-Z0-9]+$/", $password) || !preg_match("/^[a-zA-Z]+$/", $name) || !preg_match("/^[a-zA-Z]+$/", $firstName) )
			return false;

		return true;
	}
}
