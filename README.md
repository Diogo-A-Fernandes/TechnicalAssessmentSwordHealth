# TechnicalAssessmentSwordHealth

TechnicalAssessmentSwordHealth é uma aplicação Android que permite explorar diferentes raças de gatos, visualizar detalhes e marcar favoritos.

## Funcionalidades

- **Lista de Raças:** Mostra todas as raças de gatos obtidas da API.
- **Detalhes da Raça:** Exibe informação detalhada, incluindo imagem, origem, temperamento e descrição.
- **Favoritos:** Permite adicionar ou remover raças favoritas.

## Tecnologias e escolhas

- **Jetpack Compose:** Para construir uma UI moderna e reativa.
- **ViewModel + StateFlow:** Para gerir estado de forma reativa e desacoplada da UI.
- **Coil:** Para carregamento eficiente de imagens.
- **API TheCatAPI:** Fonte de dados para raças e imagens.
- **LazyVerticalGrid:** Para exibir favoritos de forma organizada em grelha.

## Decisões de design

- **Separação de responsabilidades:** Cada ecrã possui o seu ViewModel ou usa ViewModels específicos (ex.: favoritos vs. home).
- **IconToggleButton para favoritos:** Mantém a estrela no topo da carta da raça, separando interação de UI da imagem.
- **Tratamento de estados:** Loading, erro e dados ausentes são tratados de forma visual consistente.
  
