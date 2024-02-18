<?php

namespace gui;

include_once "View.php";

class ViewUsersManagement extends View
{
	public function __construct($layout, $presenter)
	{
		parent::__construct($layout);

		$this->title = "Gestion des utilisateurs";

		$this->content = $presenter->getAllUsersHTML();
	}
}