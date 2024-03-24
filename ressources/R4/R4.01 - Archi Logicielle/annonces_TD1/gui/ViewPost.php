<?php
namespace gui;

include_once "View.php";

class ViewPost extends View
{
    public function __construct($layout, $presenter)
    {
        parent::__construct($layout);

        $this->title= 'Exemple Annonces Basic PHP: domain\Post';

        $this->content = $presenter->getCurrentPostHTML();
    }
}