# 📌 Sistema de Agendamento a Saúde

<p> Somos a SAS (Sistema de Agendamento de Saúde), uma startup de tecnologia dedicada a transformar a experiência na área da saúde por meio de soluções digitais inovadoras. Nosso propósito é simplificar e agilizar o agendamento e o atendimento médico, proporcionando praticidade para os pacientes e eficiência para os profissionais da saúde.

<b>Praticidade para Pacientes:</b> Agendamento rápido, acesso a prontuários digitais e informações centralizadas.

<b>Eficiência para Profissionais:</b> Gestão simplificada de consultas, integração de históricos médicos e otimização de
processos.

<b>Inovação Contínua:</b> Soluções personalizadas e atualizadas para atender às necessidades do setor de saúde.

Na SAS, acreditamos que a tecnologia pode ser uma grande aliada para melhorar a saúde e o bem-estar da sociedade.

# 🚀 Tecnologias Utilizadas

- Frontend: HTML,CSS, Javascript e React.js
- Backend: Java (Spring Boot)
- Banco de Dados: MySQL
- Notificações: SMS/WhatsApp
- Docker

# 📑 Modelo Entidade de Relacionamento (EER)

```mermaid
erDiagram
    %% Entidades
    ENDERECO {
        VARCHAR(36) id_endereco PK
        VARCHAR(50) rua
        VARCHAR(100) complemento
        VARCHAR(10) numero
        VARCHAR(50) bairro
        VARCHAR(50) cidade
        CHAR(2) uf
        CHAR(9) cep
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    PACIENTE {
        VARCHAR(36) id_paciente PK
        VARCHAR(50) nome
        CHAR(11) cpf
        VARCHAR(100) email
        VARCHAR(250) senha
        DATE data_nascimento
        ENUM genero
        VARCHAR(15) telefone
        VARCHAR(50) grau_instrucao
        TINYINT(1) notificacoes_ativadas
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    UNIDADE_SAUDE {
        VARCHAR(36) id_unidade_de_saude PK
        VARCHAR(50) nome
        ENUM tipo
        CHAR(14) cnpj
        TEXT descricao
        VARCHAR(250) email_unidade
        VARCHAR(255) senha_unidade
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    PROFISSIONAL {
        VARCHAR(36) id_profissional_de_saude PK
        VARCHAR(50) nome
        VARCHAR(15) telefone
        VARCHAR(50) especialidade
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    DISPONIBILIDADE {
        VARCHAR(36) id_disponibilidade PK
        ENUM dia_semana
        TIME horario_inicio
        TIME horario_fim
        DATE data
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    AGENDAMENTO {
        VARCHAR(36) id_agendamento PK
        DATETIME data_hora
        ENUM status
        TEXT observacoes
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    CREDENCIAIS {
        VARCHAR(36) id_credencial PK
        VARCHAR(100) email
        VARCHAR(255) senha
        ENUM tipo_profissional
        VARCHAR(20) numero_registro
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    EXAME {
        VARCHAR(36) id_exame PK
        TEXT descricao
        VARCHAR(50) tipo_exame
        ENUM status
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    HISTORICO {
        VARCHAR(36) id_log PK
        VARCHAR(50) tabela_afetada
        VARCHAR(36) id_registro
        ENUM acao
        TIMESTAMP data_hora
        VARCHAR(100) usuario
        TEXT detalhes
    }

    LOG_ACESSO {
        VARCHAR(36) id_log PK
        TIMESTAMP data_hora
        ENUM acao
    }

    MEDICAMENTO {
        VARCHAR(36) id_medicamento PK
        VARCHAR(100) nome
        VARCHAR(50) dosagem
        VARCHAR(50) frequencia
        LONGBLOB receita
        TIMESTAMP criado_em
        TIMESTAMP atualizado_em
    }

    NOTIFICACAO {
        VARCHAR(36) id_notificacao PK
        ENUM tipo
        TEXT mensagem
        DATETIME data_envio
        ENUM status
    }

    PRONTUARIO {
        VARCHAR(36) id_prontuario PK
        TEXT descricao
        TEXT alergia
        ENUM tipo_sanguineo
        TEXT doenca_cronica
        TEXT historico_familiar
        TIMESTAMP ultima_atualizacao
    }

    RESULTADO_EXAME {
        VARCHAR(36) id_resultado PK
        LONGBLOB resultado
        TEXT interpretacao
        TIMESTAMP data_resultado
    }

    %% Relacionamentos em Português
    ENDERECO ||--o{ PACIENTE : "possui"
    ENDERECO ||--o{ UNIDADE_SAUDE : "localiza"
    UNIDADE_SAUDE ||--o{ PROFISSIONAL : "contrata"
    PROFISSIONAL ||--o{ DISPONIBILIDADE : "tem"
    UNIDADE_SAUDE ||--o{ DISPONIBILIDADE : "oferece"
    PACIENTE ||--o{ AGENDAMENTO : "realiza"
    PROFISSIONAL ||--o{ AGENDAMENTO : "atende"
    UNIDADE_SAUDE ||--o{ AGENDAMENTO : "disponibiliza"
    DISPONIBILIDADE ||--o{ AGENDAMENTO : "gera"
    PROFISSIONAL ||--|| CREDENCIAIS : "acessa_com"
    UNIDADE_SAUDE ||--o{ CREDENCIAIS : "autoriza"
    PACIENTE ||--o{ EXAME : "solicita"
    PROFISSIONAL ||--o{ EXAME : "prescreve"
    AGENDAMENTO ||--o{ EXAME : "relaciona"
    PACIENTE ||--o{ HISTORICO : "registra_alteracoes"
    UNIDADE_SAUDE ||--o{ HISTORICO : "audita"
    PROFISSIONAL ||--o{ HISTORICO : "modifica"
    AGENDAMENTO ||--o{ HISTORICO : "registra"
    EXAME ||--o{ HISTORICO : "controla"
    PROFISSIONAL ||--o{ LOG_ACESSO : "efetua"
    PACIENTE ||--o{ LOG_ACESSO : "tem_acessado"
    PACIENTE ||--o{ MEDICAMENTO : "utiliza"
    PROFISSIONAL ||--o{ MEDICAMENTO : "receita"
    AGENDAMENTO ||--o{ MEDICAMENTO : "origina"
    EXAME ||--o{ MEDICAMENTO : "indica"
    PACIENTE ||--o{ NOTIFICACAO : "recebe"
    PACIENTE ||--|| PRONTUARIO : "contém"
    PROFISSIONAL ||--o{ PRONTUARIO : "atualiza"
    EXAME ||--|| RESULTADO_EXAME : "produz"
```

