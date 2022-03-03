CREATE TABLE `projeto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_entrega` date DEFAULT NULL,
  `data_fim` date NOT NULL,
  `data_inicio` date NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `lider_id` bigint NOT NULL,
  `responsavel_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs1t8u8tmcdb0vn9tx8ruj8fao` (`lider_id`),
  KEY `FK1rps7s0gmrfuwjrucackceyln` (`responsavel_id`),
  FOREIGN KEY (`responsavel_id`) REFERENCES `usuario` (`id`),
  FOREIGN KEY (`lider_id`) REFERENCES `usuario` (`id`)
);