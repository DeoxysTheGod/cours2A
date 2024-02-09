<?php

namespace service;

class VerifyPostCreation
{
	public function verifyPost($title, $body): bool
	{
		if ( $title == null || $body == null )
			return false;
		if ( strlen($title) > 20 || strlen($body) > 200 )
			return false;

		return true;
	}
}