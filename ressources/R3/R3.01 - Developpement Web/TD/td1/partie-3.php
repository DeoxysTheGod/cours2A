<?php
function start_page($title, $stylesheet): void
{
?><!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<?php echo $stylesheet; ?>.css">
    <title><?php echo $title; ?></title>
</head>
<body>
<?php
	}
?>

<?php
	function end_page(): void
	{
?></body>
</html>
<?php
	}
?>

<?php
	start_page('Test', 'styles');
?>
<p class="text">
	<?php
		$jour = date('d/m/y');
		$display_day = date('l d, Y, h:m a');
		echo $jour , '<br>', $display_day;
	?>
</p>
<?php
	end_page();