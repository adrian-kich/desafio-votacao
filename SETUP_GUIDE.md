
# Setup Guide 



---

## 1. Estrutura do Projeto

O projeto segue princípios de arquitetura em camadas, facilitando a separação de responsabilidades e a evolução da aplicação. Cada camada do projeto tem um papel bem definido, garantindo clareza, manutenção e escalabilidade do código.

### application (Camada de Aplicação)
Esta camada contém a lógica de interface e controle da aplicação, permitindo a comunicação externa via **endpoints**. A aplicação segue uma estratégia de versionamento para permitir a evolução do sistema sem prejudicar a compatibilidade.



### domain (Camada de Domínio)
Esta camada é o centro da aplicação, nela ficam os modelos e serviços. Estes são responsáveis por implementar a lógica e as regras de negócio.


### infrastructure (Camada de Infraestrutura)
Camada onde é feita a percistência dos dados no banco de dados através dos **repositories**. Também contém arquivos de configuração da aplicação.


---

## 2. Configurações

1. **Banco de Dados**

Na raiz do projeto esta o arquivo **docker-compose.yml** que pode ser usado para rodar o banco de dados em um conteiner já configurado.

Caso não seja utilizado docker é necessário criar um database utilizando PostgreSQL:
```sql
CREATE DATABASE desafio_votacao;
```
Atualize as credenciais no arquivo `application.properties` se necessário:
```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/desafio_votacao
spring.datasource.username=<USUARIO>
spring.datasource.password=<SENHA>
```

2. O servidor está rodando por padrão na porta 8080, se necessário atualizar no arquivo `application.properties`
```yaml
server.port=8080
```

## 3. Documentação
**Acessar a Documentação Swagger**  
   Após iniciar a aplicação, a documentação estará disponível em:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```
Na raíz do projeto há uma collection do postman com as requisições configuradas.
   ```
   desafio-votacao.postman_collection.json
   ```