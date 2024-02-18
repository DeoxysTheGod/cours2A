<?php

namespace control;

class Presenter
{
	protected $annoncesCheck;

	public function __construct($annoncesCheck)
	{
		$this->annoncesCheck = $annoncesCheck;
	}

	public function isDeletable()
	{
		$deletable = false;
		if( $this->annoncesCheck->getAnnoncesTxt() != null )
		{
			$post = $this->annoncesCheck->getAnnoncesTxt()[0];
			$deletable = $post['deletable'];
		}

		return $deletable;
	}

	public function getAllAnnoncesHTML()
	{
		$content = null;
		if( $this->annoncesCheck->getAnnoncesTxt() != null )
		{
			$content = '<h1>Liste des Annonces</h1>  <ul>';
			foreach( $this->annoncesCheck->getAnnoncesTxt()as $post ) {
				$content .= ' <li>';
				$content .= '<a href="/annonces/index.php/post?id=' . $post['id'] . '">' . $post['title'] . '</a>';
				$content .= ' </li>';
			}
			$content.= '</ul>';
		}

		return $content;
	}

	public function getCurrentPostHTML()
	{
		$content = null;
		if( $this->annoncesCheck->getAnnoncesTxt() != null )
		{
			$post = $this->annoncesCheck->getAnnoncesTxt()[0];

			$content = '<p>' . $post['author'] . '</p>';
			$content .= '<h1>' . $post['title'] . '</h1>';
			$content .= '<div class="date">' . $post['date'] . '</div>';
			$content .= '<div class="body">' . $post['body'] . '</div>';
			if ($post['deletable']) {
				$content .= '<a href="/annonces/index.php/deletepost?id=' . $post['id'] . '">Delete</a>';
			}
		}

		return $content;
	}

	// Récupération du titre du post
	public function getCurrentPostTitle()
	{
		$title = null;
		if( $this->annoncesCheck->getAnnoncesTxt() != null )
		{
			$post = $this->annoncesCheck->getAnnoncesTxt()[0];
			$title = strval($post['title']);
		}

		return $title;
	}

	public function getCurrentPostId()
	{
		$id = null;
		if($this->annoncesCheck->getAnnoncesTxt() != null)
		{
			$post = $this->annoncesCheck->getAnnoncesTxt()[0];
			$id = $post['id'];
		}

		return $id;
	}
}
