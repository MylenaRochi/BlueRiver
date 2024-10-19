# Projeto de Deploy Automatizado com CI/CD

Este projeto contém um pipeline de CI/CD configurado com **GitHub Actions**, que faz o build de uma aplicação Java, a containeriza usando **Docker** e realiza o deploy em uma instância EC2 da AWS utilizando Docker Hub para armazenar as imagens.

## Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas e configuradas:

- [Docker](https://docs.docker.com/get-docker/) (instalado na sua máquina e na instância EC2)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- Chave SSH para acessar sua instância EC2 da AWS
- Conta no [Docker Hub](https://hub.docker.com/)
- [GitHub Actions](https://docs.github.com/en/actions) configurado no repositório

## Etapas de Configuração

### 1. Configurar Docker na Instância EC2

Conecte-se à sua instância EC2 via SSH e siga os passos para instalar o Docker:

```bash
ssh -i "seu-arquivo.pem" ec2-user@your-ec2-public-ip

# Atualizar pacotes
sudo yum update -y

# Instalar Docker
sudo yum install docker -y

# Iniciar o Docker
sudo service docker start

# Adicionar ec2-user ao grupo Docker
sudo usermod -a -G docker ec2-user

# (Opcional) Habilitar Docker para iniciar na inicialização
sudo chkconfig docker on
