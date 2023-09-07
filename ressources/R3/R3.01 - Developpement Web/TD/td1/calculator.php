<?php require 'adv-p3.php';?>

<?php start_page('Calculatrice', 'styles');?>

	<form action="calcul.php" method="get">
		<input name="op1">
        <input name="op2"><br>

        <?php
            $operateur = '*+-/';

            for ($i = 0 ; $i < strlen($operateur) ; ++$i) {
                echo '<button type="submit" name="action" value="', $operateur[$i], '">', $operateur[$i], '</button>', "\n";
            }
        ?>
        <button type="reset">Reset</button>
	</form>

<?php end_page();?>