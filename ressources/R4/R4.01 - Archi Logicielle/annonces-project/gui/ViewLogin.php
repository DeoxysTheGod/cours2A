<?php

namespace gui;

include_once "gui/View.php";

class ViewLogin extends View
{
	public function __construct($layout)
	{
		parent::__construct($layout);

		$this->title = 'Login';

		$this->content = '
			<h1>Connexion</h1>
            <form method="post" action="/annonces/index.php/annonces">
                <label for="login"> Votre identifiant </label> :
                <input type="text" name="login" id="login" placeholder="defaut" maxlength="12" required />
                <br />
                <label for="password"> Votre mot de passe </label> :
                <input type="password" name="password" id="password" maxlength="12" required />
        
                <input type="submit" value="Envoyer">
            </form>
            <p>Si vous n\'avez pas de compte cliquez ici : </p>
            <a href="/annonces/index.php/signin">Signin</a>';
	}
}
