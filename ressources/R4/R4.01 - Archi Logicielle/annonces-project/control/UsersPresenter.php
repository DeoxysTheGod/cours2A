<?php

namespace control;

class UsersPresenter
{
	protected $usersCheck;

	public function __construct($usersCheck)
	{
		$this->usersCheck = $usersCheck;
	}

	public function getAllUsersHTML()
	{
		$content = null;
		if( $this->usersCheck->getUsersTxt() != null )
		{
			$content = '<h1>Liste des utilisateurs</h1>  <ul>';
			foreach( $this->usersCheck->getUsersTxt() as $user ) {
				$content .= ' <li>';
				$content .= $user['login'];
				$content .= '<a href="/annonces/index.php/deleteuser?id=' . $user['id'] . '">Supprimer</a>';
				$content .= '<a href="/annonces/index.php/blockuser?id=' . $user['id'] . '">Bloquer</a>';
				$content .= '<a href="/annonces/index.php/unblockuser?id=' . $user['id'] . '">Débloquer</a>';
				$content .= $user['admin'] ? 'Admin' : 'User';
				$content .= ' </li>';
			}
			$content.= '</ul>';
		}

		return $content;
	}
}