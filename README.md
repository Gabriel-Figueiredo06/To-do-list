# To-Do List com Spring Boot e SQLite

Uma aplicação completa de gerenciamento de tarefas desenvolvida com Java, Spring Boot e SQLite, integrada com os HTMLs existentes.

## 🚀 Funcionalidades

- ✅ Criar, editar e deletar tarefas
- ✅ Marcar tarefas como concluídas
- ✅ Filtrar tarefas por status (Pendente, Concluída, Urgente)
- ✅ Visualizar estatísticas das tarefas
- ✅ Interface responsiva e moderna
- ✅ Persistência de dados com SQLite
- ✅ API REST completa

## 🛠️ Tecnologias Utilizadas

- **Backend**: Java 17, Spring Boot 3.2.0
- **Banco de Dados**: SQLite
- **Frontend**: Thymeleaf, HTML5, CSS3
- **Build Tool**: Maven

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

## 🔧 Instalação e Execução

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd To-do-list-main
```

### 2. Compile e execute a aplicação
```bash
mvn clean install
mvn spring-boot:run
```

### 3. Acesse a aplicação
Abra seu navegador e acesse: `http://localhost:8080`

## 📖 Como Usar

### Página Principal (`/`)
- Visualize todas as suas tarefas
- Adicione novas tarefas usando o formulário
- Marque tarefas como concluídas
- Delete tarefas

### Página de Detalhes (`/detalhes`)
- Visualize estatísticas das tarefas
- Filtre tarefas por status
- Veja informações detalhadas de cada tarefa

### API REST (`/api/tarefas`)
A aplicação também oferece uma API REST completa:

#### Endpoints disponíveis:
- `GET /api/tarefas` - Listar todas as tarefas
- `POST /api/tarefas` - Criar nova tarefa
- `GET /api/tarefas/{id}` - Buscar tarefa por ID
- `PUT /api/tarefas/{id}` - Atualizar tarefa
- `DELETE /api/tarefas/{id}` - Deletar tarefa
- `PATCH /api/tarefas/{id}/concluir` - Marcar como concluída
- `PATCH /api/tarefas/{id}/urgente` - Marcar como urgente
- `GET /api/tarefas/status/{status}` - Filtrar por status
- `GET /api/tarefas/usuario/{nomeUsuario}` - Filtrar por usuário

## 🗄️ Estrutura do Banco de Dados

A tabela `tarefas` possui os seguintes campos:
- `id` (Long, Primary Key)
- `descricao` (String, obrigatório)
- `status` (Enum: PENDENTE, CONCLUIDA, URGENTE)
- `data_criacao` (LocalDateTime)
- `data_conclusao` (LocalDateTime)
- `nome_usuario` (String)
- `prioridade` (Enum: BAIXA, MEDIA, ALTA, URGENTE)

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/todo/
│   │   ├── TodoListApplication.java
│   │   ├── controller/
│   │   │   ├── TarefaController.java (API REST)
│   │   │   └── WebController.java (Páginas web)
│   │   ├── model/
│   │   │   ├── Tarefa.java
│   │   │   ├── StatusTarefa.java
│   │   │   └── Prioridade.java
│   │   ├── repository/
│   │   │   └── TarefaRepository.java
│   │   └── service/
│   │       └── TarefaService.java
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   ├── style_to_do_list.css
│       │   │   └── style_details.css
│       │   └── img/
│       │       └── (arquivos de imagem)
│       ├── templates/
│       │   ├── to_do_list.html
│       │   └── detalhes.html
│       └── application.properties
```

## 🔄 Exemplos de Uso da API

### Criar uma nova tarefa
```bash
curl -X POST http://localhost:8080/api/tarefas \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Estudar Spring Boot",
    "nomeUsuario": "João",
    "status": "PENDENTE"
  }'
```

### Buscar tarefas por usuário
```bash
curl http://localhost:8080/api/tarefas/usuario/João
```

### Marcar tarefa como concluída
```bash
curl -X PATCH http://localhost:8080/api/tarefas/1/concluir
```

## 🎨 Personalização

### Alterar usuário
Para usar a aplicação com um nome de usuário específico, acesse:
- `http://localhost:8080/?nomeUsuario=SeuNome`
- `http://localhost:8080/detalhes?nomeUsuario=SeuNome`

### Modificar estilos
Os arquivos CSS estão em `src/main/resources/static/css/` e podem ser editados para personalizar a aparência.

## 🐛 Solução de Problemas

### Erro de conexão com banco
- Verifique se o arquivo `todo_list.db` foi criado na raiz do projeto
- Certifique-se de que o SQLite está funcionando corretamente

### Erro de compilação
- Verifique se o Java 17 está instalado: `java -version`
- Limpe e recompile: `mvn clean compile`

### Imagens não carregam
- Verifique se os arquivos de imagem estão em `src/main/resources/static/img/`
- Reinicie a aplicação após adicionar novos arquivos

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests. 