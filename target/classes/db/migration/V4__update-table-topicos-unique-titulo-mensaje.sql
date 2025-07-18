ALTER TABLE topicos MODIFY mensaje VARCHAR(1000);
ALTER TABLE topicos ADD CONSTRAINT unique_titulo_mensaje UNIQUE (titulo(100), mensaje(668));