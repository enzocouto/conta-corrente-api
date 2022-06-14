Teste Desenvolvedor Backend (Java)
Olá, seja bem-vindo ao desafio para desenvolvedor backend Java do
Banco Santander, o foco desta etapa é testar seus conhecimentos técnicos
em desenvolvimento de API’s de acordo com o escopo abaixo:
Para facilitar a execução do projeto pedimos que seja utilizado a
dependência h2 como banco de dados em memória e que forneça uma
carga prévia de dados junto ao projeto.
O tema do projeto fictício é um Internet Banking, o projeto precisa
ter as seguintes funcionalidades:
- Cadastrar um cliente que contenha os atributos abaixo:
Nome do campo Tipo
Nome String
Plano exclusive? Boolean
Saldo BigDecimal
Número da conta String
Data de nascimento Date
- Retornar todos clientes cadastrados
- Sacar um valor que subtrai o saldo do cliente contando uma taxa de
administração conforme a tabela abaixo:
Valor Taxa de administração
VALOR <= 100,00 Isento de taxa de saque
VALOR > 100,00 E VALOR <= 300 Taxa de 0.4%
VALOR > 300,00 Taxa de 1%
Plano exclusive? Isento de taxa de saque
2
- Depositar um valor que aumenta o saldo de um determinado cliente
- Consultar o histórico de transações de cada movimentação por Data
(Saque e depósito)
O que será avaliado?
- Padrões de classes, atributos e métodos
- Utilização dos recursos do spring-boot
- Conhecimento de orientação a objetos
- Testes unitários
- Tratamento de erros
- Métodos pequenos
- Reutilização de código
- Cobertura de testes unitários para as principais funcionalidades
Desejáveis (São considerados diferenciais)
- Configurar o Swagger para termos a documentação da API.
- Validações de entradas de dados.
- Utilizar paginação nas listagens para melhor performance.
Considerações finais
- O design da api deve contemplar minimamente a maturidade de
Richardson no nível 2.
- Realizar upload do projeto no github, deixar o repositório publico e
compartilhar o link conosco após o termino.
