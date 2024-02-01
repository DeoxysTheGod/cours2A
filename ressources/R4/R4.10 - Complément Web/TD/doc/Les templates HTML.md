
# Les templates HTML

## Le concept

Il s'agit de fragments HTML, non affichés en l'état au chargement de la page mais exploités ensuite par JavaScript.
On utilise donc des modèles  comprenant ou non des noeuds.
La plupart du temps, ces modèles ne disposent pas de contenus, c'est Javascript qui les peuplent en fonction des besoins.

Exemple :
Une table html comprenant un `tbody` vide, un template reprenant le modèle d'une ligne `tr`, `td`, `td`... et un script qui va exploiter le modèle, le nourrir de données puis l'insérer dans le `tbody`. Cette opération pouvant être effectuée autant de fois que nécessaire.

Nous disposons de modèles de contenu fort utiles lorsqu'il s'agit de manipuler de nombreux noeuds du DOM.

Evidemment, ARIA sera encore de la partie, afin de pouvoir donner à l'utilisateur les informations nécessaires pour qu'il ait accès aux modifications du DOM une fois la page chargée.

Plusieurs techniques existent, plus ou moins complexes.

### Le shadow DOM
L'API shadow DOM (DOM fantôme) est exploitée pour stocker ces fragments.
A la racine du `Document` se situe le `shadow host` qui lui même pourra contenir plusieurs `shadow tree`

Chaque élément du DOM dispose donc d'un potentiel DOM caché.
C'est déjà le cas de certaines balises HTML5 que vous avez sans doute l'habitude d'utiliser (`video`, `audio` et leur interface propre, boutons d'action etc.)

```
Document
+-- DOM que vous connaissez
    +--element
+-- shadow host
    +-- shadow tree 1
	    +-- element
		+-- element    
			+-- element
    +-- shadow tree 2
	    +-- element

```
C'est là que seront stockés nos éléments en attente d'être utilisés.
Diverses méthodes nous permettront de les manipuler.

#### Exemple avec la création d'éléments dans le DOM 

Soit le HTML suivant
	
	<demo-fantome role="section">  
  
	</demo-fantome>

Vous noterez qu'il ne s'agit pas de balises HTML classiques (mais cela pourrait !).
En effet il est possible (mais pas toujours pertinent), de créer notre propres balises, à condition toutefois de leur joindre le ou les attributs ARIA adéquats, afin de leur donner du sens.

> **Une contrainte : le nom doit comporter un tiret**

Ce sont des `CustomElement`, éléments personnalisés qui peuvent tout autant être de nouveaux éléments (comme ici), mais également étendre des éléments existants.



Classe JavaScript 

```js
class DemoFantome extends HTMLElement {  
    constructor() {  
      // Toujours appeler super en premier dans le constructeur  
	  super();  
  
	  // Écrire les fonctionnalités de l'élément ici  
	  // Créer une racine fantôme
	  let monFantome = this.attachShadow({mode: 'open'});  
	 
	  //préparation de nos éléments en vue de les insérer  
	  let monParagraphe = document.createElement('p');  
	  monParagraphe.setAttribute('class','uneClasse');  
	  monParagraphe.textContent = "Bouhouuuuuuuu";  
	  let style = document.createElement('style');  
	  style.textContent =`  
		.uneClasse{ color: blue; }`;  
		
	 // On injecte dans notre fantôme !  
	  monFantome.appendChild(style);  
	  monFantome.appendChild(monParagraphe);  
  }  
};

```

Usage :
```js
	//On exploite notre DOM fantôme  
	customElements.define('demo-fantome', DemoFantome);
```
Vous retrouvez le code de cet exemple sur ce dépôt Github :

https://github.com/moinal/JavaScript-Exemples


## Différents moyens de mise en oeuvre


### Avec des scripts de type "text/template"
Pour cet exemple, nous utiliserons le rôle alert d'ARIA et nous en profiterons pour en voir les caractéristiques.

**HTML :** 
Tout d'abord, un bouton pour notre démonstration. 
Bien entendu n'importe quel type d'événement peut être susceptible de déclencher notre alerte ! 
```html
	<button id="alert-trigger">  
	  Déclencher l'alerte  
	</button>
```
Un div, de rôle ARIA `alert`, nous avons donc injecté une sémantique très précise.
```html
	<div id="example" role="alert"></div>
```	

Le contenu de notre alerte.
Notez l'usage du type `text/template` qui nous permet de mettre en attente ce contenu.
```html
	<script type="text/template" id="alert-template">  
	 <h1>Oulala lala lala !!!</h1>  
	 <p>Quelque chose de grave est en train de se passer !!!</p>  
	</script>
```

**JavaScript :** 
Il nous faut un événement déclencheur, un bouton dans notre cas.
```js
	window.addEventListener('load', function () {  
    var button = document.getElementById('alert-trigger');  
	  button.addEventListener('click', addAlert);  
	});

```
Et enfin, la fonction qui va exploiter notre template :
```js
	function addAlert (event) {  
  
    var example = document.getElementById('example');  
	var template = document.getElementById('alert-template').innerHTML;  
	  
	  example.innerHTML = template;  
	  
	}
```
### Avec des balises "template"
Une balise `template` est également à disposition pour stocker nos fragments, lesquels ne seront pas retranscrits tels quels dans le DOM.

