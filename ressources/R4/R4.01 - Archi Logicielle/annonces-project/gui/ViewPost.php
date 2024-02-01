<?php

namespace gui;

include_once "gui/View.php";

class ViewPost extends View
{
	public function __construct($layout, $presenter)
	{
		parent::__construct($layout);

		$this->title= 'Exemple Annonces Basic PHP: Post';

		$this->content = $presenter->getCurrentPostHTML();
	}
}
