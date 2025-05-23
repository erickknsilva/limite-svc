#!/bin/bash

# Define o caminho do segredo no Vault (ajuste se quiser)
SECRET_PATH="secret/limites"

# Token do Vault (troque para o seu)
VAULT_TOKEN="vault-plaintext-root-token"
VAULT_ADDR="http://localhost:8200"

export VAULT_ADDR
export VAULT_TOKEN

vault kv put $SECRET_PATH \
  spring.datasource.driver-class-name="org.postgresql.Driver" \
  spring.datasource.url="jdbc:postgresql://localhost:5432/limites" \
  spring.datasource.username="limite" \
  spring.datasource.password="limite" \
  spring.datasource.hikari.connection-timeout="2000" \
  spring.datasource.hikari.maximum-pool-size="10" \
  spring.flyway.enabled="true" \
  spring.flyway.locations="classpath:db/migration" \
  spring.jpa.hibernate.ddl-auto="validate" \
  spring.jpa.hibernate.dialect="org.hibernate.dialect.PostgreSQLDialect" \
  spring.jpa.show-sql="true"
