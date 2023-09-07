# R3.01 - Développement Web - amphi 1

Balise <?php ?>

## Variable

* simple

$a = 10;

* référence

``` php
$a = 10;
$b = &$a;

$a = "hello world";
$b = '$a : ';
$c = <<<EOT
Hello world
EOT;
```

## Array

``` php
$arr = array('foo' => 'bar', 12 => true);
```

## Objet


``` php
class Foo
{
	function do_foo($a)
	{
		return $a / 2;
	}
}
$bar = new \Foo();
$b = $bar->do_foo(2);
```

## Constante

``` php
define('FOO', 'something');
define('2FOO', 'something);
define('__FOO__', 'something');
```

## Variables prédéfinies (super globale)

## Opérateurs

`===` plus rapide et égalité en valeur et en type

# PHP PSR Convention
