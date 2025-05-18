CREATE TABLE limite_diario(
    id BIGSERIAL NOT NULL,
    agencia BIGINT NOT NULL,
    conta BIGINT NOT NULL,
    data TIMESTAMP NOT NULL,
    valor DECIMAL(8,2),
    PRIMARY KEY(id)
);
