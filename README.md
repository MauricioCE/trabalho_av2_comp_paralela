# Guia de Configuração Rápida de Ambiente de Desenvolvimento

Este guia detalha a configuração essencial para ambientes **Python** e **Java** no VS Code, incluindo as versões mais recentes dos softwares e dependências necessárias.

## Python

### 1. Requisitos de Software

| Software | Versão Recomendada | Notas |
| :--- | :--- | :--- |
| **Python** | **3.13 (Mais Recente)** | Verifique em [python.org](https://www.python.org/) a versão mais atualizada estável. |
| **VS Code** | Mais Recente | Editor de código recomendado. |

### 2. Extensões Essenciais do VS Code

Instale as seguintes extensões para suporte completo ao desenvolvimento e notebooks Jupyter:

* **Python** (Microsoft)
* **Jupyter** (Microsoft)
* **Pylance** (Microsoft)

### 3. Bibliotecas Python (Dependencies)

As bibliotecas a seguir são as dependências principais para análise de dados:

* `pandas`
* `numpy`
* `matplotlib`
* `seaborn`
* `ipykernel` (Para uso de Jupyter Notebooks)

### 4. Comando de Instalação

Para instalar todas as dependências de uma vez (idealmente usando um arquivo `requirements.txt`):

```bash
# Certifique-se de que o ambiente virtual está ativo
pip install -r reqs.txt
```

## Java

Basta a versão mais recendo do Java