Prenons le cas d'ajout de lignes à une un tableau HTML.

**HTML :**
```html
	<table id="historique-table" >  
	 <thead>
	  <tr>
	   <td>Date</td>  
	   <td>Valeur</td>  
	  </tr>
	 </thead>
	 <tbody>
	  <tr>
	   <td>2/02/2022</td>  
	   <td>14</td>  
	  </tr>  
	 </tbody>
	</table>  
	  
	<!-- Le noeud modèle à insérer -->  
	<template id="historique-row">  
	 <tr>
	  <td></td>
	  <td></td>
	 </tr>
	</template>
```
**JavaScript :**
```js
	// On prépare une ligne pour le tableau  
	var template = document.querySelector("#historique-row");  
	  
	// On clone la ligne et on l'insère dans le tableau  
	var tbody = document.querySelector("tbody");  
	var clone = document.importNode(template.content, true);  
	var td = clone.querySelectorAll("td");  
	td[0].textContent = "3/02/2022";  
	td[1].textContent = "0";  
	  
	tbody.appendChild(clone);  
	  
	// On fait de même pour une autre ligne  
	var clone2 = document.importNode(template.content, true);  
	td = clone2.querySelectorAll("td");  
	td[0].textContent = "4/02/2022";  
	td[1].textContent = "35";  
	  
	// Puis on insère  
	tbody.appendChild(clone2);
```
Vous aurez noté l'usage de `querySelector` et `querySelectorAll` pour sélectionner un ou des éléments du DOM (id, balises etc.), plutôt que le traditionnel `getElementById`, ou encore  `getElementsByTagName`.

`importNode` est ici utilisé pour exploiter le template en tant que modèle

Bien entendu, l'ajout de plusieurs lignes pourra être effectué  
en réponse au déclenchement d'un événement,  
au retour d'une requête XHR au format du JSON  traité à l'aide de boucles. C'est tout l'intérêt.  
Un exemple dans la documentation de la recommandation W3C :  
https://html.spec.whatwg.org/multipage/scripting.html#the-template-element


> Note : ARIA   Si des ajouts ou suppressions de ligne du tableau sont
> effectués après le chargement initial de la page   Adapter le modèle
> suivant :   Cas d’étude avancé : liste de contacts   De la page :
> https://developer.mozilla.org/fr/docs/Web/Accessibility/ARIA/ARIA_Live_Regions


### Et des slots
En complément des templates les slots (emplacements) nous aident à affiner la gestion des templates.

**HTML :**
```html
	<template id="produit-template">  
	 <style> produit{  
      display:flex;  
	  flex-direction: column;  
	  }  
	  nom{font-size: 2rem;}  
	  .description{color:#333;}  
	 </style>  
	  
	  
		<produit role="section">  
		 <nom role="heading" aria-level="1"><slot name="nom">NOM PRODUIT</slot></nom>  
		 <slot name="description">BESOIN D'UNE DESCRIPTION</slot>  
		 <slot name="conditionnement" class="description">INFOS CONDITIONNEMENT</slot>  
		</produit>
		<hr>
	</template>

```
Le détail de nos produits :
```html
	<produit-details id="produit1">  
	 <nom role="heading" aria-level="1">Eau en poudre</nom>  
	 <p slot="description">Une eau riche en sels minéraux !</p>  
	 <ul slot ="conditionnement">  
		 <li>Par paquet de 50kg</li>  
		 <li>Par paquet de 25kg</li>  
	 </ul>
	</produit-details>  
	  
	<produit-details id="produit2">  
	 <nom role="heading" aria-level="1">Eau tiède</nom>  
	 <p slot="description">Une eau entre le chaud et le froid</p>  
	 <ul slot ="conditionnement">  
		 <li>Bouteille isotherme de 1,5 l</li>  
	 </ul>
	</produit-details>
```

**JavaScript :**
```js
	customElements.define('produit-details',  
	 class extends HTMLElement {  
	   constructor() {  
	     super();  
		 const template = document
				.getElementById('produit-template')  
	            .content;  
		 const shadowRoot = this.attachShadow({mode: 'open'})  
	            .appendChild(template.cloneNode(true));  
		  }  
	   })
```
Dans cet exemple, le `define` intègre directement notre classe en paramètre.



## TD : A vous de jouer

1 Créez un template et des slots pour la zone comprenant votre valeur numérique. Rappel : il s'agit de l'affichage des données d'un capteur de température. Nous pourrions vouloir en ajouter, de température ou bien d'autres types (d'humidité, de CO2 etc.)...
<!--stackedit_data:
eyJoaXN0b3J5IjpbMjAzODExMjAwOCw4NTA3OTU4MiwyMDAwMD
IzMjUwXX0=
-->