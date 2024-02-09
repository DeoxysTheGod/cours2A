<?php

namespace domain;

class Post
{
	protected $id;
	protected $author;
	protected $title;
	protected $body;
	protected $date;

	public function __construct($id, $author, $title, $body, $date)
	{
		$this->id = $id;
		$this->author = $author;
		$this->title = $title;
		$this->date = $date;
		$this->body = $body;
	}

	public function getId()
	{
		return $this->id;
	}

	public function getAuthor()
	{
		return $this->author;
	}

	public function getTitle()
	{
		return $this->title;
	}

	public function getBody()
	{
		return $this->body;
	}

	public function getDate()
	{
		return $this->date;
	}
}