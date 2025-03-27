## Prérequis

Avant de commencer, assurez-vous d'avoir installé les outils suivants :

- [JDK](https://adoptopenjdk.net/) (Jdk LTS)
- [Maven](https://maven.apache.org/) (V 3.9.9) ou [Spring Boot](https://spring.io/projects/spring-boot) (V 3.4.3)
- [Docker](https://www.docker.com/)  (V 28.0.1) et [Docker Compose](https://docs.docker.com/compose/) (V 2.32.4) hyper-v only


## Installation du projet

### Avec Maven

1. Clonez le dépôt du projet :

   ```sh
   git clone git@github.com:ldesfontaine/projet10.git
   cd projet10
   ```

2. Installez les dépendances et construisez le projet :

   ```sh
   mvn clean install
   ```

3. Lancez l'application :

   ```sh
   mvn spring-boot:run
   ```

### Avec Spring Boot

1. Clonez le dépôt du projet :

   ```sh
   git clone git@github.com:ldesfontaine/projet10.git
   cd projet10
   ```

2. Installez les dépendances et construisez le projet :

   ```sh
   ./mvnw clean install
   ```

3. Lancez l'application :

   ```sh
   ./mvnw spring-boot:run
   ```

---
## Lancement avec Docker

### Configuration du fichier `.env`

Créez un fichier `.env` à la racine du projet en copiant le fichier `.env.exemple` et en remplaçant les valeurs par défaut par celles adaptées à votre environnement :

```sh
cp .env.exemple .env
```

Editez le fichier `.env` pour configurer les variables d'environnement nécessaires :

```env
MYSQL_ROOT_PASSWORD=rootpassword
MYSQL_DATABASE=vaadindb
MYSQL_USER=user
MYSQL_PASSWORD=password
SPRING_PROFILES_ACTIVE=dev

UID=1000
GID=1000

APP_PORT=8080
DB_PORT=3306
```

### Lancement des conteneurs Docker

1. Lancez les conteneurs Docker :

   ```sh
   docker-compose up
   ```

2. Pour arrêter les conteneurs, utilisez la commande suivante :

   ```sh
   docker-compose down
   ```

## Développement

### Création de branches

Pour les corrections de bugs, créez une branche avec le préfixe `fix/` suivi du nom du bug :

```sh
git checkout -b fix/nomDuFix
```

Pour les nouvelles fonctionnalités, créez une branche avec le préfixe `feat/` suivi du nom de la fonctionnalité :

```sh
git checkout -b feat/nomDuFeat
```

Une fois le fix ou la feature terminée, créez une pull request sur la branche `develop`.
Puis merge et résoudre les conflits si il y en a.
### Conventions de commit

Pour les corrections de bugs, utilisez le format suivant pour les messages de commit :

```
fix(scoop du fix): #{deLissue} explication du commit
```
Par exemple : `fix(terminal): #2 Fix logique api`


Pour les nouvelles fonctionnalités, utilisez le format suivant pour les messages de commit :

```
feat(scoop du feat): #{deLissue} explication du commit
```

Par exemple : `feat(CI): #3 Modification du port SSH`

---
