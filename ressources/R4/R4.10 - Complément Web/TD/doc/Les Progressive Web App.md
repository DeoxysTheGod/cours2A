
# Les Progressive Web App

## Le meilleur des deux mondes (App & web)


### En résumé
L’idée générale est d’exploiter les fonctionnalités fournies par les API issus des travaux autour de Html 5, pour réaliser des sites web pouvant se transformer en applications multiplateformes.  

La notion de progression est essentielle, toute part d’une page web et, en fond, des éléments, fonctionnalités, seront chargés en cache, permettant par la suite l’autonomie partielle ou complète de l’application.

### Fonctionnalités

- Installable, avec bouton sur l’écran d’accueil,  
- ouverture et comportement comme une app (plein écran), 
-  notifications,  
- connecté / non connecté,  
- base de données locale  
- etc.

Bref, toutes les fonctionnalités disponibles dans les API HTML5, accessibles dans un navigateur, mais dans un ensemble pouvant aussi se présenter sous la forme d’une application native !

Les avantages plus en détail : https://developer.mozilla.org/fr/docs/Web/Progressive_web_apps/Introduction

Pour que l’on puisse parler de PWA, il faut trois éléments :

- un environnement sécurisé, HTTPS sera la règle, 
- un ou plusieurs services worker,  
- un fichier manifeste au format JSON.

> Documentation :
> https://developer.mozilla.org/fr/docs/Web/Progressive_web_apps


> Que peuvent faire les PWA aujourd'hui ?
>  https://whatpwacando.today

## Mise en oeuvre

### HTTPS
Nous venons de le voir l’environnement doit être en HTTPS, aussi bien en local pour les développements qu’en production ensuite.

Environnements de test, production : Voir doc d’installation pour Linux

**Cas d'une installation en local**

Un peu de littérature explicative : https://letsencrypt.org/fr/docs/certificates-for-localhost/

Vous l’aurez compris, nous allons devoir créer un certificat autosigné pour nos besoins locaux.

Pour ce faire, utilisons par exemple l’utilitaire Minica :
Extrait du ReadMe :  

> Installation
> 
> First, install the Go tools and set up your $GOPATH. Then, run:
> 
> `go get github.com/jsha/minica`
> 
> When using Go 1.11 or newer you don't need a $GOPATH and can instead
> do the following:

>`cd /ANY/PATH   git clone https://github.com/jsha/minica.git go build`  
>or  
>`go install`
> 
> Example usage
> 
> #### Generate a root key and cert in minica-key.pem, and minica.pem, then
> #### generate and sign an end-entity key and cert, storing them in ./foo.com/
> 
> `$ minica --domains foo.com`

Et voilà !

### Un ensemble de favicon

Ils vont nous servir à créer le bouton d’accès dans la liste des « app » des smartphones, tablettes ou OS.

Faisons simple, utilisons un générateur, nous en modifierons ensuite le manifeste selon nos besoins :

https://realfavicongenerator.net

### Le Manifest

C’est un fichier au format JSON qui décrit certains éléments de l’application, son nom, son apparence à l’écran, quelles icônes utiliser lors de son installation sur un terminal (smartphone, tablette mais aussi les ordinateurs)

Documentation : https://developer.mozilla.org/fr/docs/Web/Manifest

Exemple
```css 
	{
	   "lang":"fr",
	   "dir":"ltr",
	   "name":" My sweet PWA",
	   "short_name":"MyPwa",
	   "icons":[
	      {
	         "src":"\/assets\/images\/touch\/android-chrome-192x192.png",
	         "sizes":"192x192",
	         "type":"image\/png"
	      }
	   ],
	   "theme_color":"#1a1a1a",
	   "background_color":"#1a1a1a",
	   "start_url":"/",
	   "display":"standalone"
	}
```	
Intégration :  
Situé à la racine du site et intégré dans le head de notre page HTML

    <link rel= "manifest" href= "manifest.webmanifest">

Nous ferons légèrement évoluer notre manifeste au fil de nos besoins.

### Service worker

Pas de PWA sans service worker !

Situés entre l’application et le réseau, ils en interceptent les requêtes, et gèrent ainsi le mode connecté / non connecté, selon la disponibilité du réseau.

Ces services sont isolés, donc non bloquants, et dirigent diverses stratégies de mise en cache de l’application, en vue, par exemple, de son autonomie.

Nous utiliserons, à la racine de notre site, un fichier contenant nos services workers.
Appelons le sw.js. 

Ce fichier sera appelé par notre javascript au chargement de la page.

> Documentation :
> https://developer.mozilla.org/fr/docs/Web/API/Service_Worker_API
> 
> Rappel : Les promesses en javascript
> https://developer.mozilla.org/fr/docs/Web/JavaScript/Guide/Utiliser_les_promesses
> 
> Cookbook utilisé pour notre exemple :
> https://github.com/mdn/serviceworker-cookbook


