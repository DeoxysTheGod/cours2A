<?php

namespace gui;

class Menu
{

	private $menu;
	private $extendedMenu;

	public function __construct()
	{
		$this->extendedMenu = '
		<nav>
			<ul>
				<li><a href="/annonces/index.php/signin">Créer un utilisateur</a></li>
				<li><a href="/annonces/index.php/manageusers">Bloquer / Débloquer / Supprimer un utilisateur</a></li>
			</ul>
		</nav>
		';

		$this->menu = '
			<nav>
				<ul>
				<li><a href="/annonces/">Retourner au login</a></li>
					<li><a href="/annonces/index.php/annonces">Accueil</a></li>
					<li><a href="/annonces/index.php/createpost">Créer un post</a></li>
				</ul>
			</nav>';
	}

	public function getMenu(): string
	{
		return $this->menu;
	}

	public function getExtendedMenu(): string
	{
		return $this->extendedMenu;
	}
}