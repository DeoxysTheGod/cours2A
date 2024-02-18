<?php

namespace service;

interface DataAccessInterface
{
	public function getUser($login, $password);
	public function addUser($login, $password, $name, $firstName);
	public function getAllAnnonces();
	public function getAllUsers();
	public function isUserAlreadyExist($login);
	public function getPost($id);
	public function addPost($title, $body);
	public function deletePost($idPost, $idUser) : bool;
	public function getUserIdFromLogin($login);

	public function blockUser($id);
	public function unblockUser($id);
	public function deleteUser($id);

}