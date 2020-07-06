# Desafio

## Configurações

> Sei que falaram que não havia necessidade de gravar em banco de dados, mas optei por fazer a API gravando no banco H2.
>Pos isso basta configurar o caminho do arquivo DB dentro do arquivo `application.properties`.

## Segurança

* Utilize um login simples esperando um Authentication Bearer para controle de acesso. Por isso é necessário fazer um login e sempre passar o bearer token em cada requisição.
    
> Esse forma é pouco segura pois o token não é invalidado, precisaria uma forma mais robusta para uma API real. 
  
## End Points

### Registes e authenticate

  * _POST_ /register -> Usado para registrar um novo usuário
    * Espera receber um json como Abaixo.
```
{
  "email":"devel.neto@gmail.com",
  "password":"123456",
  "nome":"João Fernandes Lima Neto"
}
```

  * _POST_ /authenticate -> Usado para receber o token de authenticação. 
    * Envia o usuario e senha
```
{
    "email":"devel.neto@gmail.com",
    "password": 123456
}
```

### Candidatos e Cartoes

  * _POST_ /api/v1/candidatos -> Efetua a gravação de um candidato. 
    - Não fiz tratamento para o recebimento de cartõe nesse ponto. Mas poderia ser feito para ao ineserir o candidato já efetuar a gravação dos cartões dele.
```
Json esperado:
{
   "nome": "João Fernandes",
   "email": "devel.neto@gmail.com",
   "telefone": "(48) 991541850",
   "cpf": "00898823936"
}
``` 
  * _GET_ /api/v1/candidatos -> Recupera uma lista de candidados com os seus cartões.
  
  * _GET_ /api/v1/candidatos/{id} -> Recupera o candidato com o ID passado.
  
  * _DELETE_ /api/v1/candidatos/{id} -> Remove o candidato com o ID passado. (Pública)
 
  * _PUT_ /api/v1/candidatos/{id} -> Atualiza o candidato passado com o ID.
    * Espera receber os dados do candidato via Body.
    
```
{
    "nome": "João Fernandes",
    "email": "devel.neto@gmail.com",
    "telefone": "(48) 991541850",
    "cpf": "00898823935"
}
```

  * _POST_ /api/v1/candidatos/add/cartao/{id} -> recebe o id do candidato e o corpo contendo os dados do cartão, para inserir e vincular ao candidato.
     
  
  * _DELETE_ /api/v1/candidatos/{id}/remove/cartao/{id_cartao} -> Recebe o ID do candidato, como também o ID do cartão a ser removido. 
    * Confesso não ter gostado desta forma, mas devido ao tempo curto, e precisar terminar esta API rapido optei pelo mais facil ao mais "bonito".
    
    
    
## Notas:

  Na raiz do projeto segue as configurações do Postman usadas para teste.