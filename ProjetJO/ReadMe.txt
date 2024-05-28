## Description

Il s'agit d'une application Spring Boot pour la gestion de la billetterie des Jeux Olympiques. Elle fournit une API REST permettant aux utilisateurs de s'inscrire, de s'authentifier et d'acheter des billets pour différents événements des Jeux Olympiques. Les administrateurs peuvent gérer les événements, les billets et les rôles des utilisateurs.

## Technologies Utilisées

- Java
- Spring Boot
- Maven

## Prérequis

- Java 17
- Maven 4
- IDE (comme IntelliJ IDEA, Eclipse, etc.)

## Installation

1. Clonez le dépôt 

2. Naviguez vers le répertoire du projet 

3. Construisez le projet avec Maven 

4. Lancez l'application 

## Utilisation

- Accédez à la documentation de l'API via Swagger UI : [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
- Utilisez des outils comme Postman pour interagir avec les points de terminaison de l'API.

## Points de Terminaison de l'API

- `POST /api/auth/signup` : Inscrire un nouvel utilisateur
- `POST /api/auth/login` : Authentifier et générer un jeton JWT
- `GET /api/events` : Obtenir une liste d'événements disponibles
- `POST /api/events` : Créer un nouvel événement (administrateur seulement)
- `PUT /api/events/{eventId}` : Mettre à jour un événement existant (administrateur seulement)
- `DELETE /api/events/{eventId}` : Supprimer un événement (administrateur seulement)
- `POST /api/tickets` : Acheter des billets pour un événement
- `GET /api/users/{userId}/tickets` : Obtenir les billets achetés par un utilisateur

## Contributeurs

- Lucas LABEYE
- Valentin CIRCOSTA
