# LiveStats

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-green)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

Dashboard em tempo real para monitoramento de estatísticas de logins de clientes integrado com a API IXC Soft.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Execução](#execução)
- [Docker](#docker)
- [API](#api)
- [Documentação](#documentação)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

---

## 🎯 Sobre o Projeto

**LiveStats** é uma aplicação web desenvolvida em Spring Boot que se integra com a API do IXC Soft para monitorar e exibir estatísticas de logins de clientes em tempo real. O sistema categoriza os usuários por status (online, offline, sem status) e fornece uma interface visual intuitiva para análise de conexões.

### Características Principais

- ✅ Dashboard visual com estatísticas em tempo real
- ✅ Integração completa com API IXC Soft
- ✅ Categorização automática por status de conexão
- ✅ Interface customizável (logo, cores, gradientes)
- ✅ API REST para consumo de dados
- ✅ Containerizado com Docker
- ✅ Suporte a grandes volumes de dados (buffer 30MB)

---

## ⚡ Funcionalidades

- **Monitoramento em Tempo Real**: Visualização de logins online e offline
- **Dashboard Interativo**: Cards com totais e tabelas detalhadas
- **Customização Visual**: Logo, cores e gradientes configuráveis
- **API REST**: Endpoint para integração com outros sistemas
- **Ordenação Inteligente**: Por data de conexão/desconexão
- **Limitação Automática**: 500 registros por categoria para performance
- **Correlação de Dados**: Combina informações de logins e clientes

---

## 🚀 Tecnologias

### Backend
- **Java 25**
- **Spring Boot 4.0.0**
  - Spring WebMVC
  - Spring WebClient
  - Spring Cache
  - Spring Data Redis
  - Spring DevTools
- **Lombok**
- **Jackson**
- **Maven**

### Frontend
- **Thymeleaf**
- **TailwindCSS**
- **DaisyUI**
- **JavaScript**

### DevOps
- **Docker**
- **Eclipse Temurin JDK/JRE 25**

---

## 📦 Pré-requisitos

### Para execução local:
- Java 25 ou superior
- Maven 3.8+
- Credenciais de acesso à API IXC Soft

### Para execução com Docker:
- Docker 20.10+
- Docker Compose (opcional)

---

## 🔧 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/livestats.git
cd livestats
```

### 2. Instale as dependências

```bash
./mvnw clean install
```

### 3. Configure as variáveis de ambiente

Crie um arquivo `.env` ou configure as variáveis diretamente no sistema (veja seção [Configuração](#configuração)).

---

## ⚙️ Configuração

### Variáveis de Ambiente Obrigatórias

O projeto requer as seguintes variáveis de ambiente para funcionar corretamente:

#### Configurações da API IXC Soft

| Variável | Descrição | Exemplo |
|----------|-----------|---------|
| `BASE_URL` | URL base da API IXC Soft | `https://api.ixcsoft.com.br/v1` |
| `ID_USER` | ID do usuário para autenticação | `12345` |
| `TOKEN` | Token de acesso à API | `abc123xyz456token` |

#### Customização da Interface

| Variável | Descrição | Exemplo |
|----------|-----------|---------|
| `APP_LOGO_URL` | URL da imagem do logo da empresa | `https://example.com/logo.png` |
| `APP_HEADER_GRADIENT` | Gradient CSS para o cabeçalho | `linear-gradient(to right, #667eea, #764ba2)` |
| `APP_HEADER_FONT_COLOR` | Cor da fonte do cabeçalho | `#ffffff` |

### Arquivo application.yaml

O arquivo `src/main/resources/application.yaml` usa essas variáveis:

```yaml
spring:
  application:
    name: LiveStats

ixc:
  baseUrl: ${BASE_URL}
  id-user: ${ID_USER}
  token: ${TOKEN}

app:
  logo-url: ${APP_LOGO_URL}
  header-gradient: ${APP_HEADER_GRADIENT}
  header-font-color: ${APP_HEADER_FONT_COLOR}
```

### Exemplos de Configuração

#### Linux/MacOS (.env)
```bash
export BASE_URL="https://api.ixcsoft.com.br/v1"
export ID_USER="12345"
export TOKEN="seu_token_aqui"
export APP_LOGO_URL="https://example.com/logo.png"
export APP_HEADER_GRADIENT="linear-gradient(to right, #667eea, #764ba2)"
export APP_HEADER_FONT_COLOR="#ffffff"
```

#### Windows (PowerShell)
```powershell
$env:BASE_URL="https://api.ixcsoft.com.br/v1"
$env:ID_USER="12345"
$env:TOKEN="seu_token_aqui"
$env:APP_LOGO_URL="https://example.com/logo.png"
$env:APP_HEADER_GRADIENT="linear-gradient(to right, #667eea, #764ba2)"
$env:APP_HEADER_FONT_COLOR="#ffffff"
```

### Configurações Opcionais

#### Redis (Cache - Desabilitado por padrão)

Para habilitar cache Redis, descomente no `application.yaml`:

```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
```

---

## 🏃 Execução

### Execução Local

#### Com Maven Wrapper:
```bash
./mvnw spring-boot:run
```

#### Com JAR compilado:
```bash
./mvnw clean package
java -jar target/LiveStats-0.0.1-SNAPSHOT.jar
```

### Acessando a Aplicação

Após iniciar, acesse:
- **Dashboard Web**: http://localhost:8080
- **API REST**: http://localhost:8080/api/logins

---

## 🐳 Docker

### Build da Imagem

```bash
docker build -t livestats:latest .
```

### Executar Container

#### Modo básico:
```bash
docker run -d \
  -p 8080:8080 \
  -e BASE_URL="https://api.ixcsoft.com.br/v1" \
  -e ID_USER="12345" \
  -e TOKEN="seu_token_aqui" \
  -e APP_LOGO_URL="https://example.com/logo.png" \
  -e APP_HEADER_GRADIENT="linear-gradient(to right, #667eea, #764ba2)" \
  -e APP_HEADER_FONT_COLOR="#ffffff" \
  --name livestats \
  livestats:latest
```

#### Com arquivo .env:
```bash
docker run -d \
  -p 8080:8080 \
  --env-file .env \
  --name livestats \
  livestats:latest
```

### Docker Compose (Exemplo)

Crie um arquivo `docker-compose.yml`:

```yaml
version: '3.8'

services:
  livestats:
    build: .
    container_name: livestats
    ports:
      - "8080:8080"
    environment:
      - BASE_URL=${BASE_URL}
      - ID_USER=${ID_USER}
      - TOKEN=${TOKEN}
      - APP_LOGO_URL=${APP_LOGO_URL}
      - APP_HEADER_GRADIENT=${APP_HEADER_GRADIENT}
      - APP_HEADER_FONT_COLOR=${APP_HEADER_FONT_COLOR}
    restart: unless-stopped
```

Execute:
```bash
docker-compose up -d
```

### Verificar Logs

```bash
docker logs -f livestats
```

---

## 📡 API

### Endpoint Principal

#### **GET** `/api/logins`

Retorna todas as estatísticas do dashboard.

**Response (200 OK):**
```json
{
  "online": {
    "total": 152,
    "logins": [
      {
        "online": "S",
        "login": "user123",
        "bairro": "Centro",
        "id_cliente": "12345",
        "ultima_conexao_inicial": "2025-12-22 10:30:00",
        "ultima_conexao_final": null
      }
    ]
  },
  "offline": {
    "total": 298,
    "logins": [...]
  },
  "semStatus": {
    "total": 3,
    "logins": [...]
  }
}
```

**Exemplo de uso:**
```bash
curl http://localhost:8080/api/logins
```

---

## 📚 Documentação

Para documentação técnica detalhada, consulte:

- **[DOCUMENTATION.md](DOCUMENTATION.md)** - Documentação completa da arquitetura, fluxos e componentes
- **Javadoc** - Gerar com `./mvnw javadoc:javadoc`

---

## 📁 Estrutura do Projeto

```
LiveStats/
├── src/
│   ├── main/
│   │   ├── java/com/LiveStats/LiveStats/
│   │   │   ├── LiveStatsApplication.java       # Classe principal
│   │   │   ├── controlles/                     # Controllers REST e MVC
│   │   │   ├── service/                        # Lógica de negócio
│   │   │   ├── modulos/ixc/                    # Integração IXC Soft
│   │   │   ├── dto/                            # Data Transfer Objects
│   │   │   └── utils/                          # Utilitários
│   │   └── resources/
│   │       ├── application.yaml                # Configurações
│   │       ├── templates/                      # Templates Thymeleaf
│   │       └── static/                         # CSS/JS
│   └── test/                                   # Testes
├── Dockerfile                                  # Build Docker
├── pom.xml                                     # Configuração Maven
├── README.md                                   # Este arquivo
└── DOCUMENTATION.md                            # Documentação técnica
```

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

### Padrões de Código

- Siga as convenções Java e Spring Boot
- Adicione testes para novas funcionalidades
- Documente mudanças significativas
- Mantenha a arquitetura em camadas

---

## 🐛 Troubleshooting

### Problema: Erro 401 ao conectar com IXC Soft
**Solução**: Verifique se `BASE_URL`, `ID_USER` e `TOKEN` estão corretos.

### Problema: OutOfMemoryError
**Solução**: Aumente o heap da JVM: `JAVA_OPTS="-Xms512m -Xmx1024m"`

### Problema: Página não carrega dados
**Solução**: Verifique o console do navegador e logs do servidor para erros JavaScript ou CORS.

---

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

## 📞 Contato

Para dúvidas, sugestões ou suporte:

- Abra uma issue no GitHub
- Entre em contato com a equipe de desenvolvimento

---

## 🎉 Agradecimentos

- Equipe Spring Boot
- Comunidade IXC Soft
- Contribuidores do projeto

---

**Desenvolvido com ☕ e Spring Boot**
