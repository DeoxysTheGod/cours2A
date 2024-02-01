<?php

namespace service;

class VerifyUserInformation {
	public function verifyUser($login, $password, $name, $firstName): bool
	{
		if ( $login == null || $password == null || $name == null || $firstName == null )
			return false;
		if ( strlen($login) < 3 || strlen($password) < 3 )
			return false;
		if ( strlen($login) > 20 || strlen($password) > 20 || strlen($name) > 20 || strlen($firstName) > 20 )
			return false;
		if ( !preg_match("/^[a-zA-Z0-9]+$/", $login) || !preg_match("/^[a-zA-Z0-9]+$/", $password) || !preg_match("/^[a-zA-Z]+$/", $name) || !preg_match("/^[a-zA-Z]+$/", $firstName) )
			return false;

		return true;
	}
}
