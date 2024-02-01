<?php

namespace gui;

include_once "gui/View.php";

class ViewSignin extends View
{
	public function __construct($layout)
	{
		parent::__construct($layout);

		$this->title = 'Signin';

		$this->content = '
			<h1>Inscription</h1>
            <form method="post" action="/annonces/index.php/register">
            	<label for="name"> Votre nom </label> :
                <input type="text" name="name" id="name" placeholder="Homer" />
                <br />
            	<label for="firstname"> Votre prénom </label> :
                <input type="text" name="firstname" id="firstname" placeholder="Dalors" />
                <br />
                <label for="login"> Votre identifiant </label> :
                <input type="text" name="signin" id="signin" placeholder="xXx_dark_sasuke_du_23_xXx" />
                <br />
                <label for="password"> Votre mot de passe </label> :
                <input type="password" name="password" placeholder="Mdp compliqué" id="password" />
        
                <input type="submit" value="Envoyer">
            </form>';
	}
}
