<?php

namespace service;

include_once "domain/User.php";
include_once "domain/Post.php";

use domain\{User, Post};

class AnnoncesChecking
{
	protected $annoncesTxt;

	public function getAnnoncesTxt()
	{
		return $this->annoncesTxt;
	}

	public static function authenticate($login, $password, $data): bool
	{
		return ( $data->getUser($login, $password) != null );
	}

	public function getAllAnnonces($data)
	{
		$annonces = $data->getAllAnnonces();

		$this->annoncesTxt = array();
		foreach ($annonces as $post){
			$this->annoncesTxt[] = [ 'id' => $post->getId(), 'author' => $post->getAuthor(), 'title' => $post->getTitle(), 'body' => $post->getBody(), 'date' => $post->getDate() ];
		}
	}

	public function getPost($id, $data)
	{
		$post = $data->getPost($id);

		$this->annoncesTxt[] = array( 'id' => $post->getId(), 'author' => $post->getAuthor(), 'title' => $post->getTitle(), 'body' => $post->getBody(), 'date' => $post->getDate() );
	}
}
