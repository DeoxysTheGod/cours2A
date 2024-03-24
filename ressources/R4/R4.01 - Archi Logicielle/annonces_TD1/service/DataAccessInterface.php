<?php

namespace service;
interface DataAccessInterface
{
    public function getUser($login, $password);

    public function getAllAnnonces();

    public function getPost($id);
}