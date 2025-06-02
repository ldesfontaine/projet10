# 🚀 Projet10 - Application Mastodonte CLI : **Search IT**

## 📋 Prérequis

Avant de commencer, assurez-vous d’avoir installé les outils suivants :

- [JDK LTS](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/) (v3.9.9) ou [Spring Boot](https://spring.io/projects/spring-boot) (v3.4.3)
- [Docker](https://www.docker.com/) (v28.0.1) et [Docker Compose](https://docs.docker.com/compose/) (v2.32.4) *(Hyper-V only)*

---

## 🔧 Installation du projet

### Avec Maven

```sh
git clone git@github.com:ldesfontaine/projet10.git
cd projet10
mvn clean install
mvn spring-boot:run
````

### Avec Spring Boot Wrapper

```sh
git clone git@github.com:ldesfontaine/projet10.git
cd projet10
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🐳 Lancement avec Docker

### 📁 Configuration du fichier `.env`

Créez un fichier `.env` :

```sh
cp .env.exemple .env
```

Modifiez les valeurs :

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

### ▶️ Lancement des conteneurs

```sh
docker-compose up
```

Pour arrêter :

```sh
docker-compose down
```

---

## 👨‍💻 Développement

### 🌿 Création de branches

* Pour un bugfix :

```sh
git checkout -b fix/nomDuFix
```

* Pour une nouvelle fonctionnalité :

```sh
git checkout -b feat/nomDuFeat
```

👉 Une fois terminé : créez une **pull request** vers `develop`, mergez et résolvez les conflits si nécessaire.

### ✏️ Conventions de commit

* **Fix :**

```sh
fix(scope): #numIssue description
```

Exemple : `fix(terminal): #2 Fix logique api`

* **Feature :**

```sh
feat(scope): #numIssue description
```

Exemple : `feat(CI): #3 Modification du port SSH`

---

## 🧠 Fonctionnalités réalisées

* `clear` : Nettoie la console.
* `mshow` : Agrandit un post Mastodonte.
* `help` : Aide contextuelle automatique.
* Navigation historique : via les flèches du clavier.
* Options `-r` / `-l` : pour affiner les requêtes `mastodonte`.
* Option `-m` : filtre les posts récents par minutes.
* Correction du filtre `mastodonte` en `search`.
* Ouverture automatique des URLs avec `show`.
* Suivi des issues / PR sur GitHub.

---

## 🛠️ Architecture - Méthode Mastodonte

| Fichier                   | Rôle                                                 |
| ------------------------- | ---------------------------------------------------- |
| `MastodonteCommand.java`  | Point d’entrée, exécute la commande `chat`           |
| `OptionsParser.java`      | Analyse des options (`-d`, `-v`, etc.)               |
| `Options.java`            | Stockage des options (jours, verbeux, erreurs, etc.) |
| `MastodonClient.java`     | Requêtes HTTP + parsing JSON vers objets Java        |
| `Processor.java`          | Interface de traitement des posts                    |
| `ProcessorFactory.java`   | Choix entre `Popular` et `Recent` processors         |
| `PopularProcessor.java`   | Tri des posts par popularité (♥)                     |
| `RecentProcessor.java`    | Filtrage et tri des posts récents                    |
| `OutputFormatter.java`    | Affichage, verbosité, mise en forme                  |
| `cache/SessionCache.java` | Stockage temporaire entre deux exécutions            |

---

## 👥 Équipe

**Équipe n°10** - *Search IT*

| Membre  | Rôle                                   |
| ------- | -------------------------------------- |
| Lucas Desfontaine| Référent technique                     |
| Anthony GASS  | Développeur back-end                   |
| Elodie TRAN   | Développeuse front-end / Coordinatrice |
| Adrien ROYER  | Développeur                            |
| Aymeric BOUTIN| Développeur                            |
| Mathieu GALOIS| Développeur                            |
| Dylan POLUTELE| Développeur                            |

---

## 🔗 Ressources

* **URL du projet GitHub** : [https://github.com/ldesfontaine/projet10](https://github.com/ldesfontaine/projet10)
* **Documentation** :

  * Mastodonte (API officielle)
  * Stack Overflow / Forums développeurs
  * Tutoriels Vaadin / Spring Boot / Maven

---

## 🧰 Outils utilisés

* **GitHub** :  Gestion du code source, suivi des issues et des pull requests.
* **Discord** : Communication et coordination entre les membres de l'équipe.
* **Git** :  Contrôle de version pour le développement collaboratif.
* **VS Code** : Environnement de développement intégré (IDE) principal.
* **Intelij** : IDE principal
* **Vaadin** : Framework pour la création d'interfaces utilisateur en Java. UI Java
* **Maven** : Outil de gestion de projet et de dépendances. Build & dépendances
* **Spring Boot** : Framework pour le développement d'applications Java.
* **Docker** : Conteneurisation de l'application pour faciliter le déploiement.