# 📦 Instalação e Configuração

🔧 Pré-requisitos
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

- Node.js
- Java JDK 21
- MySQL
- Docker
- React.js

# 🎯 Passos para rodar o projeto🔹Backend (Java)

# 🔗 Clone o repositório

```git
git clone https://github.com/SAS-Organizacao/SAS_BackEnd
```

# 🛠️ Endpoints da API

<p>Em construção...</p>

# 📌 Autenticação

<p>Em construção...</p>

# 📌 Agendamentos

<p>Em construção...</p>

# 📌 Prontuário

<p>Em construção...</p>

# 📌 Contato

📧Email: sas@gmail.com

🌐Site: www.sas.com.br

# Integrantes

</tr>
  <tr align=center>
    <td>
      <a href="https://github.com/DGuabiraba">
        <img src="https://avatars.githubusercontent.com/u/81264511?v=4" height="200px" width="200px">
      </a>
    </td>
    <td>
      <a href="https://github.com/Gabrielteles001">
        <img src="https://avatars.githubusercontent.com/u/127240150?v=4" height="200px" width="200px">
      </a>
    </td>
    <td>
      <a href="https://github.com/WalterSantos08">
        <img src="https://avatars.githubusercontent.com/u/178443270?v=4" height="200px" width="200px">
      </a>
    </td>
    <td>
      <a href="https://github.com/LeonardoIrineu">
        <img src="https://avatars.githubusercontent.com/u/112736650?v=4" height="200px" width="200px">
      </a>
    </td>
    <td>
      <a href="https://github.com/dorotrodrigues">
        <img src="https://avatars.githubusercontent.com/u/111395320?v=4" height="200px" width="200px">
      </a>
    </td>
    <td>
      <a href="https://github.com/alvesrafaelaa">
        <img src="https://avatars.githubusercontent.com/u/192259118?v=4" height="200px" width="200px">
      </a>
    </td>
    <td>
      <a href="https://github.com/CeloDigital">
        <img src="https://avatars.githubusercontent.com/u/147448840?v=4" height="200px" width="200px">
      </a>
    </td>
     <td>
      <a href="https://github.com/mattheus536">
        <img src="https://avatars.githubusercontent.com/u/171884376?v=4" height="200px" width="200px">
      </a>
    </td>
    
    
