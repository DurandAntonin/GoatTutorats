# Rapport - GoatTutorats 

MASSINOND Matéo, DURAND Antonin et MENON Valentin

## Lancement et test de l'application 

L'application se lance à l'aide de la classe Main 'GoatTutoratsApplication'. Le port est le 8081 et on peut y accèder via cette URL : 
```
http://localhost:8081/auth/get-login
``` 

L'application étant déployée, on peut aussi y accèder directement via cette URL : 
```
https://goat-tutorats.techops.tf/auth/get-login
``` 

Ensuite, après avoir été redirigé sur la page de connexion, on peut utiliser ces identifiants : 
```
Nom d'utilisateur : handre
Mot de passe : azerty
```

## Outillage 

Pour ce projet, nous avons utilisé l'IDE IntelliJ IDEA Ultimate. 
Le SGBD utilisé est MySQL en version 8.0.42. 
Nous nous sommes également servi de Docker et de Kubernetes pour le déploiement. 

## Aspects importants de notre travail 

- **Les rôles et utilisateurs sont modulaires** : un tuteur hérite d'une classe 'User' qui contient les attributs nécessaires à la connexion. De cette façon, il est aisé de rajouter un autre rôle dans l'application pusqu'il suffirait de le faire hériter de la classe 'User'. 
- **POC d'un REST Controller** : pour avoir une API réutilisable ainsi que testable et documentée avec Swagger, il faut utiliser une API REST. Mais les controllers de ce type d'API ne peuvent pas renvoyer les pages pour Thymeleaf. Nous avons donc réalisé un POC d'API REST pour la classe de 'User' dans la classe 'UserController'. Cela permet de montrer la faisabilité des API REST dans notre contexte, malgré l'impossibilité de le mettre en œuvre de façon propre.
- **Notifications 'toast' et pop-up de confirmation** : afin d'obtenir une meilleure expérience utilisateur côté front, nous avons utilisé Bootstrap pour ajouter des notifications de confirmation de création et de modification d'un apprenti ('toast'). Seul le JS de Bootstrap est importé, afin de préserver notre CSS. De la même façon, il y aussi un pop-up de confirmation lors de la création d'une nouvelle année académique qui informe l'utilisateur que cette action est irréversible. 
- **Composants Thymeleaf** : nous avons utilisé les fragments de Thymeleaf pour rendre la vue modulaire et facilement réutilisable. On peut aussi noter que le formulaire utilisé pour la création d'un apprenti et aussi utilisé pour la modification d'un apprenti. 

## Difficultés 

Lorsque nous avions créé les relations entre les entités de la base de données, celles-ci se faisaient dans les deux sens. C'est-à-dire que, par exemple, un apprenti était lié à une année académique et une année académique était lié à un apprenti. 

Cette façon de faire a posé problème au moment de créer l'endpoint servant à mettre à jour un apprenti. En effet, lorsqu'on envoyait un apprenti mis à jour au backend, on devait aussi envoyer son année académique correspondante qui elle-même demandait un apprenti qui lui-même demandait une année académique et ainsi de suite. Nous avions alors une sorte de boucle infinie. 

Nous avons résolu ce bug en supprimant les doubles relations entre nos entités. Une meilleure planification des entités de la base de données au début du projet aurait surêment permis d'éviter ce problème.

## Contribution de chaque membre de l'équipe 

- **Antonin** : réalisation de l'UI du front et création des pages Thymeleaf, authentification via Spring Security, endpoint de recherche des apprentis.
- **Matéo** : réalisation du backend et de la plupart des endpoints, couche service et repository, ajout des notifications 'toast' de l'application.
- **Valentin** : création des entités et de l'architecture de l'application, déploiement et mise en production de l'application.

## Points à retenir du cours et du projet

- L'architecture de ce type d'application avec des couches Repository, Service et Controller est un concept très utile qui servira dans la plupart du développement web. 
- Spring Boot est un framework puissant qui permet de gagner beaucoup de temps et JPQL simplifie beaucoup la connexion à la base de données. Nous avons par contre trouvé que Thymeleaf n'était pas pratique dans certains cas. 
- Les opérations CRUD et les verbes HTTP associés sont aussi des points à retenir très important puisqu'ils servent lors du développement de beaucoup d'API. 

## Fonctionnalités manquantes 

Nous avons eu le temps d'implémenter toutes les fonctionnalités de base du projet. Cependant, nous avions au départ prévu d'implémenter la possibilité d'importer des apprentis depuis un fichier externe. Mais étant donné le temps limité dont nous disposions (la résolution des difficultés décrites précédemment a aussi pris beaucoup de temps), nous n'avons pas pu réaliser cette fonctionnalité. A la place, nous nous sommes concentrés sur l'amélioration de petits détails permettant d'améliorer l'expérience utilisateur. 

## Conformité aux principes SOLID 

- **S (Single Responsibility Principle)** : chacune de nos classes a une seule utilité. Ce principe est respecté. 
- **O (Open/Closed Principle)** : dans la mesure du possible, nous avons fait en sorte que ce principe soit respecté. Par exemple, on peut rajouter un nouveau type d'utilisateur sans toucher au code du tuteur.
- **L (Liskov Substitution Principle)** : les classes de notre programme peuvent bien être utilisées à la place de leur classe de base. Ce principe est respecté. 
- **I (Interface Segregation Principle)** : nous avons peu d'interfaces, à part les repository qui représente l'entité auquel ils sont associés. Ce principe n'est donc pas vraiment respecté mais nous n'en avions pas vraiment besoin.
- **D (Dependency Inversion Principle)** : la plupart de notre code dépend d'interface. Ce principe est respecté. 