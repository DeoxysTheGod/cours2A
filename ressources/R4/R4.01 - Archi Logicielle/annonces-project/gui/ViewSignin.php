<?php

namespace gui;

include_once "gui/View.php";

class ViewSignin extends View
{
	public function __construct($layout)
	{
		parent::__construct($layout);

		$this->title = 'Inscription';

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
        ';

		if (isset($_SESSION['admin']) && $_SESSION['admin']) {
			$this->content.= '
				<br />
				<label for="admin"> Admin </label> :
				<input type="checkbox" name="admin" id="admin" />
				';
		}


		$this->content .= '
                <input type="submit" value="Envoyer">
            </form>
            <ul>
            	<li>Le nom et prénom doivent comporter UNIQUEMENT des lettres majuscules ou minuscules (pas d\'espace)</li>
            	<li>Le login et le mot de passe doivent contenir UNIQUEMENT des lettres majuscules ou minuscules et des chiffres (pas d\'espace)</li>
            	<li>Le nom, prénom et login font entre 3 et 20 caractères</li>
            	<li>Le mot de passe fait entre 6 et 40 caractères</li>
            </ul>';
	}
}
