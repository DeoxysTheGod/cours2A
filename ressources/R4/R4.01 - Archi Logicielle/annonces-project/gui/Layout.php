<?php

namespace gui;

include_once "Menu.php";

use gui\Menu;

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

		$menu = $this->menu();

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

	public function menu(): string
	{
		$Menu = new Menu();
		$menu = $Menu->getMenu();
		if (isset($_SESSION['admin']) && $_SESSION['admin']) {
			$menu .= $Menu->getExtendedMenu();
		}
		return $menu;
	}

}
