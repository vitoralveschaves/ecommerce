ALTER TABLE avaliacao_produto ADD empresa_id bigint not null;
ALTER TABLE avaliacao_produto ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE categoria_produto ADD empresa_id bigint not null;
ALTER TABLE categoria_produto ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE conta_pagar ADD empresa_id bigint not null;
ALTER TABLE conta_pagar ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE conta_receber ADD empresa_id bigint not null;
ALTER TABLE conta_receber ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE cupom_desconto ADD empresa_id bigint not null;
ALTER TABLE cupom_desconto ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE endereco ADD empresa_id bigint not null;
ALTER TABLE endereco ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE forma_pagamento ADD empresa_id bigint not null;
ALTER TABLE forma_pagamento ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE imagem_produto ADD empresa_id bigint not null;
ALTER TABLE imagem_produto ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE item_venda_loja ADD empresa_id bigint not null;
ALTER TABLE item_venda_loja ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE marca_produto ADD empresa_id bigint not null;
ALTER TABLE marca_produto ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE nota_fiscal_compra ADD empresa_id bigint not null;
ALTER TABLE nota_fiscal_compra ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE nota_fiscal_venda ADD empresa_id bigint not null;
ALTER TABLE nota_fiscal_venda ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE nota_item_produto ADD empresa_id bigint not null;
ALTER TABLE nota_item_produto ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE pessoa_fisica ADD empresa_id bigint not null;
ALTER TABLE pessoa_fisica ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE pessoa_juridica ADD empresa_id bigint not null;
ALTER TABLE pessoa_juridica ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE produto ADD empresa_id bigint not null;
ALTER TABLE produto ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE status_rastreio ADD empresa_id bigint not null;
ALTER TABLE status_rastreio ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE usuario ADD empresa_id bigint not null;
ALTER TABLE usuario ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);

ALTER TABLE venda_compra_loja ADD empresa_id bigint not null;
ALTER TABLE venda_compra_loja ADD CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES pessoa_juridica(id);