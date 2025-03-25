#!/bin/sh
echo "Démarrage du watcher pour le hot reload..."
# Lancer en arrière-plan une surveillance sur le dossier src
while inotifywait -r -e modify,create,delete /app/src; do
  echo "Modification détectée dans /app/src. Lancement de mvn compile..."
  mvn compile
done &
echo "Démarrage de l'application Spring Boot..."
# Spring Boot DevTools détectera les classes modifiées dans target et redémarrera l'application
exec mvn spring-boot:run -Dspring.devtools.restart.poll-interval=1s -Dspring.devtools.restart.quiet-period=1s
