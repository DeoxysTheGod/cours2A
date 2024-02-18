<?php

namespace service;

include_once "domain/User.php";
include_once "domain/Post.php";

use domain\{User, Post};

class UsersChecking
{
	protected $usersTxt;

	public function getUsersTxt()
	{
		return $this->usersTxt;
	}

	public function getAllUsers($data)
	{
		$users = $data->getAllUsers();

		$this->usersTxt = array();
		foreach ($users as $user) {
			$this->usersTxt[] = ['id' => $user->getId(), 'login' => $user->getLogin(), 'admin' => $user->getAdmin(),
				'name' => $user->getName(), 'firstname' => $user->getFirstName()];
		}
	}
}