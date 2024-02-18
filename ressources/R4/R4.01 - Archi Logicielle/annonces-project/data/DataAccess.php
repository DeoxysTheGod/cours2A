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

		$query = 'SELECT * FROM Users WHERE login="' . $login . '" and pwd="' . $password . '"';
		$result = $this->dataAccess->query($query);


		if ($result->rowCount()) {
			$row = $result->fetch();

			$user = new User($row['Id'], $login, $password, $row['Name'], $row['FirstName'], $row['admin'], $row['blocked']);
			$_SESSION['admin'] = $row['admin'];
			$_SESSION['blocked'] = $row['blocked'];
			$_SESSION['login'] = $login;
		}
		$result->closeCursor();

		return $user;
	}

	public function getAllUsers(): array
	{
		$result = $this->dataAccess->query('SELECT * FROM Users');
		$users = array();

		while ($row = $result->fetch()) {
			$currentUser = new User($row['Id'], $row['Login'], $row['Pwd'], $row['Name'], $row['FirstName'], $row['admin']);
			$users[] = $currentUser;
		}

		$result->closeCursor();

		return $users;
	}

	public function isUserAlreadyExist($login)
	{
		$query = 'SELECT login FROM Users WHERE login="' . $login . '"';
		$result = $this->dataAccess->query($query);

		$isUserExist = $result->rowCount();

		$result->closeCursor();

		return $isUserExist;
	}

	public function addUser($login, $password, $name, $firstName, $admin = false)
	{
		$query = 'INSERT INTO Users (Login, Pwd, Name, FirstName, CreationDate, admin) VALUES ("' . $login . '", "' . $password . '", "' . $name . '", "' . $firstName . '", NOW(), "' . $admin . '")';
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
		$result = $this->dataAccess->query('SELECT Post.Id AS PostId, Users.Id AS UserId, Login, Title, Body, DatePost FROM Post INNER JOIN Users ON Post.IdUser = Users.Id WHERE Post.Id=' . $id);
		$row = $result->fetch();
		$post = new Post($row['PostId'], $row['Login'], $row['Title'], $row['Body'], $row['DatePost'],
			$this->isPostOwnerOrAdmin($row['PostId'], $this->getUserIdFromLogin($_SESSION['login'])));

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

	public function deletePost($idPost, $idUser) : bool
	{
		if ($this->isPostOwnerOrAdmin($idPost, $idUser)) {
			$query = 'DELETE FROM Post WHERE Id=?';
			$stmt = $this->dataAccess->prepare($query);
			$stmt->execute([$idPost]);
			$stmt->closeCursor();
			return true;
		}

		return false;
	}

	private function isPostOwnerOrAdmin($idPost, $idUser): bool
	{
		$query = 'SELECT IdUser FROM Post WHERE Id=?';
		$stmt = $this->dataAccess->prepare($query);
		$stmt->execute([$idPost]);
		$result = $stmt->fetch();
		$idUserPost = $result['IdUser'];
		$stmt->closeCursor();

		return ($idUser == $idUserPost || $_SESSION['admin']);
	}

	public function getUserIdFromLogin($login)
	{
		$query = 'SELECT Id FROM Users WHERE Login=?';
		$stmt = $this->dataAccess->prepare($query);
		$stmt->execute([$login]);
		$result = $stmt->fetch();
		$id = $result['Id'];
		$stmt->closeCursor();

		return $id;
	}

	public function blockUser($id)
	{
		$query = 'UPDATE Users SET blocked=1 WHERE Id=?';
		$stmt = $this->dataAccess->prepare($query);
		$stmt->execute([$id]);
		$stmt->closeCursor();
	}

	public function unblockUser($id)
	{
		$query = 'UPDATE Users SET blocked=0 WHERE Id=?';
		$stmt = $this->dataAccess->prepare($query);
		$stmt->execute([$id]);
		$stmt->closeCursor();
	}

	public function deleteUser($id)
	{
		$query = 'DELETE FROM Users WHERE Id=?';
		$stmt = $this->dataAccess->prepare($query);
		$stmt->execute([$id]);
		$stmt->closeCursor();
	}
}