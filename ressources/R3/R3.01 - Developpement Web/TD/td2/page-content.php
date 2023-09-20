<?php

class Layout
{
	public function __construct(private string $title, private string $content)
	{
	}

	public function show(): void
	{
		?>
        <!DOCTYPE html>
        <html lang="fr">
        <head>
            <meta charset="UTF-8">
            <link rel="stylesheet" href="styles.css">
            <title><?= $this->title; ?></title>
        </head>
        <body>
		<?= $this->content; ?>
        </body>
        </html>
		<?php
	}
}
