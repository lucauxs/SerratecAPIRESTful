CREATE TABLE pedido(
	id SERIAL PRIMARY KEY,
	data_pedido DATE,
	data_entrega DATE,
	data_envio DATE,
	status_pedido smallint,
	valor_total DECIMAL,
	cliente_id BIGINT,
	item_pedido_id BIGINT,
	
	CONSTRAINT fk_cliente FOREIGN KEY (cliente_id)
	REFERENCES cliente(id),
	CONSTRAINT fk_item_pedido FOREIGN KEY (item_pedido_id)
	REFERENCES item_pedido(id)
)