<?php

namespace gui;

class ViewCreatePost extends View
{
	public function __construct($layout)
	{
		parent::__construct($layout);

		$this->title = 'Login';

		$this->content = '
			<h1>Connexion</h1>
            <form method="post" action="/annonces/index.php/addpost">
                <label for="title"> Title </label> :
                <input type="text" name="title" id="title" placeholder="Titre"/>
                <br />
                <label for="body"> Corps </label> :
                <textarea name="body" id="body" placeholder="Texte du post"></textarea>
        
                <input type="submit" value="Envoyer">
            </form>';
	}
}