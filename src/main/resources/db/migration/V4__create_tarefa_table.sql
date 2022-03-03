CREATE TABLE `tarefa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `projeto_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`)
);