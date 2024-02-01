
# COURS JAVASCRIPT
## Introduction

- Javascript != Java !
- Une norme : ECMAScript
- JavaScript n’étant pas un langage compilé, il doit être exécuté par le biais d’un interpréteur. Il s’agit d’un langage dit interprété.

- Documentation :
	- ECMA : [https://www.ecma-international.org/technical-committees/tc39/?tab=general](https://www.ecma-international.org/technical-committees/tc39/?tab=general)
	- MDN (Mozilla Developper Network)
	- Compatibilité des navigateurs : [http://kangax.github.io/compat-table/es2016plus/](http://kangax.github.io/compat-table/es2016plus/)

### Evolution des usages du langage au fil du web
- débuts du web : scripts pour rendre un peu plus dynamique les pages html
  - Effet de survol
  - Petites animations
  - Contrôles de formulaires côté client (de confort et NON de sécurité !)
- Les années 2000 : les RIA (Rich Internet Application)
	- Usage d'Ajax (Asynchronous Javascript And XML)
	- Les début des bibliothèques (Prototype, Script Aculo us, Jquery)
- Années 2010 : Du Js partout, tout le temps, à toutes les sauces
	- Hégémonie de Jquery puis...
	- Côté serveur (Node)
	- Développement d'applications (Visual Studio Code, adobe acrobat)
	- Framework côté client (Angular, React, Vue...)

- Années 2020 : Retour aux fondamentaux pour le web côté client ?
	- Les PWA (Progressive web App)

### Objet de ce cours

Après avoir découvert les bases du langage, vous réaliserez une petite application permettant de visualiser en temps réel les valeurs de deux capteurs de températures accessibles via une API, de conserver leur historique, envoyer des notifications, le tout fonctionnant en mode connecté et déconnecté.

	
## Les bases du langage (cours 1)
- inclusion dans les pages html
	-	balise script 
	```js
	<script> 
		var test = "Ceci est un test";
		console.log(test);
	</script>
	```

	Vous noterez la présence de la méthode log de l'objet console : `console.log(“ma chaine“);` mise à disposition par les outils de développement présents dans tout bon navigateur.
	
	- via une référence externe
		```js
		<script src="monScript.js"></script>
		```
	
	 
- Où ? 
	
  - Partout dans le html, Head, Body...
  - Mais il y a des contraintes, nous verrons cela en temps voulu

  - "Legacy"
 	```js
 	<a href="un lien" onclick="du code js" >
	``` 
 		
### Commentaires

// Commente une ligne.

/* Ouvre un bloc de commentaire.

*/ Ferme un bloc de commentaire.
```js
	// Un commentaire sur une ligne
 
	/* Un bloc de commentaire sur une seule ligne */
 
	/* Un bloc de commentaire
	  sur
	 plusieurs lignes */
```

  
### Variables et opérateurs

Tout comme PHP, JavaScript est un langage dynamiquement typé. Cela signifie que le type d’une variable est défini uniquement au moment de l’exécution.

Toute variable déclarée non initialisée aura comme valeur `undefined`.

#### Variables
- ` var maVariable;`
	
	variable globale ou locale selon le contexte
	
- `let maVariable;`
	
	Variable locale, bloc courant
	
- `const maVariable;`
	
	Constante, bloc courant, accessible en lecture seule
	
	
- Initialisation
	```js
	// Variable initialisée avec une chaîne de caractères 

	 var S_maVariable1 = "mon texte";
	 
	// Variable non initialisée 

	 var S_maVariable2; //undefined
	 
	```	
	
	
	Vous aurez noté l'usage d'une convention (donc propre au cours et non au langage) afin, qu'à la lecture de l'identifiant de la variable, nous en connaissions le type attendu :
	
		S_ //chaine de caractères, String
		I_ //Entier, Integer
		B_ //Bouléen
		A_ // Tableau, Array
		O_ // Objet
		
- les types primitifs

	Il existe plusieurs types de variables dites primitives.
	Les types primitifs en JavaScript comprennent des types de base qui correspondent à des pseudo-- objets disposants de méthodes prédéfinies. Laissons quelques temps ceci de côté pour nous concentrer sur les fondamentaux.

 - Booléen (boolean)
	```js
	 var B_test = true ; // ou false
	```
 - Nombre (number)
 
	 ```js
	 var I_nombreEntier = 5; //entier
 		
	 var I_nombreReel = 15.67; // nombre réel
   ```		
 - Chaîne de caractères (string)
	```js
	 var S_chaineCaracteres = "Mon texte";
	```
		
		
 - Tableau (array)
	```js
	var A_tableau = [ "Premier élément", "Second élément" ]; 
	console.log(A_tableau[0]);
		
	var A_tableauAssociatif = { "cle1" : "valeur1", "cle2" : "valeur2" }; 		
	console.log(A_tableauAssociatif["cle1"]) ;
	```

 - Objet Javascript (object)
	```js
	var O_objet1 = new Object();
	var O_object2 = null;
	```	
	Remarquons qu’une variable ayant la valeur `null` est de type object. Le mot-clé `null` permet de définir une variable par référence qui ne pointe pour le moment sur aucune valeur.

 - Indéfini (undefined)
	```js
	var S_test;
	```
- Détermination d’un type

	Vous pouvez déterminer le type d’une variable grâce au mot clé typeof.
		
	```js
	// S_test1 est de type undefined		
	var S_test1;	
	console.log(" type de S_test1 : "+(typeof S_test1));
	
	// S_test2 est de type string	
	var S_test2 = “mon texte“ ;	
	console.log(“S_test2 est de type : “+(typeof S_test2)) ;
	
	// S_test3 est de type object
	var S_test3 = null;
	console.log(" type de S_test3 : "+(typeof S_test3));

	// S_test4 est de type number
	var S_test4 = 12.3;
	console.log(" type de S_test4 : "+(typeof S_test4));
	```		
#### Opérateurs
- Affectation
L’opérateur d’affectation est le signe = 
	```js
	I_maVariable = 13;
	```
- Calcul

  Ils permettent d’effectuer des calculs de base sur les nombres.

	- addition : +
	- soustraction : -
	- multiplication : *
	- division : /
	- modulo : %
	- incrémentation : ++
	- décrémentation : - -
	```js
 	var I_unNombre = 10 * 2;
	
	var I_unAutreNombre = I_unNombre - 5;
	```
	Attention :
	```js
	 var I_unNombre = 10;

	 var I_unAutreNombre = I_unNombre++;

	// I_unAutreNombre vaut 10 et I_unNombre vaut 11
	```

	Versus :
	```js
	var I_unNombre = 10;

	var I_unAutreNombre = ++I_unNombre;

	// I_unAutreNombre vaut 11 et I_unNombre vaut 11
	```
- Concaténation
	Le signe + permet d’effectuer une concaténation de chaines de caractères.
	```js
	var S_uneChaine = “une chaine “ + “de caractères“ ;
	```

- Comparaison
	Il existe deux opérateurs de comparaison d’égalité :
	Le premier est `==` qui permet de comparer les valeurs de deux variables.
	Le second est `===` qui est l’opérateur de comparaison strict. Il compare les valeurs et les types.
	- égalité de valeurs : ==
	- égalité de valeurs et de type : ===
	- supérieur : >
	- inférieur : <
	- supérieur ou égal : >=
	- inférieur ou égal : <=
- Logique
	- NON: !
	- ET : &&
	- OU : ||
	- OU exclusif : ^
	Ce type d’opérateur peut être utilisé dans les structures de contrôle conditionnelles et de boucle.
- Conditionnel
	Cet opérateur permet d’initialiser une variable dont la valeur se fonde sur le résultat d’une 	condition. La syntaxe s’appuie sur les caractères ? et : afin de définir trois blocs. Le premier bloc 	correspond à la condition, le second à la valeur à utiliser lorsque la condition est vérifiée et le 	troisième à la valeur quand la condition ne l’est pas.
	
	uneVariable = uneConditon ? valeurVrai : valeurFaux ;
	```js
	var S_unText = 5 < 2 ? “5 est bien inférieur à 2“ : “5 n’est pas inférieur à 2, bizarre“;
	```

### Structures conditionnelles
Il existe deux types de structures de conditions. Le if et le switch.

- if
	if est l’équivalent de Si.
	```js
	 if ( condition ) { 	
	 	    //instructions
	  }
	``` 
	
	if, else 
	if, else est l’équivalent de Si, sinon.
	 ```js
	 if ( condition ) { 
		  //instructions
	 } else{
		 //instructions
	  }
  ```
	
	if, else if
	if, else if est l’équivalent de Si, sinon Si.
	
	```js
	 if ( condition ) { 
		 //instructions
	 } else if ( condition ){
		 //instructions 
	 }else{
	 //instructions
	 }
	```
	Exemple :
	```js
	 if(B_test == true){ // Si B_test est égal à Vrai
		console.log(“B_test est vrai“) ;
	}else{ // Sinon
		 console.log(“B_test est faux“) ; 
	}
	```

- switch, case
	Switch, case est une structure conditionnelle par branchement.

	En fonction de la valeur d’une variable, un branchement sera effectué 		sur le cas correspondant pour effectuer les instructions jusqu’à la première instruction break rencontrée ou la fin du bloc switch.
	
	```js
	switch ( variable ){ 
		case valeur1 :
			 //instructions
		 break ; 
		 case valeur2 :
			 //instructions 
		 case valeur3 :
		 case valeur4 : 
			 //instructions
		 break ; 
		 default :
			 instructions
		 break ; //facultatif
	}
	```

Il est possible d’utiliser autant de `case` que souhaité.

Le bloc `default` correspond au cas par défaut si aucun bloc case ne correspond à la valeur de la variable. Le bloc `default` est facultatif.

```js
    
switch (I_unNombre){ 
	case 1 : 
		console.log(“Le nombre vaut 1“); 
	break ;
	case 2 :
		console.log(“Le nombre vaut 2“);
	break ; 	
	default : 
		console.log(“Le nombre vaut “ + I_unNombre);
	}
   
```

### Structures itératives

JavaScript définit quatre types de boucles, for, for...in, while et do...while. 
- for

for est l’équivalent de Pour.

for( traitements d’initialisation ; condition de fin ; traitements à effectuer après chaque itération) {
	( ...instructions... )
 }

```js
for (let I_index = 0 ; I_index < 10 ; I_index++){
	console.log(“I_index = “ + I_index) ;
}
```
usage du break pour quitter la boucle
```js
for (let I_index = 0 ; I_index < 10 ; I_index++){
	console.log("I_index = " + I_index) ;
   if (I_index > 5) break;
   // instructions
}

```

- for...in

	La boucle for...in est une variante de la boucle for.

	Elle permet de parcourir les éléments des tableaux indexés ou des objets.

	for( variable in structure ) {
		(...instructions...)
	}
	```js
	for (var I_index = 0 ; I_index < 10 ; I_index++){
		console.log(“I_index = “ + I_index); 
		}
	var A_tableauIndexe = { "cle1": "valeur1", "cle2": "valeur2" };
	
	for( var S_cle in A_tableauIndexe ) {
		console.log("Valeur pour la clé "+S_cle+": "+A_tableauIndexe[S_cle]);
	}
	```

- while

	La boucle while permet d’effectuer une boucle tant qu’une condition est vérifiée.

	While est l’équivalent de Tant que.


	while ( condition ){
		(...instructions...)
		 }
	```js
	var I_nombre = 0;
	do { 
	 I_nombre++;
	} while( I_nombre < 10 );
	```
- Renvois

	JavaScript définit les mots-clés break et continue afin de modifier l’exécution des boucles.

	- break : stoppe la boucle.
	- continue : termine l’exécution de l’itération courante et continue la boucle directement à l’itération suivante.


### Premier niveau de structuration d’applications

## Fonctions

Les fonctions représentent le concept de base de la programmation JavaScript afin de partager des traitements.

Pour décrire une fonction nous utiliserons le mot-clé `function` suivant la syntaxe suivante : 
```js
    function maFonction(paramètre1, paramètre2, ...){
	    // instructions 
    }
```
Remarquons au passage que la définition d’une fonction JavaScript ne permet pas de typer les paramètres (juste les noms des paramètres sont donnés), ni de typer son retour éventuel, même si la fonction utilise la clause return.

Comme dans la plupart des langages une fonction est utilisée par le biais de son nom suivi de parenthèses délimitant les paramètres, et les paramètres ont une portée limitée dans la méthode.
```js
/*  
Retourne la concaténation de deux chaines @param string S_parametre1  
@param string S_parametre2  
@return string  
*/

function test(S_parametre1, S_parametre2){
	 return S_parametre1 + “ “ + S_parametre2;
}

var S_retourTest = test(“un paramètre“, “un second paramètre“);

console.log(S_retourTest);

// ou directement  
console.log(test(“un paramètre“, “un second paramètre“));
```

Quand une fonction ne renvoie pas d’élément, la valeur de retour est `undefined`.

#### Aller plus loin :  
 Sachez qu’il existe un type de variable `function` pour des variables décrivant des fonctions.
```js
function test(){...}  
var F_uneFonction = test ;

F_uneFonction() ;
```

Par extrapolation nous arrivons aux fonctions dites anonymes (sans nom).
```js
var F_maFonction = function(){...}
 F_maFonction() ;
```
Il est ainsi possible de passer en paramètre d’une fonction une variable décrivant une fonction pour ainsi l’appeler ultérieurement.
```js
function direBonjour(F_uneFonction){
	console.log(F_uneFonction());
}

var F_texteBonjour = function(S_nom){
	return “Bonjour “ + S_nom ;

}  
direBonjour(F_texteBonjour(“Pierre“)) ;
```

Javascript met également à disposition la liste des arguments passés à une fonction dans une variable particulière `arguments`, implicitement définie pour chaque fonction. Cette variable est un tableau indicé. Elle permet de gérer plusieurs signatures de méthode.

```js
function maFonction(){  
if( arguments.length == 1) {
	return arguments[0];  
}else if (arguments.length == 2){
	return arguments[0] + “-“ + arguments[1];
	} 
}

console.log(maFonction(“Jean“)); 
console.log(maFonction(“Jean“, “Pierre“);
```
#### Quelques fonctions / méthodes intégrées au langage
 Comme tout langage, nous disposons d'un certains nombre de méthodes, en voici quelques unes à titre d'exemples.
 
##### Manipulation de chaines de caractères
Les chaînes de caractères peuvent être définies littéralement, aussi bien avec des guillemets ou des apostrophes, comme dans le code suivant :

```js
var S_chaine1 = "ma chaîne de caractère";  
var S_chaine2 = 'mon autre chaîne de caractère';
```

Comme JavaScript introduit la classe String correspondante nous pouvons initialiser une chaine de caractère comme suit :
```js
var S_chaine3 = new String("ma chaîne de caractère");
```

###### La classe String

La class String correspondante au type de base String met à disposition des propriétés et méthodes. Voici quelques une d’entre elles.

- Propriétés :
 
**length**

Description : Contient le nombre de caractères de la chaine. 
Type : Int

```js
var S_maChaine= “mon texte“;  
console.log(“S_maChaine contient “ + S_maChaine.length + “ caractères“);
```

- Méthodes :

**charAt**

Description : Retourne le caractère localisé à l’index spécifié en paramètre. 
Paramètres :

 - Int : Indice du caractère dans la chaine (commence à 0) 
 
 Retour :
 -  String: Le caractère de la chaine à l’indice donné.

```js
var S_maChaine = “J’aime les fruits“;
var S_char = S_maChaine.charAt(5);
// S_char = “e“

S_char = S_maChaine.charAt(S_maChaine.length-1);
// S_char = “s“
```

**concat**

Description : Concatène une ou x chaines à une chaine existante. Concat ne modifie pas la chaine. Concat retourne une nouvelle chaine concaténée.

Paramètres :
- String: Une chaine à concaténer
-   Autant de paramètres que de chaines à concaténer.
    
Retour :  
- String: Une nouvelle chaine contenant la concaténation.

```js
var S_maChaine = “J’aime les fruits“;  
var S_maChaine2 = “les légumes“;  
var S_phrase = S_maChaine.concat(“ et “, S_maChaine2);

// S_phrase = “J’aime les fruits et les légumes“;
```

**indexOf**
    
   Description : Retourne la position de la première occurrence de la chaine donnée dans une chaine.
  
  Paramètres :
 -  String : La chaine à chercher
 - Int : Optionnel : La position de départ pour la recherche. Par défaut 0.

    
  Retour :  
   - Int : La position trouvée. -1 si non trouvée.

```js
var S_maChaine = “J’aime les fruits“;
if(S_maChaine.indexOf(“fruits“) >= 0){
	console.log(“il aime les fruits“);
}
```

**lastIndexOf**

Description : Retourne la position de la dernière occurrence de la chaine donnée dans une chaine.
Paramètres :
 - String: La chaine à chercher
    
 - Int : Optionnel : La position de départ pour la recherche. Par défaut 0.
    
 Retour :  
 - Int: La position trouvée. -1 si non trouvée.

```js
var S_maChaine = “J’aime les fruits“;
if(S_maChaine.lastIndexOf(“fruits“) >= 0){
	console.log(“il aime les fruits“);
 }
```

**slice**
    
   Description : Extrait et retourne une portion de chaine à partir d’indices. 
   Paramètres :

- Int: La position de départ pour l’extraction.
- Int : Optionnel : La position de fin pour l’extraction. Par défaut length-1.
    
 Retour :  
-  String: La chaine extraite. -1 sinon.
	  ```js  
	var S_maChaine = “J’aime les fruits“;

	var S_extrait = S_maChaine.slice(0, 5);
	 // S_extrait = “J’aime“;
	 ```   
  
**split**
    
Description : Explose une chaine à partir d’un séparateur et retourne un tableau des éléments.

Paramètres :    
-  String: Optionnel : Le séparateur. Si omis, la chaine entière sera retournée.
    
-  Int : Optionnel : La limite d’éléments / d’explosions. Par défaut aucune limite.
    
Retour :  
-  Array: Le tableau indicé.
    
 ```js  
 var S_maChaine = “J’aime les fruits“; var A_tab = S_maChaine.split(“ “);  
// A_tab = [“J’aime“, “les“, “fruits“];

A_tab = S_maChaine.split(““);  
// A_tab = [“J“, “’“, “a“, “i“, “m“, “e“, “ “, “l“, “e“,“s“, “ “, “f“, “r“, “u“, “i“, “t“, “s“];

A_tab = S_maChaine.split(“ “, 2); // A_tab = [“J’aime“, “les“]; 
 ```   

**substr**

Description : Extrait et retourne une partie de chaine à partir d’un indice et d’un nombre de caractère. 
Paramètres :
- Int: L’indice de départ.
- Int : Optionnel : Le nombre de caractère à extraire. Par défaut le reste de la chaine.
    
 Retour :  
 - String: L’extrait.
 ```js   
var S_maChaine = “J’aime les fruits“;
var S_extrait = S_maChaine.substr(7); // S_extrait= “les fruits“
 ```   

Sans oublier `substring`, `toLowerCase`, `toUpperCase` ou encore `replace`, `search` ces deux dernières exploitant les expressions régulières...


##### Manipuler les tableaux
Comme nous l’avons déjà vu précédemment il existe deux types de tableaux.

-  Les tableaux indicés. (index = entier)
    
-  Les tableaux associatifs. (index = chaine de caractères)
    
 **Tableaux indicés**
    
   Les tableaux indicés ont un index numérique.  
   Comme généralement en programmation le premier indice est 0.
 ```js   
// Initialisation d’un tableau vide
 var A_tableau1 = [];

// On ajoute des éléments dans le tableau précédemment initialisé 
A_tableau1[0] = "Le premier élément";  
A_tableau1[1] = "Le seconde élément";

// Initialisation d’un tableau avec des éléments  
var A_tableau2 = ["Le premier élément", "Le second élément" ];

// On ajoute un élément dans le tableau
 A_tableau2[2] = "Le troisième élément";
 ```   

Comme JavaScript introduit la classe Array correspondante nous pouvons initialiser un tableau comme suit :
 ```js 
var A_tableau3 = new Array() ; 
A_tableau3[0] = “un élément“ ;
 ``` 


Parcourir un tableau indicé

Le parcours d’un tableau indicé s’appuie donc logiquement sur les indices avec la boucle for que vous connaissez désormais.

 ```js 
// Initialisation d’un tableau  
var A_tableau = [ "element1","element2" ];

// Nous parcourons le tableau grâce à la boucle for 

for(let I_cpt=0 ; I_cpt < A_tableau.length ; I_cpt++) {
console.log("Elément pour l’index " + I_cpt + " : " + A_tableau[I_cpt]);
 }
 ``` 

**Tableaux associatifs**

Les tableaux associatifs sont fondés sur des identifiants d’éléments, des libellés sous forme donc de chaine de caractères.

 ```js 
// Initialisation d’un tableau vide
 var A_tableau1 = {};

// On ajoute des éléments dans le tableau
 A_tableau1["cle1"]= "Le premier élément";
 A_tableau1["cle2"]= "Le second élément";

// Initialisation d’un tableau avec des éléments 
var A_tableau2 = {
"cle1": "Le premier élément",
"cle2": "Le second élément"
 };

// On ajoute un élément dans le tableau
A_tableau2["cle3"] = "Le troisième élément";
 ``` 

Comme JavaScript introduit la classe Array correspondante nous pouvons initialiser un tableau comme suit :
 ```js 
var A_tableau3 = new Array(); 
A_tableau3["cle1"] = "un élément";
 ``` 

Parcourir un tableau associatif

Le parcours d’un tableau associatif se réalise en utilisant la boucle for...in.
 ```js 
// Initialisation d’un tableau associatif
 var A_tableau = {
"cle1": "valeur1",
"cle2": "valeur2" 
};

// Nous parcourons le tableau grâce à la boucle for...in 
for(let S_cle in A_tableau) {
console.log("Elément pour la clé " + S_cle + " : " + A_tableau[S_cle]);
 }
 ``` 

**La classe Array**

La class Array correspondante au type de base Array met à disposition des propriétés et méthodes. Voici quelques une d’entre elles.

 - Propriétés :
  
	**length**

	Description : Contient le nombre d’éléments du tableau. 
	Type : Int
	
	```js
	var A_tableau = [ "element1", "element2" ];  
	console.log(“A_tableau contient “ + A_tableau.length + “ éléments“);
	```
 - Méthodes :


**concat**

Description : Concatène un tableau à un autre. 
Paramètres :
-	Array : Le tableau à ajouter.

Retour :
-	Array : Un tableau correspondant à la concaténation des deux tableaux
```js
var A_tableau = ["element1", "element2"];  
var A_tableauAAjouter = ["element3", "element4"];

//Retourne un tableau correspondant à la concaténation des deux
 var A_tableauResultat = A_tableau.concat(A_tableauAAjouter);
```

**join**

Description : Construit une chaîne de caractères à partir d’un tableau en utilisant un séparateur. 

Paramètres :
- String : Séparateur

Retour :
- String : la chaine de caractères correspondant aux éléments du tableau séparés par le séparateur fourni.
```js
var A_tableau = [ "element1", "element2", "element3", "element4" ];

var S_chaine = A_tableau.join(";");  
//S_chaine contient “element1;element2;element3;element4“
```

**pop**

Description : Supprime et retourne le dernier élément d’un tableau.

Paramètres : Aucun  
Retour :
-	Mixte : L’élément supprimé

```js
var A_fruits = ["Banane", "Orange", "Pomme", "Mangue"]; 
var S_fruitSupprime = A_fruits.pop();
```

**push**

Description : Ajoute des éléments à la fin d’un tableau.

Paramètres :
-  Mixte : L’élément à ajouter
    
-  Autant de paramètres que d’éléments à ajouter.
    
 Retour :  
 - Int : Retourne le nouveau nombre d’éléments du tableau
```js
var A_fruits = ["Banane", "Orange", "Pomme", "Mangue"];
var I_nbFruits = A_fruits.length;

I_nbFruits = A_fruits.push("Kiwi");  
I_nbFruits = A_fruits.push("Citron","Tomate");
```

**reverse**
    
Description : Inverse l’ordre des éléments d’un tableau.
 
Paramètres : Aucun  
Retour : Aucun  
```js
var A_fruits = ["Banane", "Orange", "Pomme", "Mangue"];
A_fruits.reverse();  
// A_fruits = ["Mangue", "Pomme", "Orange", "Banane"]
```

**shift**

Description : Retourne le premier élément et le supprime du tableau. Décale vers la gauche tous ses éléments.

Paramètres : Aucun

Retour :
- Mixte : L’élément supprimé.

```js
var A_fruits = ["Banane", "Orange", "Pomme", "Mangue"];
var A_partieFruits = A_fruits.slice(0, 1);  
// A_partieFruits = [“Banane“, “Orange“]
```

**slice**

Description : Retourne une partie du tableau en fonction d’indices de début et de fin. Paramètres :
-  Int : Indice de début
-  Int : indice de fin
    
 Retour :  
   -  Array : Tableau correspond à une portion du premier tableau.
   
```js
var A_fruits = ["Banane", "Orange", "Pomme", "Mangue"];
var A_partieFruits = A_fruits.slice(0, 1);  
// A_partieFruits = [“Banane“, “Orange“]
```

**sort**
    
Description : Trie les éléments d’un tableau. Par défaut, le tri s’effectue par ordre alphabétique. Une fonction de tri peut néanmoins être passée en paramètre afin de spécifier la stratégie de tri.
    
 Paramètres :  
 - Optionnel : Function : Une fonction de tri.
    
  Retour :  
   - Array : Le tableau trié.

```js
var A_tableau = [ "trois", "quatre", "cinq", "six" ];
var A_resultat = A_tableau.sort();  
// A_resultat correspond au tableau ["cinq", "quatre", "six", "trois"]
```

### Interactions avec les pages HTML

#### Le DOM

Avant la standardisation du DOM (Document Object Model) par le W3C, chaque navigateur Web implémentait ses propres méthodes afin de manipuler les éléments des pages HTML. Cela se traduisait par autant d’API qu’il y avait de navigateurs. Le code JavaScript était dès lors très difficilement portable sur l’ensemble des navigateurs.

L’API du DOM permet d’accéder à une page Web et de manipuler son contenu, sa structure ainsi que ses styles. Le DOM fournit pour cela une représentation objet normalisée de l’arbre des documents XML ou HTML.

- Structure de l’arbre HTML

Tout langage de balises possède une structure en arbre dont la représentation objet correspond au DOM.

Prenons un exemple simple. Un fragment HTML qui comprend une balise parente section contenant deux blocs, un titre et un paragraphe :

```js
<section id=“maZone“>
     <h1>Du texte dans un titre</h1>
     <p>Du texte dans un paragraphe.</p> 
</section>
```
	+Document
	+Elément section
		+ attribut id
		+ Elément h1
			+ Elément de type texte
		+ Element p
			+ Elément de type texte


Avec le DOM chacune des balises HTML est représentée par un objet correspondant à son nom. 

#### Manipulation des éléments

-	La classe Node

La classe représentant un nœud DOM est Node. Elle définit différentes propriétés afin de donner accès aux informations des nœuds. En voici une rapide liste non complète.

- Propriétés :

	-  `attributes` :  attributs  	
					type : objet ou tableau indicé
		    
	-  `childNodes` : nœud enfant  
					type : tableau indicé  
		    
	-   `firstChild` : premier nœud enfant  
				    type : objet de type Noeud  
				    
	-  `lastChild` : dernier nœud enfant 
				    type : objet de type Noeud  
				    
	-  `nodeName` : nom du nœud  
				   type : Chaine de caractères   
				   
	-  `nodeType` : type du nœud  
				    type : numérique. Définit par des constantes JavaScript. 
				    
	-  `nodeValue` : valeur/contenu du nœud  
				    type : Chaine de caractères. La valeur texte contenu dans un noeud 
				    
	-  `parentNode` : nœud parent 
				    type : objet de type Noeud

- Méthodes

	-   `appendChild(Node Noeud)` : ajoute un nœud enfant
			 retour : Le nouveau Nœud ajouté.
		    
	-   `getAttribute(String nomAttribut)` : recherche la valeur d'un nœud attribut
			 retour : Chaine de caractère. La valeur de l’attribut.
		    
	-   `removeAttribute(String nomAttribut)` : efface la valeur d'un nœud attribut
			 retour : Aucun.
		    
	-   `setAttribute(String nomAttribut, String valeur)` : fixe la valeur d'un nœud attribut
			 retour : Aucun.

#### Accès direct aux éléments

##### getElementById
    
   Pour accéder au nœud d’un document afin de pouvoir le manipuler par la suite, une méthode simple consiste à identifier ce nœud par un attribut id.
    
   La méthode `getElementById` de l’objet document peut ensuite s’appuyer sur cet identifiant pour récupérer l’instance correspondante.
    
   Comme un identifiant de balise doit être unique dans une page HTML, nous serons certains d’obtenir l’unique élément pour l’id donné.
    
   Reprenons notre exemple.  
   Notre balise section racine possède un identifiant unique : maZone. Nous pouvons donc obtenir rapidement l’objet Nœud en utilisant la méthode `getElementById` de l’objet `document`.
   
```js
var O_maZone = document.getElementById(“maZone“) ; 

/* O_maZone contiendra l’objet de type Nœud représentant mon bloc
identifié. 

Si maZone n’existe pas getElementById retournera Null.*/
```


##### getElementsByTagName
    
   La méthode `getElementsByTagName`, permet de récupérer une liste de nœuds pour un nom de balise, correspondant à la valeur de la propriété nodeName de la classe Node que nous venons de voir.
    
   La méthode `getElementsByTagName` peut s’appliquer sur l’objet `document` ou sur un objet nœud et retourne un objet de type `NodeList`.
   
```js
var O_sectionListe = document.getElementsByTagName(“section“);
var O_premiereSection = O_sectionListe.childNodes[0];
```

Bien évidemment, la documentation nous fourni également d'autres méthodes du même genre dont la très utile `getElementsByClassName`


#### Accès aux éléments à partir d’un nœud

La classe Node permet de parcourir un arbre DOM à partir d’un nœud de départ.

Elle met ainsi à disposition des propriétés spécifiques référençant les nœuds autour du nœud courant.

Nous en avons déjà listé quelques une. Pour rappel :

|Propriété     | Description  |
|--|--|
| childNodes | Représente la liste des nœuds enfants sous forme d’objet `NodeList`. La méthode `hasChildNodes` permet de déterminer si le nœud a des nœuds enfants. La propriété `childNodes` ne contient pas les attributs du nœud.|
| firstChild|Référence le premier nœud enfant, correspondant au premier nœud de la liste `childNodes`.|
| lastChild|Référence le dernier nœud enfant, correspondant au dernier nœud de la liste `childNodes`.|
| nextSibling |Pointe sur l’enfant suivant dont le parent est identique. Elle contient `null` si le nœud est le dernier enfant.|
| parentNode | Référence le nœud parent. |
| previousSibling |Pointe sur l’enfant précédent dont le parent est identique. Elle contient `null` si le nœud est le premier enfant.|

- Exemples :  
Récupération du premier enfant direct de maZone :

	```js
	var O_elementSection = document.getElementById(“maZone“);
	var O_elementTitre = O_elementSection.firstChild;
	```

	Parcourir l’ensemble des enfants direct de maZone :
	
	```
	var O_elementSection = document.getElementById(“maZone“);
	var O_elementEnfant = O_elementSection.firstChild;

	while(O_elementEnfant != null){  
		//... instructions  
		O_elementEnfant = O_elementEnfant.nextSibling;
	}
	```

#### Manipulation des attributs

Tout élément du type NODE_ELEMENT peut contenir des attributs.

La classe Node fournit des méthodes pour manipuler les attributs d’un nœud.

##### getAttribute

Description : Retourne la valeur d’un attribut par son nom. 

Paramètres :
- String: Le nom de l’attribut. 

Retour :
-	String : La valeur de l’attribut.

```js
var O_element = document.getElementById(“maZone“);  
var S_id = O_element.getAttribute(“id“); // class, etc
```

#####  hasAttribute

Description : Teste si un attribut est présent pour un élément.

Paramètres :
- String: Le nom de l’attribut. 

Retour :
-	Bool : Le statut.

```js
var O_element = document.getElementById(“maZone“) ;

if( O_element.getAttribute(“class“) ){ 
	console.log(O_element.getAttribute(“id“) + “ dispose de classes CSS“);
}
```

##### removeAttribute

Description : Supprime un attribut pour un élément. 

Paramètres :
- String: Le nom de l’attribut.

Retour : Aucun.
	
```js
var O_element = document.getElementById(“maZone“);

if( O_element.getAttribute(“class“) ){
	 O_element.removeAttribute(“id“);
	}
```

##### setAttribute

Description : Ajoute un nouvel attribut ou change la valeur d'un attribut existant.

Paramètres :

-  String : Le nom de l’attribut.
    
-  String : La valeur de l’attribut.
    
Retour : Aucun
```js
var O_element = document.getElementById(“maZone“);
O_element.setAttribute(“class“, “box-erreur“);
```

## Gestion d'événements

Accèder aux éléments du DOM, pouvoir les modifier, les supprimer etc, c'est bien, mais tout cela ne servirait pas à grand chose si nous ne pouvions pas déclencher ces actions en fonction de tel ou tel événement survenant sur nos pages Html.

C'est tout l'objet de Javascript que de pouvoir surveiller ce qu'il se produit au sein d'une page, grâce aux mécanismes d'écoute dont il dispose, fournis par l'interface `Event` du DOM.

### Les écouteurs d'événements

####  [](https://developer.mozilla.org/fr/docs/orphaned/Web/API/Document_Object_Model/Events#eventtarget.addeventlistener "Permalink to EventTarget.addEventListener")[EventTarget.addEventListener](https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener)


```js
<button id='monBouton'>mon bouton</button>

<script>
	function clickDetect(){
		console.log('bouton cliqué');
	}

	var el = document.getElementById("monBouton");
	el.addEventListener("click", clickDetect, false);
</script>
```

#### [](https://developer.mozilla.org/fr/docs/orphaned/Web/API/Document_Object_Model/Events#html_attribute "Permalink to attribut HTML")[Attribut HTML](https://developer.mozilla.org/fr/docs/Learn/JavaScript/Building_blocks/Events#Inline_event_handlers_%E2%80%94_don't_use_these)
```js
<button id='monBouton' onclick="clickDetect();">mon bouton</button>

<script>
	function clickDetect(){
		console.log('bouton cliqué');
	}
</script>
```
#### [Propriétés d'un élément DOM](https://developer.mozilla.org/fr/docs/orphaned/Web/API/Document_Object_Model/Events#dom_element_properties "Permalink to Propriétés d'un élément DOM")
```js

<button id='monBouton'>mon bouton</button>

<script>
	function clickDetect(){
		console.log('bouton cliqué');
	}

	var el = document.getElementById("monBouton");
	el.onclick = function(event){clickDetect();};
</script>
```

Couplées à la manipulation des éléments vue auparavant, nous sommes en mesure d'effectuer diverses manipulations du DOM et donc du rendu de nos pages HTML !
		 
	

## TD : A vous de jouer ! 

#### 1 - Création d'un tableau de données 

Créez un tableau (Array), contenant une vingtaine de valeurs numériques allant de -10 à 40. Ces valeurs seront créées de manière aléatoire puis insérées dans le tableau.

#### 2 - HTML

Dans une page HTML, créez un bloc  dont l'objectif est de contenir une valeur  (soignez votre sémantique, utilisez une section, avec titre donc, puis la ou les balises devant recevoir cette donnée).


#### 3 - DOM : modification du contenu d'un noeud 

Lors de l'affichage de la page, insérez successivement chaque donnée du tableau de l'étape 1 dans la zone créée à l'étape 2. chaque donnée remplace la précédente.
	- Ajoutez un délai d'affichage entre chaque donnée (2 secondes par exemple).

#### 4 - CSS

Créez 4 classes avec les propriétés suivantes :

 - Une classe avec bordure de 2px, couleur bleue,
  - Une classe avec bordure de 2px, couleur verte,
  - Une classe avec bordure de 2px, couleur orange,
  - Une classe avec bordure de 2px, couleur rouge.

#### 5 - DOM : modification des attributs d'un noeud

Lors de l'insertion des données de votre tableau, évaluez chaque valeur et ajoutez / modifiez la classe CSS selon ces critères :

-  -10 à 0   : bordure bleue,
- 0 à 20   : bordure verte,
- 20 à 30 : bordure orange,
- 30 à 40 : bordure rouge.

Note : votre code HTML initial ne doit pas contenir d'attribut `class`  		

#### 6 - DOM : ajout / suppression d'un noeud

Lors de l'insertion des données et suite à leur évaluation, prenez en compte les cas suivants :
- Si la valeur est inférieure à 0 :
	- Ajout d'un message au dessus de la zone d'affichage : "Brrrrrrr, un peu froid ce matin, mets ta cagoule !"

- Si la valeur est supérieure à 30 :
	- Ajout d'un message au dessus de la zone d'affichage : "Caliente ! Vamos a la playa, ho hoho hoho !!"

#### 7 - Approfondissement

1 -  Vous l'aurez compris, il s'agit d'afficher une température, comment optimiser notre HTML et lui donner plus de sens ?
 - définir qu'il s'agit d'une température,
 - définir qu'il s'agit de degrés celsius,
 - définir sémantiquement les plages de température (utiliser CSS c'est bien, mais cela n'apporte aucune information à notre HTML).

2 - Comment exploiter notre tableau de données pour toutes les afficher, à la manière d'un historique ?


<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE2MDA5ODM4ODAsLTE1MDQ4NTgsOTEyOT
g0MDQsODIwNDQ4MjQsLTE5NzA1MjU1NjAsLTE5MTEzMDM5MzIs
MTQ4MjQwMTA3LDE1MjU5Mjc4NDQsLTIzMDQ4MDE5OSwtMTk3ND
I5ODU5NSwtNzcwNzQwMjc4LDkwODQzMzQ4NCwtNzE0MzcwMDgy
LDE0NzgwODIzODAsLTE2ODE4MDU4ODIsMTU4MDQ4MTA5NCwtNj
M3ODA3MTIsMTcxNDE3MjQ0NCwtMzU0ODk5MDEzLC0zNTQ4OTkw
MTNdfQ==
-->