#### Stratégies de cache

Différentes stratégies sont possibles :

- Pré chargement de pages, de données
- Synchronisation de données

Pré chargement de données
A l'installation, on stocke en cache les fichiers nécessaires.
C'est le cas pour notre exemple :

``` js
	//Dans /sw.js
	
	var CACHE = 'mysweetpwa1';  
  
  
  
	// On install, cache some resource.  
	self.addEventListener('install', function(evt) {  
	  evt.waitUntil(caches.open(CACHE).then(function (cache) {  
		        cache.addAll([  
		          "/pwa.php",  
				  "/assets/images/favicon-16x16.png",  
				  "/assets/images/favicon-32x32.png",  
				  "/assets/images/android-chrome-192x192.png",  
				  "/assets/images/android-chrome-512x512.png",  
				  "/sw.js",  
		  ]);  
	  }));  
	});
```

Outre les stratégies telles que le **cache only** ou **network only**, assez facilement compréhensibles dans le concept, d'autres plus fines sont possibles.
Parmi elles :

**Network first**

Le service worker recherche d'abord la ressource sur le réseau, puis dans le cache si le réseau n'est pas accessible. Evidemment, une fois le réseau utilisé, cette ressource doit être mise en cache.


**Cache first** 

C'est l'inverse, on recherche d'abord la ressource dans le cache, puis via le réseau.

**Stale-while-revalidate**

 Si la ressource est dans le cache, on l'utilise et, dans le même temps on rafraichit ce cache via le réseau pour la fois suivante.

C'est cette dernière que nous utilisons pour notre exemple
```js
	// On fetch, use cache but update the entry with the latest contents  
	// from the server.  
	self.addEventListener('fetch', function(evt) {  
	    console.log('The service worker is serving the asset.');  
	    // You can use `respondWith()` to answer ASAP...  
	    evt.respondWith(fromCache(evt.request));  
	    // ...and `waitUntil()` to prevent the worker to be killed until  
	   // the cache is updated.
	    evt.waitUntil(  
	        update(evt.request)  
	            // Finally, send a message to the client to inform it about the  
			    // resource is up to date.
			    .then(refresh)  
	    );  
	});  
  
	// Open the cache where the assets were stored and search for the requested  
	// resource. Notice that in case of no matching, the promise still resolves  
	// but it does with `undefined` as value.  
	function fromCache(request) {  
	    console.log('match cache request');  
		return caches.open(CACHE).then(function (cache) {  
	        return cache.match(request);  
	   });  
	}  
  
  
	// Update consists in opening the cache, performing a network request and  
	// storing the new response data.  
	function update(request) {  
	    console.log('update cache');  
		return caches.open(CACHE).then(function (cache) {  
	        return fetch(request).then(function (response) {  
	            return cache.put(request, response.clone()).then(function () {  
	                return response;  
			    });  
		   });  
	  });  
	}  
  
	// Sends a message to the clients.  
	function refresh(response) {  
  
	    return self.clients.matchAll().then(function (clients) {  
	        clients.forEach(function (client) {  
            // Encode which resource has been updated. By including the  
		    // [ETag](https://en.wikipedia.org/wiki/HTTP_ETag) the client can 
		    // check if the content has changed. 
		    
			     var message = {  
	                type: 'refresh',  
					url: response.url,  
					// Notice not all servers return the ETag header. If this is not  
					// provided you should use other cache headers or rely on your own 
					// means to check if the content has changed.
				    eTag: response.headers.get('ETag')  
	            };  
				// Tell the client about the update.  
			    client.postMessage(JSON.stringify(message));  
		  });  
	  });  
	}
	
```	

Plusieurs stratégies sont donc possibles, tout dépend du type de ressource, du contenu et des objectifs de votre application !then cache

Cache, fallin back to network

Cache  network race

Cache then network




> Documentation Allons découvrir quelques recettes :
> https://github.com/mozilla/serviceworker-cookbook 
> 
> https://developer.mozilla.org/fr/docs/Web/API/Service_Worker_API/Using_Service_Workers
> 
> Différentes stratégies :
> https://jakearchibald.com/2014/offline-cookbook/

#### Notifications :

Outre la gestion du cache que nous venons d’aborder, les services workers permettent également d’envoyer des notifications à nos utilisateurs, voire de pousser des données.

Nous proposons à nos utilisateurs de s'abonner à nos notifications via un bouton.
```js
	var button = document.getElementById("notifications");  
	button.addEventListener('click', function(e) {  
	    Notification.requestPermission().then(function(result) {  
	        if(result === 'granted') {  
	            randomNotification();  
	  }  
	    });  
	});
```
Pour notre exemple, nous simulons une analyse de valeur et en fonction, créons une notification avec un message et une image.

