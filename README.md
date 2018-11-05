# ConfigHubDBManager

### Build:
`./gradlew clean build jar`

### Run:
`java -jar build/libs/ConfigHubDBManager-1.8.jar [parameters]`

### Usage: 
`parameters [-hV] -t <databaseType> -r <databaseUrl> -u <username> -p <password>`

```
  -h, --help                Show this help message and exit.
  -t, --url=<databaseType>  Database types: oracle, postgresql, mysql
  -r, --url=<databaseUrl>   Database URL.  Ex: jdbc:postgresql://127.0.0.1:5432/ConfigHub
  -u, --user=<username>     Database username  
  -p, --password=<password> Database password
  -V, --version             Print version information and exit.
```
