# notesnook-sync-server-admin

## 使用方法

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

将上述配置加入到 `notesnook-sync-server` 的 `docker-compose.yml` 中。