# desafio-back-end-02-ingrid
NewGo Project

Implementação de uma API REST para realizar cadastro de produtos e controle de estoque e preço destes produtos utilizando PostgreSQL, GitHub, Servlets.

## Documentação das APIs

### Endpoints Principais (Ativos)

#### 1. ServletProducts - `/servletProducts`
Endpoint unificado para gerenciamento de produtos.

**GET /servletProducts**
- **Descrição**: Retorna todos os produtos com opções de filtro
- **Body**: JSON com `StatusDTO`
  ```json
  {
    "status": "active|inactive",
    "hash": "uuid-opcional"
  }
  ```
- **Resposta**: Array JSON de produtos

**POST /servletProducts**
- **Descrição**: Cria um novo produto
- **Body**: JSON com `CreateDTO`
  ```json
  {
    "nome": "Nome do Produto (obrigatório)",
    "descricao": "Descrição",
    "ean13": "Código EAN-13",
    "preco": 99.99,
    "quantidade": 100,
    "estoque_min": 10
  }
  ```
- **Resposta**: JSON do produto criado com UUID
- **Validação**: Verifica duplicação de nome ou EAN-13

**PUT /servletProducts**
- **Descrição**: Atualiza um produto existente
- **Parâmetros Query String**:
  - `hash`: UUID do produto (obrigatório)
  - `descricao`: Nova descrição
  - `preco`: Novo preço (decimal)
  - `quantidade`: Nova quantidade (inteiro)
  - `estoque_min`: Novo estoque mínimo (inteiro)
- **Resposta**: Mensagem de sucesso/falha
- **Validação**: Produto deve estar ativo para atualização

**DELETE /servletProducts**
- **Descrição**: Deleta um produto
- **Parâmetros Query String**:
  - `hash`: UUID do produto (obrigatório)
- **Resposta**: JSON com mensagem de sucesso ou erro

#### 2. ChangeStatus - `/changeStatus`
Endpoint para alteração de status e consulta de estoque baixo.

**GET /changeStatus**
- **Descrição**: Lista produtos ativos com estoque abaixo do mínimo (US006)
- **Body**: JSON com `EstoqueMinDTO`
  ```json
  {
    "estoqueMin": 10
  }
  ```
- **Resposta**: Array JSON de produtos com estoque baixo
- **Regra de Negócio (RN017)**: Retorna apenas produtos ativos

**POST /changeStatus**
- **Descrição**: Altera o status do produto (ativo/inativo)
- **Body**: JSON com `ChangeStatusDTO`
- **Resposta**: Mensagem de sucesso/erro

### Endpoints Legados

Os seguintes endpoints ainda estão disponíveis mas estão sendo substituídos pelo ServletProducts:

- **GET/POST/PUT/DELETE `/listProducts`** - Retorna todos os produtos
- **ANY `/createProducts`** - Cria novo produto
- **ANY `/deleteProducts`** - Deleta produto
- **ANY `/updateProducts`** - Atualiza produto
- **GET `/listProductsActive`** - Lista produtos ativos

### Formato de Requisição/Resposta

**Content-Type**: `application/json`
**Character Encoding**: `UTF-8`

### Objetos de Transferência de Dados (DTOs)

- **CreateDTO**: nome, descricao, ean13, preco, quantidade, estoque_min
- **StatusDTO**: status, hash
- **ChangeStatusDTO**: Para operações de mudança de status
- **HashDTO**: hash (UUID opcional)
- **EstoqueMinDTO**: estoqueMin (inteiro)

### Tecnologias Utilizadas

- Java Servlets
- PostgreSQL
- JSON para serialização/deserialização
- Maven para gerenciamento de dependências


