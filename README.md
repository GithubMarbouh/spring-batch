# Spring Batch - Projet de traitement par lots

## Description
Projet multi-modules démontrant l'utilisation de Spring Batch pour le traitement de transactions bancaires par lots. Ce projet illustre les bonnes pratiques pour la création de jobs batch robustes et testables.

## Architecture du projet
Ce projet est composé de deux modules principaux :

### 1. Account Service (`account-service/`)
- **Description** : Service de gestion des comptes bancaires
- **Technologies** : Spring Boot, Spring Data JPA, Lombok
- **Fonctionnalités** :
  - Gestion des entités `Account` et `Transaction`
  - Mise à jour automatique du solde des comptes
  - Gestion des dates de dernière transaction

### 2. Transaction Batch (`transaction-batch/`)
- **Description** : Job batch pour le traitement des transactions
- **Technologies** : Spring Batch, Spring Boot, MySQL
- **Fonctionnalités** :
  - Lecture de fichiers de transactions
  - Traitement en deux étapes
  - Gestion de la reprise sur erreur

## Technologies utilisées
- **Spring Boot** - Framework principal
- **Spring Batch** - Traitement par lots
- **Spring Data JPA** - Persistance des données
- **MySQL** - Base de données
- **Lombok** - Réduction du code boilerplate
- **Maven** - Gestion des dépendances et build
- **JUnit & AssertJ** - Tests unitaires

## Prérequis
- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- MySQL 8.0 ou supérieur

## Configuration de la base de données
1. Créez une base de données MySQL
2. Configurez les paramètres de connexion dans les fichiers `application.properties` de chaque module

## Installation et démarrage
1. Clonez le projet :
   ```bash
   git clone <repo-url>
   cd spring-batch
   ```

2. Compilez le projet parent :
   ```bash
   mvn clean install
   ```

3. Démarrez les modules individuellement :
   ```bash
   # Account Service
   cd account-service
   mvn spring-boot:run
   
   # Transaction Batch (dans un autre terminal)
   cd transaction-batch
   mvn spring-boot:run
   ```

## Architecture Spring Batch
### Étapes du traitement (Transaction Batch)
1. **Étape 1** : Lecture des transactions depuis un fichier plat et écriture dans une table de staging
2. **Étape 2** : Lecture depuis la table de staging et traitement des transactions avec mise à jour des comptes

### Gestion des erreurs
- Reprise automatique en cas d'erreur
- Traitement des transactions en suspens lors du redémarrage
- Logs détaillés pour le débogage

## Structure du projet
```
spring-batch/
├── pom.xml                    # POM parent
├── lombok.config              # Configuration Lombok
├── account-service/           # Module service des comptes
│   ├── src/main/java/
│   ├── src/test/java/
│   ├── pom.xml
│   └── README.md
├── transaction-batch/         # Module batch des transactions
│   ├── src/main/java/
│   ├── src/test/java/
│   ├── pom.xml
│   └── README.md
└── README.md
```

## Tests
### Tests unitaires
```bash
# Pour tous les modules
mvn test

# Pour un module spécifique
cd account-service
mvn test
```

### Tests d'intégration Spring Batch
Le module `transaction-batch` inclut des tests spécifiques pour :
- Validation des jobs batch
- Tests de reprise sur erreur
- Vérification de l'intégrité des données

## Bonnes pratiques implémentées
- ✅ **Séparation des responsabilités** - Modules distincts pour les services et le batch
- ✅ **Gestion des erreurs** - Reprise automatique et logs détaillés
- ✅ **Tests unitaires** - Couverture complète avec JUnit et AssertJ
- ✅ **Configuration externalisée** - Paramètres dans application.properties
- ✅ **Code propre** - Utilisation de Lombok et Spring Boot best practices

## Monitoring et observabilité
- Logs structurés pour le suivi des jobs
- Métriques Spring Boot Actuator
- Tables Spring Batch pour le suivi des exécutions

## Extensions possibles
- Ajout de nouvelles étapes de traitement
- Intégration avec des systèmes de notification
- Mise en place de jobs parallèles
- Ajout de validations métier plus complexes

## Ressources
- [Documentation Spring Batch](https://spring.io/projects/spring-batch)
- [Guide Spring Boot](https://spring.io/guides/gs/spring-boot/)
- [Documentation Spring Data JPA](https://spring.io/projects/spring-data-jpa)# spring-batch
