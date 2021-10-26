# Teste Prático ⚙️

### - Este projeto está sendo aplicado para desenvolvedor júnior

## O Projeto
>A necessidade deste projeto é criar um sistema de cadastro de sócios de um clube de prática de esportes.
### Necessidade
No clube existem várias práticas de esportes e é possível que o sócio **tenha acesso a mais de uma prática de esporte**, a saber: natação, jiujitsu, karatê, basquete e futebol.
Neste cadastro, além de **conter dados básicos** ( nome, sobrenome, data de nascimento) também deve **existir dados de contato** (email, telefone e endereço), e **ser possível cadastrar qual prática esportiva** o associado se credenciou.

Como é um clube, existe **uma contribuição mensal para prática dos esportes**. Caso não haja pagamento, o sistema deve ter a informação de **suspensão de atividade naquela prática esportiva**. Caso ele tenha mais de uma prática, este controle deve ser por esporte.


## Como rodar o projeto ?

 * Após clonar o projeto execute o comando dentro da pasta clube-esportes para gerar o jar da aplicação: 
 ```
 mvn clean install 
```

* Execute o comando para buildar e subir o container da aplicação
```
docker-compose up app-service
```

* Execute o comando para buildar e subir o container do banco de dados
```
docker-compose up app-db
```

  * Pronto a aplicação está rodando na porta 8080 para acessar o Swagger acesse a url: (http://localhost:8080/swagger-ui/index.html) 

#
## MER
![mer](/resources/mer.png)
#

## Explicando as rotas

## /socios/

* para cadastrar um socio:

    [POST] /socios/
* para atualizar um sócio:
    
    [PUT] /socios/{id}
* para listar os sócios:
    
    [GET] /socios/
* para obter um sócio pelo id:
    
    [GET] /socios/{id}
* para deletar um sócio:
    
    [DELETE] /socios/{id}

#
## /inscricoes/

* para cadastrar uma inscrição:

    [POST] /inscricoes/
* para listar as inscrições:
    
    [GET] /inscricoes/
* para obter uma inscrição pelo id:
    
    [GET] /inscricoes/{id}
* para pagar uma parcela da inscrição:
    
    [POST] /inscricoes/{id}/pagar
* para verificar se uma inscrição está atrasada e desativar ou ativar caso esteja em dia:
    
    [POST] /inscricoes/{id}/check

