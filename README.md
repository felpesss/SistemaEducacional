# Sistema Educacional

Sistema simples com Spring Boot + MySQL + MongoDB.

## Como rodar

### 1. Instalar
- Java 11+
- MySQL 8.0
- MongoDB (opcional)

### 2. Criar banco
```bash
mysql -u root -p < database_completo.sql
```

### 3. Rodar
Abra `SistemaEducacionalApplication.java` e clique em **Run**.

Ou:
```bash
mvn spring-boot:run
```

### 4. Acessar
http://localhost:8080/login.html

**Login**: admin@sistema.edu  
**Senha**: 123456

## O que tem

### MySQL
- Tabelas: usuarios, grupos_usuarios, Aluno, Curso, Nota
- 6 Functions pra gerar IDs
- 3 Triggers (auditoria + validação)
- 2 Views (relatórios)
- 2 Procedures
- 3 usuários (admin, professor, aluno)

### MongoDB
- Logs de auditoria

### Backend
- API REST
- Login simples
- CRUD de alunos

### Frontend
- login.html - tela de login
- index.html - cadastro de alunos

## Estrutura

```
src/main/java/com/sistema/educacional/
├── SistemaEducacionalApplication.java
├── model/
├── repository/
├── controller/
├── service/
└── config/

src/main/resources/
├── application.properties
└── static/
```

## Arquivos importantes

- `database_completo.sql` - cria tudo no MySQL
- `DOCUMENTACAO_TECNICA.md` - artigo completo
- `pom.xml` - dependências

## Problemas comuns

**Erro MySQL**: Rode o script `database_completo.sql`  
**Erro MongoDB**: Inicie o serviço ou comente a linha no `application.properties`  
**Porta 8080 ocupada**: Mude em `application.properties`

## Credenciais

**Admin**: admin@sistema.edu / 123456  
**Professor**: professor@sistema.edu / 123456  
**Aluno**: joao@aluno.edu / 123456

## Docs

Ver `DOCUMENTACAO_TECNICA.md` pra detalhes técnicos completos.
