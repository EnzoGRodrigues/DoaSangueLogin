# Plano de Testes - Microsserviço Login

## 1. Objetivo
O objetivo do plano de testes é fornecer um guia estruturado e detalhado para testar a classe AutenticacaoController. Ele visa garantir que os métodos de validação de CPF e CNPJ (isCPF() e isCNPJ()) estejam implementados corretamente, além de testar a integração da classe com serviços externos, como o serviço de autenticação (AuthService), o repositório de usuários (UsuarioRepository), e o serviço de geração de token JWT (GeraTokenJWT).

## 2. Localização
Os testes estão localizados neste caminho: src/test/java/org/acme/controller E src/test/java/org/acme/service

## Teste 1 - Testando método de validação do CPF

### Objetivo: 
Verificar se o método isCPF da classe AutenticacaoController valida corretamente CPFs.

### Entradas:
CPF válido: "12345678910"

CPF inválido: "123456789"

### Passos:
1. Instanciar um objeto da classe AutenticacaoController. 
2. Chamar o método isCPF com o CPF válido.
3. Verificar se o retorno é verdadeiro (true).
4. Chamar o método isCPF com o CPF inválido.
5. Verificar se o retorno é falso (false).

### Saídas Esperadas:

Para CPF válido: O método deve retornar true.

Para CPF inválido: O método deve retornar false.

## Teste 2 - Testando método de validação do CNPJ

### Objetivo: 
Verificar se o método isCNPJ da classe AutenticacaoController valida corretamente CNPJs.

### Entradas:

CNPJ válido: "12345678910111"

CNPJ inválido: "1234567891"

### Passos:

1. Instanciar um objeto da classe AutenticacaoController.
2. Chamar o método isCNPJ com o CNPJ válido.
3. Verificar se o retorno é verdadeiro (true).
4. Chamar o método isCNPJ com o CNPJ inválido.
5. Verificar se o retorno é falso (false).

### Saídas Esperadas:

Para CNPJ válido: O método deve retornar true.

Para CNPJ inválido: O método deve retornar false.

## Teste 3 - Testando o login retornando 400 quando não for fornecido o documento e a senha

### Objetivo: 
Garantir que o endpoint de login retorna o status HTTP 400 quando o documento e a senha não são fornecidos.

### Entradas:

JSON vazio: "{}"

### Passos:

1. Enviar uma requisição POST para "/auth/login" com um JSON vazio.

2. Configurar o tipo de conteúdo como "application/json".

3. Verificar se o código de status retornado é 400.

### Saída Esperada:

O código de status HTTP deve ser 400.

## Teste 4 - Testando o login retornando 400 quando o documento for inválido

### Objetivo:
Verificar se o endpoint de login retorna o status HTTP 400 quando um documento inválido é fornecido.

### Entradas:

JSON com documento inválido: "{ "documento": "123456789012345", "senha": "senha123" }"

### Passos:

1. Enviar uma requisição POST para "/auth/login" com um JSON contendo um documento inválido.

2. Configurar o tipo de conteúdo como "application/json".

3. Verificar se o código de status retornado é 400.

### Saída Esperada:

O código de status HTTP deve ser 400.

## Teste 5 - Testando se autentica por CPF com sucesso

### Objetivo:
Verificar se o serviço de autenticação (AuthService) autentica corretamente um usuário por CPF.

### Entradas:

CPF válido: "12345678901"

Senha criptografada com Bcrypt: BcryptUtil.bcryptHash("enzogabrielrodriguesdasilva")

### Passos:

1. Criar uma instância de CadastroPF com o CPF e senha especificados.
2. Configurar o comportamento do usuarioRepository para retornar o usuário ao chamar findByCpf com o CPF fornecido.
3. Chamar authService.autenticaPorCpf(cpf, senha).
4. Verificar se o método findByCpf do usuarioRepository foi chamado.
5. Verificar se a senha do usuário é igual à senha fornecida.

### Saída Esperada:

O método autenticaPorCpf deve retornar um token de autenticação.

A senha do usuário deve ser igual à senha fornecida.

## Teste 6 - Testando se autentica por CNPJ com sucesso

### Objetivo:
Verificar se o serviço de autenticação (AuthService) autentica corretamente uma instituição por CNPJ.

### Entradas:

CNPJ válido: "12345678910111"

Senha criptografada com Bcrypt: BcryptUtil.bcryptHash("clinicas")

### Passos:

1. Criar uma instância de CadastroPJ com o CNPJ e senha especificados.
2. Configurar o comportamento do instituicaoRepository para retornar a instituição ao chamar findByCnpj com o CNPJ fornecido.
3. Chamar authService.autenticaPorCnpj(cnpj, senha).
4. Verificar se o método findByCnpj do instituicaoRepository foi chamado.
5. Verificar se a senha da instituição é igual à senha fornecida.

### Saída Esperada:

O método autenticaPorCnpj deve retornar um token de autenticação.

A senha da instituição deve ser igual à senha fornecida.

## Teste 7 - Testando falha de autenticação por CPF não encontrado

### Objetivo:
Verificar se o serviço de autenticação (AuthService) retorna null ao tentar autenticar um CPF não registrado.

### Entradas:

CPF válido: "12345678901"

Senha criptografada com Bcrypt: BcryptUtil.bcryptHash("enzogabrielrodriguesdasilva")

### Passos:

1. Criar uma instância de CadastroPF com o CPF e senha especificados.
2. Configurar o comportamento do usuarioRepository para retornar null ao chamar findByCpf com o CPF fornecido.
3. Chamar authService.autenticaPorCpf(cpf, senha).
4. Verificar se o método findByCpf do usuarioRepository foi chamado.
5. Verificar se o método autenticaPorCpf retorna null.

### Saída Esperada:

O método autenticaPorCpf deve retornar null.

## Teste 8 - Testando falha de autenticação por CNPJ não encontrado

### Objetivo:
Verificar se o serviço de autenticação (AuthService) retorna null ao tentar autenticar um CNPJ não registrado.

### Entradas:

CNPJ válido: "12345678910111"

Senha criptografada com Bcrypt: BcryptUtil.bcryptHash("clinicas")

### Passos:

1. Criar uma instância de CadastroPJ com o CNPJ e senha especificados.
2. Configurar o comportamento do instituicaoRepository para retornar null ao chamar findByCnpj com o CNPJ fornecido.
3. Chamar authService.autenticaPorCnpj(cnpj, senha).
4. Verificar se o método findByCnpj do instituicaoRepository foi chamado.
5. Verificar se o método autenticaPorCnpj retorna null.

### Saída Esperada:

O método autenticaPorCnpj deve retornar null.