```js
	function randomNotification() {  
    var randomNumber = getRandomInt(5);  
	  console.log(randomNumber);  
	 if(randomNumber >= 2) {  
	  
		 var notifTitle = "Chaud, non ?";  
		 var notifBody = 'Température : ' + randomNumber + '.';  
		 var notifImg = '/assets/images/android-chrome-192x192.png';  
		 var options = {  
		                body: notifBody,  
		  icon: notifImg  
		     }  
		 var notif = new Notification(notifTitle, options);   
		  }  
		    setTimeout(randomNotification, 30000);  
		}  
	  


	   //On génére un nombre aléatoire pour la démo  
	  function getRandomInt(max) {  
	    return Math.floor(Math.random() * Math.floor(max));  
	  }

```


### Bouton d'installation 

Enfin, tout cela serait moins pratique s’il n’était possible d’installer notre application comme une application native

Nous allons donc proposer aux utilisateurs d’effectuer cette installation en un clic !
HTML :
```html	
	<button class="add-button">Ajouter</button>
```

JavaScript
```js

	let deferredPrompt;  
	const addBtn = document.querySelector('.add-button');  
	addBtn.style.display = 'none';  
  
	window.addEventListener('beforeinstallprompt', (e) => {  
	    // Prevent Chrome 67 and earlier from automatically showing the prompt  
		  e.preventDefault();  
		  // Stash the event so it can be triggered later.  
		  deferredPrompt = e;  
		  // Update UI to notify the user they can add to home screen  
		  addBtn.style.display = 'block';  
	  
		  addBtn.addEventListener('click', (e) => {  
		      // hide our user interface that shows our A2HS button  
			  addBtn.style.display = 'none';  
			  // Show the prompt  
			  deferredPrompt.prompt();  
			  // Wait for the user to respond to the prompt  
			  deferredPrompt.userChoice.then((choiceResult) => {  
		            if (choiceResult.outcome === 'accepted') {  
		                console.log('User accepted the A2HS prompt');  
				  } else {  
		                console.log('User dismissed the A2HS prompt');  
				  }  
		          deferredPrompt = null;  
			  });  
	    });  
	});

```

### BONUS, les Websocket

Destinés à remplacer AJAX pour la gestion des données en temps réel les websocket présentent l’avantage de la continuité de la connexion et, bien évidemment, de la performance.

#### Côté serveur

Nul impératif d’utiliser Node js côté serveur, Php, par exemple, sait très bien faire cela grâce à une bibliothèque intégrée : Swoole

https://www.php.net/manual/fr/book.swoole.php https://www.php.net/manual/fr/class.swoole-server.php

https://www.swoole.co.uk/

Mais ce n'est pas le sujet pour nous, ce qui nous intéresse c'est ce qu'il se passe côté client.

#### Côté client

```js
	var socket = new WebSocket(‘wss: url du serveur:numéro de port');
	socket.onopen = function(event) {
		console.log("Connexion établie");
		
		//On indique sur notre page web que la connexion est établie
		let label = document.getElementById("status");
		label.innerHTML = "Connexion établie"; 

		//Envoi d'un message au serveur (obligatoire)
		socket.send("coucou !");
	
		// au retour...
		socket.onmessage = function(event) {
			var datas = document.getElementById("datas");
			datas.innerHTML = event.data; 
		}
	}
```
C'est tout ! Notre élément d'id `datas`  recevra à intervalle régulier les données en provenance du service wss.


## TD : A vous de jouer !

1 Si vous utilisez votre machine personnelle, Installez un certificat pour un domaine local. Faites de même sur votre espace d'hébergement en ligne.

2 Utilisez et analysez les sources  de cet exemple :
	https://github.com/moinal/JavaScript-Exemples/tree/main/pwa
	Note : Utilisez Chrome pour une meilleure expérience.
	
2 Pour accéder au serveur websocket, utilisez cette url :
	wss://ws.hothothot.dog:9502 





<!--stackedit_data:
eyJoaXN0b3J5IjpbNjczMDQ4MDE0LC0zNzc0ODMyNDMsMjA0OT
E5MTMxLC04NTE0MDU1OTgsMTYxNTE4NTIwOSwtMTM2MzEyNDkz
MiwtNjM1MDA1MTc0LC03MjgyMzExMiwxMjM5MTIxNDk1LDE3Mj
AxODIxMjUsLTE5NDE4ODUxMTQsLTE0OTE0MTY3NzgsLTExODU0
NjA1NzEsLTEwNDI2ODQ1MDEsLTE3MDE5NTQzOTMsLTEzNTc3MD
YwOTIsLTc0MTk0OTUsMTU1MjgwOTY2MCwtODE1Mjc0MDAzLDE4
MjE0NDIwNThdfQ==
-->