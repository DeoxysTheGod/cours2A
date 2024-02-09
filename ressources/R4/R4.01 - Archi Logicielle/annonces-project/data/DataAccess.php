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

		if ($result->rowCount()) {
			$user = new User($login, $password);
			$_SESSION['login'] = $login;
		}
		$result->closeCursor();

		return $user;
	}

	public function isUserAlreadyExist($login)
	{
		$query = 'SELECT login FROM Users WHERE login="' . $login . '"';
		$result = $this->dataAccess->query($query);

		$isUserExist = $result->rowCount();

		$result->closeCursor();

		return $isUserExist;
	}

	public function addUser($login, $password, $name, $firstName)
	{
		$query = 'INSERT INTO Users (Login, Pwd, Name, FirstName, CreationDate) VALUES ("' . $login . '", "' . $password . '", "' . $name . '", "' . $firstName . '", NOW())';
		$result = $this->dataAccess->query($query);

		$result->closeCursor();
	}

	public function getAllAnnonces(): array
	{
		$result = $this->dataAccess->query('SELECT Post.Id AS PostId, Users.Id AS UserId, Login, Title, Body, DatePost FROM Post INNER JOIN Users ON Post.IdUser = Users.Id ORDER BY DatePost DESC');
		$annonces = array();

		while ($row = $result->fetch()) {
			$currentPost = new Post($row['PostId'], $row['Login'], $row['Title'], $row['Body'], $row['DatePost']);
			$annonces[] = $currentPost;
		}

		$result->closeCursor();

		return $annonces;
	}

	public function getPost($id): Post
	{
		$id = intval($id);
		$result = $this->dataAccess->query('SELECT Post.Id AS PostId, Users.Id AS UserId, Login, Title, Body, DatePost FROM Post INNER JOIN Users ON Post.IdUser = Users.Id WHERE Post.Id=' . $id);
		$row = $result->fetch();
		$post = new Post($row['PostId'], $row['Login'], $row['Title'], $row['Body'], $row['DatePost']);

		$result->closeCursor();

		return $post;
	}

	public function addPost($title, $body)
	{
		$login = $_SESSION['login'];
		$query = 'SELECT Id FROM Users WHERE Login=?';
		$stmt = $this->dataAccess->prepare($query);
		$stmt->execute([$login]);
		$result = $stmt->fetch();
		$userId = $result['Id']; // Ajoutez cette ligne
		$stmt->closeCursor();
		$title = htmlspecialchars($title);
		$body = htmlspecialchars($body);
		$query = 'INSERT INTO Post (IdUser, Title, Body, DatePost) VALUES (?, ?, ?, NOW())';
		$stmt = $this->dataAccess->prepare($query);
		$stmt->execute([$userId, $title, $body]); // Modifiez cette ligne

		$stmt->closeCursor();
	}
}