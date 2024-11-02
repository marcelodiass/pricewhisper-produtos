# OM Corp

## Integrantes

| Nome                        | RM       |
| --------------------------- | -------- |
| André Sant'Ana Boim         | RM551575 |
| Marcelo Hespanhol Dias      | RM98251  |
| Gustavo Imparato Chaves     | RM551988 |
| Gabriel Eringer de Oliveira | RM99632  |

## Tabelas DDL
[Clique aqui para acessar o arquivo txt das tabelas DDL](docs/tabelas_ddl.txt)

## [Vídeo Demo](https://youtu.be/QVZ1t6QxIR0)

[https://youtu.be/xMzcw9Uim-g](https://youtu.be/QVZ1t6QxIR0)

## Fazendo deploy do projeto via ACR e ACI

### 1. Criando grupo de recursos

![Criando_grupo_de_recursos](https://github.com/user-attachments/assets/002fd813-591f-459d-85e9-39895ced51cb)

### 2. Criando um registro de container no grupo de recursos

![Criando_registro de container](https://github.com/user-attachments/assets/49b2436c-eaa7-42c8-ac87-37dc631e9afa)

### 3. Logando no registro de container

![Logando_no_registro de container](https://github.com/user-attachments/assets/9c55ff23-6362-48c0-aa31-1d79b063a458)

### 4. Criando o Dockerfile

![Criando_o Dockerfile](https://github.com/user-attachments/assets/4bdb4d68-5dae-4fd2-87b8-dfc9e04884f0)

### 5. Realizando build da imagem

![Realizando build da imagem](https://github.com/user-attachments/assets/d0727fe2-c8cf-463c-b675-d496cd83f82b)

### 6. Iniciando o container na porta 80

![Iniciando_container](https://github.com/user-attachments/assets/0e04a310-2a75-4e74-850f-c1fe957268b5)

### 7. API rodando na porta 80

![API rodando](https://github.com/user-attachments/assets/e8e94e2c-0e2d-4bfc-956c-6ae9acb37655)

### 8. Após testar localmente, removendo o container

![removendo_container](https://github.com/user-attachments/assets/3667e8b3-509c-41b1-bd19-ee0d583d2319)

### 9. Remarcando a imagem, criando uma nova imagem com o namespace para subir no Azure e realizando o push para o ACR

![push_acr](https://github.com/user-attachments/assets/54abe56e-6de0-4cce-b8c5-a97f755d3055)  

### 10. Após realizar o push, removendo a nova imagem do ambiente local

![removendo_nova_imagem](https://github.com/user-attachments/assets/9ca62571-e540-4525-8a14-10cc9594d067)

### 11. Removendo a imagem original

![removendo_imagem_original](https://github.com/user-attachments/assets/87f053da-93b2-46b4-a79d-2403ce439c21)

### 12. Listando as imagens registradas no Azure

![listando_imagens_azure](https://github.com/user-attachments/assets/3541794e-065e-46b3-8fdf-7b726217bf1a)

### 13. Executando a imagem do registro no Azure, com pull automático e executando o container localmente

![executando_container](https://github.com/user-attachments/assets/71ff6c98-f357-4679-908a-853275083c89)

### 14. Testando no [http://localhost:80/](http://localhost:80/)

![testando_azure_container](https://github.com/user-attachments/assets/34eb66cd-f18e-4192-a26b-52d60cf4d7ef)

### 15. Limpando o ambiente de desenvolvimento após testar o pull e rodar o container localmente

![limpando_ambiente](https://github.com/user-attachments/assets/b6831ced-2971-445e-b23f-22f8c86f3aa8)

### 16. Habilitando admin user no registro para iniciar o deploy do container

![habilitando_admin](https://github.com/user-attachments/assets/164e9c82-9ea6-4aa8-8d6e-7eb1f323e373)

### 17. Fazendo login no Azure CLI com o user e a senha de admin do container registry

![login_azure_cli](https://github.com/user-attachments/assets/a6c61893-4836-42a6-8f85-675d8d587a49)

### 18. Implantando a imagem em um Container Instance do Azure

![implantando_azure_ci](https://github.com/user-attachments/assets/edcc6dd9-371f-4fdd-8583-fc371e710dab)

### 19. Após a implantação concluir, vamos verificar e coletar os valores FQDN e o IP do container criado

![coletando_ip](https://github.com/user-attachments/assets/5b8ec707-4f8a-4c6a-812a-953284bd9e41)

### 20. Agora com o endereço coletado, vamos acessar o container (como a porta é a 80, não precisamos inserí-la no URL)

![acessando_container](https://github.com/user-attachments/assets/37b979d1-5c83-490f-9b7d-79e97151f61a)

### 21. Limpando os recursos

![limpando_recursos](https://github.com/user-attachments/assets/5a106b2f-380d-4a6f-8232-b6011f6e6c7f)
