<?php

namespace data;

use service\DataAccessInterface;
include_once "service/DataAccessInterface.php";

use domain\{User, Post};
include_once "domain/User.php";
include_once "domain/Post.php";

class DataAccess implements DataAccessInterface
{
	protected $dataAccess = null;

	public function __construct($dataAccess)
	{
		$this->dataAccess = $dataAccess;
	}

	public function __destruct()
	{
		$this->dataAccess = null;
	}



	public function getUser($login, $password)
	{
		$user = null;

		$query = 'SELECT login FROM Users WHERE login="' . $login . '" and pwd="' . $password . '"';
		$result = $this->dataAccess->query($query);

		if ($result->rowCount())
			$user = new User($login, $password);

		$result->closeCursor();

		return $user;
	}

	public function addUser($login, $password, $name, $firstName)
	{
		$query = 'INSERT INTO Users (Login, Pwd, Name, FirstName) VALUES ("' . $login . '", "' . $password . '", "' . $name . '", "' . $firstName . '")';
		$result = $this->dataAccess->query($query);

		$result->closeCursor();
	}

	public function getAllAnnonces(): array
	{
		$result = $this->dataAccess->query('SELECT * FROM Post');
		$annonces = array();

		while ($row = $result->fetch()) {
			$currentPost = new Post($row['Id'], $row['Title'], $row['Body'], $row['DatePost']);
			$annonces[] = $currentPost;
		}

		$result->closeCursor();

		return $annonces;
	}

	public function getPost($id): Post
	{
		$id = intval($id);
		$result = $this->dataAccess->query('SELECT * FROM Post WHERE id=' . $id);
		$row = $result->fetch();

		$post = new Post($row['Id'], $row['Title'], $row['Body'], $row['DatePost']);

		$result->closeCursor();

		return $post;
	}
}