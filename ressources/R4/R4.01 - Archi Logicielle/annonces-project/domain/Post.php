<?php

namespace domain;

class Post
{
	protected $id;
	protected $author;
	protected $title;
	protected $body;
	protected $date;
	protected $deletable;

	public function __construct($id, $author, $title, $body, $date, $deletable = false)
	{
		$this->id = $id;
		$this->author = $author;
		$this->title = $title;
		$this->body = $body;
		$this->date = $date;
		$this->deletable = $deletable;
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

	public function isDeletable()
	{
		return $this->deletable;
	}
}