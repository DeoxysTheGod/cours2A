<?php

namespace gui;

class Layout
{
	protected $templateFile;

	public function __construct( $templateFile )
	{
		$this->templateFile = $templateFile;
	}

	public function display( $title, $content )
	{
		$page = file_get_contents( $this->templateFile );
		$page = str_replace( ['%title%','%content%'], [$title,$content], $page);

		$menu = '
		<nav>
			<ul>
			<li><a href="/annonces/index.php">Retourner au login</a></li>
				<li><a href="/annonces/index.php/annonces">Accueil</a></li>
				<li><a href="/annonces/index.php/createpost">Créer un post</a></li>
			</ul>
		</nav>';

		if (isset($_SESSION['login']))
		{
			$page = str_replace('%menu%', $menu, $page);
		}
		else
		{
			$page = str_replace('%menu%', '', $page);
		}


		echo $page;
	}

}
