<?php

namespace domain;

class User
{
	protected $id;
	protected $login;
	protected $password;
	protected $name;
	protected $firstname;

	protected $admin;
	protected $blocked;

	public function __construct($id, $login, $password, $name, $firstname, $admin = false, $blocked = false)
	{
		$this->id = $id;
		$this->login = $login;
		$this->password = $password;
		$this->name = $name;
		$this->firstname = $firstname;

		$this->admin = $admin;
		$this->blocked = $blocked;
	}

	public function getId()
	{
		return $this->id;
	}

	public function getLogin()
	{
		return $this->login;
	}

	public function getAdmin()
	{
		return $this->admin;
	}

	public function getName() {
		return $this->name;
	}

	public function getFirstName() {
		return $this->firstname;
	}

	public function getBlocked()
	{
		return $this->blocked;
	}
}