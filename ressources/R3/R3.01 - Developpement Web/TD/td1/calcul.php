<?php require 'adv-p3.php'?>

<?php start_page('Calculator', 'styles');?>

<?php
	$op1 = $_GET['op1'];
	$op2 = $_GET['op2'];
	$action = $_GET['action'];

	echo '<p class="text">Résultat : ', eval("echo $op1 $action $op2;"), '</p>';

?>

<?php end_page();?>