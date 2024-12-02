# notesnook-sync-server-admin

## example of use

```yaml
  notesnook-server-admin:
    image: dyw770/notesnook-server-admin:latest
    ports:
      - 8080:8080
    depends_on:
      - notesnook-db
    networks:
      - notesnook
    environment:
      ADMIN_USERNAME: admin
      ADMIN_PASSWORD: admin
      MONGODB_HOST: notesnook-db
      MONGODB_PORT: 27017
```

add the above configuration to `docker-compose.yml` of `notesnook-sync-server`