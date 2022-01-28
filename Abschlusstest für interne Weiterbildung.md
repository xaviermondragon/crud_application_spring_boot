1 Aufbau einer Docker/Kubernetes-Infrastruktur (DevOps-Teil)
1.1 Aufgabe
Die unten genannte Anwendung soll innerhalb einer Microservice-Architektur lauffähig sein. Dazu soll die Anwedungsschicht in geeignete Docker-Images gekapselt, öffentlich (mit Authentifizierung) als Image in einer Registry verfügbar und in eine Container-Umgebung (Kubernetes-Cluster) deploybar sein.

Aufgabengebiete:

Evaluieren welche Baseimages genutzt werden
Geeignete Dockerfiles entwerfen und implementieren
Konfiguration einer geeigneten Image-Registry (z.B. privates Docker-Hub)
Auswahl eines geeigneten Kubernetes-Clusters (z.B. minikube oder kind)
Implementierung und Bereitstellung von Kubernetes-Manifesten (YAML) für Konfiguration des Deployments
Servicekonfiguration der Datenbank (mySQL oder PostgreSQL aus öffentlichem Image) mit den nötigen Kubernetes Ressourcen (volume, persistent volume claim, .. etc.)
Servicekonfiguration des Microservice
Konfiguration der Credentials für die Registry + geeignetes Image Naming
Konfiguration geeigneter Probes (Health+Readiness) die der Cluster nutzt
Ziel: lauffähige Anwendung, die im Cluster läuft + Microservice kann mit der Datenbank kommunizieren


OPTIONAL (erhöhte Schwierigkeit, da dies nicht im Workshop behandelt wurde):

Bereitstellung eines Messagebrokers als zusätzlichen Service im Cluster (z.B. RabbitMQ oder Kafka)
Implementierung von Messageproducer und -consumer in Microservice 
Ziel: asyncrone Kommunikation über Messages des Brokers bzw. Queues
Deployment über Helm-Charts
Implementierung von eigenen oder öffentlichen Helm-Charts statt manueller YAMLs
1.2 Technologien, die zum Einsatz kommen sollen
Docker Images
Dockerfiles
Linux Dependencies + File System
YAML Markup
Container-Umgebungen
Docker-Image Registries + Credentials
Kubernetes
Lokale Kubernetes-Cluster
Nutzung der offiziellen Dokumentationen
2 Erstellung eines Microservices und Integration in Infrastruktur (Develop-Teil)
2.1 Aufgabe
Es soll ein Spring-Boot-Microservice zur Verwaltung von Kundenstammdaten entwickelt werden. Die Datenhaltung findet in
einer externen Datenbank statt, welche initial über den Microservice mit der Tabellenstruktur befüllt wird. 
Folgende Funktionen sollen über mit BasicAuth abgesicherte REST-Endpunkte abgedeckt werden:

Hinzufügen neuer Kunden mit folgenden Parametern:

Vorname (Pflichtfeld, gültige Länge: 2 bis 20 Zeichen)

Nachname (Pflichtfeld, gültige Länge: 2 bis 20 Zeichen)

E-Mail (Pflichtfeld, muss gültiges E-Mail-Pattern erfüllen)

Alter (gültige Werte: 18 bis 99)

Adresse (max. Länge: 255 Zeichen)

Ändern eines Kunden (z.B.: Nachname nach angenommener Heirat)

Suche von Kunden anhand Name | Vorname

Abruf von Kunden über die Kunden-ID

Abruf der gesamten Kundenliste

Löschen eines Kunden über die Kunden-ID

Die REST-Endpunkte sollen über die SwaggerUI verwendet werden können, welche nicht BasicAuth geschützt ist. (https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)

Im Fehlerfall innerhalb eines Requests findet ein Roll-Back in der Datenbank statt.

Für den entstandenen Code sollen Tests existieren. Controller-Klassen (Endpunkte) werden dabei mit Spring-Boot-Tests getestet und Service-Klassen mit einfachen Unit-Tests.

2.2 Technologien, die zum Einsatz kommen sollen
Spring Boot (Maven basiertes Projekt)

externe Datenbank zur Persistierung (siehe Infrastruktur)

Liquibase

Spring Data / JPA / Hibernate

Crud-Repositories für die Persistierung

Bean/Constraint-Validation

Spring Security (für BasicAuth)

Globales Exception-Handling mit eigener Exception-Klasse

SwaggerUI für den Zugriff auf die Endpunkte

JUnit5, Mockito und REST-assured oder MockMvc