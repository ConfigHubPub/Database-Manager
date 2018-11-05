# ConfigHubDBManager

### Build:
`./gradlew clean build jar`

### Run:
`java -jar target/DBMigration-1.8.jar [options...]`

### Usage: 
`example [-hV] -r=<databaseUrl> -u=<username> -p=<password>`

```
  -h, --help                Show this help message and exit.  
  -p, --password=<password> Database password  
  -r, --url=<databaseUrl>   Database URL.  Ex: jdbc:postgresql://127.0.0.1:5432/ConfigHub  
  -u, --user=<username>     Database username  
  -V, --version             Print version information and exit.
```
