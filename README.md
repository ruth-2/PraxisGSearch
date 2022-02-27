# PraxisGSearch

Gesicherte API, um Ergebnisse von einer Google-Suche abzurufen. Verwendet die [Google Programmable Search Engine](https://developers.google.com/custom-search) (insbesondere der [Google API Client Library for Java](https://googleapis.dev/java/google-api-client/latest/index.html)), um benutzerdefinierte Suchen durchzuführen.

----

## Voraussetzungen

Die Umgebungsvariablen `GOOGLE_API_KEY` und `GOOGLE_SEARCH_ENGINE_ID` müssen von der Google Custom Search-API auf den API Key und Search ID eingestellt werden.

## Authentifizierung

Die API verfügt über eine rudimentäre Authentifizierung, um den eigenen API-Key zu verwenden, um die Anfrage zu erstellen. Es könnte in der resources/application.properties-Datei gefunden werden.

### Beispiel:

```bash
curl -H "API-KEY: 1811bf13-f6a5-41c5-8c58-19eb4c2a3be6" -d '{"keyword": "google custom search api"}' -H 'Content-Type: application/json' http://localhost:8080/search
```